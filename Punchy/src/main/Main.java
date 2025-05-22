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
		
		try {
		    UIManager.setLookAndFeel( new FlatLightLaf() );
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		
		EmployeeDBIF edb = new EmployeeDB();
		
		LoginController.getInstance().setLoggedInEmployee(edb.findEmployee("12345"));
		
		MainMenu menu = new MainMenu();
		menu.setVisible(true);
	}

}
