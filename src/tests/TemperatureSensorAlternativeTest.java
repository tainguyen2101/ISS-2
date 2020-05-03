package tests;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sensors.TemperatureSensorAlternative;

/**
 * A series of tests for the temperature sensor.
 * 
 * @author Jonathan Soledad
 * @author Benjamin Munoz
 * @author Ali Iftakhar
 * @author Tien Tang
 */
public class TemperatureSensorAlternativeTest {
	public static TemperatureSensorAlternative sense; 
	public static TemperatureSensorAlternative sense2; 
	
	@BeforeAll
	public static void setUpBeforeClass() {
		sense = new TemperatureSensorAlternative();
		sense2 = new TemperatureSensorAlternative();
		for(int testTime = 0; testTime <= 4320; testTime++) {
			sense.getData();
			sense2.getData();
		}
	}


	@Test
	void testGetData() {
		
		for (double k : sense.allTemperatureIn()) {
		    assertTrue(54 <= k && k <= 86);
		}
		for (int i = 0; i < 164; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(32 <= j && j <= 140);
		}
		for (int i = 165; i < 344; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(52 <= j && j <= 62);
		}
		
		for (int i = 345; i < 524; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(53 <= j && j <= 63);
		}
		for (int i = 525; i < 704; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(52 <= j && j <= 66);
		}
		for (int i = 705; i < 1064; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(56 <= j && j <= 66);
		}
		for (int i = 1065; i <1244 ; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(54 <= j && j <= 64);
		}
		for (int i = 1245; i < 1424; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(51 <= j && j <= 61);
		}
		for (int i = 1425; i < 1604; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(49 <= j && j <= 59);
		}
		for (int i = 1605; i < 1784; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(47 <= j && j <= 57);
		}
		for (int i = 1785; i < 1964; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(44 <= j && j <= 54);
		}
		for (int i = 1965; i < 2144; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(41 <= j && j <= 51);
		}
		for (int i = 2145; i < 2324; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(38 <= j && j <= 48);
		}
		for (int i = 2325; i < 2504; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(36 <= j && j <= 46);
		}
		for (int i = 2505; i < 2684; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(34 <= j && j <= 44);
		}
		for (int i = 2685; i < 2864; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(32 <= j && j <= 42);
		}
		for (int i = 2865; i < 3044; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(30 <= j && j <= 40);
		}
		for (int i = 3045; i < 3224; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(28 <= j && j <= 38);
		}
		for (int i = 3225; i < 3404; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(27 <= j && j <= 37);
		}
		for (int i = 3405; i < 3584; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(29 <= j && j <= 39);
		}
		for (int i = 3585; i < 3764; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(32 <= j && j <= 42);
		}
		for (int i = 3765; i < 3944; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(36 <= j && j <= 46);
		}
		for (int i = 3945; i < 4124; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(41 <= j && j <= 51);
		}
		for (int i = 4125; i < 4304; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(45 <= j && j <= 55);
		}
		for (int i = 4305; i < 4319; i++) {
			double j = (sense.allTemperatureOut()).get(i);
			assertTrue(48 <= j && j <= 60);
		}
		
		
		
		
		for (double k : sense2.allTemperatureIn()) {
		    assertTrue(54 <= k && k <= 86);
		}
		for (int i = 0; i < 164; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(32 <= j && j <= 140);
		}
		for (int i = 165; i < 344; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(52 <= j && j <= 62);
		}
		
		for (int i = 345; i < 524; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(53 <= j && j <= 63);
		}
		for (int i = 525; i < 704; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(52 <= j && j <= 66);
		}
		for (int i = 705; i < 1064; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(56 <= j && j <= 66);
		}
		for (int i = 1065; i <1244 ; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(54 <= j && j <= 64);
		}
		for (int i = 1245; i < 1424; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(51 <= j && j <= 61);
		}
		for (int i = 1425; i < 1604; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(49 <= j && j <= 59);
		}
		for (int i = 1605; i < 1784; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(47 <= j && j <= 57);
		}
		for (int i = 1785; i < 1964; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(44 <= j && j <= 54);
		}
		for (int i = 1965; i < 2144; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(41 <= j && j <= 51);
		}
		for (int i = 2145; i < 2324; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(38 <= j && j <= 48);
		}
		for (int i = 2325; i < 2504; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(36 <= j && j <= 46);
		}
		for (int i = 2505; i < 2684; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(34 <= j && j <= 44);
		}
		for (int i = 2685; i < 2864; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(32 <= j && j <= 42);
		}
		for (int i = 2865; i < 3044; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(30 <= j && j <= 40);
		}
		for (int i = 3045; i < 3224; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(28 <= j && j <= 38);
		}
		for (int i = 3225; i < 3404; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(27 <= j && j <= 37);
		}
		for (int i = 3405; i < 3584; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(29 <= j && j <= 39);
		}
		for (int i = 3585; i < 3764; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(32 <= j && j <= 42);
		}
		for (int i = 3765; i < 3944; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(36 <= j && j <= 46);
		}
		for (int i = 3945; i < 4124; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(41 <= j && j <= 51);
		}
		for (int i = 4125; i < 4304; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(45 <= j && j <= 55);
		}
		for (int i = 4305; i < 4319; i++) {
			double j = (sense2.allTemperatureOut()).get(i);
			assertTrue(48 <= j && j <= 60);
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