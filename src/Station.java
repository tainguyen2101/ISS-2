
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import sensors.*;

/**
 * Driver class.
 * @author Ford Nguyen.
 */
public class Station {

    // Update time interval
    private static int UPDATE_INTERVAL_S1 = 1000; // 1 minute = 60000;
    private static int UPDATE_INTERVAL_S2 = 2000;
    private static int UPDATE_INTERVAL_S3 = 3000;
    private static int UPDATE_INTERVAL_S4 = 4000;
    private static int UPDATE_INTERVAL_S5 = 5000;

    // Data Storage for each station
    private static ArrayList<SensorInterface> myData1 = new ArrayList<>();
    private static ArrayList<SensorInterface> myData2 = new ArrayList<>();
    private static ArrayList<SensorInterface> myData3 = new ArrayList<>();
    private static ArrayList<SensorInterface> myData4 = new ArrayList<>();
    private static ArrayList<SensorInterface> myData5 = new ArrayList<>();

    private static GUI theGUI;

    private static String[][] myFileArray = { { "Outside1.txt", "Inside1.txt" }, { "Outside2.txt", "Inside2.txt" },
            { "Outside3.txt", "Inside3.txt" }, { "Outside4.txt", "Inside4.txt" }, { "Outside5.txt", "Inside5.txt" } };

    public static void main(String[] args) {
        try {
            theGUI = new GUI();
            runStation();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * This function read the data from text file and split them for initializing
     * all sensor class.
     * 
     * @param timeInterval update once every timeInterval
     * @param theOut       Outside.txt to read
     * @throws Exception catch Exception
     */
    private void updateData(String theStationName, final int timeInterval, final File theOut, File theIn,
            ArrayList<SensorInterface> theDataSet, int tabNumber) throws Exception {
        final BufferedReader outRdr = new BufferedReader(new FileReader(theOut));
        final BufferedReader inRdr = new BufferedReader(new FileReader(theIn));
        String dataIn;
        String dataOut;
        while ((dataIn = inRdr.readLine()) != null && (dataOut = outRdr.readLine()) != null) {
            final String[] inDataArray = dataIn.split(" ", 2);
            final String[] outDataArray = dataOut.split(" ", 6);
            String dataSent = dataOut + " " + dataIn;
            for (int i = 0; i < outDataArray.length; i++) {
                switch (i) {
                    case 0:
                        theDataSet.add(new WindSensor(Double.parseDouble(outDataArray[i+1]),
                                Double.parseDouble(outDataArray[i])));
                        break;
                    case 2:
                        theDataSet.add(new TemperatureSensor(Double.parseDouble(inDataArray[0]) / 10,
                                Double.parseDouble(outDataArray[i]) / 10));
                        break;
                    case 3:
                        theDataSet.add(new BarometerSensor(Double.parseDouble(outDataArray[i]) / 10));
                        break;
                    case 4:
                        theDataSet.add(new HumiditySensor(Double.parseDouble(inDataArray[1]) / 10,
                                Double.parseDouble(outDataArray[i]) / 10));
                        break;
                    case 5:
                        theDataSet.add(new RainSensor(Double.parseDouble(outDataArray[i]) / 100));
                        break;
                }
            }
            theGUI.updateTab(dataSent, tabNumber);
            synchronized (this) {
                this.wait(timeInterval);
            }
        }
        outRdr.close();
        inRdr.close();
    }

    /**
     * Create 5 threads for 5 stations Define what each thread will run based on the
     * stationte
     * 
     * @throws IOException
     */
    private static void runStation() throws IOException {
        final RandomSensorDataGenerator generator = new RandomSensorDataGenerator();

        // generate 5 set of data for 5 weather station
        for (int i = 0; i < 5; i++) {
            generator.createISSData(myFileArray[i][0], myFileArray[i][1]);
            generator.createEnvoyData(myFileArray[i][1]);
        }

        // Run station 1.
        final Thread station1 = new Thread() {

            @Override
            public void run() {
                try {
                    Station run1 = new Station();
                    run1.updateData("STATION 1", UPDATE_INTERVAL_S1, new File(myFileArray[0][0]), new File(myFileArray[0][1]), myData1, 1);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Run station 2.
        final Thread station2 = new Thread() {

            @Override
            public void run() {
                try {
                    Station run2 = new Station();
                    run2.updateData("STATION 2", UPDATE_INTERVAL_S2, new File(myFileArray[1][0]), new File(myFileArray[1][1]), myData2, 2);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Run station 3.
        final Thread station3 = new Thread() {

            @Override
            public void run() {                
                try {
                    Station run3 = new Station();
                    run3.updateData("STATION 3", UPDATE_INTERVAL_S3, new File(myFileArray[2][0]), new File(myFileArray[2][1]), myData3, 3);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Run staion 4.
        final Thread station4 = new Thread() {

            @Override
            public void run() {
                try {
                    Station run4 = new Station();
                    run4.updateData("STATION 4", UPDATE_INTERVAL_S4, new File(myFileArray[3][0]), new File(myFileArray[3][1]), myData4, 4);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Run station 5.
        final Thread station5 = new Thread() {

            @Override
            public void run() { 
                try {
                    Station run5 = new Station();
                    run5.updateData("STATION 5", UPDATE_INTERVAL_S5, new File(myFileArray[4][0]), new File(myFileArray[4][1]), myData5, 5);

                    System.out.println();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        station1.start();
        station2.start();
        station3.start();
        station4.start();
        station5.start();

        // File deletion
        for (int i = 0; i < 5; i++) {
            Files.delete(Paths.get(myFileArray[i][0]));
            Files.delete(Paths.get(myFileArray[i][1]));
        }
    }

}
