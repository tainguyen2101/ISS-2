package ISS;

/**
 * The parent of all sensor classes.
 * 
 * @author Victor Chau
 * @version 4/14/2020
 */
public abstract class Sensor {
    /** Default data field for a sensor. */
    protected double myData;
    
    /**
     * Default constructor of the class. Set the data field to 0.
     */
    public Sensor() {
        myData = 0;
    }
    
    /**
     * Constructor that initialize the first field of sensor.
     * 
     * @param data as a double
     */
    public Sensor(double data) {
        setData(data);
    }
    
    /**
     * Return the current default data in sensor.
     * 
     * @return the double value of data.
     */
    public double getData() {
        return myData;
    }
    
    /**
     * Set default data field.
     * 
     * @param data
     */
    public void setData(double data) {
        myData = data;
    }
}
