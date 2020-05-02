import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Test the WeatherStatistics class.
 * @author Sean Robinson
 * @version 16APR2020
 */
public class WeatherStatisticsTest {

	/**The WeatherStatistics to test. */
	private WeatherStatistics myDisplay;
	
	/** The label text values to test. */
	private String[] myLabels;
	
	/** The text values of the myValue labels to test. */
	private String[] myValues;
	
	/**
	 * Instantiate the test fields.
	 */
	@BeforeEach
	public void setUp() {
		myDisplay = new WeatherStatistics();
		myLabels = new String[8];
		myValues = new String[8];
		for (int i = 0; i < 8; i++) {
			myLabels[i] = "" + i;
			myValues[i] = "" + i;
		}
	}
	
	/**
	 * Test the set and get methods for the forecast.
	 */
	@Test
	public void testForeCast() {
		myDisplay.setForeCast(ForeCast.SNOW);
		assertEquals(ForeCast.SNOW, myDisplay.getForeCast());
		myDisplay.setForeCast(ForeCast.RAIN);
		assertEquals(ForeCast.RAIN, myDisplay.getForeCast());
		myDisplay.setForeCast(ForeCast.MOSTLYCLEAR);
		assertEquals(ForeCast.MOSTLYCLEAR, myDisplay.getForeCast());
		myDisplay.setForeCast(ForeCast.PARTLYCLOUDY);
		assertEquals(ForeCast.PARTLYCLOUDY, myDisplay.getForeCast());
		myDisplay.setForeCast(ForeCast.MOSTLYCLOUDY);
		assertEquals(ForeCast.MOSTLYCLOUDY, myDisplay.getForeCast());
	}
	
	/**
	 * Test the set and get methods for the moon phase.
	 */
	@Test
	public void testMoonPhase() {
		myDisplay.setMoonPhase(MoonPhase.FIRSTQUARTER);
		assertEquals(MoonPhase.FIRSTQUARTER, myDisplay.getMoonPhase());
		myDisplay.setMoonPhase(MoonPhase.LASTQUARTER);
		assertEquals(MoonPhase.LASTQUARTER, myDisplay.getMoonPhase());
		myDisplay.setMoonPhase(MoonPhase.NEWMOON);
		assertEquals(MoonPhase.NEWMOON, myDisplay.getMoonPhase());
		myDisplay.setMoonPhase(MoonPhase.FULLMOON);
		assertEquals(MoonPhase.FULLMOON, myDisplay.getMoonPhase());
		myDisplay.setMoonPhase(MoonPhase.WAXINGGIBBOUS);
		assertEquals(MoonPhase.WAXINGGIBBOUS, myDisplay.getMoonPhase());
		myDisplay.setMoonPhase(MoonPhase.WANINGGIBBOUS);
		assertEquals(MoonPhase.WANINGGIBBOUS, myDisplay.getMoonPhase());
		myDisplay.setMoonPhase(MoonPhase.WAXINGCRESCENT);
		assertEquals(MoonPhase.WAXINGCRESCENT, myDisplay.getMoonPhase());
		myDisplay.setMoonPhase(MoonPhase.WANINGCRESCENT);
		assertEquals(MoonPhase.WANINGCRESCENT, myDisplay.getMoonPhase());
	}
	
	/**
	 * Test the set and get methods for the localdatetime.
	 * Test month, day, hour, minute, and second.
	 */
	@Test
	public void testDateTime() {
		for (int i = 0; i < 100; i++) {
			myDisplay.setDateTime();
			assertEquals(LocalDateTime.now().getMonth(), myDisplay.getDateTime().getMonth());
			assertEquals(LocalDateTime.now().getDayOfMonth(), myDisplay.getDateTime().getDayOfMonth());
			assertEquals(LocalDateTime.now().getHour(), myDisplay.getDateTime().getHour());
			assertEquals(LocalDateTime.now().getMinute(), myDisplay.getDateTime().getMinute());	
			assertEquals(LocalDateTime.now().getSecond(), myDisplay.getDateTime().getSecond());
		}
	}
	
	/**
	 * Test the set and get methods for the stats.
	 */
	@Test
	public void testStats() {
		myDisplay.setStats(myLabels, myValues);
		for (int i = 0; i < 8; i++) {
			assertEquals("" + i, myDisplay.getLabels()[i]);
			assertEquals("" + i, myDisplay.getValues()[i]);
		}
	}
	
	/**
	 * Test for an IllegalArgumentException in the setStats method.
	 */
	@Test
	public void testException() {
		//Argument length too small
		for (int i = 0; i < 8; i++) {
			myValues = new String[i];
			myLabels = new String[i];
			assertThrows(IllegalArgumentException.class, () -> {
				myDisplay.setStats(myLabels, myValues);
			});			
		}
		//Argument length too long
		for (int i = 9; i < 16; i++) {
			myValues = new String[i];
			myLabels = new String[i];
			assertThrows(IllegalArgumentException.class, () -> {
				myDisplay.setStats(myLabels, myValues);
			});			
		}
	}
	
}
