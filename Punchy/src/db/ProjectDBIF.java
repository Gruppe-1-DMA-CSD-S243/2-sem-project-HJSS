package db;

import model.Project;

public interface ProjectDBIF {

	public Project findProjectPS(String projectNumber, String employeeNumber);

}
