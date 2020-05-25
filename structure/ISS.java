/** TCSS 360 
 * 
 * */
/**
 * Program Description: Sensor class generates data for the V2SensorSuite.
 * @author Jess Smith
 * @author Leika Yamada
 * @author Jacob Sousie
 * @author Xiuxiang Wu
 * 
 * @version 4.18.20
 * */
package structure;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   

public class ISS implements Runnable, Serializable{
	private static double theTemperature;
    private static double theHumidity;
    private static double theWind;
    private static double theRain;
    protected static double[] myArr = new double[4];
    private static double minArray[] = new double[4]; 
    private static double maxArray[] = new double[4];
    private boolean powerSwitch;
    private static final int TEMP_TYPE = 0;
    private static final int HUM_TYPE = 1;
    private static final int WIND_TYPE = 2;
    private static final int RAIN_TYPE = 3;
    private static File dataPack = new File("/Users/tient/Desktop/CSS 305/Group5ProjectAdapted/data2.txt");
    private int clockTick;
    /**Constructor for the ISS class for testing purposes only. 
     * 
     * */
    public ISS(int timerTicks) {
    	powerSwitch = true;
    	minArray[TEMP_TYPE] = 60;
    	minArray[HUM_TYPE] = 44;
    	minArray[WIND_TYPE] = 10;
    	minArray[RAIN_TYPE] = 0;
    	maxArray[TEMP_TYPE] = 60;
    	maxArray[HUM_TYPE] = 44;
    	maxArray[WIND_TYPE] = 10;
    	maxArray[RAIN_TYPE] = 0;
    	clockTick = timerTicks;
    }
    
    /**This method gets the array containing the minimum
     * values collected by the sensors over the lifetime 
     * of the sensors.
     * @return double[] the array containing minimum values.
     * */
    public static double[] getMinArray() {
    	return minArray;
    }
    
    /**This method gets the array containing the maximum
     * values collected by the sensors over the lifetime 
     * of the sensors.
     * @return double[] the array containing maximum values.
     * */
    public static double[] getMaxArray() {
    	return maxArray;
    }
    /**This method returns a boolean of whether or not 
     * the ISS class is turned on.
     * @return boolean, 0 if off, 1 if on.
     * */
    public boolean getPowerSwitch() {
    	return powerSwitch;
    }
    /**This method returns myArr from the constructor.
     * @return  double [] myArr containing initial value
     * */
    public static double[] getMyArr() {
    	return myArr;
    }
    /**This method returns the number of cycles left for the 
     * program to run.
     * @return integer clock tick
     * */
    public int getClockTick() {
    	return clockTick;
    }
    /**This method returns the number of cycles left for the 
     * program to run.
     * @return integer clock tick
     * */
    public void setClockTick(int tick) {
    	clockTick = tick;
    }
    
    /**This method set myArr value.
     * */
    public static void setMyArr(double[] theArr) {
    	myArr = theArr;
    }
    /**This method turns the ISS off and on. 
     * @return double[] the array containing maximum values.
     * */
    public void setPowerSwitch(boolean power) {
    	powerSwitch = power;
    }
    
    /**This method defines the sensor threads. 
     * It creates a new thread, and passes the 
     * sensors to it. It then starts the data generation.
     * */
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
	 /**This method defines the sensor threads. 
     * It creates a new thread, and passes the 
     * sensors to it. It then starts the data generation.
     * */
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
    /**This run method of the iss thread. 
     * The methods that will be performed during the 
     * iss thread.
     * */
	public void run() {
		updateData();
		System.out.println("Temp min: " + minArray[0]);
 		System.out.println("Temp max: " + maxArray[0]);
 		System.out.println("Hum min: " + minArray[1]);
 		System.out.println("Hum max: " + maxArray[1]);
 		System.out.println("Wind min: " + minArray[2]);
 		System.out.println("Wind max: " + maxArray[2]);
 		System.out.println("Rain min: " + minArray[3]);
 		System.out.println("Rain max: " + maxArray[3]);	
     }
	
	//The main method.  If you add code make sure to add it in Runnable mainRun to run with the thread. 
	//If you add it outside it will run like a normal main method
     public static void main(String[] args) throws InterruptedException, IOException
     {
    	 	//FileWriter writer = new FileWriter(dataPack);
    	 	BufferedWriter writer = new BufferedWriter(new FileWriter(dataPack));
    	 
    	 	ISS myISS = new ISS(100);							// HERE
    	 	writer.write("Date: " + java.time.LocalDate.now());
    	 	
    	 	while(myISS.getPowerSwitch()) {
    	 		Sensor sensorSuite = new Sensor(myArr);  
    	 		sensorThread(sensorSuite);
    	 		issThread(myISS);
    	 		
    	 		writer.write("Time: " + java.time.LocalTime.now() + "data: ");
    	 		writer.write(myArr[0] + " "+ myArr[1] +" "+ myArr[2]+" "+ myArr[3]);

    	 		myISS.setClockTick(myISS.getClockTick() - 1);
    	 		myISS.endConditionCheck(myISS.getClockTick());
    	 	}
    	 	
    	 	writeToFile(writer);	
    	 	writer.close();
    	 //below sets up the socket to talk to the client
    	 	try {
    	 
          Socket issSocket = new Socket("localhost", 1999); //localhost captures the ip address of the host
          //reads the file
          BufferedReader dataReader = new BufferedReader(new FileReader(dataPack)); 
//          byte [] bitArray = new byte[30]; 
          String text = dataReader.readLine(); 
          //Transfers the file to the client
          DataOutputStream outputStream = new DataOutputStream(issSocket.getOutputStream()); 
          outputStream.writeUTF(text); 
          System.out.println("Transfer Complete");
          dataReader.close();
          issSocket.close();
         } catch (IOException ie) {
            ie.printStackTrace(); 
        }
     }
     /**This method captures the new data from the data array 
      * and sets the field values and checks for min and max temperatures.
      * Temperature is at index 0, humidity at index 1, wind at index 2, 
      * and amount of rainfall at index 3.
      * */
     public static void updateData() {
    	 	theTemperature = myArr[0];
			minOrMax(theTemperature, TEMP_TYPE);
			theHumidity = myArr[1];
			minOrMax(theHumidity, HUM_TYPE);
			theWind = myArr[2];
			minOrMax(theWind, WIND_TYPE);
			theRain = myArr[3];
			minOrMax(theRain, RAIN_TYPE);
     }
     
     /**This method captures the new data from the data array 
      * and writes it to a file.
     * @throws IOException 
      * */
     public void endConditionCheck(int tick) {
    	 if (tick < 0) {
	    	 	setPowerSwitch(false);
	 		}
     }
     
     /**This method captures the new data from the data array 
      * and writes it to a file.
     * @throws IOException 
      * */
     public static void writeToFile(BufferedWriter writer) throws IOException {
    	writer.write("Daily Maximum and Minimums: ");
 	 	writer.write("Temp min: " + minArray[0]);
 	 	writer.write("Temp max: " + maxArray[0]);
 	 	writer.write("Hum min: " + minArray[1]);
 	 	writer.write("Hum max: " + maxArray[1]);
 	 	writer.write("Wind min: " + minArray[2]);
 	 	writer.write("Wind max: " + maxArray[2]);
 	 	writer.write("Rain min: " + minArray[3]);
 	 	writer.write("Rain max: " + maxArray[3]);
     }
     
     
 	/**
 	 * This method will find the min or max value of the type of data specified for future display
 	 * 
 	 * 
 	 * @param data  data is the current data being passed to compare
 	 * @param type  type is a numbering system to tell the type of weather you want to find the Min or Max of:
 	 *  TEMP_TYPE = 0 HUM_TYPE = 1 WIND_TYPE = 2  RAIN_TYPE = 3
 	 */
     public static void minOrMax(double data, int type) {
    	if(data < minArray[type]) {
    		minArray[type] = data;
    	} else if(data > maxArray[type]) {
     		maxArray[type] = data;
     	}
     }
}