package WSAdapter;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WS2AdapterTest {

	@Test
	void testGenerateData() {
		fail("Not yet implemented");
	}

	@Test
	void testGetRandomNumberInRange() {
		   WS2Adapter adapter2 = new WS2Adapter();
		   int temp;
		   boolean bool = false;

		   
	       temp = adapter2.getRandomNumberInRange(0, 100);
	        
	        if (0 < temp && temp < 100) {
	        	bool = true;
	        }
	      
	       assertTrue("This works",bool);
	        
	}

}
