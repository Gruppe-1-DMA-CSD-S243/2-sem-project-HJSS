package model;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class TimeSheet {
	private String timeSheetNumber;
	private String weekNumber;
	private LocalDate startDateWeek;
	private LocalDate endDateWeek;
	private boolean isSubmitted;
	private boolean isApproved;
	
	private Employee employee;
	
	public TimeSheet(String timeSheetNumber, String weekNumber, LocalDate startDateWeek, 
			LocalDate endDateWeek, boolean isSubmitted, boolean isApproved, Employee employee) {
		this.timeSheetNumber = timeSheetNumber;
		this.weekNumber = weekNumber;
		this.startDateWeek = startDateWeek;
		this.endDateWeek = endDateWeek;
		this.isSubmitted = isSubmitted;
		this.isApproved = isApproved;
		
		this.employee = employee;
	}
	
	public TimeSheet(String timeSheetNumber, Employee employee, LocalDate date) {
		this.timeSheetNumber = timeSheetNumber;
		
		Locale locale = new Locale("da", "DK");
		int weekNumber = date.get(WeekFields.of(locale).weekBasedYear());
		int year = date.getYear();
		WeekFields weekFields = WeekFields.of(locale);
		
		this.startDateWeek = LocalDate.ofYearDay(year, 1).with(weekFields.weekOfYear(), weekNumber).with(weekFields.dayOfWeek(), 1);
		this.endDateWeek = LocalDate.ofYearDay(year, 1).with(weekFields.weekOfYear(), weekNumber).with(weekFields.dayOfWeek(), 7);
		this.isSubmitted = false;
		this.isApproved = false;
		this.employee = employee;
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

	@Override
	public String toString() {
		return "TimeSheet [timeSheetNumber=" + timeSheetNumber + ", weekNumber=" + weekNumber + ", startDateWeek="
				+ startDateWeek + ", endDateWeek=" + endDateWeek + ", isSubmitted=" + isSubmitted + ", isApproved="
				+ isApproved + ", employee=" + employee + "]";
	}
	
	
}
