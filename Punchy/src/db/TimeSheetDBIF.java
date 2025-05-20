package db;

import java.time.LocalDate;

import model.Employee;
import model.TimeSheet;

/**
 * Interface providing necessary methods for TimeSheetDB
 * 
 * @author Sofus Tvorup Wennike
 */
public interface TimeSheetDBIF {
	
	/**
	 * Finds the TimeSheet for given employee that covers a given date.
	 * 
	 * @param employee the employee whose TimeSheet is to be found
	 * @param date the date contained within the the TimeSheet week
	 * @param fullAssociation true if all TimeRegistrations contained by the TimeSheet should be found and built and false only the TimeSheet and TimeRegistrations should be built
	 * @return the TimeSheet for the provided employee that covers the provided date
	 * 
	 * 
	 */
	public TimeSheet findTimeSheetByEmployeeAndDate(Employee employee, LocalDate date, boolean fullAssociation);
	
	/**
	 * Inserts a new TimeSheet into the database.
	 * 
	 * @param timeSheet the TimeSheet to be inserted into the database.
	 * @return true if the TimeSheet was inserted or false if an error occurred
	 * 
	 */
	public boolean insertTimeSheet(TimeSheet timeSheet);
}
