package sensors;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is simulating a very basic simulation of temperature and displays its data
 * using the getData method of our interface. 
 * @author Tien Tang
 * @author Ali Iftakhar
 * @version 4/5/2020
 */

//THIS IS AN ALTERNATIVE IMPLEMENTATION DONE BY TIEN TANG.
public class TemperatureSensorAlternative implements SensorInterface {
	
	/**
	 * Keeps track of every time the thread runs and every 20 seconds counter increments.
	 */
	private int secondsCounter;
	
	
	/**
	 * Keeps track of the temperature inside the house.
	 */
	private double temperatureIn;
	/**
	 * Keeps track of temperature outside the house.
	 */
	private double temperatureOut;
	
	/**
     * This data structure keeps track of every temperatureIn we've derived
     * so far.
     */
    private ArrayList<Double> temperatureInArchieve;
    
    /**
     * This data structure keeps track of every temperatureOut we've derived
     * so far.
     */
    private ArrayList<Double> temperatureOutArchieve;
	
	public TemperatureSensorAlternative() {
		temperatureIn = 70; //random value chosen based on realistic assumptions.
		temperatureOut = 55; 
		secondsCounter = 0;
		temperatureInArchieve = new ArrayList<>();
        temperatureOutArchieve = new ArrayList<>();
		
	}
	
	/**
	 * This method must be implemented. This method will be the one the main class uses
	 * to get an updated value. Data is updated every 20 seconds.
	 */
	public String getData() {
		// 12-3pm
		if (secondsCounter <= 540) {
			temperatureOut += Math.random() - Math.random();
			if (secondsCounter >= 525) {
				if (temperatureOut < 58) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 60) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 345) {
				if (temperatureOut < 57) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 59) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 165) {
				if (temperatureOut < 56) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 58) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 0) {
				if (temperatureOut < 52) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 58) {
					temperatureOut -= .95;
				}
			}
		}
		// 3-6pm
		else if (secondsCounter <= 1080) {
			temperatureOut = temperatureOut + Math.random() - Math.random();
			if (secondsCounter >= 1065) {
				if (temperatureOut < 58) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 60) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 705) {
				if (temperatureOut < 60) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 62) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 541) {
				if (temperatureOut < 58) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 60) {
					temperatureOut -= .95;
				}
			}
		}
		// 6-9pm
		else if (secondsCounter <= 1620) {
			temperatureOut = temperatureOut + Math.random() - Math.random();
			if (secondsCounter >= 1605) {
				if (temperatureOut < 51) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 53) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 1425) {
				if (temperatureOut < 53) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 55) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 1245) {
				if (temperatureOut < 55) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 57) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 1066) {
				if (temperatureOut < 58) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 60) {
					temperatureOut -= .95;
				}
			}
		}
		// 9pm-12am
		else if (secondsCounter <= 2160) {
			temperatureOut = temperatureOut + Math.random() - Math.random();
			if (secondsCounter >= 2145) {
				if (temperatureOut < 42) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 44) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 1965) {
				if (temperatureOut < 45) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 47) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 1785) {
				if (temperatureOut < 48) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 50) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 1606) {
				if (temperatureOut < 51) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 53) {
					temperatureOut -= .95;
				}
			}
		}
		// 12-3am
		else if (secondsCounter <= 2700) {
			temperatureOut = temperatureOut + Math.random() - Math.random();
			if (secondsCounter >= 2685) {
				if (temperatureOut < 36) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 38) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 2505) {
				if (temperatureOut < 38) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 40) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 2325) {
				if (temperatureOut < 40) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 42) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 2146) {
				if (temperatureOut < 42) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 44) {
					temperatureOut -= .95;
				}
			}
		}
		// 3-6am
		else if (secondsCounter <= 3240) {
			temperatureOut = temperatureOut + Math.random() - Math.random();
			if (secondsCounter >= 3225) {
				if (temperatureOut < 31) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 33) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 3045) {
				if (temperatureOut < 32) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 34) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 2865) {
				if (temperatureOut < 34) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 36) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 2686) {
				if (temperatureOut < 36) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 38) {
					temperatureOut -= .95;
				}
			}
		}
		// 6-9am
		else if (secondsCounter <= 3780) {
			temperatureOut = temperatureOut + Math.random() - Math.random();
			if (secondsCounter >= 3765) {
				if (temperatureOut < 40) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 42) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 3585) {
				if (temperatureOut < 36) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 38) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 3405) {
				if (temperatureOut < 33) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 35) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 3226) {
				if (temperatureOut < 31) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 33) {
					temperatureOut -= .95;
				}
			}
		}
		// 9-12pm
		else if (secondsCounter <= 4319) {
			temperatureOut = temperatureOut + Math.random() - Math.random();
			if (secondsCounter >= 4305) {
				if (temperatureOut < 52) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 56) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 4125) {
				if (temperatureOut < 49) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 51) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 3945) {
				if (temperatureOut < 45) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 47) {
					temperatureOut -= .95;
				}
			}
			else if (secondsCounter >= 3766) {
				if (temperatureOut < 40) {
					temperatureOut += .95;
				}
				else if (temperatureOut > 42) {
					temperatureOut -= .95;
				}
			}
		}
		// resets day
		else if (secondsCounter == 4320) {
			secondsCounter = 0;
		}
		temperatureIn = temperatureIn + Math.random() - Math.random();
		while (temperatureIn > 85) {
			temperatureIn -= .95;
		}
		while (temperatureIn < 55) {
			temperatureIn += .95;
		}
		temperatureInArchieve.add(temperatureIn);
        temperatureOutArchieve.add(temperatureOut);
        secondsCounter++;
		//Giving structure and keeping answer to 2 decimal places.
		return new DecimalFormat("##.##").format(temperatureOut) + "\n" +  
				new DecimalFormat("##.##").format(temperatureIn);
	}
	
	/**
     * This returns all the temperatureIn values stored from 12pm of day 0.
     * @return ArrayList<> values of temperatureIn in sorted form.
     */
    public ArrayList<Double> allTemperatureIn() {
        //Collections.sort(temperatureInArchieve);
        return temperatureInArchieve;
    }
    
    /**
     * This returns all the temperatureOut values stored from 12pm of day 0.
     * @return ArrayList<> values of temperatureOut in sorted form.
     */
    public ArrayList<Double> allTemperatureOut() {
        //Collections.sort(temperatureOutArchieve);
        return temperatureOutArchieve;
    }
    
}