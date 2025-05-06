package main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;

import controller.TimeRegistrationController;
import controller.*;
import db.EmployeeDB;
import db.ProjectDB;
import db.TimeRegistrationDB;
import db.TimeRegistrationDBIF;
import db.*;
import model.Employee;
import model.Project;
import model.TimeRegistration;
import model.TimeSheet;

public class Main {

	public static void main(String[] args) {
		
		TimeRegistrationController trc = new TimeRegistrationController();
		
//		trc.makeNewTimeRegsistration();
//		
//		Employee foundEmployee = trc.findEmployee("23456");
//		System.out.println(foundEmployee.getFirstName() + ", " + foundEmployee.getEmployeeNumber());
//		
//		TimeRegistrationDBIF trdb = new TimeRegistrationDB();
//		
//		trc.assignEmployeeToTimeRegistration(foundEmployee);
//		
//		Project foundProject = trc.findProject("6000", foundEmployee.getEmployeeNumber());
//		
//		trc.assignProjectToTimeRegistration(foundProject);
//		
//		trc.clockIn();
		
		trc.findEmployee("23456");
		
		trc.clockOut();
		
		trc.setDescription("yo yo yo");
		
		trc.submitRegistration(trc.getCurrentTimeRegistration());
		
		
		
		
		
		
//		EmployeeDBIF edb = new EmployeeDB();
//		TimeSheetDBIF tsdb = new TimeSheetDB();
//		
//		Employee employee = edb.findEmployee("12345");
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate date = LocalDate.parse("2024-05-06", formatter);
//		
//		TimeSheet ts = tsdb.findTimeSheetByEmployeeAndDate(employee, date);
//		
//		System.out.println(ts.toString());
		
	}

}
