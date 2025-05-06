package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.TimeRegistration;

class TimeRegistrationTest {
	DateTimeFormatter formatter;
	TimeRegistration timeRegistration;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		timeRegistration = new TimeRegistration(null, null, null);
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void shouldRejectParsingNegativeDate() throws DateTimeParseException{
		// Arrange
		String negativeDate = "2025-05--06 13:20:10";
		System.out.println();
		
		assertThrows(DateTimeParseException.class, () -> timeRegistration.setStartTime(LocalDateTime.parse(negativeDate, formatter)));
	}

}
