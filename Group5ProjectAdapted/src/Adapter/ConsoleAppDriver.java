package Adapter;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

import structure.ISS;
import structure.Sensor;

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
	
	private static Random rand = new Random();
	
	/**
	 * Driver for the Console Application Project.
	 * @param theArgs used for command line input.
	 * @throws IOException 
	 */
	public static void main(String[] theArgs) throws IOException {
		
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
				File dataPack = new File("/Users/tient/Desktop/CSS 305/Group5ProjectAdapted/data.txt");
				//FileWriter writer = new FileWriter(dataPack);
	    	 	BufferedWriter writer = new BufferedWriter(new FileWriter(dataPack));
	    	 
	    	 	ISS myISS = new ISS(4);
	    	 	writer.write("Date: " + java.time.LocalDate.now());
	    	 	double[] myArr = new double[4];
	    	 	while(myISS.getPowerSwitch()) {
	    	 		Sensor sensorSuite = new Sensor(myArr);  
	    	 		sensorThread(sensorSuite);
	    	 		issThread(myISS);
	    	 		
	    	 		writer.write("Time: " + java.time.LocalTime.now() + "data: ");
	    	 		writer.write(myArr[0] + " "+ myArr[1] +" "+ myArr[2]+" "+ myArr[3]);

	    	 		myISS.setClockTick(myISS.getClockTick() - 1);
	    	 		myISS.endConditionCheck(myISS.getClockTick());
	    	 	}
	    	 	writer.close();
	    	 	
	    	 	PrintWriter outgoingData = new PrintWriter("WS4Data.txt");
	    	  	File data = new File("data.txt");
	    	  	Scanner sc = new Scanner(data);
	    	  	sc.next();
	    	  	sc.next();
	    	  	for (int i = 0; i < 5; i ++) {
	    	  		sc.next();
		    	  	String temp = sc.next();
		    	  	String hum = sc.next();
		    	  	String windSpd = sc.next();
		    	  	String rain = sc.next();
		    	  	rain = rain.substring(0, 3);
		    	  	int bar = getBar();
		    	  	int windDir = getWindDir();
		    	  	outgoingData.println(windSpd + " " + windDir + " " + temp + " " + 
		    	  						 hum + " " + bar + " " + rain);
	    	  	}
	    	  	outgoingData.close();
	    	  	
				// display weather station 4 info
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
		
	public static void sensorThread(Sensor sensors) {
         ReentrantLock lock = new ReentrantLock();
	 		lock.lock();
	 		try { 
	 		     Thread t = new Thread(sensors);
	 	         t.start();
	 	        try {
	                Thread.sleep(2500);
	           } catch (InterruptedException e) {
	            }
	 			}
	 		finally {
	 			lock.unlock();
	 		}	
    }
	public static void issThread(ISS iss) {
        ReentrantLock lock = new ReentrantLock();
	 		lock.lock();
	 		try { 
	 			Thread t = new Thread(iss);
   	 		t.start(); 
	 			}
	 		finally {
	 			lock.unlock();
	 		}	
    }
	public static int getBar() {
        return rand.nextInt(100);
    }
	
	public static int getWindDir() {
		return rand.nextInt(360);
	}
	
}