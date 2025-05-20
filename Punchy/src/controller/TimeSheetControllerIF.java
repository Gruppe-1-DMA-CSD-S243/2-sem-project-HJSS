package controller;

import java.time.LocalDate;

import model.Employee;
import model.TimeSheet;

/**
 * Interface providing necessary methods for TimeSheetController
 * 
 * @author Sofus Tvorup Wennike
 */
public interface TimeSheetControllerIF {
	
	/**
	 * Finds the TimeSheet for a given employee that covers a given date
	 * by calling findTimeSheetByEmployeeAndDate in TimeSheetDBIF.
	 * 
	 * @param employee the employee whose TimeSheet is to be found
	 * @param date the date contained within the TimeSheet week
	 * @param fullAssociation true if all TimeRegistrations contained by the TimeSheet should be found and built and false only the TimeSheet and no TimeRegistrations should be built.
	 * @return The TimeSheet for the employee that includes the provided date, or null if none exists
	 * 
	 */
	public TimeSheet findTimeSheetByEmployeeAndDate(Employee employee, LocalDate date, boolean fullAssociation);
}
