package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Employee;

public class EmployeeDB implements EmployeeDBIF {
	private static final String FIND_EMPLOYEE_QUERY = "SELECT * FROM Employee "
			+ "JOIN JobTitle ON Employee.job_title_id = JobTitle.job_title_id "
			+ "WHERE employee_number = ?;";
	private PreparedStatement findEmployeePS;
	
	public EmployeeDB() {
		init();
	}
	
	private void init() {
		Connection con = DBConnection.getInstance().getDBcon();
		try {
			findEmployeePS = con.prepareStatement(FIND_EMPLOYEE_QUERY);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Employee findEmployee(String employeeNumber) {
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
	
	private Employee buildObject(ResultSet resultSet) {
		Employee currentEmployee = null;
		
		try {
			if (resultSet.next()) {
				String employeeNumber = resultSet.getString("employee_number");
				String phoneNumber = resultSet.getString("phone_number");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String address = resultSet.getString("address");
				String zipCode = resultSet.getString("zip_code");
				String email = resultSet.getString("email");
				String jobTitle = resultSet.getString("job_title");
				String username = resultSet.getString("user_name");
				String password = resultSet.getString("password");
				boolean isAdministrator = resultSet.getBoolean("is_administrator");
				
				currentEmployee = new Employee(employeeNumber, phoneNumber, firstName, lastName, address, zipCode, email, jobTitle, username, password, isAdministrator);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return currentEmployee;
	}

}
