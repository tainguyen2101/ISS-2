package data;

import java.io.Serializable;
import java.util.Random;

/**
 * This class represents a barometer, taking pressure readings and height readings.
 * Updated code to allow for static value unit testing
 * @author Alex Amado
 * @version Spring 2020
 *
 * Updated code to produce more realistic data.
 * @author Dean Kelley
 */
public class Barometer implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8317334203445694092L;
    /** Random object for random data generation. */
    private static final Random RANDOM = new Random();
    /** Represents air pressure of a specific environment in inHg. */
    private double myPressure;

    public Barometer() {
        myPressure = res.R.Doubles.PRESSURETEST;
    }

    /**
     * Randomly nudges pressure between +/-0.1 inHG at a time.
     * @param theToggleStaticValue - boolean for unit testing static value 
     */
    public void setPressure(final boolean theToggleStaticValue) {
        if (theToggleStaticValue) {
            myPressure = res.R.Integers.THIRTY;
        } else {
            myPressure += (RANDOM.nextDouble() - res.R.Doubles.ZPFIVE) / res.R.Integers.TEN;
            if (myPressure > res.R.Integers.THRONE) {
                myPressure = res.R.Integers.THRONE;
            } else if (myPressure < res.R.Integers.TWEIGHT) {
                myPressure = res.R.Integers.TWEIGHT;
            }
            myPressure = round(myPressure, 2);
        }
    }

    /**
     * For testing purposes.
     * @param theVal - pressure
     */
    public void setPressureValue(final double theVal) {
        myPressure = theVal;
    }

    /**
     * Returns pressure.
     * @return pressure
     */
    public double getPressure() {
        return myPressure;
    }

    /**
     * Rounds to nearest desired tens place.
     * @param theVal - value to round
     * @param thePlace desired tens place
     * @return pressure
     */
    public static double round(final double theVal, final int thePlace) {
        return Math.round(theVal * Math.pow(res.R.Integers.TEN, thePlace)) / Math.pow(res.R.
                Integers.TEN, thePlace);
    }
}
