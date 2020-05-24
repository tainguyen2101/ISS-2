package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ISS.Humidity;

class HumidityTest {
	Humidity testHum = new Humidity(25.5);

	@Test
	void testConstructor() {
		assertEquals(25.5, testHum.getMyHumid());
	}
}
