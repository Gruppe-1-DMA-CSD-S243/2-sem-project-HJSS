package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import model.Employee;
import model.Project;
import model.TimeRegistration;
import model.TimeSheet;

public class TimeRegistrationDB implements TimeRegistrationDBIF {
	private static final String INSERT_TIME_REGISTRATION_QUERY = "INSERT INTO TimeRegistration (time_registration_number, time_registration_date, "
			+ "start_time, end_time, hours, registration_type, description, time_sheet_id, project_id, employee_id)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, "
			+ "(SELECT time_sheet_id FROM TimeSheet WHERE time_sheet_number = ?), "
			+ "(SELECT project_id FROM Project WHERE project_number = ?), "
			+ "(SELECT employee_id FROM Employee WHERE employee_number = ?));";
	private static final String FIND_ACTIVE_TIME_REGISTRATION_QUERY = "SELECT * FROM TimeRegistration JOIN Employee ON TimeRegistration.employee_id = Employee.employee_id "
			+ "JOIN Project ON TimeRegistration.project_id = Project.project_id "
			+ "WHERE employee_number = ? AND end_time IS null;";
	private static final String UPDATE_TIME_REGISTRATION_QUERY = "UPDATE TimeRegistration SET time_registration_date = ?,"
			+ "start_time = ?, end_time = ?, hours = ?, registration_type = ?, description = ?, "
			+ "time_sheet_id = (SELECT time_sheet_id FROM TimeSheet WHERE time_sheet_number = ?), "
			+ "project_id = (SELECT project_id FROM Project WHERE project_number = ?),"
			+ "employee_id = (SELECT employee_id FROM Employee WHERE employee_number = ?)"
			+ " WHERE time_registration_number = ?;";
	private PreparedStatement insertTimeRegistrationPS;
	private PreparedStatement findActiveTimeRegistrationPS;
	private PreparedStatement updateTimeRegistrationPS;
	
	public TimeRegistrationDB() {
		init();
	}
	
	private void init() {
		Connection con = DBConnection.getInstance().getDBcon();
		try {
			insertTimeRegistrationPS = con.prepareStatement(INSERT_TIME_REGISTRATION_QUERY);
			findActiveTimeRegistrationPS = con.prepareStatement(FIND_ACTIVE_TIME_REGISTRATION_QUERY);
			updateTimeRegistrationPS = con.prepareStatement(UPDATE_TIME_REGISTRATION_QUERY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean insertTimeRegistration(TimeRegistration timeRegistration) {
		boolean success = false;
		try {
			DBConnection.getInstance().startTransaction();
			insertTimeRegistrationPS.setString(1, timeRegistration.getTimeRegistrationNumber());
			insertTimeRegistrationPS.setDate(2, Date.valueOf(timeRegistration.getDate()));
			insertTimeRegistrationPS.setObject(3, timeRegistration.getStartTime());
			insertTimeRegistrationPS.setObject(4, timeRegistration.getEndTime());
			
			
			insertTimeRegistrationPS.setDouble(5, timeRegistration.getHours());
			insertTimeRegistrationPS.setString(6, timeRegistration.getRegistrationType());
			insertTimeRegistrationPS.setString(7, timeRegistration.getDescription());
			
			//Set TimeSheetID, ProjectID, EmployeeID for TimeRegistration
			insertTimeRegistrationPS.setString(8, timeRegistration.getTimeSheet().getTimeSheetNumber()); //TimeSheetID
			insertTimeRegistrationPS.setString(9, timeRegistration.getProject().getProjectNumber()); //ProjectID
			insertTimeRegistrationPS.setString(10, timeRegistration.getEmployee().getEmployeeNumber()); //EmployeeID
			
			insertTimeRegistrationPS.executeUpdate();
			
			DBConnection.getInstance().commitTransaction();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().rollbackTransaction();
		}
		return success;
	}

	@Override
	public TimeRegistration findActiveTimeRegistration(Employee employee) {
		TimeRegistration currentTimeRegistration = null;
		try {
			findActiveTimeRegistrationPS.setString(1, employee.getEmployeeNumber());
			ResultSet resultSet = findActiveTimeRegistrationPS.executeQuery();
			
			currentTimeRegistration = buildObject(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return currentTimeRegistration;
	}

	@Override
	public boolean updateTimeRegistration(TimeRegistration timeRegistration) {
		boolean success = false;
		try {
			DBConnection.getInstance().startTransaction();
			updateTimeRegistrationPS.setDate(1, Date.valueOf(timeRegistration.getDate()));
			updateTimeRegistrationPS.setObject(2, timeRegistration.getStartTime());
			updateTimeRegistrationPS.setObject(3, timeRegistration.getEndTime());
			updateTimeRegistrationPS.setDouble(4, timeRegistration.getHours());
			updateTimeRegistrationPS.setString(5, timeRegistration.getRegistrationType());
			updateTimeRegistrationPS.setString(6, timeRegistration.getDescription());
			
			updateTimeRegistrationPS.setString(7, timeRegistration.getTimeSheet().getTimeSheetNumber()); //TimeSheetID
			updateTimeRegistrationPS.setString(8, timeRegistration.getProject().getProjectNumber()); //ProjectID
			updateTimeRegistrationPS.setString(9, timeRegistration.getEmployee().getEmployeeNumber()); //EmployeeID
			
			//WHERE time_registration_number = ?
			updateTimeRegistrationPS.setString(10, timeRegistration.getTimeRegistrationNumber());
			
			updateTimeRegistrationPS.executeUpdate();
			
			DBConnection.getInstance().commitTransaction();
			
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().rollbackTransaction();
		}
		
		return success;
	}
	
	private TimeRegistration buildObject(ResultSet resultSet) {
		TimeRegistration currentTimeRegistration = null;
		
		// TODO:
		// Employee skal findes og bygges! Men hvordan?
		
		TimeSheetDBIF tsdb = new TimeSheetDB();
		ProjectDBIF pdb = new ProjectDB();
		EmployeeDBIF edb = new EmployeeDB();
		
		try {
			if (resultSet.next()) {
				String timeRegistrationNumber = "" + resultSet.getString("time_registration_number");
				LocalDate date = resultSet.getDate("time_registration_date").toLocalDate();
				LocalDateTime startTime = resultSet.getTimestamp("start_time").toLocalDateTime();
				LocalDateTime endTime = null;
				if (resultSet.getTimestamp("end_time") != null) {
					endTime = resultSet.getTimestamp("end_time").toLocalDateTime();
				}
				double hours = resultSet.getDouble("hours");
				String registrationType = resultSet.getString("registration_type");
				String description = resultSet.getString("description");
				
				Project project = null;
				if (pdb.findProject(resultSet.getString("project_number"), resultSet.getString("employee_number")) != null) {
					project = pdb.findProject(resultSet.getString("project_number"), resultSet.getString("employee_number"));
				}
				
				Employee employee = null;
				if (edb.findEmployee(resultSet.getString("employee_number")) != null) {
					employee = edb.findEmployee(resultSet.getString("employee_number"));
				}
				
				TimeSheet timeSheet = null;
				if (tsdb.findTimeSheetByEmployeeAndDate(employee, date) != null) {
					timeSheet = tsdb.findTimeSheetByEmployeeAndDate(employee, date);
				}
				
				currentTimeRegistration = new TimeRegistration(timeRegistrationNumber, date, startTime, endTime, 
						hours, registrationType, description, timeSheet, project, employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return currentTimeRegistration;
	}
	
}
