package db;

import java.sql.SQLException;
import java.util.List;

import model.Employee;
import model.Project;

/** Purpose of this interface is to make a blueprint for the ProjectDB class <br> 
 * @author Henrik Holmberg Kringel
 */

public interface ProjectDBIF {

	public Project findProject(String projectNumber, String employeeNumber);
	public List<Project> findProjectsByEmployee(Employee employee);
}
