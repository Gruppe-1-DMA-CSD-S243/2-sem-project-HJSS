package db;

import java.util.List;

import model.Employee;
import model.TimeRegistration;

public interface TimeRegistrationDBIF {
	public boolean insertTimeRegistration(TimeRegistration timeRegistration);
	public TimeRegistration findActiveTimeRegistration(Employee employee);
	public boolean updateTimeRegistration(TimeRegistration newTimeRegistration);
	
	public List<TimeRegistration> findTimeRegistrationsByTimeSheetNumber(String timeSheetNumber);
}
