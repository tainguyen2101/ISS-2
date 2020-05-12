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
			String[][] data = new String[50][4];
			input.nextLine();
			int i = 0;
			while(input.hasNextLine()) {
				String onlyData = "";
				String next = input.nextLine();
				boolean rain = next.contains("rainfall");
				boolean temp = next.contains("temperature");
				boolean humid = next.contains("humidity");
				boolean wind = next.contains("wind speed");
				onlyData = filterWord(next);
				if (rain == true) {
					data[i][0] = onlyData;
					output.print(data[i][0] + " ");
				} else if (temp == true) {
					data[i][1] = onlyData;
					output.print(data[i][1] + " ");
				} else if (humid == true) {
					data[i][2] = onlyData;
					output.print(data[i][2] + " ");
				} else if (wind == true) {
					data[i][3] = onlyData;
					output.print(data[i][3] + "\n");
				} else {
					onlyData = "";
				}
				i++;
			}
			
//			String[] data = new String[] {""};
//			input.nextLine();
//			while(input.hasNextLine()) {
//				int i = 0;
//				String onlyData = "";
//				onlyData = filterWord(input.nextLine());
//				data[i] = onlyData;
//				output.print(data[i] + " ");
//				i++;
//			}
			input.close();
	}
	
	public static String filterWord(final String theWord) {
		final String lowerWord = theWord.toLowerCase();
		String cleanword = "";
		int index = 0;
		boolean letter = lowerWord.contains("wireless");
		if (letter == true) {
			return cleanword += "\n";
		} else {
			while (index < lowerWord.length()) {
				if (Character.isDigit(lowerWord.charAt(index))) {
					cleanword += lowerWord.charAt(index);
				}
				index++;
			}
			return cleanword;
		}
	}

}
