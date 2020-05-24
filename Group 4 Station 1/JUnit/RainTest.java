package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ISS.Rain;

class RainTest {
	Rain testRain = new Rain(12.0);

	@Test
	void testConstructor() {
		assertEquals(12.0, testRain.getMyRainRate());
	}

}
