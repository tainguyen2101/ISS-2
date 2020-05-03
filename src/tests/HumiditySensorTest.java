
 package tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sensors.HumiditySensor;

/**
 * A series of tests for the Humidity Sensor
 * 
 * @author Jonathan Soledad
 * @author Benjamin Munoz
 *
 */
public class HumiditySensorTest {
	public static HumiditySensor sense; 
	public static HumiditySensor sense2; 
	
	@BeforeAll
	public static void setUpBeforeClass() {
		sense = new HumiditySensor();
		sense2 = new HumiditySensor();
		for(int testTime = 1; testTime <= 20; testTime++) {
			sense.getData();
			sense2.getData();
		}	
	}

	@Test
	public void testHumiditySensor() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        new HumiditySensor(0, 50);
	    });
	    assertThrows(IllegalArgumentException.class, () -> {
            new HumiditySensor(101, 50);
        });
	    assertThrows(IllegalArgumentException.class, () -> {
            new HumiditySensor(50, 0);
        });
	    assertThrows(IllegalArgumentException.class, () -> {
            new HumiditySensor(50, 101);
        });
	}

	
	@Test
	public void testGetData() {
		assertNotNull(sense.getData());
	}

	@Test
	public void testAllInnerHum() {
		assertFalse(sense.allInnerHum().isEmpty());
		assertNotEquals(sense.allInnerHum(), sense2.allInnerHum());
	}

	@Test
	public void testAllOuterHum() {
		assertFalse(sense.allOuterHum().isEmpty());
		assertNotEquals(sense.allOuterHum(),sense2.allOuterHum());
	}

}