package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import controller.EmployeeController;
import controller.IllegalTimeRegistrationException;
import controller.LoginController;
import controller.TimeRegistrationController;
import controller.TimeSheetController;
import model.Employee;
import model.Project;
import model.TimeRegistration;
import model.TimeSheet;


/**
 * Integration tests for TimeRegistrationController covering clock-in and clock-out workflows.
 * Requires a logged-in employee ("12345") and access to a database. 
 * Tests run in order due to dependencies.
 * 
 * @author Sebastian Nørlund Nielsen
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TimeRegistrationControllerTest {
	
	static TimeRegistrationController timeRegistrationController;
	static TimeSheetController timeSheetController;
	static EmployeeController employeeController;
	static LoginController loginController;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		timeRegistrationController = new TimeRegistrationController();
		timeSheetController = new TimeSheetController();
		employeeController = new EmployeeController();
		loginController = LoginController.getInstance();
		loginController.setLoggedInEmployee(employeeController.findEmployee("12345"));
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
	
	/**
     * Tests creating and clocking in a new time registration.
     * Verifies correct data and that start time and timesheet are set.
     */
	@Test
	@Order(1)
	void shouldClockInNewTimeRegistration() throws IllegalTimeRegistrationException {
		
		// Arrange
		LocalDate expectedDate = LocalDate.now();
		String expectedRegistrationType = "TidsRegistrering";
		String expectedProjectName = "Website Revamp";
		String expectedEmployeeNumber = "12345";
		
		// Act
		timeRegistrationController.makeNewTimeRegistration();
		Employee loggedInEmployee = loginController.getLoggedInEmployee();
		timeRegistrationController.assignEmployeeToTimeRegistration(loggedInEmployee);
		List<Project> projects = timeRegistrationController.findProjectsByEmployee(loggedInEmployee);
		// Uses lambda to simulate behaviour from GUI.
		// ParallelStream is used to process with multiple threads.
		Project selectedProject = projects.parallelStream().filter(project -> project.getProjectName().equals("Website Revamp")).findFirst().orElse(null);
		timeRegistrationController.assignProjectToTimeRegistration(selectedProject);
		timeRegistrationController.clockIn();
		
		TimeRegistration timeRegistration = timeRegistrationController.getCurrentTimeRegistration();
		LocalDate actualDate = timeRegistration.getDate();
		String actualRegistrationType = timeRegistration.getRegistrationType();
		String actualProjectName = timeRegistration.getProject().getProjectName();
		String actualEmployeeNumber = timeRegistration.getEmployee().getEmployeeNumber();
		TimeSheet isTimeSheetNull = timeRegistrationController.getCurrentTimeRegistration().getTimeSheet();
		LocalDateTime isStartTimeNull = timeRegistrationController.getCurrentTimeRegistration().getStartTime();
		
		// Assert
		assertAll("TimeRegistration assertions after Start Time usecase",
				() -> assertEquals(expectedDate, actualDate),
				() -> assertEquals(expectedRegistrationType, actualRegistrationType),
				() -> assertEquals(expectedProjectName, actualProjectName),
				() -> assertEquals(expectedEmployeeNumber, actualEmployeeNumber),
					  // AssertNotNull is used because there is no way of comparing an expected value to them, as they are either random or dynamically set
				() -> assertNotNull(isTimeSheetNull),
				() -> assertNotNull(isStartTimeNull)
				);
	}
	
	/**
     * Tests clocking out an active registration, setting description, and submitting.
     * Verifies end time is set and description is saved.
     */
	@Test
	@Order(2)
	void shouldClockOutActiveTimeRegistration() throws IllegalTimeRegistrationException {
		
		// Arrange
		String expectedDescription = "Description for integrationtest on end time";
		
		// Act
		Employee employee = loginController.getLoggedInEmployee();
		TimeRegistration timeRegistration = timeRegistrationController.findActiveTimeRegistration(employee);
		timeRegistrationController.setCurrentTimeRegistration(timeRegistration);
		timeRegistrationController.clockOut();
		timeRegistrationController.setDescription("Description for integrationtest on end time");
		timeRegistrationController.submitRegistration(timeRegistrationController.getCurrentTimeRegistration());
		
		String actualDescription = timeRegistrationController.getCurrentTimeRegistration().getDescription();
		LocalDateTime isEndTimeNull = timeRegistrationController.getCurrentTimeRegistration().getEndTime();
		
		// Assert
		assertAll("TimeRegistration assertions after End Time usecase",
				() -> assertEquals(expectedDescription, actualDescription),
				() -> assertNotNull(isEndTimeNull)
				);
	}

}
