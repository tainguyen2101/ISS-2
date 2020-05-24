# TCSS 360 Project 2

## May 24

### New
* JUnit tested (94%)

## May 22

### New
* Added Jar file.

### Future Implementation
* JUnit testing


## May 17 2020

### New
* Stations.Station.java run the stations
* Driver.java main class that run
* Data for each station is sent over a socket from Stations.Station.java and received by GUI.GUI depends on a port
* Add 24 hours temperature graph (reset everyday)
* Add date and time
* 30 days worth of data generated.

### Future Implementation
* Reduce redundancy in Stations.Station java Thread method somehow.
* Maybe moon phase
* JUnit testing

## May 15 2020

### New
* GUI.GUI updated with graph and compass layout
* GUI.GUI is updated by a thread through a method
* 5 Thread running and update the 5 tabs on GUI.GUI

### Future Implementation
* Format the data (data right now come in raw eg. 100 200, need to be divided by 10 or 100)
* Graph implementation

## May 14 2020

### New
* runStation() for 5 thread and user input. This decouple and simplify main method.
* Layout for GUI.GUI created.
* Add Barometric sensor.

## May 13 2020 Update 2

### New
* Fix group 2 implementation of inside data
* Allow user to choose a weather station to run instead of all at the same time.

### Future Implementation
* GUI.GUI that has a side menu for user to choose which weather station to display
* Be able to sent data through a socket from ISS to Console
* GUI.GUI received data from 5 weather station simultaniously through different socket

## May 13 2020

### New:
* 5 weather staion at different time interval
* 5 different thread for each station
* These thread does not sleep because update interval is define in another function

### Need to fix:
* Group 2 implement one sensor class for both outside and inside data.
* Need to implement another function that read inside.txt for this
* For now inside data is set within function.
