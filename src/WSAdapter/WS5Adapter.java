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
import java.util.Scanner;

import sensorSuite.SensorSuite;

/**
 * This adapter gets the data from a weather station, and puts it into the correct format for the GUI.
 * 
 * @author Melinda Tran
 *
 */

public class WS5Adapter {
	
	public static SensorSuite myData = new SensorSuite();
	public static String myString = " ";
	
	/**
	 * Constructor for the weather station adapter
	 */
	
	public WS5Adapter() {
		
	}

	/**
	 * Function that uses previous code to get that projects data and turn it into correct format.
	 */
	
	public void generateData() {
		myString = myData.getData();
		correctFileIO();
	}
	
	/**
	 * Creates a new file with correctly formatted data.
	 */
	public void correctFileIO() {
		Scanner input = null;
		PrintStream output = null;
		File formattedFile = null;
			try {
				formattedFile = new File("sensor-suite-data.txt");
				input = new Scanner(formattedFile);
				output = new PrintStream("WeatherStation5.txt");
			} catch (IOException e) {
				e.printStackTrace();
			}
			String onlyData = "";
			String[] data = new String[] {" "};
			int i = 0;
			while(input.hasNextLine()) {
				onlyData = filterWord(input.nextLine());
				data[i] = onlyData;
				output.print(data[i] + " ");
//				String data = "";
//				int i = 0;
//				if (i < 5) {
//					data += onlyData;
//					data += " ";
//					String[] vals = data.split(" ");
//					output.print(onlyData + " ");
//					i = 0;
//				}
			}
			input.close();
	}
	
	public static String filterWord(final String theWord) {
		final String lowerWord = theWord.toLowerCase();
		String cleanword = "";
		int index = 0;
		while (index < lowerWord.length()) {
			if (Character.isDigit(lowerWord.charAt(index))) {
				cleanword += lowerWord.charAt(index);
			}
			index++;
		}
		return cleanword;
	}
	
//	private void correctFileIO() {
//		Scanner scOutside = null;
//		PrintStream output = null;
//		File formattedFile = null;
//		
//			try {
//				formattedFile = new File("OutSide.txt"); 
//				scOutside = new Scanner(formattedFile);
//				output = new PrintStream("Weather Station 5.txt");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			while(scOutside.hasNextLine()) {
//				String line = scOutside.nextLine();
//				String[] vals = line.split(",");
//				
//				// add WindSpeed to file
//				output.print(vals[2] + " ");
//				
//				// add windDirection to file 
//				output.print(vals[1] + " ");
//				
//				//add temperature
//				output.print(vals[3] + " ");
//				
//				//add Humidity
//				output.print(vals[4] + " ");
//				
//				//add barometric pressure
//				output.print(vals[5] + " ");
//				
//				//add myRainRate
//				int rainFall = Integer.parseInt(vals[6]);
//				if (rainFall < 60) {
//					output.print(0 + "\n");
//				} else if (rainFall >= 60 && rainFall < 80) {
//					output.print(1 + "\n");
//				} else if (rainFall >= 80 && rainFall < 100) {
//					output.print(2 + "\n");
//				} else {
//					output.print(3 + "\n");
//				}
//				
//			}
//	}
}
