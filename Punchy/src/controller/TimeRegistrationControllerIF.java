package controller;

import java.time.LocalDate;
import java.util.List;

import model.Employee;
import model.Project;
import model.TimeRegistration;
import model.TimeSheet;

public interface TimeRegistrationControllerIF {
	public TimeRegistration makeNewTimeRegistration();
	public Employee findEmployee(String employeeNumber);
	public void assignEmployeeToTimeRegistration(Employee employee);
	public Project findProject(String projectNumber, String employeeNumber);
	public void assignProjectToTimeRegistration(Project foundProject);
	public void clockIn() throws IllegalTimeRegistrationException;
	public void clockOut() throws IllegalTimeRegistrationException;
	public void setDescription(String description);
	public boolean submitRegistration(TimeRegistration newTimeRegistration);
	
	public List<Project> findProjectsByEmployee(Employee employee);
	public TimeSheet findTimeSheetByEmployeeAndDate(Employee employee, LocalDate date);
	public TimeRegistration findActiveTimeRegistration(Employee employee);
}
