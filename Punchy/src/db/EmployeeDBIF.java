package db;

import model.Employee;

/**
 * Interface for the EmployeeDB class.
 * @author Jonas Hovaldt
 */

public interface EmployeeDBIF {
	/**
	 * Method for returning an employee object by inserting the employee number.
	 * @param employeeNumber
	 * @return Employee
	 */
	public Employee findEmployee(String employeeNumber);

}
