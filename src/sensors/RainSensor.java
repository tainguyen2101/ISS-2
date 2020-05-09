package sensors;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;


/**
 * This class simulates a rain sensor.
 * 
 * @author Benjamin Munoz
 * @author Ali Iftakhar
 * @version 4/5/2020
 */

public class RainSensor implements SensorInterface {
    /**
     * The rainfall amount format
     */
    private static final DecimalFormat RAINFALL_FORMAT = new DecimalFormat("###.##\"");
    
    /**
     * The amount of rainfall in inches
     */
    private double currentRainfall;
    
    /**
     * Keeps track of all the values of rainfall.
     */
    
    private ArrayList<Double> currentRainfallArchieve;
    
    /**
     * The default constructor
     */
    public RainSensor() {
        currentRainfall = 0;
        currentRainfallArchieve = new ArrayList<>();
    }
    
    /**
     * The primary constructor. The initial amount, in inches, of rainfall can be set
     * 
     * An IllegalArgumentException will be thrown if curr is negative.
     * 
     * @param curr -- the initial rainfall amount in inches
     */
    public RainSensor(double curr) {
        if (curr < 0) {
            throw new IllegalArgumentException("The amount of rainfall cannot be negative");
        }
        
        currentRainfall = curr;
        currentRainfallArchieve = new ArrayList<>();
    }
    
    /**
     * Returns the amount of rainfall as a string
     */
    public String getData() {
        if (Math.random() > 0.9) {
            currentRainfall = Math.random() * 0.04;
        } else {
            currentRainfall = 0;
        }
        currentRainfallArchieve.add(currentRainfall);
        return "{rainfall: " + RAINFALL_FORMAT.format(currentRainfall) + "}";
    }
    
    /**
	 * This returns all the rainfall values stored as an a sorted ArrayList.
	 * @return ArrayList<> values of rainfall in sorted form.
	 */
    public ArrayList<Double> allCurrentRainfall() {
    	Collections.sort(currentRainfallArchieve);
    	return currentRainfallArchieve;
    }
    
    /**
     * Getter method for testing to get size of currentRainfallArchieve.
     * @return size of currentRainfallArchieve.
     */
    public int getCRASize() {
    	return currentRainfallArchieve.size();
    }

}