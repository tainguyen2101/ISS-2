import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * Displays a message on the console receiver.
 * @author Sean Robinson
 * @version 16APR2020
 */
public class MessageDisplay extends JComponent {

	/** Serial Version UID	 */
	private static final long serialVersionUID = -7844540550396966475L;

	/** The width of this component. */
	private static final int MY_WIDTH = 900;

	/**  The height of this component. */
	private static final int MY_HEIGHT = 60;

	/** The maximum message length. */
	private static final int MY_MAX_LENGTH = 30;

	/** The starting x position for the message. */
	private static final int MY_X_OFFSET = 20;

	/** The message that will be displayed. */
	private String myMessage;

	/**
	 * Set the message and preferred size.
	 * @param theMessage The initial message.
	 */
	public MessageDisplay(final String theMessage) {
		if (theMessage.length() > MY_MAX_LENGTH) {
			throw new IllegalArgumentException("The message argument in"
					+ " class MessageDIsplay is too long");
		} else {
			myMessage = theMessage;
			setPreferredSize(new Dimension(MY_WIDTH, MY_HEIGHT));
		}

	}

	/**
	 * Draws the message on this component.
	 * @param theGraphics Used to draw.
	 */
	@Override
	public synchronized void paintComponent(final Graphics theGraphics) {
		theGraphics.setFont(new Font("Dialog", Font.PLAIN, 30));
		theGraphics.drawString(myMessage, MY_X_OFFSET, MY_HEIGHT / 2);
	}
	
	/**
	 * Return the currently displayed message.
	 * @return The current message.
	 */
	public String getMessage() {
		return myMessage;
	}

	/**
	 * Set the message.
	 * @param theMessage The new message.
	 */
	public void setMessage(final String theMessage) {
		if (theMessage.length() > 50) {
			throw new IllegalArgumentException("The message argument in "
					+ "class MessageDIsplay is too long");
		} else {
		myMessage = theMessage;
		repaint();
		}
	}
}
