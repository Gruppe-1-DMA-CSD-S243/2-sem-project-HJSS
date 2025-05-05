package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Project;

public class ProjectDB implements ProjectDBIF {

	private static final String FIND_PROJECT_QUERY = "SELECT * FROM Project WHERE project_number = ?";
	private PreparedStatement findProjectPS;
	
	public ProjectDB() {
		init();
	}
	
	private void init() {
		Connection con = DBConnection.getInstance().getDBcon();
		
		try {
			findProjectPS = con.prepareStatement(FIND_PROJECT_QUERY);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Project findProject(String projectNumber, String employeeNumber) throws SQLException {
		Project currentProject = null;
		
		try {
			findProjectPS.setString(1, projectNumber);
			ResultSet resultSet = findProjectPS.executeQuery();
			
			currentProject = buildObject(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentProject;
	}
	
	public Project buildObject(ResultSet resultSet) throws SQLException {
		Project currentProject = null;
		try {
			String projectNumber = resultSet.getString("project_number");
			String projectName = resultSet.getString("project_name");
			int timeBudget = resultSet.getInt("time_budget");
			String description = resultSet.getString("description");
			
			currentProject = new Project(projectNumber, projectName, timeBudget, description);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return currentProject;
	}

	
}
