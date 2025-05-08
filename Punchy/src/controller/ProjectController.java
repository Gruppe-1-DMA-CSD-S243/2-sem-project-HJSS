package controller;

import java.util.List;

import db.ProjectDB;
import db.ProjectDBIF;
import model.Employee;
import model.Project;

public class ProjectController implements ProjectControllerIF {

	private ProjectDBIF projectDB;
	
	public ProjectController() {
		
		this.projectDB = new ProjectDB();
		
	}
	
	@Override
	public Project findProject(String projectNumber, String employeeNumber) {
		return projectDB.findProject(projectNumber, employeeNumber); 
	}
	
	@Override
	public List<Project> findProjectsByEmployee(Employee employee) {
		return projectDB.findProjectsByEmployee(employee);
	}

}
