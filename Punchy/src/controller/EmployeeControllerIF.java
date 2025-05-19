package controller;

import model.Employee;

/**
 * Interface for the EmployeeController
 * @author Jonas Hovaldt
 */
public interface EmployeeControllerIF {
	/**
	 * Method for returning an employee object by inserting the employee number
	 * @param employeeNumber
	 * @return Employee
	 */
	public Employee findEmployee(String employeeNumber);

}
