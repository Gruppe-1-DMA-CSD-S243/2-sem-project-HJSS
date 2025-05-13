package main;

import java.util.Calendar;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import controller.LoginController;
import db.EmployeeDB;
import db.EmployeeDBIF;
import db.TimeSheetDB;
import db.TimeSheetDBIF;
import gui.MainMenu;

public class Main {

	public static void main(String[] args) {
		
		try {
		    UIManager.setLookAndFeel( new FlatLightLaf() );
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		
		//TimeRegistrationController trc = new TimeRegistrationController();
		
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
		
//		trc.findEmployee("23456");
//		
//		trc.clockOut();
//		
//		trc.setDescription("yo yo yo");
//		
//		trc.submitRegistration(trc.getCurrentTimeRegistration());
//		
		
		
		
		
		
		EmployeeDBIF edb = new EmployeeDB();
		TimeSheetDBIF tsdb = new TimeSheetDB();
//		
//		Employee employee = edb.findEmployee("12345");
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate date = LocalDate.parse("2024-05-06", formatter);
//		
//		TimeSheet ts = tsdb.findTimeSheetByEmployeeAndDate(employee, date);
//		
//		System.out.println(ts.toString());
		
		//TimeSheet ts = tsdb.findTimeSheetByEmployeeAndDate(edb.findEmployee("12345"), );
		
		int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
		System.out.println("" + week);
		
		LoginController.getInstance().setLoggedInEmployee(edb.findEmployee("12345"));
		
//		EndTimeRegistrationView end = new EndTimeRegistrationView();
//		end.setVisible(true);
		
		MainMenu menu = new MainMenu();
		menu.setVisible(true);
		
//		TimeRegistrationView frame = new TimeRegistrationView(edb.findEmployee("12345"));
//		frame.setVisible(true);
		
		
		
	}

}
