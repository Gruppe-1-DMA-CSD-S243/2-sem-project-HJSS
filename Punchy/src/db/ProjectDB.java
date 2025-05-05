package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Project;

public class ProjectDB implements ProjectDBIF {

	private static final String FIND_PROJECT_QUERY = "SELECT ";
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
	public Project findProjectPS(String projectNumber, String employeeNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
