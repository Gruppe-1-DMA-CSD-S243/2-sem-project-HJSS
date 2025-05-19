package controller;

import db.EmployeeDB;
import db.EmployeeDBIF;
import model.Employee;

/**
 * The class EmployeeController implements the EmployeeControllerIF interface.<br>
 * Has an EmployeeDBIF interface as an instance variable.
 * @author Jonas Hovaldt
 */
public class EmployeeController implements EmployeeControllerIF {
	private EmployeeDBIF employeeDB;
	
	/**
	 * Constructs an EmployeeController.<br>
	 * Constructs a new EmployeeDB.
	 */
	public EmployeeController() {
		this.employeeDB = new EmployeeDB();
	}

	/**
	 * Method for finding an employee by inserting the employee number.
	 * @param employeeNumber
	 * @return Employee by calling the method findEmployee in the EmployeeDB class.
	 */
	@Override
	public Employee findEmployee(String employeeNumber) {
		return employeeDB.findEmployee(employeeNumber);
	}

}
