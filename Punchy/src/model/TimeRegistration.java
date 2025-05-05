package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeRegistration {
	private String timeRegistrationNumber;
	private LocalDate date;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private double hours;
	private String registrationType;
	private String description;
	private boolean isValidated;
	
	public TimeRegistration(String timeRegistrationNumber, LocalDate date, String registrationType) {
		this.timeRegistrationNumber = timeRegistrationNumber;
		this.date = date;
		this.registrationType = registrationType;
	}
	
	
	//Constructor til buildObject
	public TimeRegistration(String timeRegistrationNumber, LocalDate date, LocalDateTime startTime,
			LocalDateTime endTime, double hours, String registrationType, String description, boolean isValidated) {
		super();
		this.timeRegistrationNumber = timeRegistrationNumber;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.hours = hours;
		this.registrationType = registrationType;
		this.description = description;
		this.isValidated = isValidated;
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

	public boolean isValidated() {
		return isValidated;
	}

	public void setValidated(boolean isValidated) {
		this.isValidated = isValidated;
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
	
	
}
