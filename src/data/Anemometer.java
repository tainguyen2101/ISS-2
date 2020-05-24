package data;

import java.io.Serializable;
import java.util.Random;

/**
 * This class represents a the wind data, taking wind speed and direction readings.
 * Updated code to allow for static value unit testing
 * @author Alex Amado
 * @version Spring 2020
 *
 * Updated code to produce more realistic data.
 * @author Dean Kelley
 * 
 * Added method to set wind angle for testing
 * @author Yolanda Xu
 * 
 */
public class Anemometer implements Serializable {
    /**
     * Generated serial ID.
     */
    private static final long serialVersionUID = 2309683871792615107L;
    /** Random object for random data generation. */
    private static final Random RANDOM = new Random();
    /** Represents wind speed of a specific environment. */
    private int myWindSpeed;
    /** Represents wind direction of a specific environment. */
    private final String[] myWindDirection;
    /** Represends wind direction in degrees. */
    private int myWindAngle;
    /** Represents index of wind dorection array. */
    private int myWindIndex;

	
	/**
	 * Constructs an anemometer object which initializes fields to random values.
	 */
    public Anemometer() {
        myWindSpeed = 0;
        myWindAngle = 0;
        myWindIndex = 0;
        myWindDirection = new String[]{"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
    }
	
    /**
     * Randomly nudges wind direction between +/-res.R.Integers.TEN degrees at a time
     * Also adjusts wind speed +/-1 MPH at a time.
     * @param theToggleStaticValue - boolean for unit testing static value 
     */
    public void setWind(final boolean theToggleStaticValue) {
        if (theToggleStaticValue) {
            myWindSpeed = res.R.Integers.WINDSPEEDTEST;
        } else {
            myWindSpeed += RANDOM.nextInt(res.R.Integers.THREE) - res.R.Integers.ONE;
            if (myWindSpeed < 0) {
                myWindSpeed = 0;
            } else if (myWindSpeed > res.R.Integers.WINDSPEEDTEST) {
                myWindSpeed = res.R.Integers.WINDSPEEDTEST;
            }
            myWindAngle += RANDOM.nextInt(res.R.Integers.TWENTY) - res.R.Integers.TEN;
            if (myWindAngle > res.R.Integers.PIDEG) {
                myWindAngle  = -res.R.Integers.PIDEG + (myWindAngle % res.R.Integers.PIDEG);
            } else if (myWindAngle < -res.R.Integers.PIDEG) {
                myWindAngle  = res.R.Integers.PIDEG + (myWindAngle % res.R.Integers.PIDEG);
            }
            if ((myWindAngle >= -res.R.Integers.NN && myWindAngle <= 0) || (myWindAngle >= 0 
					&& myWindAngle <= res.R.Integers.NN)) {
                myWindIndex = 0;
            }
            if (myWindAngle > res.R.Integers.NN && myWindAngle <= res.R.Integers.NEW) {
                myWindIndex = res.R.Integers.ONE;
            }
            if (myWindAngle > res.R.Integers.NEW && myWindAngle <= res.R.Integers.SEW) {
                myWindIndex = res.R.Integers.TWO;
            }
            if (myWindAngle > res.R.Integers.SEW && myWindAngle <= res.R.Integers.SS) {
                myWindIndex = res.R.Integers.THREE;
            }
            if ((myWindAngle > res.R.Integers.SS && myWindAngle <= res.R.Integers.PIDEG) || (myWindAngle < -res.R.Integers.SS 
					&& myWindAngle >= -res.R.Integers.PIDEG)) {
                myWindIndex = res.R.Integers.FOUR;
            }
            if (myWindAngle > -res.R.Integers.SS && myWindAngle <= -res.R.Integers.SEW) {
                myWindIndex = res.R.Integers.FIVE;
            }
            if (myWindAngle > -res.R.Integers.SEW && myWindAngle <= -res.R.Integers.NEW) {
                myWindIndex = res.R.Integers.SIX;
            }
            if (myWindAngle > -res.R.Integers.NEW && myWindAngle < -res.R.Integers.NN) {
                myWindIndex = res.R.Integers.SEVEN;
            }
        }
    }
	
	/**
	 * Method for testing the windSpeed.
	 * @param theSpeed
	 */
    public void setWindSpeed(final int theSpeed) {
        myWindSpeed = theSpeed;
    }
	
	/**
	 * Method for testing the windAngle.
	 * @param theAngle
	 */
    public void setWindAngle(final int theAngle) {
        myWindAngle = theAngle;
    }
	
	/**
	 * Method for testing the windIndex.
	 * @return wind index
	 */
    public int getWindIndex() {
        return myWindIndex;
    }
	
	/**
	 * Returns the wind speed of a surrounding area.
	 * @return the wind speed of a surrounding area.
	 */
    public int getWindSpeed() {
        return myWindSpeed;
    }
	
	/**
	 * Returns the wind direction of a certain area.
	 * @return the wind direction of a certain area.
	 */
    public String getWindDirection() {
        return myWindDirection[myWindIndex];
    }
}
