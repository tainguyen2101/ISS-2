package views;

import org.junit.jupiter.api.Test;
import sensorSuite.SensorSuite;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleReceiverTest {

    /**
     * Checks if the console receiver prints out text on console.
     */
    @Test
    void testUpdate() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        SensorSuite sensorSuite = new SensorSuite();
        ConsoleReceiver consoleReceiver = new ConsoleReceiver(sensorSuite);
        consoleReceiver.update();

        assertEquals("Wireless Vantage Pro2 Console Receivers\n\n", os.toString());
    }
}
