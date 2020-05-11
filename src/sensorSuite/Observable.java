package sensorSuite;

import views.Observer;

public interface Observable {
    void addObserver(Observer observer);
    void notifyAllObservers();
    String getData();
}
