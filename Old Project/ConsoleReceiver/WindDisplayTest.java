import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the WindDisplay class.
 * @author Sean Robinson
 * @version 16APR2020
 */
public class WindDisplayTest {

	/** The WindDisplay to test. */
	private WindDisplay myDisplay;
	
	/**
	 * Instantiate the WindDisplay.
	 */
	@BeforeEach
	public void setUp() {
		myDisplay = new WindDisplay("100", Math.PI);
	}
	
	/**
	 * Test the set and get methods for wind speed and direction.
	 * Test the calculateDirection method for proper x,y values.
	 */
	@Test
	public void testSpeedDirection() {
		assertEquals("100", myDisplay.getSpeed());
		assertEquals(Math.PI, myDisplay.getDirection());
		
		for (int i = 0; i < 100; i++) {
			myDisplay.setSpeedDirection("" + i, (double) i);
			assertEquals("" + i, myDisplay.getSpeed());
			assertEquals((double) i, myDisplay.getDirection());
			assertEquals((int) (110 * Math.cos(i)), (int) myDisplay.getWindXY().x);
			assertEquals((int) (-110 * Math.sin(i)), (int) myDisplay.getWindXY().y);
		}
	}
	
}
