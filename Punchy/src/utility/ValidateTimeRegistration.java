package utility;

import java.time.LocalDateTime;

import controller.IllegalTimeRegistrationException;
import model.TimeRegistration;

public class ValidateTimeRegistration {
	
	// FLYT EndTime LOGIK FRA GUI TIL VALIDATE KLASSE
	public static void validateData(TimeRegistration timeRegistration) throws IllegalTimeRegistrationException {
		
	}
	
	public static boolean validateStartTimeBeforeEndTime(LocalDateTime startTime, LocalDateTime endTime) {
		boolean startTimeIsBeforeEndTime = false;
		
		if (startTime.isBefore(endTime)) {
			startTimeIsBeforeEndTime = true;
		}
		
		return startTimeIsBeforeEndTime;
	}
	
	public static boolean validateStartTime(LocalDateTime startTime) {
		boolean startTimeIsValid = false;
		
		if (startTime != null) {
			startTimeIsValid = true;
		}
		
		return startTimeIsValid;
	}
	
	public static boolean validateEndTime(LocalDateTime endTime) {
		boolean endTimeIsValid = false;
		
		if (endTime != null) {
			endTimeIsValid = true;
		}
		
		return endTimeIsValid;
	}
	
	
	
}
