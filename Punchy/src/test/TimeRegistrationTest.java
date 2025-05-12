package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Clock;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

	/*
	 * I vores testcase havde vi tiltænkt de forskellige typer af malformeret data skulle testes på.
	 * Fordi LocalDateTime allerede har logik der forhindrer ugyldig data af alle former, så giver det ikke mening at teste mere end en gang.
	 * Her resulterer det i en DateTimeParseException idet malformeret data ikke kan parses.
	 * Der kan ikke kaldes en metode i act, fordi koden ville fejle før der kan assertes.
	 */
	@Test
	void shouldRejectParsingMalformedDate() throws DateTimeParseException{
		// Arrange
		String negativeDate = "2025-05--06 13:20:10";
		System.out.println();
		
		// Act
		
		// Assert
		assertThrows(DateTimeParseException.class, () -> timeRegistration.setStartTime(LocalDateTime.parse(negativeDate, formatter)));
	}
	
	/*
	 * Fordi tid er dynamisk kan vi ikke genskabe expected værdier.
	 * Derfor vælger vi at teste på en range af værdier, "before" og "after".
	 * Hvis "actualStart" ikke er null, og er indenfor den range, så asserter testen true.
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
	
	@Test
	void shouldRejectEndTimeBeforeStartTime() throws IllegalTimeRegistrationException{
		// Arrange
			timeRegistration.setEndTime(LocalDateTime.now());

		// Act
			
		// Assert
		assertThrows(IllegalTimeRegistrationException.class, () -> ValidateTimeRegistration.validateClockOut(timeRegistration));
	}

}
