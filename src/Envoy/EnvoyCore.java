package Envoy;

import java.util.ArrayList;
import java.util.List;

import ISS.Sensor;

/**
 * The envoy stores all weather data.
 */

public class EnvoyCore {
    List<Sensor[]> myData;
    
    /**
     * Default constructor.
     */
    public EnvoyCore() {
        myData = new ArrayList<>();
    }
    
    /**
     * Put new data to storage.
     * 
     * @param data array of sensor data.
     */
    public void addNewData(Sensor[] data) {
        myData.add(data);
    }
    
    /**
     * Retrieve data from storage.
     * 
     * @param location index of data
     * @return an array of sensor data at given location
     */
    public Sensor[] getData(int location) {
        return myData.get(location);
    }
}
