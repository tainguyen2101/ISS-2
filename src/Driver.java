import java.util.ArrayList;
import java.util.Scanner;

public class Driver {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Choose a weather station: ");
        String stationNumber = input.nextLine();

        ArrayList<RandomSensorDataGenerator> generators = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            generators.add(new RandomSensorDataGenerator());
        }
        generators.get(Integer.parseInt(stationNumber)).createISSData();
        generators.get(Integer.parseInt(stationNumber)).printDataToConsole();
    }
}
