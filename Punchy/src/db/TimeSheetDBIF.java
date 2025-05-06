package db;

import java.time.LocalDate;

import model.Employee;
import model.TimeSheet;

public interface TimeSheetDBIF {
	public TimeSheet findTimeSheetByEmployeeAndDate(Employee employee, LocalDate date);
}
