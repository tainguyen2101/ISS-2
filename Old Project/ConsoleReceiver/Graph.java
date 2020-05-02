import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * Displays a graph on the console receiver.
 * @author Sean Robinson
 * @version 16APR2020
 */
public class Graph extends JComponent {

	/** Serial Version UID. */
	private static final long serialVersionUID = -1601288328104021943L;

	/** The width of this component. */
	private static final int MY_WIDTH = 350;

	/** The height of this component. */
	private static final int MY_HEIGHT = 225;

	/** The amount of stats that will be displayed. */
	private static final int MY_STAT_SIZE = 25;

	/** The x and y offset for the graphics. */
	private static final int MY_OFFSET = 20;

	/** The stats that will be displayed. */
	private double[] myStats;

	/** A message about the state of the graph. */
	private String myMessage;

	/**
	 * Set the default message and statistics.
	 */
	public Graph() {
		myStats = new double[MY_STAT_SIZE];
		for (int i = 0; i < MY_STAT_SIZE; i++) {
			myStats[i] = (double) i / MY_STAT_SIZE;
		}
		myMessage = " ";
		setPreferredSize(new Dimension(MY_WIDTH, MY_HEIGHT));
	}

	/**
	 * Set the new stat values and message and repaint.
	 * @param theStats The new stats.
	 * @param theMessage The new message to display.
	 * @param theHighest The largest value in theStats;
	 */
	public synchronized void setStats(final double[] theStats, final String theMessage, final double theHighest) {
		if (theStats.length != MY_STAT_SIZE) {
			throw new IllegalArgumentException("The array is too small in graph.setstats()");
		} else {
			for (int i = 0; i < MY_STAT_SIZE; i++) {
				myStats[i] = (theStats[i] / theHighest) * (MY_HEIGHT - 40);
				myStats[i] = (MY_HEIGHT - 40) - myStats[i];
			}	
			myMessage = theMessage;
			repaint();
		}
	}

	/**
	 * Return the graph message.
	 * @return theMessage The currently displayed message.
	 */
	public String getMessage() {
		return myMessage;
		
	}
	
	/**
	 * Return the stats for this graph.
	 * @return myStats The currently displayed stats.
	 */
	public synchronized double[] getStats() {
		return myStats;
	}
	
	/**
	 * Draws the graph and the statistic values.
	 * @param theGraphics Used to draw the graph.
	 */
	@Override
	public synchronized void paintComponent(final Graphics theGraphics) {
		super.paintComponent(theGraphics);
		theGraphics.drawString(myMessage, MY_OFFSET, MY_OFFSET);
		theGraphics.drawRect(MY_OFFSET, MY_OFFSET, MY_WIDTH - (2 * MY_OFFSET), MY_HEIGHT - (2 * MY_OFFSET));
		theGraphics.drawLine(MY_OFFSET, MY_HEIGHT / 2, MY_WIDTH - MY_OFFSET, MY_HEIGHT / 2);
		for (int i = 0; i < MY_STAT_SIZE; i++) {
			if (myStats[i] != (MY_HEIGHT - 40)) {
				theGraphics.fillOval(MY_OFFSET + i * 12, (int) (MY_OFFSET 
						+ myStats[i]), 5, 5);
			}	
		}
	}
		
}
