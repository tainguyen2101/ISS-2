import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.DecimalFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the MainConsole class.
 * @author SeanRobinson
 * @version 16APR2020
 */
public class MainConsoleTest {
	
	/** Format for the temperature values. */
	private DecimalFormat myDecimalFormat;
	
	/**
	 * Set up the decimal formatter.
	 */
	@BeforeEach
	public void setUp() {
		myDecimalFormat = new DecimalFormat("####.##");
	}
	
	/**
	 * Test the temperature conversion methods.
	 */
	@Test
	public void testTempConversion() {
		for (int i = 0; i < 100; i++) {
			assertEquals(myDecimalFormat.format((double) ((i - 32) / 1.8)), MainConsole.convertToCelcius(i + ""));
			assertEquals("" + (int) Math.round((i * 1.8 + 32)), MainConsole.convertToFahrenheit(i + ""));
		}
	}
	
}
