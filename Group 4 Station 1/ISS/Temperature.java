package ISS;
 
 /**
 * Temperature Sensor. Contains temperature value.
 */
public class Temperature extends Sensor{
    // Temperature value is the default data field (myData)

    /**
     * Main public constructor.
     * 
     * @param theTemp
     */
    public Temperature(final double theTemp) {
        super(theTemp);
    }

    /**
     * Get the temperature value. Same effect as getData()
     * 
     * @return temperature value
     */
    public double getMyTemp() {
        return getData();
    }
}