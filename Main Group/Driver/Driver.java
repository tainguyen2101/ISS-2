/*
 * Group 2
 * 5/20/20
 * TCSS 360 Software Development
 * Professor Dincer
 */

package Driver;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Driver class.
 * 
 * @author Ford Nguyen
 */
public class Driver {
	
    /**
     * Main method for this application.
     * 
     * @param theArgs argument for main method.
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        new Station();
        GUI test = new GUI();
        while (true) {
            test.collectWSData(9876);
            test.collectWSData(9877);
            test.collectWSData(9878);
            test.collectWSData(9879);
            test.collectWSData(9880);
        }
        
    }

}