package main;

import java.time.LocalDate;
import java.time.LocalDateTime;

import controller.TimeRegistrationController;
import db.EmployeeDB;
import db.ProjectDB;
import db.TimeRegistrationDB;
import db.TimeRegistrationDBIF;
import model.Employee;
import model.Project;
import model.TimeRegistration;

public class Main {

	public static void main(String[] args) {
		
		TimeRegistrationController trc = new TimeRegistrationController();
		
		trc.makeNewTimeRegsistration();
		
		Employee foundEmployee = trc.findEmployee("100000002");
		System.out.println(foundEmployee.getFirstName() + ", " + foundEmployee.getEmployeeNumber());
		
		TimeRegistrationDBIF trdb = new TimeRegistrationDB();
		
		trc.assignEmployeeToTimeRegistration(foundEmployee);
		
		Project foundProject = trc.findProject("200000002", "100000001");
		
		trc.assignProjectToTimeRegistration(foundProject);
		
		trc.clockIn();
		
//		trc.clockOut();
//		
//		trc.setDescription("yo yo yo");
//		
//		trc.submitRegistration(trc.getCurrentTimeRegistration());
		
	}

}
