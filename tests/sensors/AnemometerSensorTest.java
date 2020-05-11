package sensors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class AnemometerSensorTest {
    /**
     * Test AnemometerSensor name is correct.
     */
    @Test
    void testGetFieldName() {
        final Sensor anemometerSensor = new AnemometerSensor();
        assertEquals("wind speed (miles per hour)", anemometerSensor.getFieldName());
    }

    /**
     * Test get data method is correct.
     */
    @Test
    void testGetData() {
        final Sensor anemometerSensor = new AnemometerSensor();
        for(int i = 0; i < 100; i ++) {
            final int data = anemometerSensor.getData();
            assertTrue(20 <= data);
            assertTrue(data <= 40);
        }
    }
}
