package db;

import java.util.List;

import model.Employee;
import model.TimeRegistration;

/**
 * Interface providing necessary methods for TimeRegistrationDB
 * 
 * @author Sofus Tvorup Wennike
 */
public interface TimeRegistrationDBIF {
	
	/**
	 * Inserts a new TimeRegistration into the database.
	 * 
	 * @param timeRegistration the TimeRegistration to be inserted into the database
	 * @return true if the TimeRegistration was inserted or false if an error occurred
	 * 
	 * 
	 */
	public boolean insertTimeRegistration(TimeRegistration timeRegistration);
	
	/**
	 * Finds the currently active TimeRegistration for given Employee in the database.
	 * 
	 * @param employee the employee whose active TimeRegistration is to be found
	 * @return the active TimeRegistration for the provided Employee, or null of none exists
	 * 
	 * 
	 */
	public TimeRegistration findActiveTimeRegistration(Employee employee);
	
	/**
	 * Updates an existing TimeRegistration in the database.
	 * 
	 * @param newTimeRegistration the TimeRegistration object containing the updated values
	 * @return true if the update was successful or false if an error occurred
	 * 
	 * 
	 */
	public boolean updateTimeRegistration(TimeRegistration newTimeRegistration);
	
	/**
	 * Finds all TimeRegistrations contained by a given TimeSheet
	 * 
	 * @param timeSheetNumber the timesheet number of the TimeSheet whose TimeRegistrations are to be found
	 * @return a list of all the TimeRegistrations contained by the TimeSheet with the provided timesheet number
	 * 
	 */
	public List<TimeRegistration> findTimeRegistrationsByTimeSheetNumber(String timeSheetNumber);
}
