package ISS;
/**
 * Wind Sensor. Contains wind speed and wind direction.
 */
public class Wind extends Sensor{

    // Wind speed is the first data field (myData)

    /** Wind direction of sensor. */
    private int myDirection;
    
    /** Wind direction as a string. */
    private String myStringDirection;
     
    /** Compass */
    private String myCompass;

    /**
     * Main public constructor of wind sensor. Initialize wind speed and direction.
     * 
     * @param theDirection
     * @param theWindSpeed
     */
    public Wind(final int theDirection, final int theWindSpeed) {
        setData(theWindSpeed);
        this.myDirection = theDirection;
    }
    
    /**
     * Set wind direction value.
     * 
     * @param myDirection wind direction as an integer
     */
    public void setMyDirection(final int myDirection) {
        this.myDirection = myDirection;
    }
    
    /**
     * Return the direction of wind.
     * 
     * @return wind direction integer value
     */
    public int getMyDirection() {
        return this.myDirection;
    }
    
    /**
     * Return the direction of wind as a String.
     * 
     * @return wind direction string
     */
    public String getMyStringDirection() {
        return myStringDirection;
    }
    
    /**
     * Set the wind direction string.
     * 
     * @param myStringDirection
     */
    public void setMyStringDirection(final String myStringDirection) {
        this.myStringDirection = myStringDirection;
    }
    
    /**
     * Get the wind speed. Same effect as getData().
     * 
     * @return integer wind speed
     */
    public int getMyWindSpeed() {
        return (int) getData();
    }
    
    /**
     * Return the compass value.
     * 
     * @return compass string
     */
    public String getMyCompass() {
        return myCompass;
    }
}