/*
 * Melinda Tran
 * 5/2/20
 * TCSS 360 Software Development
 * Professor Dincer
 */

package WSAdapter;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

/**
 * This adapter gets the data from a weather station, and puts it into the correct format for the GUI.
 * 
 * @author Melinda Tran
 */
public class WS5Adapter {
	
  
  	// Formatted Data: Wind Speed, Wind Direction, Temperature, Humidity, Barometric Pressure, Rain rate
  	// What I have currently: Rainfall, Temperature, Humidity, Wind Speed
	// Goal: Switch Rainfall and Wind Speed to correct position and add Wind Direction and Barometric Pressure
  	// Wind speed, wind direction, temperature, humidity, barometric pressure, and rainfall
  
	private static final int max = 360; // 360
	private static final int min = 1; // 1
	private static Random rand = new Random();

	/**
	 * Prints to the WeatherStation5.txt file of the weather data formatted into a matrix.
	 */
	public void generateData() {
		Scanner scanner = null;
		PrintStream printStream = null;

		try {
			File file = new File("sensor-suite-data.txt");
			scanner = new Scanner(file);
			printStream = new PrintStream("WeatherStation5.txt");
		} catch (IOException exception) {
			exception.printStackTrace();
		}
      
      	int weatherDataPoints = 6;
      	int[] weatherData = new int[weatherDataPoints];

		int lineNumber = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (lineNumber % 6 != 0 && lineNumber % 6 != 5) {
				int data = filterWord(line);
              	if (lineNumber % 6 == 1) { // rainfall [5] and barometric pressure [4]
                	weatherData[4] = barometricPressure();
                  	weatherData[5] = data;
                } else if (lineNumber % 6 == 4) { // wind speed [0] and wind direction [1]
                	weatherData[0] = data;
                  	weatherData[1] = windDirection();
                } else { // temperature [1] and humidity [2]
                  	weatherData[lineNumber % 6] = data;
                }
              	
				if (lineNumber % 6 == 4) {
					String result = "";
                  	for (int weatherPoint : weatherData) {
                    	result += weatherPoint + " ";
                    }
                  	printStream.print(result + "\n");
				}
			}
			lineNumber++;
		}

		scanner.close();
		printStream.close();
	}

	/**
	 * Generate the wind direction.
	 * 
	 * @return a random integer for wind direction.
	 */
	public int windDirection() {
		return  rand.nextInt(max + 1 - min) + min; // [1, 360]
	}
	
	/**
	 * Generate the barometric pressure.
	 * 
	 * @return a random integer for barometric pressure.
	 */
	public static int barometricPressure() {
        return rand.nextInt(100);
    }
	
	/**
	 * Filters the provided string input for the weather data at the end of the line.
	 */
	public static int filterWord(String line) {
		String[] arguments = line.split(" ");
		return Integer.valueOf(arguments[arguments.length - 1]);
	}
}
