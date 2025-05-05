package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import model.Employee;
import model.TimeRegistration;

public class TimeRegistrationDB implements TimeRegistrationDBIF {
	private static final String INSERT_TIME_REGISTRATION_QUERY = "INSERT INTO TimeRegistration (time_registration_number, time_registration_date, "
			+ "start_time, end_time, hours, registration_type, description, is_validated, time_sheet_id, project_id, employee_id);"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String FIND_ACTIVE_TIME_REGISTRATION_QUERY = "SELECT * FROM TimeRegistration WHERE employee_number = ? AND end_time = null;";
	private static final String UPDATE_TIME_REGISTRATION_QUERY = "UPDATE TimeRegistration SET (time_registration_number = ?, time_registration_date = ?,"
			+ "start_time = ?, end_time = ?, hours = ?, registration_type = ?, description = ?, is_validated = ?, time_sheet_id = ?, project_id = ?,"
			+ "employee_id = ?) WHERE time_registration_number = ?;";
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
		// TODO Auto-generated method stub
		return false;
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
	public boolean updateTimeRegistration(TimeRegistration newTimeRegistration) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private TimeRegistration buildObject(ResultSet resultSet) {
		TimeRegistration currentTimeRegistration = null;
		Employee employee = null;
		
		// TODO:
		// Employee skal findes og bygges! Men hvordan?
		
		try {
			if (resultSet.next()) {
				String timeRegistrationNumber = "" + resultSet.getString("time_registration_number");
				LocalDate date = resultSet.getDate("time_registration_date").toLocalDate();
				LocalDateTime startTime = resultSet.getTimestamp("start_time").toLocalDateTime();
				LocalDateTime endTime = resultSet.getTimestamp("end_time").toLocalDateTime();
				double hours = resultSet.getDouble("hours");
				String registrationType = resultSet.getString("registration_type");
				String description = resultSet.getString("description");
				boolean isValidated = resultSet.getBoolean("is_validated");
				
				currentTimeRegistration = new TimeRegistration(timeRegistrationNumber, date, startTime, endTime, 
						hours, registrationType, description, isValidated);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return currentTimeRegistration;
	}
	
}
