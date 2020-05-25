package ISS;
/**
 * Humidity sensor. Contains humidity value.
 */
public class Humidity extends Sensor{
    // The humidity is the default field (myData)
    
    /**
     * Main public constructor.
     * 
     * @param theHumid
     */
    public Humidity(final double theHumid) {
        super(theHumid);
    }
    
    /**
     * Return the humidity. Same effect as getData()
     * 
     * @return humidity
     */
    public double getMyHumid() {
        return getData();
    }
}