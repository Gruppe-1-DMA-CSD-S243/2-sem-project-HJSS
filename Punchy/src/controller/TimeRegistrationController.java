package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import db.TimeRegistrationDB;
import db.TimeRegistrationDBIF;
import model.Employee;
import model.Project;
import model.TimeRegistration;
import model.TimeSheet;

public class TimeRegistrationController implements TimeRegistrationControllerIF {
	private TimeRegistration currentTimeRegistration;
	
	private Employee currentFoundEmployee;
	private Project currentFoundProject;
	
	private TimeSheetControllerIF timeSheetController;
	private ProjectControllerIF projectController;
	private EmployeeControllerIF employeeController;
	private TimeRegistrationDBIF timeRegistrationDB;
	
	public TimeRegistrationController() {
		timeSheetController = new TimeSheetController();
		projectController = new ProjectController();
		employeeController = new EmployeeController();
		timeRegistrationDB = new TimeRegistrationDB();
		
		//Temp:
		currentFoundEmployee = employeeController.findEmployee("100000002");
	}

	@Override
	public TimeRegistration makeNewTimeRegsistration() {
		//Værdier er hardcoded!!!
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse("2024-05-13", formatter);
		currentTimeRegistration = new TimeRegistration("999", date, "TidsRegistrering");
		
		return currentTimeRegistration;
	}

	@Override
	public Employee findEmployee(String employeeNumber) {
		currentFoundEmployee = employeeController.findEmployee(employeeNumber);
		return currentFoundEmployee;
	}

	@Override
	public void assignEmployeeToTimeRegistration(Employee employee) {
		currentTimeRegistration.setEmployee(employee);
	}

	@Override
	public Project findProject(String projectNumber, String employeeNumber) {
		currentFoundProject = projectController.findProject(projectNumber, employeeNumber);
		return currentFoundProject;
	}

	@Override
	public void assignProjectToTimeRegistration(Project foundProject) {
		currentTimeRegistration.setProject(foundProject);
	}

	@Override
	public void clockIn() {
		if (timeRegistrationDB.findActiveTimeRegistration(currentFoundEmployee) == null) {
			currentTimeRegistration.setStartTime(LocalDateTime.now());
			
			TimeSheet timeSheet = timeSheetController.findTimeSheetByEmployeeAndDate(currentTimeRegistration.getEmployee(), currentTimeRegistration.getDate());
			currentTimeRegistration.setTimeSheet(timeSheet);
			
			timeRegistrationDB.insertTimeRegistration(currentTimeRegistration);
		}
		else {
			System.out.println("A TimeRegistration is already active for this employee!");
		}
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
