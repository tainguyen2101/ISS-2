package data;

import java.io.Serializable;

/**
 * This class simulates the relative humidity based on a res.R.Integers.HDAY-hour sinusoid and the 
 * Seattle's average daily humidity.
 * Added boolean to allow static value for unit testing.
 * @author Dean Kelley
 * @version Spring 2020
 */
public class RelHumidity implements Serializable {

    /**
     * Generated Serial ID.
     */
    private static final long serialVersionUID = -2865338597916659569L;

    /** Simulated humidity value. */
    private int myHumidity;

    /**
     * Public constructor for initializing object instance.
     */
    public RelHumidity() {
        myHumidity = 0;
    }

    /**
     * Uses res.R.Integers.HDAY-hour sinusoid to determine Relative Humidity.
     * @param theToggleStaticValue - test boolean
     */
    public void setHumidity(final boolean theToggleStaticValue) {
        if (theToggleStaticValue) {
            myHumidity = res.R.Integers.HUMIDITYTEST;
        } else {
            final int secInDay = res.R.Integers.HDAY * res.R.Integers.SIXTY 
                    * res.R.Integers.SIXTY;
            final int timeSec = java.time.LocalTime.now().toSecondOfDay();
            myHumidity = (int) (res.R.Integers.BHUM + Math.sin(2. * Math.PI 
                    * (double) timeSec / secInDay) * res.R.Integers.HFDAY);
        }
    }
    
    /**
     * 
     * @return humidity value
     */
    public int getHumidity() {
        return myHumidity;
    }
}
