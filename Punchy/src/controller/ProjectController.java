package controller;

import db.ProjectDB;
import db.ProjectDBIF;
import model.Project;

public class ProjectController implements ProjectControllerIF {

	private ProjectDBIF projectDB;
	
	public ProjectController() {
		
		this.projectDB = new ProjectDB();
		
	}
	
	@Override
	public Project findProject(String projectNumber, String employeeNumber) {
		return projectDB.findProjectPS(projectNumber, employeeNumber);
	}

}
