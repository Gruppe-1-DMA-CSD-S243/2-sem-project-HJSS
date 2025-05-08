package db;

import java.sql.SQLException;
import java.util.List;

import model.Employee;
import model.Project;

public interface ProjectDBIF {

	public Project findProject(String projectNumber, String employeeNumber);
	public List<Project> findProjectsByEmployee(Employee employee);
}
