package controller;

import model.Project;

public interface ProjectControllerIF {

	public Project findProject(String projectNumber, String employeeNumber);
	
}
