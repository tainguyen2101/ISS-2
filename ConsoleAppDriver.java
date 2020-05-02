import java.util.Scanner;

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
				//display weather station 5 info
				break;
			default :
				System.out.println("Wrong input\n");
				System.exit(1);
		}
		
	input.close();
	}
}
