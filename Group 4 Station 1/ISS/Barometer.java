package ISS;
/**
 * Barometer (sensor). Contains value of values of elevation and pressure.
 */
public class Barometer extends Sensor{

   /*  private static double READ_ACCURACY = 0.03;

    private static double EQUATION_ACCURACY = 0.01;

    private static int ELEVATION_ACCURACY = 10;

    private static double OVERALL_ACCURACY = 0.04; */
    
    /** The elevation of the Barometer. */
    private int myElevation;

    // private double myBaroPressure;
    
    
    // The default data field (myData) is the pressure.

    /**
     * Main public constructor.
     * 
     * @param theBaroPressure
     */
    public Barometer(final double theBaroPressure) {
        super(theBaroPressure);
    }
    
    /**
     * Return the elevation of Barometer. 
     * 
     * @return elevation
     */
    public int getMyElevation() {
        return myElevation;
    }
    
    /**
     * Set the elevation of Barometer.
     * 
     * @param myElevation 
     */
    public void setMyElevation(int myElevation) {
        this.myElevation = myElevation;
    }

    /**
     * Return pressure value of barometer. Same effect as getData()
     * 
     * @return pressure of barometer
     */
    public double getMyBaroPressure() {
        return getData();
    }
    
    /**
     * Set pressure value of barometer. Same effect as setData()
     * 
     * @param myBaroPressure
     */
    public void setMyBaroPressure(double myBaroPressure) {
        setData(myBaroPressure);
    }
}