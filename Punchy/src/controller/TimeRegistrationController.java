package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import db.TimeRegistrationDB;
import db.TimeRegistrationDBIF;
import model.Employee;
import model.Project;
import model.TimeRegistration;

public class TimeRegistrationController implements TimeRegistrationControllerIF {
	private TimeRegistration currentTimeRegistration;
	
	private Employee currentFoundEmployee;
	private Project currentFoundProject;
	
	private ProjectControllerIF projectController;
	private EmployeeControllerIF employeeController;
	private TimeRegistrationDBIF timeRegistrationDB;
	
	public TimeRegistrationController() {
		projectController = new ProjectController();
		employeeController = new EmployeeController();
		timeRegistrationDB = new TimeRegistrationDB();
		
		currentFoundEmployee = employeeController.findEmployee("100000002");
	}

	@Override
	public TimeRegistration makeNewTimeRegsistration() {
		currentTimeRegistration = new TimeRegistration("12345", LocalDate.now(), "TidsRegistrering");
		
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
	public void clockIn() {
		currentTimeRegistration.setStartTime(LocalDateTime.now());
		
		timeRegistrationDB.insertTimeRegistration(currentTimeRegistration);
	}

	@Override
	public void clockOut() {
		currentTimeRegistration = timeRegistrationDB.findActiveTimeRegistration(currentFoundEmployee);
		
		currentTimeRegistration.setEndTime(LocalDateTime.now());
	}

	@Override
	public void setDescription(String description) {
		currentTimeRegistration.setDescription(description);
	}

	@Override
	public boolean submitRegistration(TimeRegistration newTimeRegistration) {
		return timeRegistrationDB.updateTimeRegistration(newTimeRegistration);
	}

	public TimeRegistration getCurrentTimeRegistration() {
		return currentTimeRegistration;
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
