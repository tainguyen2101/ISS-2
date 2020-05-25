/**
 * 
 */
package WSAdapter;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.jupiter.api.Test;


/**
 * @author tranm
 *
 */
class WS5AdapterTest {
	
	private String myTestString = "Rainfall (inches): 145";
	
    /**
     * Setup method called before every test method.
     */
    @Before
    public void setUp() {
    	
    }

	/**
	 * Test method for {@link Adapter.WS5Adapter#generateData()}.
	 */
	@Test
	void testGenerateData() throws IOException {
		final WS5Adapter envoy = new WS5Adapter();
		envoy.generateData();
		assertTrue(Files.isReadable(Path.of("WeatherStation5.txt")));
		assertTrue(Files.isReadable(Path.of("WeatherStation5Inside.txt")));
	}
	
	

	/**
	 * Test method for {@link Adapter.WS5Adapter#filterWord(java.lang.String)}.
	 */
	@Test
	void testFilterWord() {
		assertEquals("Filter Word failed", 145, WS5Adapter.filterWord(myTestString));
	}

}
