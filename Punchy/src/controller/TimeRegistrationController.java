package controller;

public class TimeRegistrationController implements TimeRegistrationControllerIF {
	private TimeRegistration currentTimeRegistration;
	private ProjectControllerIF projectController;
	private EmployeeControllerIF employeeController;
	private TimeRegistrationDBIF timeRegistrationDB;
	
	public TimeRegistrationController() {
		
	}

	@Override
	public TimeRegistration makeNewTimeRegsistration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findEmployee(String employeeNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assignEmployeeToTimeRegistration(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Project findProject(String projectNumber, String employeeNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assignProjectToTimeRegistration(Project foundProject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clockIn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clockOut() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean submitRegistration(TimeRegistration newTimeRegistration) {
		// TODO Auto-generated method stub
		return false;
	}

	public TimeRegistration getCurrentTimeRegistration() {
		return currentTimeRegistration;
	}

	public ProjectControllerIF getProjectController() {
		return projectController;
	}

	public EmployeeControllerIF getEmployeeController() {
		return employeeController;
	}

	public TimeRegistrationDBIF getTimeRegistrationDB() {
		return timeRegistrationDB;
	}
	
	
}
