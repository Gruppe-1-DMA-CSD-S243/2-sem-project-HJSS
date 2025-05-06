package db;

import java.sql.SQLException;

import model.Project;

public interface ProjectDBIF {

	public Project findProject(String projectNumber, String employeeNumber);

}
