
package controller;

import java.util.List;

import db.ProjectDB;
import db.ProjectDBIF;
import model.Employee;
import model.Project;

/** Creates class for ProjectController. <br>
 * Purpose of ProjectController class: <br>
 * Creation of projectDB object <br>
 * Find project by project number and employee number <br>
 * Find a list of project by employee <br>
 * @author Henrik Holmberg Kringel
 * 
 * 
 * 
 */

public class ProjectController implements ProjectControllerIF {

	private ProjectDBIF projectDB;
	
	public ProjectController() {
		
		this.projectDB = new ProjectDB();
		
	}
	
	/** Purpose of this method is to find a project which matches the project number and employee number. <br>
	 * which is done by an external method call from the projectDB class.
	 *  
	 * @param projectNumber
	 * @param employeeNumber
	 * @return the project which matches project number and employee number.
	 */
	@Override
	public Project findProject(String projectNumber, String employeeNumber) {
		return projectDB.findProject(projectNumber, employeeNumber); 
	}
	
	/** Purpose of this method is to find a list of project which matches the employee. <br>
	 * which is done by an external method call from the projectDB class.
	 *  
	 * @param employee
	 * @return the list of project which matches the employee.
	 */
	@Override
	public List<Project> findProjectsByEmployee(Employee employee) {
		return projectDB.findProjectsByEmployee(employee);
	}

}
