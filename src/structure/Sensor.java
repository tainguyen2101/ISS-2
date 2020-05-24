package structure;
import java.util.Random;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
/**
 * Program Description: Sensor class generates data for the V2SensorSuite.
 * @author Jess Smith
 * @author Leika Yamada
 * @author Jacob Sousie
 * 
 * @version 4.10.20
 * */
public class Sensor implements Runnable{ 
    private double theTemperature = -125;
    private double theHumidity = 55;
    private double theWind = 102;
    private double theRain = 9;

    protected double[] theArr;
    
    /**
     * Special Sensor constructor to be called by the ISS class to 
     * transfer sensor data from the Sensor to the ISS.
     * Resets theRain amount for new day.
     * Sets theTemperature,theHumidity, and theWind based off most recent sensor.
     * @param myTemperature theTemperature from most recent sensor.
     * @param myHumidity theHumidity from most recent sensor.
     * @param myWind theWind from most recent sensor.
     * @param myArr the ISS array to which data will be passed.
     */
    public Sensor(double[] myArr) {
    	
    	// Assign new sensor readings based off parameters
    	theArr = myArr;
    }

    /**
     * Data chosen based off weather at Jess's house.
     * To be used as initial data point.
     */
    public Sensor() {
        theTemperature = 60;
        theHumidity = 44;
        theWind = 10;
        theRain = 0;  
    }
    
    /**
     * Meant to provide a thread of data that makes sense together.
     * @param currentData the most recent sensor
     */
    public Sensor(Sensor currentData) {
    	// Assign new sensor readings based off of currentData's.
    	theTemperature = generateNotRainData(currentData.getTheTemperature());
    	theHumidity = generateNotRainData(currentData.getTheHumidity());
    	theWind = generateNotRainData(currentData.getTheWind());
    	theRain = generateRainData(currentData.getTheRain());
    	
    }
    
    /**
     * Resets theRain amount for new day.
     * Sets theTemperature,theHumidity, and theWind based off most recent sensor.
     * @param myTemperature theTemperature from most recent sensor.
     * @param myHumidity theHumidity from most recent sensor.
     * @param myWind theWind from most recent sensor.
     */
    public Sensor(double myTemperature, double myHumidity, double myWind) {
    	// Assign new sensor readings based off parameters
    	theTemperature = generateNotRainData(myTemperature);
    	theHumidity = generateNotRainData(myHumidity);
    	theWind = generateNotRainData(myWind);
    	// Reset theRain to 0 for new day
    	theRain = 0;
    }
    /**This method gets the array inside sensor. 
     * @ return the sensor array;
     * */
    public double[] getTheArray() {
    	return theArr;
    }

	public double getTheTemperature() {
		return theTemperature;
	}

	public double getTheHumidity() {
		return theHumidity;
	}

	public double getTheWind() {
		return theWind;
	}

	public double getTheRain() {
		return theRain;
	}
	
	public String toString() {
		return "Temperature: " + this.getTheTemperature() 
		+ " Humidity: " + this.getTheHumidity() + " Wind: " + this.getTheWind() 
		+ " Rain: " + this.getTheRain();
		
	}
	
	/*
	 * Generates data for all sensors besides rain.
	 * Rain cannot decrease in a day.
	 */
	public double generateNotRainData(double theData) {
		Random random = new Random();
		
		// Determines if data will increase or decrease
    	boolean boolDirection = random.nextBoolean();
    	int direction;
    	if (boolDirection) {
    		direction = 1;
    	} else {
    		direction = -1;
    	}
    	
    	// Determines magnitude of data change
    	// Allows no more larger change than 1%
    	int change = random.nextInt(11);
    	
    	if (theData != 0.0) {
			return round((theData * (direction * change / 1000.0) + theData), 2);
		} else {
			return round((direction * change / 1000.0 + theData), 2);
		}
	}
	
	public double generateRainData(double theData) {
		Random random = new Random();
		
		// Determines if rain will increase (T) or stay the same (F)
		boolean willChange = random.nextBoolean();
    	if (!willChange) {
			return round(theData, 2);
		} else {
			// Allows increase of no more than 0.01
			return round((random.nextInt(11) / 1000.0 + theData), 2);
		}
	}
	//changes 4/10/2020 Leika
	/**
	 * This method contains the run method for the thread that 
	 * will control the sensor class. It generates new data
	 * and sends it to the ISS.
	 * */
	public void run() {
		
		
		double theTemperature = generateNotRainData(getTheTemperature());
    	double theHumidity = generateNotRainData(getTheHumidity());
    	double theWind = generateNotRainData(getTheWind());
    	// Reset theRain to 0 for new day
    	double theRain = 0;
    	theArr[0] = theTemperature;
    	theArr[1] = theHumidity;
    	theArr[2] = theWind;
    	theArr[3] = theRain;
	}
	//end changes

	/**
	 * This rounding method will take whatever value is passed and round it to a
	 * specified place value
	 * 
	 * @param value  The number that will be rounded
	 * @param places The number of place values that you will round to
	 */
	public static double round(double value, int places) throws IllegalArgumentException{
		if (places < 0)
			throw new IllegalArgumentException();
		BigDecimal bigDecimalValue = BigDecimal.valueOf(value);
		bigDecimalValue = bigDecimalValue.setScale(places, RoundingMode.HALF_UP);
		return bigDecimalValue.doubleValue();
	}
}