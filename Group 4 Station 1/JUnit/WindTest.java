package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ISS.Wind;

class WindTest {
	Wind test = new Wind(250, 22);

	@Test
	void testConstructor() {
		assertEquals(250, test.getMyDirection(), "wind direction not initialized correctly");
		assertEquals(22, test.getMyWindSpeed(), "wind speed not initialized correctly");
	}
	
	@Test
	void testSetMyDirection() {
		test.setMyDirection((120));
		assertEquals(120, test.getMyDirection(), "wind direction setter failure");
	}
	
	@Test
	void testSetMyStringDirection() {
		test.setMyStringDirection("N");
		assertEquals("N", test.getMyStringDirection(), "string direction setter failure");
	}
	
	@Test
	void testGetMyCompass() {
		assertEquals(null, test.getMyCompass(), "compass getter method is incorrect");
	}

}
