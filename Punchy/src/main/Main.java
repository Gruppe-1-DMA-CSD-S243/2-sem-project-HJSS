package main;

import java.time.LocalDate;
import java.time.LocalDateTime;

import db.TimeRegistrationDB;
import model.Employee;
import model.Project;
import model.TimeRegistration;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//TimeSheet ts = new TimeSheet();
		Project p = new Project();
		Employee e = new Employee("100000002");
		
		TimeRegistration tr = new TimeRegistration("123", LocalDate.now(), LocalDateTime.now(), null, 8, "Development", "Test Description", false, e, p);
		
		TimeRegistrationDB trdb = new TimeRegistrationDB();
		
		//trdb.insertTimeRegistration(tr);
		
		TimeRegistration newts = trdb.findActiveTimeRegistration(e);
		
		System.out.println(newts.toString());
		
		//newts.setEndTime(LocalDateTime.now());
		
		//trdb.updateTimeRegistration(newts);
		
	}

}
