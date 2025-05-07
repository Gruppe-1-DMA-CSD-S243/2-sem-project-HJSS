package controller;

import model.Employee;
import model.Project;
import model.TimeRegistration;

public interface TimeRegistrationControllerIF {
	public TimeRegistration makeNewTimeRegistration();
	public Employee findEmployee(String employeeNumber);
	public void assignEmployeeToTimeRegistration(Employee employee);
	public Project findProject(String projectNumber, String employeeNumber);
	public void assignProjectToTimeRegistration(Project foundProject);
	public void clockIn();
	public void clockOut();
	public void setDescription(String description);
	public boolean submitRegistration(TimeRegistration newTimeRegistration);
}
