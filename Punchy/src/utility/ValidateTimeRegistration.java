package utility;

import java.time.LocalDate;
import java.time.LocalDateTime;

import controller.IllegalTimeRegistrationException;
import db.TimeRegistrationDB;
import model.Employee;
import model.Project;
import model.TimeRegistration;
import model.TimeSheet;

/**
 * The class provides static validation methods for 
 * ensuring the correctness and consistency of objects.
 * It includes checks for all required fields and supports validation for specific operations 
 * such as clock-in and clock-out actions.
 *
 * This class throws IllegalTimeRegistrationException when validation fails 
 * to ensure proper error handling and informative messaging.
 * 
 * This class is not intended to be instantiated.
 * 
 * @author Sebastian Nørlund Nielsen
 */

public class ValidateTimeRegistration {
	
	 /**
     * Validates all fields in the given TimeRegistration object.
     * 
     * @param timeRegistration the TimeRegistration object to validate
     * @throws IllegalTimeRegistrationException if any required field is invalid or missing
     */
	public static void validateData(TimeRegistration timeRegistration) throws IllegalTimeRegistrationException {
		String timeRegistrationNumber = timeRegistration.getTimeRegistrationNumber();
		LocalDate date = timeRegistration.getDate();
		LocalDateTime startTime = timeRegistration.getStartTime();
		LocalDateTime endTime = timeRegistration.getEndTime();
		String registrationType = timeRegistration.getRegistrationType();
		String description = timeRegistration.getDescription();
		TimeSheet timeSheet = timeRegistration.getTimeSheet();
		Project project = timeRegistration.getProject();
		Employee employee = timeRegistration.getEmployee();
		
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
	
	/**
     * Validates that a TimeRegistration object is not null.
     * 
     * @param timeRegistration the TimeRegistration to check
     * @throws IllegalTimeRegistrationException if the time registration is null
     */
	public static void validateActiveTimeRegistration(TimeRegistration timeRegistration) throws IllegalTimeRegistrationException{
		if (timeRegistration == null) {
			throw new IllegalTimeRegistrationException("There is no active time registration");
		}
	}
	
	/**
     * Validates the conditions necessary to clock in:
     * 
     * No other active time registration exists for the employee.
     * A valid project is assigned.
     * A start time is set.
     * A time sheet is linked.
     * 
     * @param timeRegistration the time registration object containing clock-in data
     * @throws IllegalTimeRegistrationException if any clock-in condition is not met
     */ 
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
	
	/**
     * Validates the conditions necessary to clock out:
     * 
     * Start time must be present (indicating the user has clocked in).
     * End time must not already be set (ensures clock-out hasn’t already occurred).
     * 
     * @param timeRegistration the time registration object containing clock-out data
     * @throws IllegalTimeRegistrationException if clock-out conditions are not satisfied
     */
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
	
	/**
     * Checks if the time registration number is not null or empty.
     * 
     * @param timeRegistrationNumber the registration number to validate
     * @return true if valid, false otherwise
     */
	public static boolean validateTimeRegistrationNumber(String timeRegistrationNumber) {
		boolean timeRegistrationNumberIsValid = true;
		
		if (timeRegistrationNumber == "" || timeRegistrationNumber == null) {
			timeRegistrationNumberIsValid = false;
		}
		
		return timeRegistrationNumberIsValid;
	}
	
	/**
     * Checks that the date is not null.
     * 
     * @param date the date to validate
     * @return true if valid, false otherwise
     */
	public static boolean validateDate(LocalDate date) {
		boolean dateIsValid = true;
		
		if (date == null) {
			dateIsValid = false;
		}
		
		return dateIsValid;
	}
	
	/**
     * Validates that the start time is before the end time.
     * 
     * @param startTime the start time
     * @param endTime the end time
     * @return true if start time is before end time, false otherwise
     */ 
	public static boolean validateStartTimeBeforeEndTime(LocalDateTime startTime, LocalDateTime endTime) {
		boolean startTimeIsBeforeEndTime = false;
		
		if (startTime.isBefore(endTime)) {
			startTimeIsBeforeEndTime = true;
		}
		
		return startTimeIsBeforeEndTime;
	}

	/**
     * Checks if the start time is not null.
     * 
     * @param startTime the start time to validate
     * @return true if valid, false otherwise
     */
	public static boolean validateStartTime(LocalDateTime startTime) {
		boolean startTimeIsValid = true;
		
		if (startTime == null) {
			startTimeIsValid = false;
		}
		
		return startTimeIsValid;
	}

	/**
     * Checks if the end time is not null.
     * 
     * @param endTime the end time to validate
     * @return true if valid, false otherwise
     */
	public static boolean validateEndTime(LocalDateTime endTime) {
		boolean endTimeIsValid = true;
		
		if (endTime == null) {
			endTimeIsValid = false;
		}
		
		return endTimeIsValid;
	}
	
	/**
     * Placeholder method for validating hours worked.
     * Currently not implemented.
     * 
     * @param hours the number of hours to validate
     * @return false always
     */
	public static boolean validateHours(double hours) {
		return false;
	}
	
	/**
     * Checks if the registration type is not null or empty.
     * 
     * @param registrationType the registration type
     * @return true if valid, false otherwise
     */ 
	public static boolean validateRegistrationType(String registrationType) {
		boolean registrationTypeIsValid = true;
		
		if (registrationType == "" || registrationType == null) {
			registrationTypeIsValid = false;
		}
		
		return registrationTypeIsValid;
	}
	
	/**
     * Checks if the description is not null or empty.
     * 
     * @param description the description
     * @return true if valid, false otherwise
     */
	public static boolean validateDescription(String description) {
		boolean descriptionIsValid = true;
		
		if (description == "" || description == null) {
			descriptionIsValid = false;
		}
		
		return descriptionIsValid;
	}
	
	/**
     * Validates that the time sheet is not null.
     * 
     * @param timeSheet the associated TimeSheet object
     * @return true if valid, false otherwise
     */ 
	public static boolean validateTimeSheet(TimeSheet timeSheet) {
		boolean timeSheetIsValid = true;
		
		if (timeSheet == null) {
			timeSheetIsValid = false;
		}
		
		return timeSheetIsValid;
	}
	
	/**
     * Validates that the project is not null.
     * 
     * @param project the associated Project object
     * @return true if valid, false otherwise
     */ 
	public static boolean validateProject(Project project) {
		boolean projectIsValid = true;
		
		if (project == null) {
			projectIsValid = false;
		}
		
		return projectIsValid;
	}
	
	/**
     * Validates that the employee is not null.
     * 
     * @param employee the associated Employee object
     * @return true if valid, false otherwise
     */
	public static boolean validateEmployee(Employee employee) {
		boolean employeeIsValid = true;
		
		if (employee == null) {
			employeeIsValid = false;
		}
		
		return employeeIsValid;
	}
		
	/**
     * Checks whether the employee currently has no active time registration.
     * 
     * @param employee the employee to check
     * @return true if no active registration exists, false otherwise
     */
	public static boolean validateNoActiveTimeRegistration(Employee employee) {
		TimeRegistrationDB timeRegistrationDB = new TimeRegistrationDB();
		boolean noActiveTimeRegistration = true;
		
		if (timeRegistrationDB.findActiveTimeRegistration(employee) != null) {
			noActiveTimeRegistration = false;
		}
		
		return noActiveTimeRegistration;
	}
}
