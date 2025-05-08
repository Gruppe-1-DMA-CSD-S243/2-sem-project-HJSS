package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Project;

public class ProjectDB implements ProjectDBIF {

	private static final String FIND_PROJECT_QUERY = "SELECT * FROM Project JOIN WorksOn ON Project.project_id = WorksOn.project_id "
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
	
	@Override
	public Project findProject(String projectNumber, String employeeNumber) {
		Project currentProject = null;
		
		try {
			findProjectPS.setString(1, projectNumber);
			findProjectPS.setString(2, employeeNumber);
			
			ResultSet resultSet = findProjectPS.executeQuery();
			
			currentProject = buildObject(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return currentProject;
	}
	
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
	
	private Project buildObject(ResultSet resultSet) {
		Project currentProject = null;
		try {
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
