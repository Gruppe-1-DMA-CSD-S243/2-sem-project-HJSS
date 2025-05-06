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

public class TimeRegistrationDB implements TimeRegistrationDBIF {
	private static final String INSERT_TIME_REGISTRATION_QUERY = "INSERT INTO TimeRegistration (time_registration_number, time_registration_date, "
			+ "start_time, end_time, hours, registration_type, description, is_validated, time_sheet_id, project_id, employee_id)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, "
			+ "(SELECT project_id FROM Project WHERE project_number = ?), "
			+ "(SELECT employee_id FROM Employee WHERE employee_number = ?));";
	private static final String FIND_ACTIVE_TIME_REGISTRATION_QUERY = "SELECT * FROM TimeRegistration JOIN Employee ON TimeRegistration.employee_id = Employee.employee_id "
			+ "JOIN Project ON TimeRegistration.project_id = Project.project_id "
			+ "WHERE employee_number = ? AND end_time IS null;";
	private static final String UPDATE_TIME_REGISTRATION_QUERY = "UPDATE TimeRegistration SET time_registration_date = ?,"
			+ "start_time = ?, end_time = ?, hours = ?, registration_type = ?, description = ?, is_validated = ?, time_sheet_id = ?, "
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
			insertTimeRegistrationPS.setString(1, timeRegistration.getTimeRegistrationNumber());
			insertTimeRegistrationPS.setDate(2, Date.valueOf(timeRegistration.getDate()));
			insertTimeRegistrationPS.setObject(3, timeRegistration.getStartTime());
			insertTimeRegistrationPS.setObject(4, timeRegistration.getEndTime());
			
			
			insertTimeRegistrationPS.setDouble(5, timeRegistration.getHours());
			insertTimeRegistrationPS.setString(6, timeRegistration.getRegistrationType());
			insertTimeRegistrationPS.setString(7, timeRegistration.getDescription());
			insertTimeRegistrationPS.setBoolean(8, timeRegistration.isValidated());
			
			//Set TimeSheetID, ProjectID, EmployeeID for TimeRegistration
			insertTimeRegistrationPS.setInt(9, 2); //TimeSheetID
			insertTimeRegistrationPS.setString(10, timeRegistration.getProject().getProjectNumber()); //ProjectID
			insertTimeRegistrationPS.setString(11, timeRegistration.getEmployee().getEmployeeNumber()); //EmployeeID
			
			insertTimeRegistrationPS.executeUpdate();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
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
			updateTimeRegistrationPS.setDate(1, Date.valueOf(timeRegistration.getDate()));
			updateTimeRegistrationPS.setObject(2, timeRegistration.getStartTime());
			updateTimeRegistrationPS.setObject(3, timeRegistration.getEndTime());
			updateTimeRegistrationPS.setDouble(4, timeRegistration.getHours());
			updateTimeRegistrationPS.setString(5, timeRegistration.getRegistrationType());
			updateTimeRegistrationPS.setString(6, timeRegistration.getDescription());
			updateTimeRegistrationPS.setBoolean(7, timeRegistration.isValidated());
			
			//Her sættes time_sheet_id. Vi kan ikke hente det endnu, så de sættes bare til 2.
			updateTimeRegistrationPS.setInt(8, 2); //TimeSheetID
			updateTimeRegistrationPS.setString(9, timeRegistration.getProject().getProjectNumber()); //ProjectID
			updateTimeRegistrationPS.setString(10, timeRegistration.getEmployee().getEmployeeNumber()); //EmployeeID
			
			//WHERE time_registration_number = ?
			updateTimeRegistrationPS.setString(11, timeRegistration.getTimeRegistrationNumber());
			
			updateTimeRegistrationPS.executeUpdate();
			
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	private TimeRegistration buildObject(ResultSet resultSet) {
		TimeRegistration currentTimeRegistration = null;
		
		// TODO:
		// Employee skal findes og bygges! Men hvordan?
		
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
				boolean isValidated = resultSet.getBoolean("is_validated");
				
				Project project = null;
				if (pdb.findProject(resultSet.getString("project_number"), resultSet.getString("employee_number")) != null) {
					project = pdb.findProject(resultSet.getString("project_number"), resultSet.getString("employee_number"));
				}
				
				Employee employee = null;
				if (edb.findEmployee(resultSet.getString("employee_number")) != null) {
					employee = edb.findEmployee(resultSet.getString("employee_number"));
				}
				
				currentTimeRegistration = new TimeRegistration(timeRegistrationNumber, date, startTime, endTime, 
						hours, registrationType, description, isValidated, project, employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return currentTimeRegistration;
	}
	
}
