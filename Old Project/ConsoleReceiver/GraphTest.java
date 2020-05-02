import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Test the Graph class.
 * @author Sean Robinson
 * @version 16APR2020
 */
public class GraphTest {

	/** The graph to test. */
	private Graph myGraph;
	
	/** A double used for testing. */
	private double[] myDouble;
	
	/**
	 * Instantiate the graph.
	 */
	@BeforeEach
	public void setUp() {
		myGraph = new Graph();
	}
	
	/**
	 * Test the setStats, getStats, and getMessage methods.
	 */
	@Test
	public void statsTest() {
		assertEquals(" ", myGraph.getMessage());
		for (int i = 1; i < 25; i++) {
			myDouble = new double[25];
			System.out.println(myDouble.length);
			myDouble[24] = i;
			myGraph.setStats(myDouble, "test" + i, i);
			assertEquals("test" + i, myGraph.getMessage());
			assertEquals(170, myGraph.getStats()[24]);
		}
	}
	
	/**
	 * Test for an IllegalArgumentException in setMessage().
	 */
	@Test
	public void exceptionTest() {
		//Argument length too small
		for (int i = 1; i < 25; i++) {
			myDouble = new double[i];
			assertThrows(IllegalArgumentException.class, () -> {
				myGraph.setStats(myDouble, "test", 25);
			});
		}
		//Argument length too large
		for (int i = 26; i < 50; i++) {
			myDouble = new double[i];
			assertThrows(IllegalArgumentException.class, () -> {
				myGraph.setStats(myDouble, "test", 25);
			});
		}	
	}
	
}
