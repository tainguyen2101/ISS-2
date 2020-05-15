# TCSS 360 Project 2

## May 15 2020

### New
* GUI updated with graph and compass layout
* Inner class created to support multiple tabs displaying the same component

### Future Implementation
* A way for 5 thread to run with 5 tabs in the GUI

## May 14 2020

### New
* runStation() for 5 thread and user input. This decouple and simplify main method.
* Layout for GUI created.
* Add Barometric sensor.

## May 13 2020 Update 2

### New
* Fix group 2 implementation of inside data
* Allow user to choose a weather station to run instead of all at the same time.

### Future Implementation
* GUI that has a side menu for user to choose which weather station to display
* Be able to sent data through a socket from ISS to Console
* GUI received data from 5 weather station simultaniously through different socket

## May 13 2020

### New:
* 5 weather staion at different time interval
* 5 different thread for each station
* These thread does not sleep because update interval is define in another function

### Need to fix:
* Group 2 implement one sensor class for both outside and inside data.
* Need to implement another function that read inside.txt for this
* For now inside data is set within function.
