import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.ArrayList;

/**
 * GUI class.
 * 
 * @author Ford Nguyen
 */
public class GUI {

    private final JFrame myFrame;

    private final JTabbedPane myTabs;

    private TabComponent c1, c2, c3, c4, c5;


    /**
     * Parameterless constructor.
     */
    public GUI() {
        myFrame = new JFrame("Console");
        c1 = new TabComponent();
        c2 = new TabComponent();
        c3 = new TabComponent();
        c4 = new TabComponent();
        c5 = new TabComponent();
        myTabs = createTabs();
        setUp();
    }

    /**
     * test run.
     * 
     * @param args
     */

    /**
     * set up myFrame Close on exit add Tabs pane set size set visible
     */
    private void setUp() {
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.add(myTabs);
        myFrame.setSize(new Dimension(800, 450));
        myFrame.pack();
        myFrame.setVisible(true);
    }

    /**
     * crete 5 tabs for 5 station.
     * 
     * @return JTabbedPanel have 5 JPanel
     */
    private JTabbedPane createTabs() {
        final JTabbedPane theTabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        theTabs.addTab("Station 1", c1.getDisplay());
        theTabs.addTab("Station 2", c2.getDisplay());
        theTabs.addTab("Station 3", c3.getDisplay());
        theTabs.addTab("Station 4", c4.getDisplay());
        theTabs.addTab("Station 5", c5.getDisplay());

        return theTabs;
    }
    public void updateTab(String data, int index) {
        switch (index) {
            case 1:
                c1.updateDisplay(data);
            case 2:
                c2.updateDisplay(data);
            case 3:
                c3.updateDisplay(data);
            case 4: 
                c4.updateDisplay(data);
            case 5:
                c5.updateDisplay(data);
        }
        myTabs.revalidate();
        myTabs.repaint();
        myFrame.revalidate();
        myFrame.repaint();
        myFrame.pack();
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
    private final String windSpeed;

    /**
     * Variable to store radian value when converted from degrees.
     */
    private final double radian;

    /**
     * The x-value in the data point.
     */
    private final double x;

    /**
     * The y-value in the data point.
     */
    private final double y;

    /**
     * Parameterized constructor.
     * 
     * @param theSpeed     current wind speed value.
     * @param theDirection the direction of the wind.
     */
    public makeCompass(final int theSpeed, final int theDirection) {
		
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
class TabComponent {

    private JTextArea myTemp, myBaro, myRain, myHumid, myTempIn, myHumidIn, myChill;

    private JPanel myDisplay;

    private JPanel myGraphDisplay;

    private JPanel myNumDisplay;

    private JPanel myCompass;

    private JPanel myGraph;


    public TabComponent() {
        myGraphDisplay = new JPanel();
        myNumDisplay = new JPanel();
        myDisplay = new JPanel();
        myDisplay = createPanel();
        myDisplay.setSize(myDisplay.getPreferredSize());
    }

    /**
     * create JPanel containing a buttons group on the right.
     * @return mainPanel.
     */
    private JPanel createPanel() {
        final JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createButtonPanel(), BorderLayout.EAST);
        mainPanel.add(createDisplayPanel(), BorderLayout.WEST);
        return mainPanel;
    }

    /**
     * create new Button group with GridLayout(6 rows, 2 column).
     * @return buttonPanel.
     */
    private JPanel createButtonPanel() {
        final JPanel buttonPanel = new JPanel(new GridLayout(6,2));
        buttonPanel.add(new JButton("Bar"));
		buttonPanel.add(new JButton("Hum"));
		buttonPanel.add(new JButton("RainDay"));
		buttonPanel.add(new JButton("Temp"));
		buttonPanel.add(new JButton("Wind"));			
		buttonPanel.add(new JButton("2ND"));
		buttonPanel.add(new JButton("Forecast"));
		buttonPanel.add(new JButton("Graph"));
		buttonPanel.add(new JButton("Hi/Low"));
		buttonPanel.add(new JButton("RainYr")); 
		buttonPanel.add(new JButton("Alarm"));
		buttonPanel.add(new JButton("Done"));
        
        return buttonPanel;
    }

    /**
     * Create display panel
     */
    private JPanel createDisplayPanel() {
		final JPanel theDisplay = new JPanel();

		myGraphDisplay.setLayout(new BorderLayout());   
		myNumDisplay.setLayout(new GridLayout(3,3));
		theDisplay.setLayout(new BorderLayout());

		myTemp = new JTextArea("TEMP OUT\n <value>");
		myBaro = new JTextArea("BARO\n <value>");
		myHumid = new JTextArea("HUMIDITY\n <value>");
		myRain = new JTextArea("RAIN RATE\n <value>");
		myTempIn = new JTextArea("TEMP IN\n <value>");
		myHumidIn = new JTextArea("HUM IN\n <value>");
		myChill = new JTextArea("CHILL\n <value>");


		myNumDisplay.add(myTemp);
		myNumDisplay.add(myBaro);
		myNumDisplay.add(myHumid);
		myNumDisplay.add(myTempIn);
		myNumDisplay.add(myHumidIn);
		myNumDisplay.add(myChill);
		myNumDisplay.add(myRain);


		myCompass = new makeCompass(0,0);
		myGraph = new makeGraphTemp();

		myGraphDisplay.add(myCompass, BorderLayout.NORTH);
		myGraphDisplay.add(myGraph, BorderLayout.SOUTH);


		theDisplay.add(myGraphDisplay, BorderLayout.WEST);
		theDisplay.add(myNumDisplay, BorderLayout.EAST);

		return theDisplay;
    }

    public JPanel getDisplay() {
        return myDisplay;
    }

    public void updateDisplay(String data) {

        String[] dataArray = data.split(" ", 8);

        
        
        double temp = Double.parseDouble(dataArray[2]); // outside temperature
        double windSpd = Double.parseDouble(dataArray[0]); // wind speed

        double chill = 35.74 + 0.6215 * temp - (35.75 * (Math.pow(windSpd, 0.16))) 
                + (0.4275 * temp * (Math.pow(windSpd, 0.16)));
        
        
        myTemp.setText("TEMP OUT\n " + temp + "\u00B0" + "F");
        myBaro.setText("BARO\n " + Double.parseDouble(dataArray[4]) + " in");
        myHumid.setText("HUM OUT\n " + Double.parseDouble(dataArray[3]) + "%");
        myRain.setText("RAIN RATE\n " + Double.parseDouble(dataArray[5]) + " in/hr");
        myTempIn.setText("TEMP IN\n " + Double.parseDouble(dataArray[6])  + "\u00B0" + "F");
        myHumidIn.setText("HUM IN\n " +Double.parseDouble(dataArray[7]) + "%");
        myChill.setText("CHILL\n" + String.format("%.2f",chill) + "\u00B0" + "F");

        //myGraph = new makeGraph(toArray());
        myCompass = new makeCompass((int) windSpd, Integer.parseInt(dataArray[1]));
        myGraphDisplay.removeAll();
        myGraphDisplay.add(myCompass, BorderLayout.NORTH);
        myGraphDisplay.add(myGraph, BorderLayout.SOUTH);

        myGraphDisplay.setSize(myGraphDisplay.getPreferredSize());
        myGraphDisplay.revalidate();
        myDisplay.revalidate();
        myGraphDisplay.repaint();
        myDisplay.repaint();


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