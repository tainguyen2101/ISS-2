

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Display Weather Information to the console.
 *
 * @author elijah (elijahff@uw.edu)
 * @version 1.0 (April 11, 2020)
 */
public class WeatherAppMain {

    public static void main(String[] args) {

        ArrayList<Day> days = new ArrayList<Day>();
        Scanner input = null;
        String fileName = "src/SensorData.txt";

        try {
            input = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file. " + e);
            System.exit(1);
        }

        String line = input.nextLine();
        while (input.hasNextLine()) {
            String[] lineData = line.split(" ");
            days.add(new Day(lineData[0], lineData[1], lineData[2], lineData[3],lineData[4]));
            line = input.nextLine();
        }
        displayInfo(days);
    }

    private static void displayInfo(ArrayList<Day> days) {
        WeatherCalculations calculator = new WeatherCalculations();

        Day year = days.get(0);
        Day sixMonth = days.get(days.size()/2);
        Day today = days.get(days.size()-1);
        String percent = "%";

        char degree = '\u2109';

        System.out.println("Today's Forecast: "); //most recent data.
        System.out.println("-------------------------------");
        System.out.printf("Temperature: %11s %c\n", today.getTemperature(), degree);
        System.out.printf("Dew point: %16.2f %c \n", calculator.dewPoint(days),degree);
        System.out.printf("Heat Index: %15.2f %c\n", calculator.heatIndex(days), degree);
        System.out.printf("Wind Chill: %15.2f %c\n", calculator.windChill(days), degree);
        System.out.printf("Humidity: %14s%s\n", today.getHumidity(),percent);
        System.out.printf("Atmospheric Pressure: %1s bar\n", today.getBarometricPressure());
        System.out.printf("Wind Speed: %12smph\n", today.getWindSpeed());
        System.out.printf("Wind Direction: %11s\n", today.getWindDirection());
        System.out.println("-------------------------------\n");

        System.out.println("Weather Info (historical):  YEAR    6 MONTHS   TODAY");
        System.out.println("-----------------------------------------------------");
        System.out.printf("Temperature: %17s%c %6s%c %8s%c \n",year.getTemperature(),degree,
                sixMonth.getTemperature(),degree, today.getTemperature(),degree);
        System.out.printf("Humidity: %20s%s %6s%s %8s%s \n",year.getHumidity(), percent
                ,sixMonth.getHumidity(), percent, today.getHumidity(), percent);
        System.out.printf("Wind Speed: %18smph %4smph %6smph \n",year.getWindSpeed(),
                sixMonth.getWindSpeed(), today.getWindSpeed());
        System.out.printf("Barometric Pressure: %9sbar %4sbar %6sbar \n",
                year.getBarometricPressure(), sixMonth.getBarometricPressure(), today.getBarometricPressure());
        System.out.println("-----------------------------------------------------\n");

        System.out.println("Weather Statistics:           YEARLY    MONTHLY");
        System.out.println("-------------------------------------------------");
        System.out.printf("Temperature averages: %13.2f%c %8.2f%c\n",
                calculator.tempAvgYear(days),degree, calculator.tempAvgMonth(days),degree);
        System.out.printf("Humidity averages: %16.2f%s %8.2f%s\n",
                calculator.humidityAvgYear(days), percent ,calculator.humidityAvgMonth(days), percent);
        System.out.printf("Wind Speed averages: %14.2fmph %6.2fmph\n",
                calculator.windSpeedAvgYear(days), calculator.windSpeedAvgMonth(days));
        System.out.printf("Barometric Pressure averages: %5.2fbar %6.2fbar\n",
                calculator.atmAvgYear(days), calculator.atmAvgMonth(days));
        System.out.println("-------------------------------------------------\n");
    }
}
