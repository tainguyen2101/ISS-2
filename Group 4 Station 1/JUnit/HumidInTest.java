package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Envoy.HumidIn;

class HumidInTest {
	HumidIn test = new HumidIn(1500);
	
	@Test
	void test() {
		assertEquals(1500, test.getMyHumid(), "inside humidity constructor getter/initialization is incorrect");
	}

}
