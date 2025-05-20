package main;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import controller.LoginController;
import db.EmployeeDB;
import db.EmployeeDBIF;
import gui.MainMenu;

public class Main {

	public static void main(String[] args) {
		
		//System.setProperty("sun.java2d.uiScale", "1.0");
		
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
		
		LocalDateTime test1res1 = LocalDateTime.parse("2025-05-15T17:51:17.778182");
		LocalDateTime test1res2 = LocalDateTime.parse("2025-05-15T17:51:18.020567");
		
		LocalDateTime test2res1 = LocalDateTime.parse("2025-05-15T17:52:50.737330600");
		LocalDateTime test2res2 = LocalDateTime.parse("2025-05-15T17:52:51.040436900");
		
		long result1 = test1res1.until(test1res2, ChronoUnit.NANOS);
		long result2 = test2res1.until(test2res2, ChronoUnit.NANOS);
		
		System.out.println("1: " + result1);
		System.out.println("2: " + result2);
		
		
		
		EmployeeDBIF edb = new EmployeeDB();
		
		
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
