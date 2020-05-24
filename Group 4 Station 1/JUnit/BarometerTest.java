package JUnit;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ISS.Barometer;

/**
 * A JUnit test class for the Barometer sensor class designed for the Weather Vantage
 * Pro2 ISS. Null and illegal values are not tested because the random number generator
 * ensures that values are to specification.
 * @author Jordan Holland
 */
class BarometerTest {
	Barometer testBaro = new Barometer(20.0);

	@Test
	void testConstructor() {
		assertEquals(20.0, testBaro.getMyBaroPressure());
	}
	
	@Test
	void testSetElevation() {
		testBaro.setMyElevation(1000);
		assertEquals(1000, testBaro.getMyElevation());
	}

	@Test
	void testSetBaroPressure() {
		testBaro.setMyBaroPressure(30.0);
		assertEquals(30.0, testBaro.getMyBaroPressure());
	}
}
