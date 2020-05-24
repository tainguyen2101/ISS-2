package ISS;
/**
 * Rain sensor. Contains rain data.
 */
public class Rain extends Sensor{
    // rain value is the default data field (myData)
    
    /**
     * Main public constructor.
     * 
     * @param theRainRate
     */
    public Rain(final double theRainRate) {
        super(theRainRate);
    }
    
    /**
     * Return the rain rate. Same effect as getData()
     * 
     * @return the rain rate
     */
    public double getMyRainRate() {
        return getData();
    }
}