package utility;

import java.time.LocalDate;
import java.time.LocalDateTime;

import controller.IllegalTimeRegistrationException;
import db.TimeRegistrationDB;
import model.Employee;
import model.Project;
import model.TimeRegistration;
import model.TimeSheet;

public class ValidateTimeRegistration {
		// Validates all fields of a TimeRegistration object.
		// Throws IllegalTimeRegistrationException if a field is invalid.
	public static void validateData(TimeRegistration timeRegistration) throws IllegalTimeRegistrationException {
		String timeRegistrationNumber = timeRegistration.getTimeRegistrationNumber();
		LocalDate date = timeRegistration.getDate();
		LocalDateTime startTime = timeRegistration.getStartTime();
		LocalDateTime endTime = timeRegistration.getEndTime();
		double hours = timeRegistration.getHours();
		String registrationType = timeRegistration.getRegistrationType();
		String description = timeRegistration.getDescription();
		TimeSheet timeSheet = timeRegistration.getTimeSheet();
		Project project = timeRegistration.getProject();
		Employee employee = timeRegistration.getEmployee();
		
		// Validates  each field and throws an exception message if invalid
		
		if (!validateTimeRegistrationNumber(timeRegistrationNumber)) {
			throw new IllegalTimeRegistrationException("Registration number missing");
		}
		
		if (!validateDate(date)) {
			throw new IllegalTimeRegistrationException("Missing date");
		}
		
		if (!validateStartTime(startTime)) {
			throw new IllegalTimeRegistrationException("Missing start time");
		}
		
		if (!validateEndTime(endTime)) {
			throw new IllegalTimeRegistrationException("Missing end time");
		}
		
		if (!validateStartTimeBeforeEndTime(startTime, endTime)) {
			throw new IllegalTimeRegistrationException("Start time: " + startTime + " is after end time: " + endTime);
		}
		
//		if (!validateHours(hours)) {
//			throw new IllegalTimeRegistrationException("Invalid hours: " + hours);
//		}
		
		if (!validateRegistrationType(registrationType)) {
			throw new IllegalTimeRegistrationException("Registration missing");
		}
		
		if (!validateDescription(description)) {
			throw new IllegalTimeRegistrationException("Description missing");
		}
		
		if (!validateTimeSheet(timeSheet)) {
			throw new IllegalTimeRegistrationException("Timesheet missing");
		}
		
		if (!validateProject(project)) {
			throw new IllegalTimeRegistrationException("Project missing");
		}
		
		if (!validateEmployee(employee)) {
			throw new IllegalTimeRegistrationException("Employee missing");
		}
	}
	
		// Validates conditions for clocking in.
		// Ensures that no active time registration exists.
		// Ensures that start time, and timesheet are present. 
	public static void validateClockIn(TimeRegistration timeRegistration) throws IllegalTimeRegistrationException {
		Employee employee = timeRegistration.getEmployee();
		boolean noActiveTimeRegistration = validateNoActiveTimeRegistration(employee);
		Project project = timeRegistration.getProject();
		boolean projectIsValid = validateProject(project);
		LocalDateTime startTime = timeRegistration.getStartTime();
		boolean startTimeIsValid = validateStartTime(startTime);
		TimeSheet timeSheet = timeRegistration.getTimeSheet();
		boolean timeSheetIsValid = validateTimeSheet(timeSheet);
		
		if (!noActiveTimeRegistration) {
			throw new IllegalTimeRegistrationException("You already have an active TimeRegistration");
		}
		
		if (!projectIsValid) {
			throw new IllegalTimeRegistrationException("You must assign a project to the registration before clocking in");
		}
		
		if (!startTimeIsValid) {
			throw new IllegalTimeRegistrationException("Invalid Start Time");
		}
		
		if (!timeSheetIsValid) {
			throw new IllegalTimeRegistrationException("Couldn't find a valid Time Sheet");
		}
		
	}
	
		// Validates conditions for clocking out.
		// Ensures user has clocked in before clocking out.
		// Ensures user hasn't already clocked out.
	
	public static void validateClockOut(TimeRegistration timeRegistration) throws IllegalTimeRegistrationException{
		LocalDateTime startTime = timeRegistration.getStartTime();
		LocalDateTime endTime = timeRegistration.getEndTime();
		boolean startTimeIsValid = validateStartTime(startTime);
		boolean endTimeIsValid = validateEndTime(endTime);
		
		if (!startTimeIsValid && endTimeIsValid) {
			throw new IllegalTimeRegistrationException("Can't clock out before clocking in");
		}
		
		if (startTimeIsValid && endTimeIsValid) {
			throw new IllegalTimeRegistrationException("You have already clocked out");
		}
	}
	
	// Validates that the TimeRegistrationNumber is never empty or null. 
	
	public static boolean validateTimeRegistrationNumber(String timeRegistrationNumber) {
		boolean timeRegistrationNumberIsValid = true;
		
		if (timeRegistrationNumber == "" || timeRegistrationNumber == null) {
			timeRegistrationNumberIsValid = false;
		}
		
		return timeRegistrationNumberIsValid;
	}
	
	// Validates that the date is not null.
	
	public static boolean validateDate(LocalDate date) {
		boolean dateIsValid = true;
		
		if (date == null) {
			dateIsValid = false;
		}
		
		return dateIsValid;
	}
	// Validates that the StartTime is prior to EndTime. 
	public static boolean validateStartTimeBeforeEndTime(LocalDateTime startTime, LocalDateTime endTime) {
		boolean startTimeIsBeforeEndTime = false;
		
		if (startTime.isBefore(endTime)) {
			startTimeIsBeforeEndTime = true;
		}
		
		return startTimeIsBeforeEndTime;
	}
	// Validates the startTime is not null. 
	public static boolean validateStartTime(LocalDateTime startTime) {
		boolean startTimeIsValid = true;
		
		if (startTime == null) {
			startTimeIsValid = false;
		}
		
		return startTimeIsValid;
	}
	// Validates that the endTime is not null.
	public static boolean validateEndTime(LocalDateTime endTime) {
		boolean endTimeIsValid = true;
		
		if (endTime == null) {
			endTimeIsValid = false;
		}
		
		return endTimeIsValid;
	}
	// Mangler implementering
	public static boolean validateHours(double hours) {
		return false;
	}
	// Validates the registration type is not null. 
	public static boolean validateRegistrationType(String registrationType) {
		boolean registrationTypeIsValid = true;
		
		if (registrationType == "" || registrationType == null) {
			registrationTypeIsValid = false;
		}
		
		return registrationTypeIsValid;
	}
	
	
	// Validates that the description is not null.
	public static boolean validateDescription(String description) {
		boolean descriptionIsValid = true;
		
		if (description == "" || description == null) {
			descriptionIsValid = false;
		}
		
		return descriptionIsValid;
	}
	// Validates that the timeSheet is not null. 
	public static boolean validateTimeSheet(TimeSheet timeSheet) {
		boolean timeSheetIsValid = true;
		
		if (timeSheet == null) {
			timeSheetIsValid = false;
		}
		
		return timeSheetIsValid;
	}
	// Validates that the project is not null. 
	public static boolean validateProject(Project project) {
		boolean projectIsValid = true;
		
		if (project == null) {
			projectIsValid = false;
		}
		
		return projectIsValid;
	}
	// Validates that the employee is not null. 
	public static boolean validateEmployee(Employee employee) {
		boolean employeeIsValid = true;
		
		if (employee == null) {
			employeeIsValid = false;
		}
		
		return employeeIsValid;
	}
		
		// Ensures that the employee has no active timeRegistration. 
		// Returns true if no active registration exists.
	public static boolean validateNoActiveTimeRegistration(Employee employee) {
		TimeRegistrationDB timeRegistrationDB = new TimeRegistrationDB();
		boolean noActiveTimeRegistration = true;
		
		if (timeRegistrationDB.findActiveTimeRegistration(employee) != null) {
			noActiveTimeRegistration = false;
		}
		
		return noActiveTimeRegistration;
	}
}
