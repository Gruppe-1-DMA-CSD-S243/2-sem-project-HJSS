package controller;

import java.time.LocalDate;

import model.Employee;
import model.TimeSheet;

public interface TimeSheetControllerIF {
	public TimeSheet findTimeSheetByEmployeeAndDate(Employee employee, LocalDate date, boolean fullAssociation);
}
