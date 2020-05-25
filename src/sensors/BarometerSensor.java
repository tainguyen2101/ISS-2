package sensors;

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