package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Employee;

public class EmployeeDB implements EmployeeDBIF {
	private static final String FIND_EMPLOYEE_QUERY = 
			"SELECT * "
			+ "FROM Employee employee"
			+ "JOIN JobTitle ON employee.job_title_id = job_title_id"
			+ "WHERE employee_number = ?";
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
		Employee currentEmployee = null;
		try {
			findEmployeePS.setString(1, employeeNumber);
			ResultSet resultSet = findEmployeePS.executeQuery();
			
			currentEmployee = buildObject(resultSet);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return currentEmployee;
	}
	
	private Employee buildObject(ResultSet resultSet) throws SQLException{
		Employee currentEmployee = null;
		
		try {
			String employeeNumber = resultSet.getString("employee_number");
			String phoneNumber = resultSet.getString("phone_number");
			String firstName = resultSet.getString("first_name");
			String lastName = resultSet.getString("last_name");
			String address = resultSet.getString("address");
			String zipCode = resultSet.getString("zip_code");
			String email = resultSet.getString("email");
			String jobTitle = resultSet.getString("job_title");
			String username = resultSet.getString("username");
			String password = resultSet.getString("password");
			boolean isAdministrator = resultSet.getBoolean("is_administrator");
			
			currentEmployee = new Employee(employeeNumber, phoneNumber, firstName, lastName, address, zipCode, email, jobTitle, username, password, isAdministrator);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return currentEmployee;
		
	}

}
