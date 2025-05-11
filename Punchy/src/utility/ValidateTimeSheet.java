package utility;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import model.TimeRegistration;
import model.TimeSheet;

public class ValidateTimeSheet {
	
	public static void validateBusinessRules(TimeSheet timeSheet) {
		
		List<TimeRegistration> timeRegistrations = timeSheet.getTimeRegistrations();
		boolean timeBetweenIsValid = validateTimeBetweenRegistrations(timeRegistrations);
		boolean sevenDayPeriodIsValid = validateSevenDayPeriod(timeRegistrations);
		
		if (timeBetweenIsValid) {
			
			timeSheet.setTimeBetweenRegistrations(true);
		} else {
			
			timeSheet.setTimeBetweenRegistrations(false);
		}
		
		if (sevenDayPeriodIsValid) {
			
			timeSheet.setSevenDayPeriod(true);
		} else {
			
			timeSheet.setSevenDayPeriod(false);
		}
		
	}
	
	public static boolean validateTimeBetweenRegistrations(List<TimeRegistration> timeRegistrations) {
		
		boolean timeBetweenIsValid = false;
		
		if (timeRegistrations != null) {
			
			TimeRegistration previousTimeRegistration = null;
			LocalDate currentDate = null;
			
			for (TimeRegistration timeRegistration : timeRegistrations) {
				
				currentDate = timeRegistration.getDate();
				
				if (previousTimeRegistration != null && currentDate.isAfter(previousTimeRegistration.getDate())) {
					
					LocalDateTime previousEndTime = previousTimeRegistration.getEndTime();
					LocalDateTime currentStartTime = timeRegistration.getStartTime(); 
					double durationBetweenTimes = Duration.between(previousEndTime, currentStartTime).toHours();
					
					if (durationBetweenTimes >= 11) {
						
						timeBetweenIsValid = true;
					}
				} 
				
				previousTimeRegistration = timeRegistration;
			}
		}
		
		return timeBetweenIsValid;
	}
	
	public static boolean validateSevenDayPeriod(List<TimeRegistration> timeRegistrations) {
		
		boolean sevenDayPeriodIsValid = false;
		double totalHoursInTimeSheet = 0;
		
		if (timeRegistrations != null) {
			
			for (TimeRegistration timeRegistration : timeRegistrations) {
				
				LocalDateTime currentStartTime = timeRegistration.getStartTime();
				LocalDateTime currentEndTime = timeRegistration.getEndTime();
				double durationBetweenStartEnd = Duration.between(currentStartTime, currentEndTime).toHours();
				totalHoursInTimeSheet += durationBetweenStartEnd;
			}
			
			if (totalHoursInTimeSheet <= 48) {
				
				sevenDayPeriodIsValid = true;
			}
		} 
		
		return sevenDayPeriodIsValid;
	}

}
