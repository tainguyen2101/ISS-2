import sensorSuite.SensorSuite;
import views.ConsoleReceiver;
import views.WeatherEnvoy;

/**
 * Main program.
 */
public class Main {
    private static SensorSuite sensorSuite;
    private static ConsoleReceiver consoleReceiver;
    private static WeatherEnvoy weatherEnvoy;

    public static void main(String[] args) throws InterruptedException {
        setUp();
        startSensorSuite();
    }

    private static void setUp() {
        sensorSuite = new SensorSuite();
        consoleReceiver = new ConsoleReceiver(sensorSuite);
        weatherEnvoy = new WeatherEnvoy(sensorSuite);
        sensorSuite.addObserver(consoleReceiver);
        sensorSuite.addObserver(weatherEnvoy);
    }

    private static void startSensorSuite() throws InterruptedException {
        sensorSuite.run();
    }
}
