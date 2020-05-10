import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import sensors.*;

public class Driver {

    private static ArrayList<SensorInterface> myData = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        RandomSensorDataGenerator generator = new RandomSensorDataGenerator();
        Driver run = new Driver();

        String[][] fileArray = { { "Outside1.txt", "Inside1.txt" }, { "Outside2.txt", "Inside2.txt" },
                { "Outside3.txt", "Inside3.txt" }, { "Outside4.txt", "Inside4.txt" },
                { "Outside5.txt", "Inside5.txt" } };

        //Generate data, initialize sensors.
        for (int i = 0; i < 5; i++) {
            generator.createISSData(fileArray[i][0], fileArray[i][1]);
            run.updateData(10, new File(fileArray[i][0]));
        }
        //User input for weather station
        boolean done = false;
        while (!done) {
            System.out.print("Choose a weather station: ");
            String stationNumber = input.nextLine();
            generator.printDataToConsole(fileArray[Integer.parseInt(stationNumber) - 1][0]);
            System.out.print("Another Station? (y/n) ");
            String keepRun = input.nextLine();
            if (keepRun.compareTo("y") != 0) {
                done = true;
            }
        }

        //File deletion
        for (int i = 0; i < 5; i++) {
            Files.delete(Paths.get(fileArray[i][0]));
            Files.delete(Paths.get(fileArray[i][1]));
        }
        input.close();
    }

    private void updateData(int timeInterval, File theOut/* , File theIn */) throws Exception {
        BufferedReader outRdr = new BufferedReader(new FileReader(theOut));
        // BufferedReader inRdr = new BufferedReader(new FileReader(theIn));
        // String dataIn = inRdr.readLine();
        String dataOut;

        while (/* dataIn != null && */ (dataOut = outRdr.readLine()) != null) {
            // String[] inDataArray = dataIn.split(" ", 3);
            String[] outDataArray = dataOut.split(" ", 7);
            for (int i = 1; i < outDataArray.length; i++) {
                switch (i) {
                    case 1:
                        myData.add(new WindSensor(Double.parseDouble(outDataArray[i]),
                                Double.parseDouble(outDataArray[i + 1])));
                        break;
                    case 3:
                        myData.add(new TemperatureSensor(33, Double.parseDouble(outDataArray[i])/10)); // Temp in is 33 for
                                                                                                   // now.
                        break;
                    case 5:
                        myData.add(new HumiditySensor(2, Double.parseDouble(outDataArray[i])/10)); // Hum in is 0 for now.
                        break;
                    case 7:
                        myData.add(new RainSensor(Double.parseDouble(outDataArray[i])));
                        break;
                }
            }
            synchronized (this) {
                this.wait(timeInterval);
            }
        }
        outRdr.close();
    }
}
