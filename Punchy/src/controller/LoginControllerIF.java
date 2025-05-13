package controller;

import model.Employee;

public interface LoginControllerIF {
	public Employee getLoggedInEmployee();
	public void setLoggedInEmployee(Employee employee);
}
