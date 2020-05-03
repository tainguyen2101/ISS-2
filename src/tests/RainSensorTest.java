package tests;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sensors.RainSensor;

/**
 * A series of tests for the RainSensor
 * 
 * @author Jonathan Soledad
 * @author Benjamin Munoz
 */
public class RainSensorTest {
	public static RainSensor sense;
	public static RainSensor sense2;
	
	@BeforeAll
	public static void setUpBeforeClass() {
		sense = new RainSensor();
		sense2 = new RainSensor();
		for(int testTime = 1; testTime <= 20; testTime++) {
			sense.getData();
			sense2.getData();
		}
	}

	
	@Test
	public void testRainSensorConstructors() {
		assertThrows(IllegalArgumentException.class, () -> {
		    new RainSensor(-0.1);
		});
		
		try {
		    new RainSensor(0.5);
		} catch (Exception e) {
		    fail("An exception was not supposed to be thrown when constructing a "
		            + "RainSensor with a current rainfall of 0.5 inches");
		}
	}

	@Test
	public void testGetData() {
		for (double k : sense.allCurrentRainfall()) {
		    assertTrue(k >= 0);
		}
		for (double k : sense2.allCurrentRainfall()) {
		    assertTrue(k >= 0);
		}
	}

	@Test
	public void testAllCurrentRainfall() {
		assertFalse(sense.allCurrentRainfall().isEmpty());
		assertNotEquals(sense.allCurrentRainfall(), sense2.allCurrentRainfall());
	}
}