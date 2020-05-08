import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner input = new Scanner(System.in);

        System.out.print("Choose a weather station: ");
        String stationNumber = input.nextLine();

        ArrayList<RandomSensorDataGenerator> generators = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            generators.add(new RandomSensorDataGenerator());
        }
        String[][] fileArray =  {
            {"Outside1.txt", "Inside1.txt"},
            {"Outside2.txt", "Inside2.txt"},
            {"Outside3.txt", "Inside3.txt"},
            {"Outside4.txt", "Inside4.txt"},
            {"Outside5.txt", "Inside5.txt"}
        };
        generators.get(Integer.parseInt(stationNumber)).createISSData(fileArray[Integer.parseInt(stationNumber)-1][0], 
                                    fileArray[Integer.parseInt(stationNumber)-1][1]);
        generators.get(Integer.parseInt(stationNumber)).printDataToConsole(fileArray[Integer.parseInt(stationNumber)-1][0]);

        Files.delete(Paths.get(fileArray[Integer.parseInt(stationNumber)-1][0]));
        Files.delete(Paths.get(fileArray[Integer.parseInt(stationNumber)-1][1]));
        input.close();
    }
}
