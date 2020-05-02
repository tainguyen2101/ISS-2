package ISS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import Console.GUI;
import Envoy.EnvoyCore;
import Envoy.HumidIn;
import Envoy.TempIn;
import Generator.RandomSensorDataGenerator;

/**
 * Driver class. The main of the whole program. 
 */
public class Driver {
    /** Location of wind sensor in array. */
    private static final int WIND = 4;
    
    /** Location of outside temperature sensor in array. */
    private static final int TEMPERATURE = 3;
    
    /** Location of outside humidity sensor in array. */
    private static final int HUMIDITY = 1;
    
    /** Location of barometer in array. */
    private static final int BAROMETER = 0;
    
    /** Location of rain sensor in array. */
    private static final int RAIN = 2;
    
    /** Location of inside humidity sensor in array. */
    private static final int HUMID_IN = 5;
    
    /** Location of inside temperature sensor in array. */
    private static final int TEMP_IN = 6;
    
    /** Debug flag. */
    private static boolean debug = false;
    
    /** GUI of program. */
    private static GUI theGUI;
    
    /** File object to the outside data file. */
   private static File Outside = new File("OutSide.txt");
    
    /** File object to the inside data file. */
    private static File Inside = new File("InSide.txt");
    
    /** Array to hold all sensor data. Sensor order is currently determined by GUI.updateDisplay()*/
    private final Sensor[] myData = new Sensor[7];

    /** Envoy core that holds long term data. */
    private final EnvoyCore envoy = new EnvoyCore();

    /**
     * Main program that runs everything.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        generateData();
        try {
            theGUI = new GUI();
        } catch (final Exception e1) {
            e1.printStackTrace();
        }
        try {
            new Driver().run();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Use the random generator to generate data set.
     */
    private static void generateData() {
        final RandomSensorDataGenerator generate = new RandomSensorDataGenerator();
        generate.createISSData();
        generate.createEnvoyData();
    }

    /**
     * Read a line from a txt file, data is split by "," Create a new object of the
     * sensor class file with data.
     * 
     * @param theFile
     * @throws Exception
     */
    private void run() throws Exception {
        try {
            final BufferedReader rdrISS = new BufferedReader(new FileReader(Outside)); // reader for outside data
            final BufferedReader rdrEnv = new BufferedReader(new FileReader(Inside)); // reader for inside data
            String dataISS; // data lines
            String dataEnv; // data lines
            while ((dataISS = rdrISS.readLine()) != null && (dataEnv = rdrEnv.readLine()) != null) {
                final String[] arrDataISS = dataISS.split(",", 7); // splitting data from line
                final String[] arrDataEnv = dataEnv.split(",", 3); // splitting data from line
                for (int i = 1; i < dataISS.length(); i++) { // assign outside data to array
                    switch (i) {
                        case 1:
                            myData[WIND] = new Wind(Integer.valueOf(arrDataISS[i]), Integer.valueOf(arrDataISS[i + 1]));
                            break;
                        case 3:
                            myData[TEMPERATURE] = new Temperature(Double.valueOf(arrDataISS[i]) / 10);
                            break;
                        case 4:
                            myData[HUMIDITY] = new Humidity(Double.valueOf(arrDataISS[i]) / 10);
                            break;
                        case 5:
                            myData[BAROMETER] = new Barometer(Double.valueOf(arrDataISS[i]) / 10);
                            break;
                        case 6:
                            myData[RAIN] = new Rain(Double.valueOf(arrDataISS[i]) / 100);
                            break;
                    }
                }
                for (int i = 1; i < dataEnv.length(); i++) { // assign inside data to array
                    switch (i) {
                        case 1:
                            myData[TEMP_IN] = new TempIn(Double.valueOf(arrDataEnv[i]) / 10);
                            break;
                        case 2:
                            myData[HUMID_IN] = new HumidIn(Double.valueOf(arrDataEnv[i]) / 10);
                    }
                }
                theGUI.updateDisplay(myData);
                envoy.addNewData(myData);
                synchronized (this) {
                    this.wait(20);
                }
                if (debug) { // if the program is in debug mode, stop after the first read file iteration
                    break;
                }
            }
            rdrISS.close();
            rdrEnv.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Intended to use for JUnit testing. Generate data, initialize the GUI and pass
     * 1 line of data to the GUI for it to update, then STOP the program.
     */
    public int debug() {
        int result = 1;
        generateData();
        debug = true;
        try {
            theGUI = new GUI();
            run();
        } catch (final Exception e) {
        	result = 0;
            e.printStackTrace();
        }
        debug = false;
        return result;
    }
}