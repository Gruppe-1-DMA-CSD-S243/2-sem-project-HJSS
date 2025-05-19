package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Project;

/** Purpose of this class is to handle all the database related operations <br>
 * Setting up prepared statements <br>
 * Find project by calling method buildObject <br>
 * Find a list of projects by calling method buildObjects <br>
 * Build object from data <br>
 * Build a list of objects from data <br> 
 * @author Henrik Holmberg Kringel
 */

public class ProjectDB implements ProjectDBIF {
	private static final String FIND_PROJECT_QUERY = "SELECT * FROM Project "
			+ "JOIN WorksOn ON Project.project_id = WorksOn.project_id "
			+ "JOIN Employee ON WorksOn.employee_id = Employee.employee_id "
			+ "WHERE project_number = ? AND employee_number = ?;";
	private PreparedStatement findProjectPS;
	
	private static final String FIND_PROJECTS_BY_EMPLOYEE_QUERY = "SELECT * FROM Project "
			+ "JOIN WorksOn ON Project.project_id = WorksOn.project_id "
			+ "JOIN Employee ON WorksOn.employee_id = Employee.employee_id "
			+ "WHERE employee_number = ?;";
	private PreparedStatement findProjectsByEmployeePS;
	
	public ProjectDB() {
		init();
	}
	
	private void init() {
		Connection con = DBConnection.getInstance().getDBcon();
		
		try {
			findProjectPS = con.prepareStatement(FIND_PROJECT_QUERY);
			findProjectsByEmployeePS = con.prepareStatement(FIND_PROJECTS_BY_EMPLOYEE_QUERY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Purpose of this method is to find the project which matches the two parameters. <br>
	 * This is done by using the prepared statement, which joins Employee table and Project table by Works_on table and then retrieves <br>
	 * the project where project_number and employee_number matches to parameter on the project. <br>
	 * It uses the buildObject method.
	 * @param projectNumber
	 * @param employeeNumber
	 * @return foundProject
	 */
	@Override
	public Project findProject(String projectNumber, String employeeNumber) {
		Project foundProject = null;
		
		try {
			findProjectPS.setString(1, projectNumber);
			findProjectPS.setString(2, employeeNumber);
			
			ResultSet resultSet = findProjectPS.executeQuery();
			
			foundProject = buildObject(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundProject;
	}
	
	/**
	 * Purpose of this method is to find a list of projects which matches the employee. <br>
	 * This is done by using the prepared statement, which joins Employee table and Project table by Works_on table and then retrieves <br>
	 * all projects where project_number and employee_number matches the to parameter on the projects. <br>
	 * It uses the buildObjects method.
	 * @param employee
	 * @return foundProject
	 */
	@Override
	public List<Project> findProjectsByEmployee(Employee employee) {
		List<Project> foundProjects = new ArrayList<>();
		try {
			findProjectsByEmployeePS.setString(1, employee.getEmployeeNumber());
			
			ResultSet resultSet = findProjectsByEmployeePS.executeQuery();
			
			foundProjects = buildObjects(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return foundProjects;
	}
	
	/**
	 * Purpose of this method is to take a resulset from the database and build it into a Java object.
	 * @param resultSet
	 * @return currentProject
	 */
	private Project buildObject(ResultSet resultSet) {
		Project currentProject = null;
		try {
			if (resultSet.isBeforeFirst()) {
				resultSet.next();
			}
			String projectNumber = resultSet.getString("project_number");
			String projectName = resultSet.getString("project_name");
			int timeBudget = resultSet.getInt("time_budget");
			String description = resultSet.getString("description");
			
			currentProject = new Project(projectNumber, projectName, timeBudget, description);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return currentProject;
	}
	
	/**
	 * Purpose of this method is to take a resulset from the database and build it into a list of Java objects.
	 * @param resultSet
	 * @return a list of projects
	 */
	private List<Project> buildObjects(ResultSet resultSet) {
		List<Project> projects = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Project currentProject = buildObject(resultSet);
				projects.add(currentProject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return projects;
	}

	
}
