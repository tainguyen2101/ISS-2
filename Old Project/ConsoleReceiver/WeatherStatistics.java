import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Displays chosen weather statistics in the console receiver.
 * @author Sean Robinson
 * @version 16APR2020
 */
public class WeatherStatistics extends JPanel {

	/** Serial version UID. */
	private static final long serialVersionUID = -5215598194377084161L;

	/** This panels width. */
	private static final int MY_WIDTH = 500;

	/** This panels height. */
	private static final int MY_HEIGHT = 500;

	/** The amount of stats that are displayed. */
	private static final int MY_STAT_COUNT = 8;

	/** The amount of panels that will be used. */
	private static final int MY_LABEL_COUNT = 3;

	/** The current date and time. */
	private LocalDateTime myTime;

	/** The current moon phase. */
	private MoonPhase myMoonPhase;

	/** The current forecast. */
	private ForeCast myForeCast;

	/** The image of the current moonphase. */
	private Image myMoonPhaseImage;

	/** The image of the current forecast.*/
	private Image myForeCastImage;

	/** The uppermost panel in this display. */
	private final WeatherDatePane myTopComponent;

	/** The lower 3 panels in this display. */
	private final JPanel[] myPanels;

	/** The labels for each statistic. */
	private final JLabel[] myStatLabels;

	/** The value of each statistic. */
	private final JLabel[] myStatValues;

	/** The constraints for component layout. */
	private final GridBagConstraints myConstraints;

	/** Insets for component layout. */
	private final Insets myInsets;

	//various field initializations.
	{
		myTime = LocalDateTime.now();
		myMoonPhase = MoonPhase.FULLMOON;
		myForeCast = ForeCast.SNOW;
		myConstraints = new GridBagConstraints();
		myInsets = new Insets(0, 30, 0, 30);
		myStatValues = new JLabel[MY_STAT_COUNT];
		for (int i = 0; i < MY_STAT_COUNT; i++) {
			myStatValues[i] = new JLabel("0");
		}
	}
	
	//Stat label initialization.
	{
		myStatLabels = new JLabel[MY_STAT_COUNT];
		myStatLabels[0] = new JLabel("Temp Out");
		myStatLabels[1] = new JLabel("Hum Out");
		myStatLabels[2] = new JLabel("Barometer");
		myStatLabels[3] = new JLabel("Temp In");
		myStatLabels[4] = new JLabel("Hum In");
		myStatLabels[5] = new JLabel("Chill");
		myStatLabels[6] = new JLabel("Daily Rain");
		myStatLabels[7] = new JLabel("Rain Rate");
	}
	
	//panel initialization.
	{
		myTopComponent = new WeatherDatePane();
		myPanels = new JPanel[MY_LABEL_COUNT];
		myPanels[0] = new JPanel();
		myPanels[1] = new JPanel();
		myPanels[2] = new JPanel();
	}

	/**
	 * Sets various properties for the components, adds them to this panel,
	 * and set the layout..
	 */
	public WeatherStatistics() {
		for (int i = 0; i < MY_LABEL_COUNT; i++) {
			myPanels[i].setPreferredSize(new Dimension(MY_WIDTH, MY_HEIGHT / 5));
			myPanels[i].setLayout(new GridBagLayout());
		}
		for (int i = 0; i < MY_STAT_COUNT; i++) {
			myStatLabels[i].setFont(new Font("Dialog", Font.PLAIN, 20));
			myStatValues[i].setFont(new Font("Dialog", Font.PLAIN, 35));
		}

		myConstraints.insets = myInsets;
		layoutLabels();
		layoutValues();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(myTopComponent);
		for (int i = 0; i < MY_LABEL_COUNT; i++) {
			add(myPanels[i]);
		}
		myPanels[1].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		loadForeCastImage();
		loadMoonPhaseImage();
		setPreferredSize(new Dimension(MY_WIDTH, MY_HEIGHT));
	}

	/**
	 * Set the layout for each JLabel.
	 */
	private void layoutLabels() {
		myPanels[0].add(myStatLabels[0], myConstraints);
		myPanels[1].add(myStatLabels[3], myConstraints);
		myPanels[2].add(myStatLabels[6], myConstraints);
		myConstraints.gridx = 1;
		myPanels[0].add(myStatLabels[1], myConstraints);
		myPanels[1].add(myStatLabels[4], myConstraints);
		myPanels[2].add(myStatLabels[7], myConstraints);
		myConstraints.gridx = 2;
		myPanels[0].add(myStatLabels[2], myConstraints);
		myPanels[1].add(myStatLabels[5], myConstraints);
	}

	/**
	 * Set the layout for each statistic value.
	 */
	private void layoutValues() {
		myConstraints.gridy = 1;
		myConstraints.gridx = 0;
		myPanels[0].add(myStatValues[0], myConstraints);
		myPanels[1].add(myStatValues[3], myConstraints);
		myPanels[2].add(myStatValues[6], myConstraints);
		myConstraints.gridx = 1;
		myPanels[0].add(myStatValues[1], myConstraints);
		myPanels[1].add(myStatValues[4], myConstraints);
		myPanels[2].add(myStatValues[7], myConstraints);
		myConstraints.gridx = 2;
		myPanels[0].add(myStatValues[2], myConstraints);
		myPanels[1].add(myStatValues[5], myConstraints);
	}

	/**
	 * Load the forecast image.
	 */
	private void loadForeCastImage() {
		myForeCastImage = new ImageIcon("Images/" + myForeCast.toString() + ".png").getImage();
	}

	/**
	 * Load the moonphase image.
	 */
	private void loadMoonPhaseImage() {
		myMoonPhaseImage = new ImageIcon("Images/" + myMoonPhase.toString() + ".png").getImage();
	}

	/**
	 * Set the new moonphase and redraw.
	 * @param theMoonPhase The new moonphase.
	 */
	public void setMoonPhase(final MoonPhase theMoonPhase) {
		myMoonPhase = theMoonPhase;
		loadMoonPhaseImage();
		myTopComponent.repaint();
	}
	
	/**
	 * Returns myMoonPhase.
	 * @return The current MoonPhase.
	 */
	public MoonPhase getMoonPhase() {
		return myMoonPhase;
	}
	
	/**
	 * Set the new forecast and redraw.
	 * @param theForeCast The new forecast.
	 */
	public void setForeCast(final ForeCast theForeCast) {
		myForeCast = theForeCast;
		loadForeCastImage();
		myTopComponent.repaint();
	}

	/**
	 * Returns  myForeCast.
	 * @return The current ForeCast.
	 */
	public ForeCast getForeCast() {
		return myForeCast;
	}
	
	/**
	 * Update the date and time.
	 */
	public void setDateTime() {
		myTime = LocalDateTime.now();
		myTopComponent.updateTime();
	}
	
	/**
	 * Returns the myLocalDateTime.
	 * @return The current LocalDateTime.
	 */
	public LocalDateTime getDateTime() {
		return myTime;
	}
	
	/**
	 * Set the value of the labels and stats.
	 * @param theLabels The new Labels.
	 * @param theValues The values of the associate stats.
	 */
	public synchronized void setStats(final String[] theLabels, final String[] theValues) {
		if (theLabels.length != MY_STAT_COUNT || theValues.length != MY_STAT_COUNT) {
			throw new IllegalArgumentException("The arguments in "
					+ "WeatherStatistics.setStats() must be length 8");
		} else {
			for (int i = 0; i < MY_STAT_COUNT; i++) {
				myStatLabels[i].setText(theLabels[i]);
				myStatValues[i].setText(theValues[i]);
			}
		}
	}
	
	/**
	 * Return the text of myStatLabels[].
	 * @return A string[] containing the label text.
	 */
	public String[] getLabels() {
		String[] labels = new String[MY_STAT_COUNT];
		for (int i = 0; i < MY_STAT_COUNT; i++) {
			labels[i] = myStatLabels[i].getText();
		}
		return labels;
	}
	
	/**
	 * Return the text of myStatValues[].
	 * @return A string[] containing the value text.
	 */
	public String[] getValues() {
		String[] labels = new String[MY_STAT_COUNT];
		for (int i = 0; i < MY_STAT_COUNT; i++) {
			labels[i] = myStatValues[i].getText();
		}
		return labels;
	}

	/**
	 * This inner JComponent class creates a panel with the moonphase, forecast, date, and time.
	 * @author Sean Robinson
	 * @version 08APR2020
	 */
	private final class WeatherDatePane extends JComponent {

		/** Serial Version UID. */
		private static final long serialVersionUID = -6209001132254529804L;

		/** The current time. */
		private String myCurrentTime;

		/** The current date. */
		private String myCurrentDate;

		/** The format for the time. */
		private DateTimeFormatter myTimeFormat;

		/** The format for the date. */
		private DateTimeFormatter myDateFormat;		

		/**
		 * Sets the date and dtime format and the preferred size.
		 */
		private WeatherDatePane() {
			myTimeFormat = DateTimeFormatter.ofPattern("hh:mm a");
			myDateFormat = DateTimeFormatter.ofPattern("MM/dd");
			myCurrentTime = myTime.format(myTimeFormat);
			myCurrentDate = myTime.format(myDateFormat);
			setPreferredSize(new Dimension(MY_WIDTH, MY_HEIGHT / 5));
		}
		
		/**
		 * Update this panel to display the current time.
		 */
		private void updateTime() {
			myCurrentTime = myTime.format(myTimeFormat);
			myCurrentDate = myTime.format(myDateFormat);
			repaint();
		}
		
		/**
		 * Draws the forecast, moonphase, date, and time.
		 * @param theGraphics Used to draw on this panel.
		 */
		@Override
		public synchronized void paintComponent(final Graphics theGraphics) {
			theGraphics.drawImage(myForeCastImage, 5, 5, 80, 80, null);
			theGraphics.drawImage(myMoonPhaseImage, 85, 5, 100, 80, null);
			theGraphics.setFont(new Font("Dialog", Font.PLAIN, 25));
			theGraphics.drawString(myCurrentTime, 220, 50);
			theGraphics.drawString(myCurrentDate, 365, 50);
		}
	}

}
