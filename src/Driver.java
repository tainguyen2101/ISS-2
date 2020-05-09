import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import sensors.HumiditySensor;
import sensors.RainSensor;
import sensors.SensorInterface;
import sensors.TemperatureSensor;
import sensors.WindSensor;

public class Driver {

    private static ArrayList<SensorInterface> myData = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.print("Choose a weather station: ");
        String stationNumber = input.nextLine();

        ArrayList<RandomSensorDataGenerator> generators = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            generators.add(new RandomSensorDataGenerator());
        }
        String[][] fileArray = { { "Outside1.txt", "Inside1.txt" }, { "Outside2.txt", "Inside2.txt" },
                { "Outside3.txt", "Inside3.txt" }, { "Outside4.txt", "Inside4.txt" },
                { "Outside5.txt", "Inside5.txt" } };
        generators.get(Integer.parseInt(stationNumber)).createISSData(fileArray[Integer.parseInt(stationNumber) - 1][0],
                fileArray[Integer.parseInt(stationNumber) - 1][1]);
        generators.get(Integer.parseInt(stationNumber))
                .printDataToConsole(fileArray[Integer.parseInt(stationNumber) - 1][0]);
        new Driver().updateData(10, new File(fileArray[Integer.parseInt(stationNumber)-1][0]));
        Files.delete(Paths.get(fileArray[Integer.parseInt(stationNumber) - 1][0]));
        Files.delete(Paths.get(fileArray[Integer.parseInt(stationNumber) - 1][1]));
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
