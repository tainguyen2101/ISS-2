package tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sensors.HumiditySensor;
import sensors.TemperatureSensor;

/**
 * A series of tests for the temperature sensor.
 * 
 * @author Jonathan Soledad
 * @author Benjamin Munoz
 */
public class TemperatureSensorTest {
	public static TemperatureSensor sense; 
	public static TemperatureSensor sense2; 
	
	@BeforeAll
	public static void setUpBeforeClass() {
		sense = new TemperatureSensor();
		sense2 = new TemperatureSensor();
		for(int testTime = 1; testTime <= 20; testTime++) {
			sense.getData();
			sense2.getData();
		}
	}

	@Test
	void testTemperatureSensor() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        new TemperatureSensor(30, 50);
	    });
	    assertThrows(IllegalArgumentException.class, () -> {
            new TemperatureSensor(150, 50);
        });
	    assertThrows(IllegalArgumentException.class, () -> {
            new TemperatureSensor(50, -50);
        });
	    assertThrows(IllegalArgumentException.class, () -> {
            new TemperatureSensor(50, 200);
        });
	}

	@Test
	void testGetData() {
		for (double k : sense.allTemperatureIn()) {
		    assertTrue(32 <= k && k <= 140);
		}
		for (double k : sense.allTemperatureOut()) {
		    assertTrue(-40 <= k && k <= 150);
		}
		
		for (double k : sense2.allTemperatureIn()) {
		    assertTrue(32 <= k && k <= 140);
		}
		for (double k : sense2.allTemperatureOut()) {
		    assertTrue(-40 <= k && k <= 150);
		}
	}

	@Test
	void testAllTemperatureIn() {
		assertFalse(sense.allTemperatureIn().isEmpty());
		assertNotEquals(sense.allTemperatureIn(),sense2.allTemperatureIn());
	}

	@Test
	void testAllTemperatureOut() {
		assertFalse(sense.allTemperatureOut().isEmpty());
		assertNotEquals(sense.allTemperatureOut(),sense2.allTemperatureOut());
	}

}