package WSAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import model.AbstractOutputDevice;
import model.ISS;
import model.WeatherMonitoringApp;

/*
 * Jacob Sousie
 * 5/14/20
 * TCSS 360 Software Development
 * Professor Dincer
 */

/**
 * This adapter gets the data from a weather station, and puts it into the
 * correct format for the GUI.
 * 
 * @author Jacob Sousie
 *
 */
public class WS2Adapter {

	/**
	 * Constructor for the weather station adapter
	 */
	public WS2Adapter() {

	}

	/**
	 * Function that uses previous code to get that projects data and turn it into
	 * correct format.
	 * 
	 * @throws IOException
	 */
	public void generateData() throws IOException {

		final WeatherMonitoringApp testApp = new WeatherMonitoringApp();

		final ArrayList<AbstractOutputDevice> testDeviceArray = new ArrayList<AbstractOutputDevice>();
		testDeviceArray.add(testApp);

		Runnable ISSThreadR = new Runnable() {
			@Override
			public void run() {
				
				try {
					while (true) {
					
						PrintStream out = new PrintStream(new FileOutputStream("WeatherStation2BEFOREFORMAT.txt"));
						System.setOut(out);
						new ISS(testDeviceArray);
						this.wait();
					}
				} catch (IllegalMonitorStateException k) {

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
		
		Thread ISSThread = new Thread(ISSThreadR);
		correctFileIO();
		ISSThread.start();
		
	}

	/**
	 * Creates a new file with correctly formatted data.
	 * 
	 * @throws IOException
	 */
	private void correctFileIO() throws IOException {

		Scanner scOutside = null;
		PrintStream inside = null;
		PrintStream output = null;
		File formattedFile = null;
		try {
			inside = new PrintStream("WeatherStation2Inside.txt");
			formattedFile = new File("WeatherStation2BEFOREFORMAT.txt");
			scOutside = new Scanner(formattedFile);
			output = new PrintStream("WeatherStation2.txt");
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int count = 0;
		while (scOutside.hasNextLine() && count < 40) {
			
			count++;
			String line = scOutside.nextLine();
			if (line.equals("")) {
				continue;
			}
			String[] vals = line.split("		");
			inside.print(getTempIn() + " " + getHumIn() + "\n");
			// add WindSpeed to file
			output.print(vals[6] + " ");

			// add windDirection to file
			
			if(vals[7].contains("N")) {
				int North = getRandomNumberInRange(0,44);
			
				vals[7]=Integer.toString(North);
			}
			if(vals[7].contains("NE")) {
				int NorthEast = getRandomNumberInRange(45,89);
			
				vals[7]=Integer.toString(NorthEast);
			}
			if(vals[7].contains("E")) {
				int East = getRandomNumberInRange(90,134);
			
				vals[7]=Integer.toString(East);
			}
			if(vals[7].contains("SE")) {
				int SouthEast = getRandomNumberInRange(135,179);
			
				vals[7]=Integer.toString(SouthEast);
			}
			if(vals[7].contains("S")) {
				int South = getRandomNumberInRange(181,224);
			
				vals[7]=Integer.toString(South);
			}
			if(vals[7].contains("SW")) {
				int SouthWest = getRandomNumberInRange(270,314);
			
				vals[7]=Integer.toString(SouthWest);
			}
			if(vals[7].contains("W")) {
				int West = getRandomNumberInRange(270,314);
			
				vals[7]=Integer.toString(West);
			}
			if(vals[7].contains("NW")) {
				int NorthWest = getRandomNumberInRange(315,359);
			
				vals[7]=Integer.toString(NorthWest);
			}
			output.print(vals[7] + " ");

			// add temperature
			output.print(vals[0] + " ");

			// add Humidity

			output.print(vals[9] + " ");

			// add barometric pressure
			Double temp = Double.parseDouble(vals[5]);
			int temp2 = (int) (temp / 1);
			output.print(temp2 + " ");
			
			output.print(vals[8] + " \n");


		}
		
		

	}
	
	public static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}
	
	private int getTempIn() {
        Random rand = new Random();
        return rand.nextInt((750-600) + 1) + 600;
    }
    
    private int getHumIn() {
        Random rand = new Random();
        return rand.nextInt((550-350) + 1) + 350;
    }
	
}
