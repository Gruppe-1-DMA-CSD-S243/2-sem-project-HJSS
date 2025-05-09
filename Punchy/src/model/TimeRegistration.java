package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TimeRegistration {
	private String timeRegistrationNumber;
	private LocalDate date;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private double hours;
	private String registrationType;
	private String description;
	
	//TimeSheet variable skal fjernes, og skal kun gøre brug af timesheet der holder collection af registrations
	private TimeSheet timeSheet;
	private Project project;
	private Employee employee;
	
	public TimeRegistration(String timeRegistrationNumber, LocalDate date, String registrationType) {
		this.timeRegistrationNumber = timeRegistrationNumber;
		this.date = date;
		this.registrationType = registrationType;
	}
	
	
	//Constructor til buildObject
	public TimeRegistration(String timeRegistrationNumber, LocalDate date, LocalDateTime startTime,
			LocalDateTime endTime, double hours, String registrationType, String description, 
			TimeSheet timeSheet, Project project, Employee employee) {
		super();
		this.timeRegistrationNumber = timeRegistrationNumber;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.hours = hours;
		this.registrationType = registrationType;
		this.description = description;
		this.timeSheet = timeSheet;
		this.project = project;
		this.employee = employee;
	}

	//Temp constructor!!!
	public TimeRegistration(LocalDate date, LocalDateTime startTime, LocalDateTime endTime) {
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTimeRegistrationNumber() {
		return timeRegistrationNumber;
	}

	public LocalDate getDate() {
		return date;
	}

	public double getHours() {
		return hours;
	}

	public String getRegistrationType() {
		return registrationType;
	}

	public TimeSheet getTimeSheet() {
		return timeSheet;
	}


	public void setTimeSheet(TimeSheet timeSheet) {
		this.timeSheet = timeSheet;
	}


	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}


	@Override
	public String toString() {
		return "TimeRegistration [timeRegistrationNumber=" + timeRegistrationNumber + ", date=" + date + ", startTime="
				+ startTime + ", endTime=" + endTime + ", hours=" + hours + ", registrationType=" + registrationType
				+ ", description=" + description + ", employee=" + employee
				+ ", project=" + project + "]";
	}
	
	
}
