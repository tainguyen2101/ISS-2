import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * A panel which displays wind direction and speed.
 * @author Sean Robinson
 * @version 16APR2020
 */
public class WindDisplay extends JPanel {

	/** Serial Version UID. */
	private static final long serialVersionUID = 2145795312838585176L;

	/** The width of this panel. */
	private static final int MY_WIDTH = 350;

	/** The height of this panel. */
	private static final int MY_HEIGHT = 263;

	/** An image of a compass for wind direction. */
	private Image myCompassImage;

	/** The current wind speed. */
	private String myWindSpeed;

	/** The current wind direction. */
	private double myWindDirection;

	/** The x offset from the compass center for wind direction. */
	private int myWindX;

	/** The y offset from the compass center for wind direction. */
	private int myWindY;

	/**
	 * Sets the wind direction and speed, sets the size, and loads the compass image.
	 * @param theWindSpeed The initial wind speed.
	 * @param theWindDirection The initial wind direction.
	 */
	public WindDisplay(final String theWindSpeed, final double theWindDirection) {
		myWindSpeed = theWindSpeed;
		myWindDirection = theWindDirection;
		calculateDirection();
		setPreferredSize(new Dimension(MY_WIDTH, MY_HEIGHT));
		loadImage();
	}

	/**
	 * Load an image of a compass.
	 */
	private void loadImage() {
		myCompassImage = new ImageIcon("Images/Compass.png").getImage();
	}

	/**
	 * Sets the wind speed and direction and repaints this component.
	 * @param theWindSpeed The new wind speed.
	 * @param theWindDirection The new wind direction.
	 */
	public synchronized void setSpeedDirection(final String theWindSpeed, final double theWindDirection) {
		myWindSpeed = theWindSpeed;
		myWindDirection = theWindDirection;
		calculateDirection();
		repaint();
	}
	
	/**
	 * Return the wind direction.
	 * @return The current wind direction.
	 */
	public double getDirection() {
		return myWindDirection;
	}
	
	/**
	 * Return the wind speed.
	 * @return The current wind speed.
	 */
	public String getSpeed() {
		return myWindSpeed;
	}

	/**
	 * Calculates the wind direction for display on the compass.
	 */
	private void calculateDirection() {
		double opposite = Math.sin(myWindDirection);
		double adjacent = Math.cos(myWindDirection);
		int scalar = 110;
		opposite = opposite * scalar;
		adjacent = adjacent * scalar;
		myWindY = (int) opposite * (-1);
		myWindX = (int) adjacent;
	}
	
	/**
	 * Return a point containing the myWindX and myWindY variables.
	 * @return A point with x = myWindX and y = myWindY
	 */
	public Point getWindXY() {
		return new Point(myWindX, myWindY);
	}

	/**
	 * Draws the compass image, windspeed, and direction.
	 * @param theGraphics Used to draw on this panel.
	 */
	@Override
	public synchronized void paintComponent(final Graphics theGraphics) {
		theGraphics.drawImage(myCompassImage, 0, 0, 350, 263, null);
		theGraphics.setFont(new Font("Dialog", Font.PLAIN, 30));
		theGraphics.drawString(myWindSpeed + " mph", 120, 150);	
		theGraphics.fillOval(165 + myWindX, 125 + myWindY, 8, 8);
	}

}
