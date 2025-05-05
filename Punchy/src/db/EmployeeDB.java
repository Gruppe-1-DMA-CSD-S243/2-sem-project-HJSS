package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import model.Employee;

public class EmployeeDB implements EmployeeDBIF {
	private static final String FIND_EMPLOYEE_QUERY = "SELECT * FROM Employee WHERE employee_number = ?";
	private PreparedStatement findEmployeePS;
	
	public EmployeeDB() {
		init();
	}
	
	private void init() {
		// TODO Auto-generated method stub
		Connection con = DBConnection.getInstance().getDBcon();
		try {
			findEmployeePS = con.prepareStatement(FIND_EMPLOYEE_QUERY);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Employee findEmployee(String employeeNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
