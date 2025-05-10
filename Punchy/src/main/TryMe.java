package main;

import controller.TimeRegistrationController;
import model.TimeRegistration;

public class TryMe {
	
	
	public static void main(String[]args) {
		TimeRegistration timeRegistration = new TimeRegistration("3");
		TimeRegistrationController trc = new TimeRegistrationController();
		
		
		trc.submitRegistration(timeRegistration);
		
	}
}
