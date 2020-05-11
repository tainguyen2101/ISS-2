package sensorSuite;

import sensors.*;
import views.Observer;

import java.util.LinkedList;
import java.util.List;

/**
 * This class acts like a sensor suite.
 */
public class SensorSuite implements Observable {
    private List<Observer> observers;
    private List<Sensor> sensors;
    private String data;

    public static final int INTERVAL = 2500;

    public SensorSuite() {
        observers = new LinkedList<>();
        sensors = new LinkedList<>();
        data = "";
        attachBasicSensors();
    }

    /**
     * When the sensor suite is constructed,
     * it will attach the required sensors.
     */
    private void attachBasicSensors() {
        Sensor rainSensor = new RainSensor();
        Sensor temperatureSensor = new TemperatureSensor();
        Sensor humiditySensor = new HumiditySensor();
        Sensor anemometerSensor = new AnemometerSensor();
        sensors.add(rainSensor);
        sensors.add(temperatureSensor);
        sensors.add(humiditySensor);
        sensors.add(anemometerSensor);
    }

    /**
     * Sensor Suite starts fetching sensor data by a certain time interval.
     */
    public void run() throws InterruptedException {
        while (true) {
            Thread.sleep(INTERVAL);
            fetchData();
            notifyAllObservers();
        }
    }

    /**
     * Serialize sensor data as a string and save it in class variable data,
     */
    public void fetchData() {
        StringBuilder builder = new StringBuilder();
        for (Sensor sensor : sensors) {
            builder.append(sensor.getFieldName()).append(": ").append(sensor.getData()).append("\n");
        }
        data = builder.toString();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    @Override
    public String getData() {
        return data;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public Observer getFirstObserver() {
        return observers.get(0);
    }

    public Observer getSecondObserver() {
        return observers.get(1);
    }

    public List<Observer> getObservers() {
        return observers;
    }
}
