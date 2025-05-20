package controller;

import java.time.LocalDate;
import java.util.List;

import model.Employee;
import model.Project;
import model.TimeRegistration;
import model.TimeSheet;

/**
 * Interface providing methods necessary TimeRegistrationController
 * 
 * @author Sofus Tvorup Wennike
 */
public interface TimeRegistrationControllerIF {
	
	/**
	 * Creates a new instance of TimeRegistration and stores it as a field.
	 * 
	 * @return the TimeRegistration that was created
	 * 
	 */
	public TimeRegistration makeNewTimeRegistration();
	
	/**
	 * Finds an employee by employee number 
	 * by calling the findEmployee method in EmployeeControllerIF.
	 * 
	 * @param employeeNumber the employee number of the employee to find
	 * @return the Employee matching provided employee number, or null if none exists
	 * 
	 */
	public Employee findEmployee(String employeeNumber);
	
	/**
	 * Calls the setEmployee method on the currently held TimeRegistration. 
	 * 
	 * @param employee the employee to add to the currently held TimeRegistration
	 * 
	 */
	public void assignEmployeeToTimeRegistration(Employee employee);
	
	/**
	 * Finds a project by project number and employee number
	 * by calling the findProject method in ProjectControllerIF.
	 * 
	 * @param projectNumber the project number of the project to find
	 * @param employeeNumber the employee number of the employee currently signed in
	 * @return The project matching the provided project number, or null if none exists
	 * 
	 */
	public Project findProject(String projectNumber, String employeeNumber);
	
	/**
	 * Calls the setProject method on the currently held TimeRegistration.
	 * 
	 * @param foundProject the project to add to the currently held TimeRegistration.
	 * 
	 */
	public void assignProjectToTimeRegistration(Project foundProject);
	
	/**
	 * Starts the currently held TimeRegistration by setting the start time to the current time,
	 * finding and assigning a matching TimeSheet and validating the TimeRegistration 
	 * before inserting it into the database.
	 * 
	 * @throws IllegalTimeRegistrationException
	 * 
	 */
	public void clockIn() throws IllegalTimeRegistrationException;
	
	/**
	 * Calls the setEndTime method on the currently held TimeRegistration
	 * to set the end time to the current time.
	 * 
	 * @throws IllegalTimeRegistrationException
	 *
	 */
	public void clockOut() throws IllegalTimeRegistrationException;
	
	/**
	 * Calls setDescription on the currently held TimeRegistration.
	 * 
	 * @param description the description to add to the TimeRegistration
	 *
	 */
	public void setDescription(String description);
	
	/**
	 * Validates the a timeRegistration before updating it in the database.
	 * 
	 * @param newTimeRegistration the TimeRegistration to update and save in the database
	 * @return true if newTimeRegistration was updated and saved or false if the update wasn't possible
	 * 
	 */
	public boolean submitRegistration(TimeRegistration newTimeRegistration) throws IllegalTimeRegistrationException;
	
	/**
	 * Finds all the projects a given employee is assigned to by calling
	 * findProjectsByEmployee in ProjectControllerIF.
	 * 
	 * @param employee the employee whose assigned projects are to be found
	 * @return a list of all the Projects the provided employee is assigned to.
	 *
	 */
	public List<Project> findProjectsByEmployee(Employee employee);
	
	/**
	 * Finds the TimeSheet for a given employee that covers a given date
	 * by calling findTimeSheetByEmployeeAndDate in TimeSheetControllerIF.
	 * 
	 * @param employee the employee whose TimeSheet is to be found
	 * @param date the date contained within the TimeSheet week
	 * @return the TimeSheet for the employee that includes the provided date
	 * 
	 */
	public TimeSheet findTimeSheetByEmployeeAndDate(Employee employee, LocalDate date);
	
	/**
	 * Finds the TimeRegistration without an end time for a given employee.
	 * 
	 * @param employee the employee whose active TimeRegistration are to be found
	 * @return the active TimeRegistration for the provided employee, or null if none exists
	 * @throws IllegalTimeRegistrationException
	 * 
	 * 
	 */
	public TimeRegistration findActiveTimeRegistration(Employee employee) throws IllegalTimeRegistrationException;
}
