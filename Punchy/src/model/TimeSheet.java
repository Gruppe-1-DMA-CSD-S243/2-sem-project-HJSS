package model;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;
import java.util.List;

/**
 * This class represents a timesheet for an employee. <br>
 * <br>
 * A TimeSheet contains all the TimeRegistrations created by a specific employee during a given calendar week.
 * 
 * @author Sofus Tvorup Wennike, Sebastian Nørlund Nielsen
 * 
 */
public class TimeSheet {
	private String timeSheetNumber;
	private String weekNumber;
	private LocalDate startDateWeek;
	private LocalDate endDateWeek;
	private boolean isTimeBetweenRegistrations;
	private boolean isSevenDayPeriod;
	private boolean isSubmitted;
	private boolean isApproved;
	
	private Employee employee;
	private List<TimeRegistration> timeRegistrations;
	
	public TimeSheet(String timeSheetNumber, String weekNumber, LocalDate startDateWeek, 
			LocalDate endDateWeek, boolean isSubmitted, boolean isApproved, Employee employee, 
			List<TimeRegistration> timeRegistrations) {
		this.timeSheetNumber = timeSheetNumber;
		this.weekNumber = weekNumber;
		this.startDateWeek = startDateWeek;
		this.endDateWeek = endDateWeek;
		this.isSubmitted = isSubmitted;
		this.isApproved = isApproved;
		
		this.employee = employee;
		this.timeRegistrations = timeRegistrations;
	}
	
	public TimeSheet(String timeSheetNumber, Employee employee, LocalDate date) {
		this.timeSheetNumber = timeSheetNumber;
		
		int weekNumber = date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
		int year = date.getYear();
		WeekFields weekFields = WeekFields.ISO;
		
		this.startDateWeek = LocalDate.ofYearDay(year, 1).with(weekFields.weekOfYear(), weekNumber).with(weekFields.dayOfWeek(), 1);
		this.endDateWeek = LocalDate.ofYearDay(year, 1).with(weekFields.weekOfYear(), weekNumber).with(weekFields.dayOfWeek(), 7);
		this.isSubmitted = false;
		this.isApproved = false;
		this.employee = employee;
	}
	
	public boolean isTimeBetweenRegistrations() {
		return isTimeBetweenRegistrations;
	}

	public void setTimeBetweenRegistrations(boolean isTimeBetweenRegistrations) {
		this.isTimeBetweenRegistrations = isTimeBetweenRegistrations;
	}

	public boolean isSevenDayPeriod() {
		return isSevenDayPeriod;
	}

	public void setSevenDayPeriod(boolean isSevenDayPeriod) {
		this.isSevenDayPeriod = isSevenDayPeriod;
	}

	public boolean isSubmitted() {
		return isSubmitted;
	}

	public void setSubmitted(boolean isSubmitted) {
		this.isSubmitted = isSubmitted;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public String getTimeSheetNumber() {
		return timeSheetNumber;
	}

	public String getWeekNumber() {
		return weekNumber;
	}

	public LocalDate getStartDateWeek() {
		return startDateWeek;
	}

	public LocalDate getEndDateWeek() {
		return endDateWeek;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public List<TimeRegistration> getTimeRegistrations() {
		return timeRegistrations;
	}

	@Override
	public String toString() {
		return "TimeSheet [timeSheetNumber=" + timeSheetNumber + ", weekNumber=" + weekNumber + ", startDateWeek="
				+ startDateWeek + ", endDateWeek=" + endDateWeek + ", isSubmitted=" + isSubmitted + ", isApproved="
				+ isApproved + ", employee=" + employee + "]";
	}
	
	
}
