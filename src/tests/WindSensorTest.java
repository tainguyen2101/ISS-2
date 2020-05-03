package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sensors.WindSensor;

/**
 * A series of tests for the Wind Sensor.
 * 
 * @author Jonathan Soledad
 * @author Benjamin Munoz
 */
public class WindSensorTest {
	public static WindSensor sense; 
	public static WindSensor sense2; 
	
	@BeforeAll
	public static void setUpBeforeClass() {
		sense = new WindSensor();
		sense2 = new WindSensor();
		for(int testTime = 1; testTime <= 20; testTime++) {
			sense.getData();
			sense2.getData();
		}
	}

	@Test
	void testWindSensor() {
		assertThrows(IllegalArgumentException.class, () -> {
		    new WindSensor(250, -20);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
		    new WindSensor(250, 2000);
		});
		
		try {
		    new WindSensor(-10, 10);
		} catch(Exception e) {
		    fail("An exception should not be thrown when given a negative direction angle");
		}
	}


	@Test
	public void testGetData() {
		for (double k : sense.allWindDirection()) {
		    assertTrue(0 <= k && k <= 360);
		}
		for (double k : sense.allWindSpeed()) {
		    assertTrue(0 <= k && k <= 200);
		}
		
		for (double k : sense2.allWindDirection()) {
		    assertTrue(0 <= k && k <= 360);
		}
		for (double k : sense2.allWindSpeed()) {
		    assertTrue(0 <= k && k <= 200);
		}
	}

	@Test
	public void testAllWindDirection() {
		assertFalse(sense.allWindDirection().isEmpty());
		assertEquals(20, sense.allWindDirection().size());
		assertNotEquals(sense.allWindDirection(), sense2.allWindDirection());
	}

	@Test
	public void testAllWindSpeed() {
		assertFalse(sense.allWindSpeed().isEmpty());
		assertEquals(20, sense.allWindSpeed().size());
		assertNotEquals(sense.allWindSpeed(), sense2.allWindSpeed());
	}
}