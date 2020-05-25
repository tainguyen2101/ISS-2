package WSAdapter;

import org.junit.jupiter.api.Test;
import sensors.BarometerSensor;

import static org.junit.jupiter.api.Assertions.*;

class BarometerSensorTest {

    @Test
    void getData() {
        BarometerSensor testBarometer = new BarometerSensor(20.02);
        assertEquals("{Barometer: " + 20.02 + "}", testBarometer.getData());
    }
}