/*
 * Nicolas Roberts
 * 5/23/20
 * Group Prj 2
 * TCSS 360
 */

package WSAdapter;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

/**
 * Test Cases for the Weather Station Adapter 1.
 * @author Nicol
 *
 */
public class WS1AdapterTest {
	
	
	/**
	 * WS1Adapter instance.
	 */
	private WS1Adapter myAdap;
	
	/**
	 * Scanner for created files.
	 */
	Scanner mySc = null;
	
	/**
	 * Parse file's fields.
	 */
	String[] myStrArray;
	
	

    /**
     * Creating instances of item to use throughout JUnit testing. Runs before every test case.
     */
	@Before
	public void setUp() {
		myAdap = new WS1Adapter();
		myAdap.generateData();
		mySc = null;
		try {
			mySc = new Scanner(new File("WeatherStation1.txt"));
		} catch (Exception e) {
			
		}	

		String line = mySc.nextLine();
		myStrArray = line.split(" ");
  }
	

	/**
	 * Testing windspeed.
	 */
	@Test 
	public void testWindspeed() {
		
		
		assertTrue(Integer.parseInt(myStrArray[0]) > 0 && Integer.parseInt(myStrArray[0]) < 180);
				
	}
	
	/**
	 * Testing winddirection.
	 */
	@Test 
	public void testWinddirection() {
		
		assertTrue(Integer.parseInt(myStrArray[1]) > -1 && Integer.parseInt(myStrArray[1]) < 361);	
	}
	
	/**
	 * testing temp
	 */
	@Test 
	public void testTemp() {
		
		assertTrue(Integer.parseInt(myStrArray[2]) > 10 && Integer.parseInt(myStrArray[2]) < 1000);
	}
	
	/**
	 * testing humidity
	 */
	@Test 
	public void testHum() {
		
		assertTrue(Integer.parseInt(myStrArray[3]) > 400 && Integer.parseInt(myStrArray[3]) < 700);
	}
	
	/**
	 * Testing barometric pressure
	 */
	@Test 
	public void testBar() {
		
		assertTrue(Integer.parseInt(myStrArray[4]) > 100 && Integer.parseInt(myStrArray[4]) < 400);
	}
	
	/**
	 * test rain.
	 */
	@Test 
	public void testRain() {
		
		assertTrue(Integer.parseInt(myStrArray[5]) > -1 && Integer.parseInt(myStrArray[5]) < 11);
	
	}
	
	/**
	 * test temperature inside.
	 */
	@Test 
	public void testTempIn() {
		for (int i = 0; i < 90; i++ ) {
			assertTrue((myAdap.getTempIn() > 599) && myAdap.getTempIn() < 751);
		}		
	}
	
	/**
	 * test humidity inside.
	 */
	@Test 
	public void testHumIn() {
		for (int i = 0; i < 90; i++ ) {
			assertTrue((myAdap.getHumIn() > 349) && myAdap.getHumIn() < 551);
		}		
	}
	
  
}



	
