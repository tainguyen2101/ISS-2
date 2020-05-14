package Prj2Driver;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import WSAdapter.WS5Adapter;

/*
 * TCSS Software Development 360
 * Group #2
 * Group Project - Phase 2
 */

/**
 * @author Group 2
 * Main executable to connect weather stations.
 *
 */
public class ConsoleAppDriver {
	
	/**
	 * Driver for the Console Application Project.
	 * @param theArgs used for command line input.
	 */
	public static void main(String[] theArgs) {
		
		//open scanner for user choice for weather station 
		//TESTING PURPOSES ONLY
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter which weather station to view [1-5]: ");
		int inputVal = input.nextInt();
		switch(inputVal) {
			case 1 :
//				WS1Adapter adapter = new WS1Adapter();
//				adapter.generateData();
//				Scanner input2 = null;
//				try {
//					input2 = new Scanner(new File("WeatherStation1.txt"));
//				} catch(IOException e) {
//					e.printStackTrace();
//				}
//				//For sake of testing, print the first 5 lines of data
//				int i = 0;
//				while (input2.hasNextLine() && (i < 5)) {
//					i++;
//					System.out.println(input2.nextLine());
//				}
				//display weather station 1 info
				break;
			case 2 :
				//display weather station 2 info
				break;
			case 3 :
				//display weather station 3 info
				break;
			case 4 :
				//display weather station 4 info
				break;
			case 5 :
		        WS5Adapter ws5Adapter = new WS5Adapter();
		        ws5Adapter.generateData();

		        Scanner scanner = null;
		        try {
		          File file = new File("WeatherStation5.txt");
		          scanner = new Scanner(file);
		        } catch(IOException exception) {
		          exception.printStackTrace();
		        }
		        
		        //For sake of testing, print the first 5 lines of data
		        int i = 0;
		        while (scanner.hasNextLine() && (i < 5)) {
		            i++;
		            System.out.println(scanner.nextLine());
		        }
		        
		        //display weather station 5 info
		        
		        scanner.close();
				break;
			default :
				System.out.println("Wrong input\n");
				System.exit(1);
		}
		
	input.close();
	}
}