package model;

import java.time.LocalDate;

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

	@Override
	public String toString() {
		return "TimeSheet [timeSheetNumber=" + timeSheetNumber + ", weekNumber=" + weekNumber + ", startDateWeek="
				+ startDateWeek + ", endDateWeek=" + endDateWeek + ", isSubmitted=" + isSubmitted + ", isApproved="
				+ isApproved + ", employee=" + employee + "]";
	}
	
	
}
