package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.ProjectController;
import model.Employee;

class ProjectControllerTest {

	static ProjectController projectController;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		projectController = new ProjectController();
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

	@Test
	void shouldDisplayEmptyProjectsList() {
		
		// Arrange
		int expectedProjectListSize = 0;
		Employee employee = new Employee(null, null, null, null, null, null, null, null, null, null, false);
		
		// Act
		int actualProjectListSize = projectController.findProjectsByEmployee(employee).size();

		// Assert
		assertEquals(expectedProjectListSize, actualProjectListSize, "Should display project list size 0, actual list size:" + actualProjectListSize);
	}

}
