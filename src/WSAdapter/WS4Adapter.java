package WSAdapter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

import structure.ISS;
import structure.Sensor;

public class WS4Adapter {
	
	private static Random rand = new Random();
	
	public void generate() throws IOException {
		
		File dataPack = new File("data.txt");
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
	 	
	 	PrintWriter insideStats = new PrintWriter("in.txt");
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
    	  	insideStats.println(getTempIn() + " " + getHumIn());
	  	}
	  	outgoingData.close();
	  	insideStats.close();
		

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
	
	private static int getTempIn() {
       Random rand = new Random();
       return rand.nextInt((750-600) + 1) + 600;
   }
   
   private static int getHumIn() {
       Random rand = new Random();
       return rand.nextInt((550-350) + 1) + 350;
   }
	

}
