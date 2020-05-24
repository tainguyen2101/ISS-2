package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ISS.Driver;

class DriverTest extends Driver {
	Driver test = new Driver();

	@Test
	void testDebug() {
		assertEquals(1, test.debug(), "GUI not created successfully");
	}
}
