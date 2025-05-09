package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;

import model.Employee;
import model.TimeSheet;

public class TimeSheetDB implements TimeSheetDBIF {
	private static final String FIND_TIME_SHEET_BY_EMPLOYEE_AND_DATE_QUERY = "SELECT * FROM TimeSheet"
			+ " JOIN WeekStartDates ON TimeSheet.start_date_week = WeekStartDates.start_date_week "
			+ " JOIN Employee ON TimeSheet.employee_id = Employee.employee_id"
			+ " WHERE employee_number = ? AND week_number = ? AND year = ?;";
	private PreparedStatement findTimeSheetByEmployeeAndDatePS;
	
	private static final String INSERT_WEEK_START_DATES_QUERY = "INSERT INTO WeekStartDates (start_date_week, week_number, month, year) "
			+ "VALUES (?, ?, ?, ?);";
	private PreparedStatement insertWeekStartDatesPS;
	
	private static final String INSERT_TIME_SHEET_QUERY = "INSERT INTO TimeSheet (time_sheet_number, "
			+ "start_date_week, end_date_week, "
			+ "is_submitted, is_approved, employee_id) VALUES (?, "
			+ "(SELECT start_date_week FROM WeekStartDates WHERE week_number = ? AND year = ?), "
			+ "?, ?, ?, "
			+ "(SELECT employee_id FROM Employee WHERE employee_number = ?));";
	private PreparedStatement insertTimeSheetPS;
	
	public TimeSheetDB() {
		init();
	}
	
	private void init() {
		Connection con = DBConnection.getInstance().getDBcon();
		try {
			findTimeSheetByEmployeeAndDatePS = con.prepareStatement(FIND_TIME_SHEET_BY_EMPLOYEE_AND_DATE_QUERY);
			insertWeekStartDatesPS = con.prepareStatement(INSERT_WEEK_START_DATES_QUERY);
			insertTimeSheetPS = con.prepareStatement(INSERT_TIME_SHEET_QUERY);
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
		
		if (currentTimeSheet == null) {
			//TODO: TimeSheetNumber bliver valgt tilfældigt!!!
			int randomNumber = (int)(Math.random() * 1001);
			String timeSheetNumber = "" + randomNumber;
			
			insertTimeSheet(new TimeSheet(timeSheetNumber, employee, date));
			currentTimeSheet = findTimeSheetByEmployeeAndDate(employee, date);
		}
		
		return currentTimeSheet;
	}
	
	@Override
	public boolean insertTimeSheet(TimeSheet timeSheet) {
		boolean success = false;
		try {
			DBConnection.getInstance().startTransaction();
			insertWeekStartDatesPS.setDate(1, Date.valueOf(timeSheet.getStartDateWeek()));
			Locale locale = new Locale("da", "DK");
			int weekNumber = timeSheet.getStartDateWeek().get(WeekFields.of(locale).weekOfYear());
			insertWeekStartDatesPS.setInt(2, weekNumber);
			int monthNumber = timeSheet.getStartDateWeek().getMonthValue();
			insertWeekStartDatesPS.setInt(3, monthNumber);
			int year = timeSheet.getStartDateWeek().getYear();
			insertWeekStartDatesPS.setInt(4, year);
			
			insertWeekStartDatesPS.executeUpdate();
			
			insertTimeSheetPS.setString(1, timeSheet.getTimeSheetNumber()); 
			insertTimeSheetPS.setInt(2, weekNumber); //start_date_week
			insertTimeSheetPS.setInt(3, year); //start_date_week
			insertTimeSheetPS.setDate(4, Date.valueOf(timeSheet.getEndDateWeek()));
			insertTimeSheetPS.setBoolean(5, timeSheet.isSubmitted());
			insertTimeSheetPS.setBoolean(6, timeSheet.isApproved());
			insertTimeSheetPS.setString(7, timeSheet.getEmployee().getEmployeeNumber());
			
			insertTimeSheetPS.executeUpdate();
			
			DBConnection.getInstance().commitTransaction();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().rollbackTransaction();
		}
		return success;
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
