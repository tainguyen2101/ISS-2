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

/**
 * This adapter gets the data from a weather station, and puts it into the correct format for the GUI.
 * 
 * @author Melinda Tran
 */
public class WS5Adapter {

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

		int lineNumber = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			if (lineNumber % 6 != 0 && lineNumber % 6 != 5) {
				String weatherData = filterWord(line);
				printStream.print(weatherData + " ");
				if (lineNumber % 6 == 4) {
					printStream.print("\n");
				}
			}

			lineNumber++;
		}

		scanner.close();
		printStream.close();
	}

	/**
	 * Filters the provided string input for the weather data at the end of the line.
	 */
	public static String filterWord(String line) {
		String[] arguments = line.split(" ");
		return arguments[arguments.length - 1];
	}
}
