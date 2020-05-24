package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Generator.RandomSensorDataGenerator;

class RandomSensorDataGeneratorTest {
	RandomSensorDataGenerator testGen = new RandomSensorDataGenerator();
	
	@Test
	void testSetInitialOutValues() {
		testGen.setInitialOutValues();
		assertEquals(1200, RandomSensorDataGenerator.milTime, "milTime initialized incorrectly");
		assertEquals(5, RandomSensorDataGenerator.mywindDirection, "wind direction initialized incorrectly");
		assertEquals(5, RandomSensorDataGenerator.myWindSpeed, "wind speed initialized incorrectly");
		assertEquals(700, RandomSensorDataGenerator.myTempOut, "outside temperature initialized incorrectly");
		assertEquals(600, RandomSensorDataGenerator.myHumOut, "outside humidity initialized incorrectly");
		assertEquals(300, RandomSensorDataGenerator.myBarometer, "barometer initialized incorrectly");
		assertEquals(100, RandomSensorDataGenerator.myRainRate, "rain rate initialized incorrectly");
	}
	
	@Test
	void testSetInitialInValues() {
		testGen.setInitialInValues();
		assertEquals(800, RandomSensorDataGenerator.myTempIn, "inside temperature initialized incorrectly");
		assertEquals(600, RandomSensorDataGenerator.HumIn, "inside humidity initialized incorrectly");
	}
	
	@Test
	void testCreateISSData() {
		testGen.setInitialOutValues();
		testGen.createISSData();
		assertTrue(RandomSensorDataGenerator.milTime >=  0 && RandomSensorDataGenerator.milTime <= 2400, "milTime not in specified range");
		assertTrue(RandomSensorDataGenerator.mywindDirection >=  0 && RandomSensorDataGenerator.mywindDirection <= 360, "wind direction not in specified range");
		assertTrue(RandomSensorDataGenerator.myTempOut >=  0 && RandomSensorDataGenerator.myTempOut <= 1400, "outside temperature not in specified range");
		assertTrue(RandomSensorDataGenerator.myHumOut >=  0 && RandomSensorDataGenerator.myHumOut <= 1000, "outside humidity not in specified range");
		assertTrue(RandomSensorDataGenerator.myBarometer >=  259 && RandomSensorDataGenerator.myBarometer <= 320, "barometer not in specified range");
		assertTrue(RandomSensorDataGenerator.myRainRate >=  0 && RandomSensorDataGenerator.myRainRate <= 2000, "rain rate not in specified range");
	}
	
	@Test
	void testCreateEnvoyData() {
		testGen.setInitialInValues();
		testGen.createEnvoyData();
		assertTrue(RandomSensorDataGenerator.myTempIn >=  0 && RandomSensorDataGenerator.myTempIn <= 1400, "inside temperature not in specified range");
		assertTrue(RandomSensorDataGenerator.HumIn >=  0 && RandomSensorDataGenerator.HumIn <= 1000, "inside humidity not in specified range");
	}
	
	@Test
	void testPrintDataToConsole() {
		testGen.setInitialInValues();
		testGen.setInitialOutValues();
		testGen.createISSData();
		testGen.createEnvoyData();
		testGen.printDataToConsole();
	}
}
