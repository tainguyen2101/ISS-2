package sensors;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class simulates a humidity sensor.
 * 
 * @author Benjamin Munoz
 * @author Ali Iftakhar
 */
public class HumiditySensor implements SensorInterface {
    
    private static final DecimalFormat HUM_FORMAT = new DecimalFormat("###");
    
    /**
     * The humidity inside the house
     */
    private double innerHum;
    
    /**
     * The humidity outside the house
     */
    private double outerHum;

    /**
     * The default constructor. The inner and outer humidity are set to
     * 10%.
     */
    
    /**
     * Keeps track of all the inner humidity values so far we've received.
     */
    private ArrayList<Double> innerHumArchieve;
    
    /**
     * Keeps track of all the outer humidity values so far we've received.
     */
    private ArrayList<Double> outerHumArchieve;
    
    
    public HumiditySensor() {
        this(10, 10);
        innerHumArchieve = new ArrayList<>();
        outerHumArchieve = new ArrayList<>();
    }
    
    /**
     * The primary constructor. The initial inner and outer humidty can be 
     * set.
     * 
     * An IllegalArgumentException will be thrown on the following conditions:
     * <ul>
     *      <li>in < 1 or in > 100</li>
     *      <li>out < 1 or out > 100</li>
     * </ul>
     * 
     * @param in -- the initial inner humidity
     * @param out -- the initial outer humidity
     */
    public HumiditySensor(double in, double out) {
        if (in < 1 || in > 100) {
            throw new IllegalArgumentException("The inner humidity must be in the interval [1,100]");
        }
        if (out < 1 || out > 100) {
            throw new IllegalArgumentException("The outer humidty must be in the interval [1,100]");
        }
        
        innerHum = in;
        outerHum = out;
        innerHumArchieve = new ArrayList<>();
        outerHumArchieve = new ArrayList<>();
    }
    
    public String getData() {
        innerHum += (Math.random() * 2) - 1;
        if (innerHum < 1) {
            innerHum = 1;
        } else if (innerHum > 100) {
            innerHum = 100;
        }
        innerHumArchieve.add(innerHum);
        
        outerHum += (Math.random() * 2) - 1;
        if (outerHum < 1) {
            outerHum = 1;
        } else if (outerHum > 100) {
            outerHum = 100;
        }
        outerHumArchieve.add(outerHum);
        
        String inString = HUM_FORMAT.format(innerHum);
        String outString = HUM_FORMAT.format(outerHum);
        
        return "{innerHumidty: " + inString + "%, outerHumidity: " + outString + "%}";
    }
    
    /**
	 * This returns all the innerHum values stored as an a sorted ArrayList.
	 * @return ArrayList<> values ofinnerHum in sorted form.
	 */
	public ArrayList<Double> allInnerHum() {
		Collections.sort(innerHumArchieve);
		return innerHumArchieve;
	}
	
	/**
	 * This returns all the outerHum values stored as an a sorted ArrayList.
	 * @return ArrayList<> values of outerHum in sorted form.
	 */
	public ArrayList<Double> allOuterHum() {
		Collections.sort(outerHumArchieve);
		return outerHumArchieve;
	}
    
    
}