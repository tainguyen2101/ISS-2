import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the MessageDisplay class.
 * @author Sean Robinson
 * @version 16APR2020
 */
public class MessageDisplayTest {

	/** The MessageDisplay to test */
	private MessageDisplay myDisplay;
	
	/**
	 * Set up the display.
	 */
	@BeforeEach
	public void setUp() {
		myDisplay = new MessageDisplay("test");
	}
	
	/**
	 * Test for the correct message.
	 */
	@Test
	public void messageTest() {
		assertEquals("test", myDisplay.getMessage());
		for (int i = 0; i < 10; i++) {
			myDisplay.setMessage("test" + i);
			assertEquals("test" + i, myDisplay.getMessage());
		}		
	}
	
}
