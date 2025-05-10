package utility;

import java.time.LocalDate;
import java.time.LocalDateTime;

import controller.IllegalTimeRegistrationException;
import model.Employee;
import model.Project;
import model.TimeRegistration;
import model.TimeSheet;

public class ValidateTimeRegistration {
	
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
		
		if (!validateTimeRegistrationNumber(timeRegistrationNumber)) {
			throw new IllegalTimeRegistrationException("Registration number missing");
		}
		
		if (!validateDate(date)) {
			throw new IllegalTimeRegistrationException("Missing date");
		}
		
		if(!validateStartTime(startTime)) {
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
	
	public static void validateClockOut(TimeRegistration timeRegistration) throws IllegalTimeRegistrationException{
		LocalDateTime startTime = timeRegistration.getStartTime();
		LocalDateTime endTime = timeRegistration.getEndTime();
		boolean startTimeIsValid = validateStartTime(startTime);
		boolean endTimeIsValid = validateEndTime(endTime);
		
		if (!startTimeIsValid && !endTimeIsValid) {
			throw new IllegalTimeRegistrationException("Can't clock out before clocking in");
		}
		
		if (startTimeIsValid && endTimeIsValid) {
			throw new IllegalTimeRegistrationException("You have already clocked out");
		}
	}
	
	public static boolean validateTimeRegistrationNumber(String timeRegistrationNumber) {
		boolean timeRegistrationNumberIsValid = true;
		
		if (timeRegistrationNumber == "" || timeRegistrationNumber == null) {
			timeRegistrationNumberIsValid = false;
		}
		
		return timeRegistrationNumberIsValid;
	}
	
	public static boolean validateDate(LocalDate date) {
		boolean dateIsValid = true;
		
		if (date == null) {
			dateIsValid = false;
		}
		
		return dateIsValid;
	}
	
	public static boolean validateStartTimeBeforeEndTime(LocalDateTime startTime, LocalDateTime endTime) {
		boolean startTimeIsBeforeEndTime = true;
		
		if (startTime.isBefore(endTime)) {
			startTimeIsBeforeEndTime = false;
		}
		
		return startTimeIsBeforeEndTime;
	}
	
	public static boolean validateStartTime(LocalDateTime startTime) {
		boolean startTimeIsValid = true;
		
		if (startTime == null) {
			startTimeIsValid = false;
		}
		
		return startTimeIsValid;
	}
	
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
	
	public static boolean validateRegistrationType(String registrationType) {
		boolean registrationTypeIsValid = true;
		
		if (registrationType == "" || registrationType == null) {
			registrationTypeIsValid = false;
		}
		
		return registrationTypeIsValid;
	}
	
	public static boolean validateDescription(String description) {
		boolean descriptionIsValid = true;
		
		if (description == "" || description == null) {
			descriptionIsValid = false;
		}
		
		return descriptionIsValid;
	}
	
	public static boolean validateTimeSheet(TimeSheet timeSheet) {
		boolean timeSheetIsValid = true;
		
		if (timeSheet == null) {
			timeSheetIsValid = false;
		}
		
		return timeSheetIsValid;
	}
	
	public static boolean validateProject(Project project) {
		boolean projectIsValid = true;
		
		if (project == null) {
			projectIsValid = false;
		}
		
		return projectIsValid;
	}
	
	public static boolean validateEmployee(Employee employee) {
		boolean employeeIsValid = true;
		
		if (employee == null) {
			employeeIsValid = false;
		}
		
		return employeeIsValid;
	}
}
