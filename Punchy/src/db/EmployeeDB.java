package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Employee;

/**
 * <p>
 * The class EmployeeDB implements the EmployeeDBIF interface.
 * </p>
 * This class handles the database connectivity and queries
 * that concerns the employee in the system.
 * <p>
 * A prepared statement is created for retrieving
 * an employee based on their employee number.
 * </p>
 * @author Jonas Hovaldt
 */

public class EmployeeDB implements EmployeeDBIF {
	/**
	 * SQL query for finding an employee based on their employee number including their job title.
	 */
	private static final String FIND_EMPLOYEE_QUERY = "SELECT * FROM Employee "
			+ "JOIN JobTitle ON Employee.job_title_id = JobTitle.job_title_id "
			+ "WHERE employee_number = ?;";
	private PreparedStatement findEmployeePS;
	
	/**
	 * Constructs an EmployeeDB instance.
	 */
	public EmployeeDB() {
		init();
	}
	
	/**
     * Initializes the database connection and prepares the SQL statements.
     */
	private void init() {
		Connection con = DBConnection.getInstance().getDBcon();
		try {
			findEmployeePS = con.prepareStatement(FIND_EMPLOYEE_QUERY);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for finding an employee by inserting their employee number.<br>
	 * Uses the findEmployeePS prepared statement.<br>
	 * Uses the buildObject method to convert the employee in the database<br>
	 * to a Java object.
	 * @param employeeNumber
	 * @return Employee from the database.
	 */
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
	
	/**
	 * Initializes an instance of Employee.<br>
	 * Uses the chosen ResultSet<br>
	 * Converts the ResultSet to a Java object.
	 * @param resultSet
	 * @return Employee
	 */
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
