import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A JFrame containing components which display various weather statistics.
 * Requires the Sensors class to function properly.
 * @author Sean Robinson
 * @version 16APR2020
 */
public class MainConsole extends JFrame {

	/**	 Serial Version UID. */
	private static final long serialVersionUID = -2537044402683960271L;

	/** The width of this the western panel. */
	private static final int MY_PANEL_WIDTH = 350;

	/** The height of the western panel. */
	private static final int MY_PANEL_HEIGHT = 500;

	/** A graph showing weather statistics. */
	private static Graph myGraph;

	/** Shows a message. */
	private static MessageDisplay myMessageDisplay;

	/** Shows the wind direction and speed. */
	private static WindDisplay myWindDisplay;

	/** The leftmost panel which holds the graph and wind display. */
	private static JPanel myWestPanel;

	/** A panel which will display various weather statistics. */
	private static WeatherStatistics myWeatherStatistics;
	
	/** The currently displayed messsage. */
	private static String myMessage;

	/** The temperature history.*/
	private static ArrayDeque<String> myTempHistory;
	
	/** The humidity history.*/
	private static ArrayDeque<String> myBarHistory;
	
	/** The barometric history.*/
	private static ArrayDeque<String> myHumHistory;
	
	/** The rain history. */
	private static ArrayDeque<String> myRainHistory;
	
	/** The currently displayed history. */
	private static ArrayDeque<String> myCurrentHistory;

	/** The currently displayed graph message. */
	private static String myGraphMessage;

	/** The currently displayed windspeed. */
	private static String myWindSpeed;

	/** The currently displayed wind direction. */
	private static double myWindDirection;

	/** The currently displayed forecast. */
	private static ForeCast myForeCast;

	/** The currently displayed moon phase. */
	private static MoonPhase myMoonPhase;

	/** The labels of the currently displayed stats. */
	private static String[] myLabels;

	/** The currently displayed values.*/
	private static String[] myValues;
	
	/** The buttons for this GUI. */
	private static JButton[] myButtons;
	
	/** The eastern panel which contains buttons. */
	private static JPanel myEastPanel; 

	/** The units for the temperature (celcius or fahreneheit). */
	private static String myTempUnit;
	
	/** The units for the barometric pressure. */
	private static String myBarUnit;
	
	/** Format for the statistic values. */
	private static DecimalFormat myDecimalFormat;
	
	//data field initialization
	static {
		myMessage = "Test";
		myGraphMessage = "Past 25 Temperature Readings.";
		myTempHistory = new ArrayDeque<String>();
		myBarHistory = new ArrayDeque<String>();
		myHumHistory = new ArrayDeque<String>();
		myRainHistory = new ArrayDeque<String>();
		myCurrentHistory = myTempHistory;
		myDecimalFormat = new DecimalFormat("####.##");
		myWindSpeed = "0";
		myWindDirection = 0;
		myForeCast = ForeCast.RAIN;
		myMoonPhase = MoonPhase.NEWMOON;
		myLabels = new String[8];
		myValues = new String[8];
		myTempUnit = "\u2109";
		myBarUnit = "in";
	}

	//label and myValues initialization.
	static {
		myLabels[0] = new String("Temp Out");
		myLabels[1] = new String("Hum Out");
		myLabels[2] = new String("Barometer");
		myLabels[3] = new String("Temp In");
		myLabels[4] = new String("Hum In");
		myLabels[5] = new String("Chill");
		myLabels[6] = new String("Daily Rain");
		myLabels[7] = new String("Rain MO");
		for (int i = 0; i < 8; i++) {
			myValues[i] = "" + i;
		}
	}
	
	//Panel initialization.
	static {
		myGraph = new Graph();
		myWindDisplay = new WindDisplay(myWindSpeed, myWindDirection);
		myMessageDisplay = new MessageDisplay(myMessage);
		myWeatherStatistics = new WeatherStatistics();
		myWestPanel = new JPanel();
		myEastPanel = new JPanel();
	}
	
	//Button initialization.
	static {
		myButtons = new JButton[12];
		myButtons[0]  = new JButton("TEMP / HEAT");
		myButtons[1]  = new JButton("2ND / LAMPS");
		myButtons[2]  = new JButton("HUM / DEW");
		myButtons[3]  = new JButton("FORECAST / TIME");
		myButtons[4]  = new JButton("WIND / CHILL");
		myButtons[5]  = new JButton("GRAPH / UNITS");
		myButtons[6]  = new JButton("RAINday / SOLAR");
		myButtons[7]  = new JButton("HI/LOW / CLEAR");
		myButtons[8]  = new JButton("RAINyr / UV");
		myButtons[9]  = new JButton("ALARM / SET");
		myButtons[10]  = new JButton("BAR / ET");
		myButtons[11]  = new JButton("DONE");
		myButtons[1].setEnabled(false);
		myButtons[2].setEnabled(false);
		myButtons[3].setEnabled(false);
		myButtons[4].setEnabled(false);
		myButtons[6].setEnabled(false);
		myButtons[7].setEnabled(false);
		myButtons[8].setEnabled(false);
		myButtons[9].setEnabled(false);
		myButtons[10].setEnabled(false);
		myButtons[11].setEnabled(false);
	}

	/**
	 * Set various properties for the components and add them to this Frame.
	 */
	public MainConsole() {
		myWestPanel.setPreferredSize(new Dimension(MY_PANEL_WIDTH, MY_PANEL_HEIGHT));
		myWestPanel.setLayout(new BorderLayout());
		myWestPanel.add(myWindDisplay, BorderLayout.NORTH);
		myWestPanel.add(myGraph, BorderLayout.CENTER);
		
		myEastPanel.setPreferredSize(new Dimension(300, 500));
		myEastPanel.setLayout(new GridLayout(6, 2));
		
		for (int i = 0; i < 12; i++) {
			myEastPanel.add(myButtons[i]);
		}

		add(myMessageDisplay, BorderLayout.SOUTH);
		add(myWestPanel, BorderLayout.WEST);
		add(myWeatherStatistics, BorderLayout.CENTER);
		add(myEastPanel, BorderLayout.EAST);

		addButtonListeners();
		
		pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private static void addButtonListeners() {
		myButtons[0].addActionListener(e -> {
			changeTempUnit();
		});
		myButtons[5].addActionListener(e -> {
			changeGraphType();
		});
		/*myButtons[10].addActionListener( e -> {
			changeBarUnit();
		});*/
	}
	
	/**
	 * Calculate the radian wind direction from a cardinal direction.
	 * @param theCardinalDirection The cardinal direction of the wind.
	 */
	private static void calculateWindDirection(final String theCardinalDirection) {
		String cardinalDirection = theCardinalDirection.toLowerCase();
		switch (cardinalDirection) {
		case "north"     :	myWindDirection = Math.PI / 2;
							break;
		case "northeast" :	myWindDirection = Math.PI / 4;
							break;
		case "east"      :	myWindDirection = 0;
							break;
		case "southeast" :	myWindDirection =  Math.PI * 1.75;
							break;
		case "south"     :	myWindDirection = Math.PI * 1.5;
							break;
		case "southwest" :	myWindDirection = Math.PI * 1.25;
							break;
		case "west"      :	myWindDirection = Math.PI;
							break;
		case "northwest" :	myWindDirection = Math.PI * 0.75;
							break;
		default          : 	myWindDirection = 0;
							break;
		}
	}

	/**
	 * Update the values of the graph.
	 */
	private static synchronized void updateGraph() {
		double[] values = new double[25];
		int count = 0;
		double highest = 0;
		for (String data : myCurrentHistory) {
			values[count] = Double.parseDouble(data);
			if (values[count] > highest) {
				highest = values[count];
			}
			count++;
		}
		for (int i = count; i < 25; i++) {
			values[i] = 0;
		}
		myGraph.setStats(values, myGraphMessage, highest);
	}
	
	/**
	 * Changes the currently displayed graph data depending on the previous type.
	 */
	private static void changeGraphType() {
		if (myCurrentHistory == myTempHistory) {
			myCurrentHistory = myHumHistory;
			myGraphMessage = "Past 25 Humidity Readings.";
		} else if (myCurrentHistory == myHumHistory) {
			myCurrentHistory = myBarHistory;
			myGraphMessage = "Past 25 Barometric Readings.";
		} else {
			myCurrentHistory = myTempHistory;
			myGraphMessage = "Past 25 Temperature Readings.";
		}
		updateGraph();
	}
	
	/**
	 * Set the display message
	 */
	private static void setMessage() {
		if (Integer.parseInt(myWindSpeed) > 30) {
			myMessageDisplay.setMessage(myForeCast.toString() + "      HIGH WINDS");
		} else {
			myMessageDisplay.setMessage(myForeCast.toString() + "   " + myMoonPhase.toString());
		}
	}
	
	/**
	 * Calculate the wind chill from the outside temperature and the wind speed.
	 * @theTemp The temperature used in the calculation.
	 */
	private static void calculateChill(final String theTemp) {
		try {
			myValues[5] = myDecimalFormat.format((Double.parseDouble(theTemp) 
					    - Double.parseDouble(myWindSpeed) * 0.1)) + " " + myTempUnit;
		} catch (Exception e) {
			e.printStackTrace();
		}		 
	}
	
	/**
	 * Change the units from Fahrenheit to Celsius and vice versa.
	 */
	private static void changeTempUnit() {
		String val1;
		String val2;
		String val3;
		if (myTempUnit.equals("\u2109")) {
			myTempUnit = "\u2103";
		} else {
			myTempUnit = "\u2109";
		}
		try {
		val1 = "" + Double.parseDouble(myValues[0].substring(0, myValues[0].indexOf(" ")).trim());
		val2 = "" + Double.parseDouble(myValues[3].substring(0, myValues[3].indexOf(" ")).trim());
		val3 = "" + Double.parseDouble(myValues[5].substring(0, myValues[5].indexOf(" ")).trim());
		if (myTempUnit.equals("\u2103")) {
			val1 = convertToCelcius(val1);
			val2 = convertToCelcius(val2);
			val3 = convertToCelcius(val3);
		} else {
			val1 = convertToFahrenheit(val1);
			val2 = convertToFahrenheit(val2);
			val3 = convertToFahrenheit(val3);
		}
		myValues[0] = val1 + " " + myTempUnit;
		myValues[3] = val2 + " " + myTempUnit;
		myValues[5] = val3 + " " + myTempUnit;
		myWeatherStatistics.setStats(myLabels, myValues);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Change the barometric measurement unit.
	 *
	private static void changeBarUnit() {
		try {
			double value = Double.parseDouble(myValues[2].substring(0, myValues[5].indexOf(" ")).trim());
			double conversion;
			if ("in".equals(myBarUnit)) {
				myBarUnit = "mm";
				conversion = value * 25.4;
			} else if ("mm".equals(myBarUnit)) {
				myBarUnit = "mb";
				conversion = value * 1.333224;
			} else if ("mb".equals(myBarUnit)) {
				myBarUnit = "hPa";
				conversion = value;
			} else { //hPa
				myBarUnit = "in";
				conversion = value * 0.02953;
			}
			myValues[2] = conversion + " " + myBarUnit;
			myWeatherStatistics.setStats(myLabels, myValues);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	} */
	
	/**
	 * Calculate the weather forecast based on certain weather variables.
	 */
	private static void setForeCast() {
		double value1;
		int value2;
		double value3;
		try {
		value1 = Double.parseDouble(myValues[0].substring(0, myValues[0].indexOf(" ")));
		value2 = Integer.parseInt(myValues[1].substring(0, myValues[1].indexOf(" ")));
		value3 = Double.parseDouble(myValues[2].substring(0, myValues[2].indexOf(" ")));
			if (value1 < 32) {
				myForeCast = ForeCast.SNOW;
			} else if (value2 > 35) {
				myForeCast = ForeCast.RAIN;
			} else if ((value3 > 40 && "in".equals(myBarUnit))) {
				myForeCast = ForeCast.MOSTLYCLOUDY;
			} else if (value1 < 50) {
				myForeCast = ForeCast.PARTLYCLOUDY;
			} else {
				myForeCast = ForeCast.MOSTLYCLEAR;
			}
			myWeatherStatistics.setForeCast(myForeCast);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Calculate a random moon phase.
	 */
	private static void setMoonPhase() {
		myMoonPhase = MoonPhase.getRandom();
		myWeatherStatistics.setMoonPhase(myMoonPhase);
	}
	
	/**
	 * Convert from celsius to fahrenheit
	 * @param theTemp the fahrenheit temp.
	 * @return The celsius temp.
	 */
	public static String convertToFahrenheit(final String theTemp) {
		String temp = "";
		double fahr;
		try {
			fahr = (Double.parseDouble(theTemp) * 1.8) + 32;
			fahr = Math.round(fahr);
			temp = myDecimalFormat.format(fahr);		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	/**
	 * convert from fahrenheit to celsius
	 * @param theTemp the celsius temp.
	 * @return The fahrenheit temp.
	 */
	public static String convertToCelcius(final String theTemp) {
		String temp = "";
		try {
			temp = myDecimalFormat.format((Double.parseDouble(theTemp) - 32) / 1.8);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	/**
	 * Find the average monthly rain rate.
	 * @return The average monthly rain rate.
	 */
	private static String calculateAverageRain() {
		double average = 0;
		try {
			Object[] values = myRainHistory.toArray();
			for (int i = 0; i < myRainHistory.size(); i++) {
				average += Double.parseDouble((String) values[i]);
			}
		} catch (NumberFormatException | ClassCastException e) {
			e.printStackTrace();
		}
		average = average / myRainHistory.size();
		return  myDecimalFormat.format(average);
	}
	
	/**
	 * Start the GUI.
	 * @param theArgs Command line arguments aren't used.
	 */
	public static void main(final String[] theArgs) {
		//The port number
		final int socketAddress = 9876;
		//The host IP address
		final String host = "127.0.0.1";
		//The delay for information retrieval
		final int threadDelay = 1000;
		//A Timer to update the current date and time
		final Timer timer = new Timer();
		
		//Start the GUI
		EventQueue.invokeLater(() -> {	
			new MainConsole();		
		});
		
		//Retrieve data from the sensors every x milliseconds and update the display
		new Thread(new Runnable() {
	        public void run() {
	            while (true) {
	        	    try {       
	                    var socket = new Socket(host, socketAddress);
	                    var in = new Scanner(socket.getInputStream());
	                    
	                    String[] sensors = in.nextLine().split(" ");
	                    String windSpeed = sensors[0];
	                    String windDirection = sensors[1];
	                    String temperature = sensors[2];
	                    String humidity = sensors[3];
	                    String bar = sensors[4];
	                    String rain = sensors[5];
	                    System.out.println("Wind Speed: " + windSpeed);
	                    System.out.println("Wind Direction: " + windDirection);
	                    System.out.println("Temperature: " + temperature);
	                    System.out.println("Humidity: " + humidity);
	                    System.out.println("Barometric Pressure: " + bar);
	                    System.out.println("Daily rain: " + rain);
	                    
	                  //if temp is in Fahrenheit, calculate Celsius value
	                    if ("\u2103".equals(myTempUnit)) {
	                    	temperature = convertToCelcius(temperature);
	                    }
	                    
	                    //update graph display values
	                    if(myTempHistory.size() == 24) {
	    		        	myTempHistory.remove();
	    		        }  
	                    myTempHistory.add(temperature);
	                    
	                    if(myBarHistory.size() == 24) {
	    		        	myBarHistory.remove();
	    		        }  
	                    myBarHistory.add(bar);
	                    
	                    if(myHumHistory.size() == 24) {
	    		        	myHumHistory.remove();
	    		        }  
	                    myHumHistory.add(humidity);
	                    
	                    if(myRainHistory.size() == 30) {
	    		        	myRainHistory.remove();
	    		        }  
	                    myRainHistory.add(rain);
	                    updateGraph(); 
	                    
	                    //update weatherstats display
	                    myValues[0] = temperature + " " + myTempUnit;
	                    myValues[1] = humidity + " %";
	                    myValues[2] = bar + " " + myBarUnit;
	                    myValues[3] = myDecimalFormat.format((Double.parseDouble(temperature) - 10)) + " " + myTempUnit; 
	                    myValues[4] = (Integer.parseInt(humidity) - 5) + " %"; 
	                    myValues[6] = rain + " in"; 
	                    myValues[7] = calculateAverageRain() + " in";
	                    calculateChill(temperature);
	                    myWeatherStatistics.setStats(myLabels, myValues);
	                    
	                    //update wind display
	                    myWindSpeed = windSpeed;
	                    calculateWindDirection(windDirection);
	                    myWindDisplay.setSpeedDirection(myWindSpeed, myWindDirection);  
	                    
	                    //update Forecast and moonphase
	                    setForeCast();
	                    setMoonPhase();
	                    
	                    //update message display
	                    setMessage();             
	                    
	                    //Close the socket and scanner, and sleep the thread
	                    in.close();
	                    socket.close();
	                    Thread.sleep(threadDelay);
	                } catch (IOException | InterruptedException e) {
	                	e.printStackTrace();
	                }
	           }              
	        }
	    }).start();		
		
		//Updates the time display every second
		timer.schedule(new TimerTask() {
			public void run() {
		        myWeatherStatistics.setDateTime(); 
			}
		}, 100, 1000);
	}
	
}
