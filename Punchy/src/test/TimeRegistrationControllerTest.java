package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.IllegalTimeRegistrationException;
import controller.TimeRegistrationController;
import controller.TimeSheetController;
import db.TimeRegistrationDB;
import db.TimeRegistrationDBIF;
import model.Employee;
import model.Project;
import model.TimeRegistration;
import model.TimeSheet;

class TimeRegistrationControllerTest {
	
	static TimeRegistrationController timeRegistrationController;
	static TimeRegistrationDBIF timeRegistrationDB;
	static TimeSheetController timeSheetController;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		timeRegistrationController = new TimeRegistrationController();
		timeRegistrationDB = new TimeRegistrationDB();
		timeSheetController = new TimeSheetController();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/*
	 * Tester nuværende uden insertion fordi vi ikke har nogen måde at rydde op i database der ikke er tedious
	 * Kan refaktoreres når CRUD er implementeret, eller hvis ny opsætning af JDBC connection vil gøres
	 */
	@Test
	void shouldMakeNewTimeRegistrationAndPersistWithValidFields() throws IllegalTimeRegistrationException{
		// Arrange
		String expectedTimeRegistrationNumber; //Currently impossible to assert on since it is hardcoded and random
		LocalDate expectedDate; //Currently impossible to assert on
		LocalDateTime expectedStartTime; //Currently impossible to assert on
		LocalDateTime expectedEndTime; //Currently impossible to assert on
		double expectedHours; //Currently impossible to assert on
		String expectedRegistrationType = "TidsRegistrering";
		String expectedDescription = "Assert test";
		String expectedTimeSheetNumber = "727";
		String expectedProjectNumber = "5000";
		String expectedEmployeeNumber = "12345";
		
		
		timeRegistrationController.setCurrentTimeRegistration(timeRegistrationController.makeNewTimeRegistration());
		Employee foundEmployee = timeRegistrationController.findEmployee("12345");
		timeRegistrationController.assignEmployeeToTimeRegistration(foundEmployee);
		System.out.println();
		
		TimeSheet foundTimeSheet = timeSheetController.findTimeSheetByEmployeeAndDate(
				timeRegistrationController.getCurrentTimeRegistration().getEmployee(), 
				timeRegistrationController.getCurrentTimeRegistration().getDate(), false);
		System.out.println(foundTimeSheet.getTimeSheetNumber());
		timeRegistrationController.getCurrentTimeRegistration().setTimeSheet(foundTimeSheet);
		// Kan ikke nuværende bruge clockIn() fordi logik der tjekker for activeTimeSheet ligger der
		// Skal kunne teste et happy days scenarie hvor der er ingen active time sheet
		timeRegistrationController.getCurrentTimeRegistration().setStartTime(LocalDateTime.now());
		timeRegistrationController.clockOut();
		timeRegistrationController.setDescription("Assert test");
		
//		String actualTimeRegistrationNumber; 
//		LocalDate actualDate; 
//		LocalDateTime actualStartTime; 
//		LocalDateTime actualEndTime; 
//		double actualHours; 
//		String actualRegistrationType = 
//		String actualDescription = 
//		String actualTimeSheetNumber = 
//		String actualProjectNumber = 
//		String actualEmployeeNumber = 
		
		
		// Act
		
//		if ( == null) {
//			timeRegistrationController.makeNewTimeRegistration();
//			timeRegistrationController.assignEmployeeToTimeRegistration(foundEmployee);
//		}
		
		
		
		Project foundProject = timeRegistrationController.findProject("5000", "12345");
		timeRegistrationController.assignProjectToTimeRegistration(foundProject);
		
		timeRegistrationController.clockIn();
		System.out.println(timeRegistrationController.getCurrentTimeRegistration().getStartTime());
			timeRegistrationController.clockOut();
		
		// Assert
		assertEquals(expectedEmployeeNumber, "12345");
	}

}
