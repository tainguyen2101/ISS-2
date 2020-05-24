package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ISS.Temperature;

class TemperatureTest {
	Temperature testTemp = new Temperature(100.0);

	@Test
	void testConstructor() {
		assertEquals(100.0, testTemp.getMyTemp());
	}

}
