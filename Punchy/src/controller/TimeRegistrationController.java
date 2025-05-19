package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import db.TimeRegistrationDB;
import db.TimeRegistrationDBIF;
import model.Employee;
import model.Project;
import model.TimeRegistration;
import model.TimeSheet;
import utility.ValidateTimeRegistration;

public class TimeRegistrationController implements TimeRegistrationControllerIF {
	private TimeRegistration currentTimeRegistration;
	
	private TimeSheetControllerIF timeSheetController;
	private ProjectControllerIF projectController;
	private EmployeeControllerIF employeeController;
	private TimeRegistrationDBIF timeRegistrationDB;
	
	public TimeRegistrationController() {
		timeSheetController = new TimeSheetController();
		projectController = new ProjectController();
		employeeController = new EmployeeController();
		timeRegistrationDB = new TimeRegistrationDB();
		
	}

	@Override
	public TimeRegistration makeNewTimeRegistration() {
		//Værdier er hardcoded!!!
		int randomNumber = (int)(Math.random() * 1001);
		String registrationNumber = "" + randomNumber;
		currentTimeRegistration = new TimeRegistration(registrationNumber, LocalDate.now(), "TidsRegistrering");
		
		return currentTimeRegistration;
	}

	@Override
	public Employee findEmployee(String employeeNumber) {
		return employeeController.findEmployee(employeeNumber);
	}

	@Override
	public void assignEmployeeToTimeRegistration(Employee employee) {
		currentTimeRegistration.setEmployee(employee);
	}

	@Override
	public Project findProject(String projectNumber, String employeeNumber) {
		return projectController.findProject(projectNumber, employeeNumber);
	}

	@Override
	public void assignProjectToTimeRegistration(Project foundProject) {
		currentTimeRegistration.setProject(foundProject);
	}
	
	@Override
	public TimeSheet findTimeSheetByEmployeeAndDate(Employee employee, LocalDate date) {
		return timeSheetController.findTimeSheetByEmployeeAndDate(employee, date, false);
	}

	@Override
	public void clockIn() throws IllegalTimeRegistrationException {
		currentTimeRegistration.setStartTime(LocalDateTime.now());
		
		TimeSheet foundTimeSheet = timeSheetController.findTimeSheetByEmployeeAndDate(currentTimeRegistration.getEmployee(), currentTimeRegistration.getDate(), false);
		currentTimeRegistration.setTimeSheet(foundTimeSheet);
		
		ValidateTimeRegistration.validateClockIn(currentTimeRegistration);
		
		timeRegistrationDB.insertTimeRegistration(currentTimeRegistration);
	}

	@Override
	public void clockOut() throws IllegalTimeRegistrationException{		
		ValidateTimeRegistration.validateClockOut(currentTimeRegistration);
		
		currentTimeRegistration.setEndTime(LocalDateTime.now());
	}

	@Override
	public void setDescription(String description) {
		currentTimeRegistration.setDescription(description);
	}

	@Override
	public boolean submitRegistration(TimeRegistration newTimeRegistration) {
		//ændre til throws og catch i gui med relevant besked ændring
		try {
			ValidateTimeRegistration.validateData(newTimeRegistration);
		} catch (IllegalTimeRegistrationException e) {
			e.printStackTrace();
		}
		return timeRegistrationDB.updateTimeRegistration(newTimeRegistration);
	}
	
	@Override 
	public List<Project> findProjectsByEmployee(Employee employee) {
		return projectController.findProjectsByEmployee(employee);
	}
	
	@Override
	public TimeRegistration findActiveTimeRegistration(Employee employee) {
		return timeRegistrationDB.findActiveTimeRegistration(employee);
	}

	public TimeRegistration getCurrentTimeRegistration() {
		return currentTimeRegistration;
	}
	
	public void setCurrentTimeRegistration(TimeRegistration timeRegistration) {
		currentTimeRegistration = timeRegistration;
	}

	public ProjectControllerIF getProjectController() {
		return projectController;
	}

	public EmployeeControllerIF getEmployeeController() {
		return employeeController;
	}

	public TimeRegistrationDBIF getTimeRegistrationDB() {
		return timeRegistrationDB;
	}
	
	
}
