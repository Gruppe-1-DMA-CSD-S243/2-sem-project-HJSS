package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Locale;

import model.Employee;
import model.TimeSheet;

public class TimeSheetDB implements TimeSheetDBIF {
	private static final String FIND_TIME_SHEET_BY_EMPLOYEE_AND_DATE_QUERY = "SELECT * FROM TimeSheet"
			+ " JOIN WeekStartDates ON TimeSheet.start_date_week = WeekStartDates.start_date_week "
			+ " JOIN Employee ON TimeSheet.employee_id = Employee.employee_id"
			+ " WHERE employee_number = ? AND week_number = ? AND year = ?;";
	private PreparedStatement findTimeSheetByEmployeeAndDatePS;
	
	public TimeSheetDB() {
		init();
	}
	
	private void init() {
		Connection con = DBConnection.getInstance().getDBcon();
		try {
			findTimeSheetByEmployeeAndDatePS = con.prepareStatement(FIND_TIME_SHEET_BY_EMPLOYEE_AND_DATE_QUERY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public TimeSheet findTimeSheetByEmployeeAndDate(Employee employee, LocalDate date) {
		TimeSheet currentTimeSheet = null;
		try {
			findTimeSheetByEmployeeAndDatePS.setString(1, employee.getEmployeeNumber());
			//Set weekNumber
			Locale locale = new Locale("da", "DK");
			int weekNumber = date.get(WeekFields.of(locale).weekOfYear());
			findTimeSheetByEmployeeAndDatePS.setInt(2, weekNumber);
			//Set year
			findTimeSheetByEmployeeAndDatePS.setInt(3, date.getYear());
			
			ResultSet resultSet = findTimeSheetByEmployeeAndDatePS.executeQuery();
			
			currentTimeSheet = buildObject(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return currentTimeSheet;
	}
	
	private TimeSheet buildObject(ResultSet resultSet) {
		TimeSheet currentTimeSheet = null;
		
		// TODO:
		// Employee skal findes og bygges! Men hvordan?
		
		EmployeeDBIF edb = new EmployeeDB();
		
		try {
			if (resultSet.next()) {
				String timeSheetNumber = resultSet.getString("time_sheet_number");
				String weekNumber = resultSet.getString("week_number");
				LocalDate startDateWeek = resultSet.getDate("start_date_week").toLocalDate();
				LocalDate endDateWeek = resultSet.getDate("end_date_week").toLocalDate();
				boolean isSubmitted = resultSet.getBoolean("is_submitted");
				boolean isApproved = resultSet.getBoolean("is_approved");
				
				Employee employee = null;
				if (edb.findEmployee(resultSet.getString("employee_number")) != null) {
					employee = edb.findEmployee(resultSet.getString("employee_number"));
				}
				
				currentTimeSheet = new TimeSheet(timeSheetNumber, weekNumber, startDateWeek, 
						endDateWeek, isSubmitted, isApproved, employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return currentTimeSheet;
	}

}
