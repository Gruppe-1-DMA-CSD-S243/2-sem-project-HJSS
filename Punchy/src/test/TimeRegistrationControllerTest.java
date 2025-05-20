package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.IllegalTimeRegistrationException;
import controller.LoginController;
import controller.TimeRegistrationController;
import controller.TimeSheetController;
import db.TimeRegistrationDB;
import db.TimeRegistrationDBIF;
import model.Employee;
import model.Project;
import model.TimeSheet;
import utility.ValidateTimeRegistration;

class TimeRegistrationControllerTest {
	
	static TimeRegistrationController timeRegistrationController;
	static TimeRegistrationDBIF timeRegistrationDB;
	static TimeSheetController timeSheetController;
	static LoginController loginController;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		timeRegistrationController = new TimeRegistrationController();
		timeRegistrationDB = new TimeRegistrationDB();
		timeSheetController = new TimeSheetController();
		loginController = LoginController.getInstance();
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
	 * Hele den her test er lort og når flere usecases er implementeret kan denne være meget bedre
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
		String expectedTimeSheetNumber = "72"; //Currently impossible to test on because this value will change
		String expectedProjectNumber = "5000";
		String expectedEmployeeNumber = "12345";
		
		// Act
		timeRegistrationController.setCurrentTimeRegistration(timeRegistrationController.makeNewTimeRegistration());
		// Kan ikke nuværende bruge clockIn() fordi logik der tjekker for activeTimeSheet ligger der
		// Skal kunne teste et happy days scenarie hvor der er ingen active time sheet
		timeRegistrationController.getCurrentTimeRegistration().setStartTime(LocalDateTime.now());
		// Kan hellere ikke bruge clockOut() af samme årsag som clockIn()
		timeRegistrationController.getCurrentTimeRegistration().setEndTime(LocalDateTime.now());
		timeRegistrationController.setDescription("Assert test");
		Employee foundEmployee = timeRegistrationController.findEmployee("12345");
		timeRegistrationController.assignEmployeeToTimeRegistration(foundEmployee);
		TimeSheet foundTimeSheet = timeSheetController.findTimeSheetByEmployeeAndDate(
				timeRegistrationController.getCurrentTimeRegistration().getEmployee(), 
				timeRegistrationController.getCurrentTimeRegistration().getDate(), false);
		timeRegistrationController.getCurrentTimeRegistration().setTimeSheet(foundTimeSheet);
		Project foundProject = timeRegistrationController.findProject("5000", "12345");
		timeRegistrationController.assignProjectToTimeRegistration(foundProject);
		
		String actualTimeRegistrationNumber = timeRegistrationController.getCurrentTimeRegistration().getTimeRegistrationNumber(); 
		LocalDate actualDate = timeRegistrationController.getCurrentTimeRegistration().getDate(); 
		LocalDateTime actualStartTime = timeRegistrationController.getCurrentTimeRegistration().getStartTime(); 
		LocalDateTime actualEndTime = timeRegistrationController.getCurrentTimeRegistration().getEndTime(); 
		double actualHours; 
		String actualRegistrationType = timeRegistrationController.getCurrentTimeRegistration().getRegistrationType();
		String actualDescription = timeRegistrationController.getCurrentTimeRegistration().getDescription();
		String actualTimeSheetNumber = timeRegistrationController.getCurrentTimeRegistration().getTimeSheet().getTimeSheetNumber();
		String actualProjectNumber = timeRegistrationController.getCurrentTimeRegistration().getProject().getProjectNumber();
		String actualEmployeeNumber = timeRegistrationController.getCurrentTimeRegistration().getEmployee().getEmployeeNumber();
		
		// Assert
		assertAll("TimeRegistration comparison",
//		        () -> assertEquals(expectedTimeRegistrationNumber, actualTimeRegistrationNumber),
//		        () -> assertEquals(expectedDate, actualDate),
//		        () -> assertEquals(expectedStartTime, actualStartTime),
//		        () -> assertEquals(expectedEndTime, actualEndTime),
		        () -> assertEquals(expectedRegistrationType, actualRegistrationType),
		        () -> assertEquals(expectedDescription, actualDescription),
		        () -> assertEquals(expectedTimeSheetNumber, actualTimeSheetNumber),
		        () -> assertEquals(expectedProjectNumber, actualProjectNumber),
		        () -> assertEquals(expectedEmployeeNumber, actualEmployeeNumber),
		        () -> assertDoesNotThrow(() -> ValidateTimeRegistration.validateData(timeRegistrationController.getCurrentTimeRegistration()))
		        );
	}
	
	@Test
	void shouldClockInNewTimeRegistration() {
		
		// Arrange
		
		// Act
		timeRegistrationController.makeNewTimeRegistration();
		Employee loggedInEmployee = loginController.getLoggedInEmployee();
		timeRegistrationController.assignEmployeeToTimeRegistration(loggedInEmployee);
		List<Project> projects = timeRegistrationController.findProjectsByEmployee(loggedInEmployee);
		
		// Assert
		
		
		timeRegistrationController.makeNewTimeRegistration();
	}
	
	@Test
	void shouldClockOutActiveTimeRegistration() {
		
	}

}
