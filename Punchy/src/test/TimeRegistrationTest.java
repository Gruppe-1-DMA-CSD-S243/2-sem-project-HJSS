package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.IllegalTimeRegistrationException;
import model.TimeRegistration;
import utility.ValidateTimeRegistration;

/**
 * Unit tests for TimeRegistration date parsing and time validation.
 * 
 * @author Sebastian Nørlund Nielsen
 */
class TimeRegistrationTest {
	static DateTimeFormatter formatter;
	static TimeRegistration timeRegistration;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		timeRegistration = new TimeRegistration(null, null, "");
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
     * Malformed date string should throw DateTimeParseException.
     */
	@Test
	void shouldRejectParsingMalformedDate() throws DateTimeParseException{
		// Arrange
		String negativeDate = "2025-05--06 13:20:10";
		
		// Act
		
		// Assert
		assertThrows(DateTimeParseException.class, () -> timeRegistration.setStartTime(LocalDateTime.parse(negativeDate, formatter)));
	}
	
	/**
     * Start time should be set and fall within expected time range.
     */
	@Test
	void shouldDisplayExpectedStartTime() {
		// Arrange
		LocalDateTime before = LocalDateTime.now();
		timeRegistration.setStartTime(LocalDateTime.now());
		LocalDateTime after = LocalDateTime.now();
		
		// Act
		LocalDateTime actualStart = timeRegistration.getStartTime();
		
		// Assert
		assertNotNull(actualStart, "Start time should not be null");
		assertTrue(
				(actualStart.isEqual(before) || actualStart.isAfter(before)) &&
				(actualStart.isEqual(after) || actualStart.isBefore(after)));
	}
	
	/**
     * End time before start time should throw IllegalTimeRegistrationException.
     */
	@Test
	void shouldRejectEndTimeBeforeStartTime() throws IllegalTimeRegistrationException{
		// Arrange
		timeRegistration.setEndTime(LocalDateTime.now());

		// Act
			
		// Assert
		assertThrows(IllegalTimeRegistrationException.class, () -> ValidateTimeRegistration.validateClockOut(timeRegistration));
	}

}
