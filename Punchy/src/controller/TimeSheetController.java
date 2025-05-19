package controller;

import java.time.LocalDate;

import db.TimeSheetDB;
import db.TimeSheetDBIF;
import model.Employee;
import model.TimeSheet;

/**
 * Controller class responsible for handling use case logic related to TimeSheet
 * 
 * @author Sofus Tvorup Wennike
 */
public class TimeSheetController implements TimeSheetControllerIF {
	private TimeSheetDBIF timeSheetDB;
	
	public TimeSheetController() {
		timeSheetDB = new TimeSheetDB();
	}
	
	@Override
	public TimeSheet findTimeSheetByEmployeeAndDate(Employee employee, LocalDate date, boolean fullAssociation) {
		return timeSheetDB.findTimeSheetByEmployeeAndDate(employee, date, fullAssociation);
	}

}
