package controller;

import model.Employee;

public class LoginController implements LoginControllerIF {
	private static LoginController instance;
	private Employee loggedInEmployee;
	
	private LoginController() {
		
	}
	
	public static synchronized LoginController getInstance() {
		if (instance == null) {
			instance = new LoginController();
		}
		return instance;
	}
	
	@Override
	public Employee getLoggedInEmployee() {
		return loggedInEmployee;
	}

	@Override
	public void setLoggedInEmployee(Employee employee) {
		this.loggedInEmployee = employee;
	}
}
