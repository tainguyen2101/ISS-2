package Console;

/**
 * Class that builds the GUI. 
 */

import ISS.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.geom.Ellipse2D;

/**
 * Build the components of the GUI object to be used in main program.
 * 
 * @author Group 4
 * @version Spring 2020
 */
public class GUI {

	/**
     * Sets the size of top-level container.
     */
	private static final Toolkit TOOL = Toolkit.getDefaultToolkit();

	/**
     * Set the screen size using the Toolkit class getScreenSize method.
     */
	private static final Dimension SCREEN_SIZE = TOOL.getScreenSize();

	/**
	 * Format the time with hour and minute.
	 */
	private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

	/**
	 * Format the date with month and day.
	 */
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd");

	/**
     * Create JFrame object to display GUI.
     */
	private final JFrame myFrame;

	/**
	 * Immutable data-time object. 
	 */
	private LocalDateTime time, date;

	 /**
     * Containers to organize components. 
     */
	private JPanel myDisplay, tempInfo, graphInfo;

	 /**
     * An arraylist of JButton components.  
     */
	private final ArrayList<JButton> myButton;

	/**
	 * Multi-line area that displays plain text.
	 */
	private JTextArea myDate, myTime, myTemp, myBaro, myRain, myHumid, myTempIn, myHumidIn, myChill;

	/**
	 * Containers to organize graph and compass components.
	 */
	private JPanel myGraph, myCompass;

	/**
	 * Contains the 24 hour interval data. 
	 */
	static double [][] dataMemory; 

	/**
	 * Index of 2D array of the current data. 
	 */
	static int currentMemIndex;      

	/**
	 * Choose the index of the variable you want to graph.
	 */
	static int graphVariable;		

	/**
	 * Every 60 data points, which represents 1 hr, then data is added to the 2D array.
	 */
	static int hour;

	/**
     * Non-parameterized constructor to initialize some instance fields.
	 * 
	 * @throws Exception 
     */
	public GUI() throws Exception {
		super();
		myFrame = new JFrame("ISS");
		graphInfo = new JPanel();
		tempInfo = new JPanel();
		myButton = new ArrayList<JButton>();
		dataMemory = new double[7][26];
		setupDataArray();
		setUp();
	}

	/**
     * Set up and configure the GUI with all components.  
     * 
     * @throws Exception
     */
	private void setUp() throws Exception {
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.add(createPanel());
		myFrame.setMinimumSize(myFrame.getPreferredSize());
		myFrame.setLocation(SCREEN_SIZE.width / 8, (int) (SCREEN_SIZE.getHeight() / 8));
		myFrame.setVisible(true);
	}

	/**
     * Create and add labels to panel that include data on temperature,
     * barometric pressure, humidity, and rain fall/rate.
     * 
     * @return a panel consisting of labels which display data.
     * @throws Exception
     */
	private JPanel createPanel() throws Exception {
		final JPanel mainPanel = new JPanel(new BorderLayout());
		final JPanel buttonPanel = createButtonPanel();
		myDisplay = createDisplay();
		mainPanel.add(myDisplay, BorderLayout.WEST);
		mainPanel.add(buttonPanel, BorderLayout.EAST);
		return mainPanel;
	}

	/**
     * Create a panel which will have buttons for the different 
     * types of data that can be displayed on the console.
     * 
     * @return a panel consisting of buttons for the different data types.
     */
	private JPanel createButtonPanel() {
		final JPanel buttonPanel = new JPanel(new GridLayout(6, 2));
		myButton.add(new JButton("Bar"));
		myButton.add(new JButton("Hum"));
		myButton.add(new JButton("RainDay"));
		myButton.add(new JButton("Temp"));
		myButton.add(new JButton("Wind"));
		
		
		/*myButton.add(new JButton("2ND"));
		myButton.add(new JButton("Forecast"));
		myButton.add(new JButton("Graph"));
		myButton.add(new JButton("Hi/Low"));
		myButton.add(new JButton("RainYr"));
		myButton.add(new JButton("Alarm"));
		myButton.add(new JButton("Done"));
		*/
		
		for (int i = 0; i < myButton.size(); i++) {
			buttonPanel.add(myButton.get(i));
		}
	
		myButton.get(0).addActionListener(e -> { /// Action listeners for Jbutton changes the variable on the graph
			graphVariable=0;  //Bar
		});
		myButton.get(1).addActionListener(e -> {
			graphVariable=1;  //HumidOut
		});
		myButton.get(2).addActionListener(e -> {
			graphVariable=2;	//RainRate
		});
		myButton.get(3).addActionListener(e -> {
			graphVariable=3;   // temp
		});
		myButton.get(4).addActionListener(e -> {
			graphVariable=4;  //Wind Speed
		});
		

		return buttonPanel;
	}

	/**
	 * Create the display for the graph and compass.
	 * 
	 * @return a JPanel with graph and compass components.
	 */
	private JPanel createDisplay() {

		time = LocalDateTime.now();
		date = LocalDateTime.now();
		final JPanel theDisplay = new JPanel();

		graphInfo.setLayout(new BorderLayout());   
		tempInfo.setLayout(new GridLayout(3,3));
		theDisplay.setLayout(new BorderLayout());

		myTemp = new JTextArea();
		myBaro = new JTextArea();
		myHumid = new JTextArea();
		myRain = new JTextArea();
		myTempIn = new JTextArea();
		myHumidIn = new JTextArea();
		myChill = new JTextArea();
		myTime = new JTextArea(TIME_FORMAT.format(time));
		myDate = new JTextArea(DATE_FORMAT.format(date));


		tempInfo.add(myDate);
		tempInfo.add(myTime);
		tempInfo.add(myTemp);
		tempInfo.add(myBaro);
		tempInfo.add(myHumid);
		tempInfo.add(myTempIn);
		tempInfo.add(myHumidIn);
		tempInfo.add(myChill);
		tempInfo.add(myRain);


		myCompass = new makeCompass(0,0);
		myGraph = new makeGraphTemp();

		graphInfo.add(myCompass, BorderLayout.NORTH);
		graphInfo.add(myGraph, BorderLayout.SOUTH);


		theDisplay.add(graphInfo, BorderLayout.WEST);
		theDisplay.add(tempInfo, BorderLayout.EAST);

		return theDisplay;
	}

	/**
	 * Update the graph and compass display with new data. 
	 * 
	 * @param theBaro barometric pressure value.
	 * @param theHumid outside humidity value.
	 * @param theRain rain rate value.
	 * @param theTemp outside temperature value.
	 * @param theWind wind speed value.
	 * @param humidIn inside humidity value.
	 * @param tempIn inside temperature value.
	 */
	public void updateDisplay(Sensor[] sensors) {


				if(hour%60==0) {     /// Every 60 minutes data points are added to the 2D array for the graph
		    
					for (int i = 0; i < sensors.length; i++) {
						dataMemory[i][currentMemIndex] = (double) (sensors[i].getData());
					}
				
					currentMemIndex=(currentMemIndex+1)%24;
					hour=time.getHour();
					hour=hour%60;
		
				}
				hour++;
				
				double temp = sensors[3].getData(); // outside temperature
				double windSpd = sensors[4].getData(); // wind speed
		
				double chill = 35.74 + 0.6215 * temp - (35.75 * (Math.pow(windSpd, 0.16))) 
						+ (0.4275 * temp * (Math.pow(windSpd, 0.16)));
				
				
				time = LocalDateTime.now();
				date = LocalDateTime.now();
				myTime.setText(TIME_FORMAT.format(time));
				myDate.setText(DATE_FORMAT.format(date));
				myTemp.setText("TEMP OUT\n " + temp + "\u00B0" + "F");
				myBaro.setText("BARO\n " + sensors[0].getData() + " in");
				myHumid.setText("HUM OUT\n " + sensors[1].getData() + "%");
				myRain.setText("RAIN RATE\n " + sensors[2].getData() + " in/hr");
				myTempIn.setText("TEMP IN\n " + sensors[6].getData()  + "\u00B0" + "F");
				myHumidIn.setText("HUM IN\n " + sensors[5].getData() + "%");
				myChill.setText("CHILL\n" + String.format("%.2f",chill) + "\u00B0" + "F");
		
				myGraph = new makeGraph(toArray());
				myCompass = new makeCompass((int) windSpd, ((Wind) sensors[4]).getMyDirection());
				graphInfo.removeAll();
				graphInfo.add(myCompass, BorderLayout.NORTH);
				graphInfo.add(myGraph, BorderLayout.SOUTH);
		
				graphInfo.setSize(graphInfo.getPreferredSize());
				graphInfo.revalidate();
				myDisplay.revalidate();
				graphInfo.repaint();
				myDisplay.repaint();
				myFrame.setMinimumSize(myFrame.getPreferredSize());
				myFrame.revalidate();
				myFrame.repaint();
		
			}

	/**
	 * Fills an array with data points to be used for graph.
	 * 
	 * @return an array of data points.
	 */
	private int[] toArray() {
		
		final int[] result = new int[26];//// Creates array to be sent to the graph
		double min =  dataMemory[graphVariable][24];   /// Max and min change the data to fit the graph
		double max = dataMemory[graphVariable][25];
		for (int i = 0; i < 24; i++) {
			result[i] = (int) (8*(dataMemory[graphVariable][(i+currentMemIndex)%24]/((max-min)/17)));
		
		}



		result[24]=(int) dataMemory[graphVariable][24];     // Min of variable
		result[25]=(int) dataMemory[graphVariable][25];		// Max of variable 
		return result;
	}

	/**
	 * Fills in the 2D array with the passed data.
	 * 
	 * @param data the data to fill the 2D array with.
	 */
	public static void dataMemory(double[] data) {     // Creates the 2D array that holds 24 hr information
		currentMemIndex=(currentMemIndex+1)%24;			// after 24 hrs the data rewrites over old data
		for(int i =0; i< dataMemory.length;i++) {
			for(int k = 0; k<data.length; k++) {
				dataMemory[k][currentMemIndex]=data[k];
			}
		}
	}

	/**
	 * Fill the 2D array with a negative placeholder value.
	 */
	public static void setupDataArray() {
		for(int i =0; i<dataMemory.length;i++) {  // Fill the 2D array with negative, negative number represents null value
			for (int k = 0;  k<dataMemory[i].length; k++) {
				dataMemory[i][k]=-1;				// If value is negative then it does not get graphed. Initial set up.
			} 
		}
		
		//Change max and min of variable to adjust variance of data representation on graph
		
		//BARO 
		dataMemory[0][24]=0; //MIN
		dataMemory[0][25]=50; //MAX

		//HUMID OUT
		dataMemory[1][24]=0;   
		dataMemory[1][25]=100;

		// RAIN
		dataMemory[2][24]=0;   
		dataMemory[2][25]=5;

		//TEMPOUT
		dataMemory[3][24]=40;  
		dataMemory[3][25]=130;

		//WINDSPEED
		dataMemory[4][24]=0;   
		dataMemory[4][25]=30;

		//HUMIDIN
		dataMemory[5][24]=0;   
		dataMemory[5][25]=100;

		//TEMPIN
		dataMemory[6][24]=50;   
		dataMemory[6][25]=90;

	}




}

/**
 * Class which creates and displays the data points on the graph.
 * 
 * @author Group 4
 * @version Spring 2020
 */
class makeGraph extends JPanel {
	
	/**
	 * Serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Data to be used for graph. 
	 */
	private int[] data;
	
	/**
	 * An ArrayList that stores Shape objects.
	 */
	private final ArrayList<Shape> shapes = new ArrayList<>();
	
	/**
	 * The width of the panel.
	 */
	private static final int WIDTH = 300;

	/**
	 * The height of the panel.
	 */
	private static final int HEIGHT = 200;

	/**
	 * Parameterized constructor. 
	 * 
	 * @param theData to be used for the graph.
	 */
	public makeGraph(final int[] theData) {
		super();
		this.data = theData;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		final Shape rectangle = new Rectangle2D.Double(10, 10, getWidth() - 20, getHeight() - 20);
		g2d.draw(rectangle);

		for (int i = 0; i < 24; i++) {

			if(data[i]>=0) {
				shapes.add(new Ellipse2D.Double(((getWidth() / 24) * i) + 10, getHeight() - 16 - data[i], 5, 5));
			}
		}
		for (int i = 0; i < shapes.size(); i++) {
			g2d.fill(shapes.get(i));
			g2d.draw(shapes.get(i));
		}
	}




}

/**
 * Create the graph to display on the GUI.
 * 
 * @author Group 4
 * @version Spring 2020
 */
class makeGraphTemp extends JPanel {

	/**
	 * Serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The width of the panel.
	 */
	private static final int WIDTH = 300;

	/**
	 * The height of the panel.
	 */
	private static final int HEIGHT = 200;

	/**
	 * Non-parameterized constructor. 
	 */
	public makeGraphTemp() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		final Shape rectangle = new Rectangle2D.Double(10, 10, getWidth() - 20, getHeight() - 20);
		g2d.draw(rectangle);
	}
}

/**
 * Create the compass to display on the GUI.
 * 
 * @author Group 4
 * @version Spring 2020
 */
class makeCompass extends JPanel {

	/**
	 * Serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The width of the panel.
	 */
	private static final int WIDTH = 300;

	/**
	 * The height of the panel.
	 */
	private static final int HEIGHT = 200;

	/**
	 * The current windspeed.
	 */
	private String windSpeed;

	/**
	 * Variable to store radian value when converted from degrees.
	 */
	private double radian;

	/**
	 * The x-value in the data point.
	 */
	private double x;

	/**
	 * The y-value in the data point.
	 */
	private double y;
	
	/**
	 * Parameterized constructor. 
	 * 
	 * @param theSpeed current wind speed value.
	 * @param theDirection the direction of the wind. 
	 */
	public makeCompass(int theSpeed, int theDirection) {
		
		super();
		this.windSpeed = Integer.toString(theSpeed);
	
		radian = ((450-theDirection)%360)*Math.PI/180;	// converts compass degrees to radians
		x=80*Math.cos(radian);							// Radius=80
		y=80*Math.sin(radian);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		final Shape circle = new Ellipse2D.Double(getWidth()/8, 0, getHeight(), getHeight());
		final Shape circle2 = new Ellipse2D.Double((getWidth()/8)+14, 14, 
				getHeight()-28,getHeight()-28);
		g2d.draw(circle);
		g2d.draw(circle2);

		g2d.drawString("o", (getWidth()/2-16)+(int)x, getHeight()/2+5 -(int) y);  /// Need to add better image for pointer
	
		g2d.drawString("Wind", 10,15);
		g2d.drawString("N", getWidth()/2-16, 12);
		g2d.drawString("S", getWidth()/2-16, getHeight()-2);
		g2d.drawString("W", getWidth()/8+2, getHeight()/2);
		g2d.drawString("E", getWidth()-(getWidth()/4), getHeight()/2);
		g2d.drawString(windSpeed, (getWidth()/2)-15, getHeight()/2);
		g2d.drawString("MPH", (getWidth()/2)-25, (getHeight()/2)+15);
	}

}


