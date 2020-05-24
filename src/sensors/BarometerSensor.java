package sensors;

/**
 * Barometer Sensor class.
 * @author Ford Nguyen
 */
public class BarometerSensor implements SensorInterface{

    private double myBaro;
    public BarometerSensor(Double theBaro) {
        myBaro = theBaro;
    }
    @Override
    public String getData() {
        return "{Barometer: " + myBaro + "}";
    }
    
}