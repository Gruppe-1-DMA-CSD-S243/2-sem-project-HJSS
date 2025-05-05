package db;

public interface TimeRegistrationDBIF {
	public boolean insertTimeRegistration(TimeRegistration timeRegistration);
	public TimeRegistration findActiveTimeRegistration(Employee employee);
	public boolean updateTimeRegistration(TimeRegistration newTimeRegistration);
}
