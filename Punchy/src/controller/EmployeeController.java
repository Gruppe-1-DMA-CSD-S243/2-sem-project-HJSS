package controller;

import db.EmployeeDB;
import db.EmployeeDBIF;
import model.Employee;

public class EmployeeController implements EmployeeControllerIF {
	private EmployeeDBIF employeeDB;
	
	public EmployeeController() {
		this.employeeDB = new EmployeeDB();
	}

	@Override
	public Employee findEmployee(String employeeNumber) {
		return employeeDB.findEmployee(employeeNumber);
	}

}
