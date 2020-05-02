package WSAdapter;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import Generator.RandomSensorDataGenerator;

/*
 * Nicolas Roberts
 * 5/2/20
 * TCSS 360 Software Development
 * Professor Dincer
 */

/**
 * This adapter gets the data from a weather station, and puts it into the correct format for the GUI.
 * @author Nicolas Roberts
 *
 */
public class WS1Adapter {

	
	/**
	 * Constructor for the weather station adapter
	 */
	public WS1Adapter() {

	}

	/**
	 * Function that uses previous code to get that projects data and turn it into correct format.
	 */
	public void generateData() {
		final RandomSensorDataGenerator generate = new RandomSensorDataGenerator();
        generate.createISSData();
        generate.createEnvoyData();
        
        correctFileIO();
		
	}
	
	/**
	 * Creates a new file with correctly formatted data.
	 */
	private void correctFileIO() {
		Scanner scOutside = null;
		PrintStream output = null;
		File formattedFile = null;
		
			try {
				formattedFile = new File("OutSide.txt"); 
				scOutside = new Scanner(formattedFile);
				output = new PrintStream("WeatherStation1.txt");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			while(scOutside.hasNextLine()) {
				String line = scOutside.nextLine();
				String[] vals = line.split(",");
				
				// add WindSpeed to file
				output.print(vals[2] + " ");
				
				// add windDirection to file 
				output.print(vals[1] + " ");
				
				//add temperature
				output.print(vals[3] + " ");
				
				//add Humidity
				output.print(vals[4] + " ");
				
				//add barometric pressure
				output.print(vals[5] + " ");
				
				//add myRainRate
				int rainFall = Integer.parseInt(vals[6]);
				if (rainFall < 60) {
					output.print(0 + "\n");
				} else if (rainFall >= 60 && rainFall < 80) {
					output.print(1 + "\n");
				} else if (rainFall >= 80 && rainFall < 100) {
					output.print(2 + "\n");
				} else {
					output.print(3 + "\n");
				}
				
			}	
	}
}

//[1, WindDirection, windSpeed, temp, humidity, barometer, myRainRate]
// * [0] = WindSpeed, [1] = myWindDirection, [2] = myTemp, [3] = Humidity
//* [4] = Barometric Pressure. [5] Daily Rain
///** Wind Speed. */
//private String myWindSpeed;
//
///** Wind Direction. */
//private String myWindDirection;
//
///** Temperature. */
//private String myTemperature;
//
///** Humidity. */
//private String myHumidity;
//
///** Barometric Pressure */
//private String myBar;
//
///** Daily Rain fall */
//private String myRainFall;