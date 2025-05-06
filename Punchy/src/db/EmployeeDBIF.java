package db;

import model.Employee;

public interface EmployeeDBIF {
	public Employee findEmployee(String employeeNumber);

}
