package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import db.TimeRegistrationDB;
import db.TimeRegistrationDBIF;
import model.Employee;
import model.Project;
import model.TimeRegistration;

public class TimeRegistrationController implements TimeRegistrationControllerIF {
	private TimeRegistration currentTimeRegistration;
	
	private ProjectControllerIF projectController;
	private EmployeeControllerIF employeeController;
	private TimeRegistrationDBIF timeRegistrationDB;
	
	public TimeRegistrationController() {
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
	public void clockIn() {
		if (timeRegistrationDB.findActiveTimeRegistration(currentTimeRegistration.getEmployee()) == null) {
			currentTimeRegistration.setStartTime(LocalDateTime.now());
			
			timeRegistrationDB.insertTimeRegistration(currentTimeRegistration);
		}
		else {
			System.out.println("A TimeRegistration is already active for this employee!");
		}
	}

	@Override
	public void clockOut() {
		currentTimeRegistration = timeRegistrationDB.findActiveTimeRegistration(currentTimeRegistration.getEmployee());
		
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
	
	@Override 
	public List<Project> findProjectsByEmployee(Employee employee) {
		return projectController.findProjectsByEmployee(employee);
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
