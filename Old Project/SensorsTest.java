import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * @author Brandon Francis
 * @version April 2020
 */
class SensorsTest {
	
	/**
	 * Generates 10 random wind speeds and makes sure 
	 * they are within the proper range.
	 */
	@Test
	void testGetWindSpeed() {
		for (int i = 0; i < 10; i++) {
			int windSpeed = Sensors.getWindSpeed();
			assertTrue(windSpeed < 40 && windSpeed >= 10);
		}
	}
	
	/**
	 * Generates 20 random wind directions and makes sure
	 * they are all valid directions.
	 */
	@Test
	void testGetWindDirection() {
		String[] directions = { "North", "East", "South", "West", "Northeast", "Northwest", "Southeast", "Southwest" };
		List<String> list = Arrays.asList(directions);
		for (int i = 0; i < 20; i++) {
			String windDirection = Sensors.getWindDirection();
			assertTrue(list.contains(windDirection));
		}
	}
	
	/**
	 * Generates 10 random temperatures and checks
	 * that they are between 30 and 79 (inclusive).
	 */
	@Test
	void testGetTemperature() {
		for (int i = 0; i < 10; i++) {
			int temperature = Sensors.getTemperature();
			assertTrue(temperature < 80 && temperature >= 30);
		}
	}
	
	/**
	 * Generates 10 random humidities and checks
	 * that they are between 20 and 49 (inclusive).
	 */
	@Test
	void testGetHumidity() {
		for (int i = 0; i < 10; i++) {
			int humidity = Sensors.getHumidity();
			assertTrue(humidity < 50 && humidity >= 20);
		}
	}
	
	/** Generates 10 random barometric pressures and checks
	 * that they are between 0 and 99 (inclusive).
	 */
	@Test
	void testGetBar() {
		for (int i = 0; i < 10; i++) {
			int bar = Sensors.getBar();
			assertTrue(bar < 100 && bar >= 0);
		}
	}
	
	/** Generates 10 random daily rainfalls and checks
	 * that they are between 0 and 1 (inclusive).
	 */
	@Test
	void testGetRain() {
		for (int i = 0; i < 10; i++) {
			int rain = Sensors.getRain();
			assertTrue(rain < 2 && rain >= 0);
		}
	}

}
