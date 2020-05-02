import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Random;

/**
 * Creates a data server that sends sensor data to other devices.
 * @author Brandon Francis
 * @version April 2020
 */
public class Sensors {

	/** Generates sensor data. */
    private static Random rand = new Random();
    
    /** Port for the server to listen on. */
    private static int port = 9876;
    
    /**
     * Opens up the server on the specified port 
     * and sends sensor data to clients that connect.
     * @param theArgs command line arguments
     * @throws IOException if socket fails to open.
     */
    public static void main(String[] theArgs) throws IOException {
    	
    	// Try to create an open socket
    	try (var listener = new ServerSocket(port)) {
				
			System.out.println("Sensors Online");
				
			// Continually run the server
			while (true) {
				
				// Try to accept a connection to the server
				try (var socket = listener.accept()) {
					
					// Send the data to the connected client
					var out = new PrintWriter(socket.getOutputStream(), true);
					out.println(getWindSpeed() + " " + getWindDirection() + " "
								+ getTemperature() + " " + getHumidity() + " " + getBar() + " " + getRain());
				}
				catch (IOException e){
					System.exit(1);
				}
			}
		} catch (IOException e1) {
			// Catches exception if socket fails to open
			e1.printStackTrace();
		}
    	
    	
    }

    /**
     * Generates a random wind speed between 10 and 39.
     */
    public static int getWindSpeed() {
        return rand.nextInt(30) + 10;
    }

    /**
     * Generates a random wind direction.
     */
    public static String getWindDirection() {
        int dir = rand.nextInt(8);
        switch (dir) {
            case 0:
                return "North";
            case 1:
                return "East";
            case 2:
                return "South";
            case 3: 
                return "West";
            case 4:
                return "Northeast";
            case 5:
                return "Northwest";
            case 6:
                return "Southeast";
            default:
                return "Southwest";
        }
    }

    /**
     * Generates a random temperature between 30 and 79.
     */
    public static int getTemperature() {
        return rand.nextInt(50) + 30;
    }

    /**
     * Generates a random humidity between 20 and 49.
     */
    public static int getHumidity() {
        return rand.nextInt(30) + 20;
    }

    /**
     * Generates a random barometric pressure between 0 and 99.
     */
    public static int getBar() {
        return rand.nextInt(100);
    }
    
    /**
     * Generates a random amount of rainfall between 0 and 1 inches.
     */
    public static int getRain() {
    	return rand.nextInt(2);
    }

}
