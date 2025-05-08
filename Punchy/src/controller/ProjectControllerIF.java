package controller;

import java.util.List;

import model.Employee;
import model.Project;

public interface ProjectControllerIF {

	public Project findProject(String projectNumber, String employeeNumber);
	public List<Project> findProjectsByEmployee(Employee employee);
}
