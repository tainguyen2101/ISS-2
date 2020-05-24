package structure;
import java.net.*;
import java.io.*;
/**This program is a demo class of the client side. It serves to demonstrate that ISS class can send 
 * data files over a network using an ip address. This class is currently sends data to its own 
 * ip address, but the parameters of the ServerSocket can be changes, an ip adresss passed and data can
 * be sent between computers. 
 * 
 * @author Leika Yamada
 * */
public class Client {
public static void main (String []args) throws IOException {
	File file = new File("/Users/tient/Desktop/roger.txt");
	  	try { 
	  		    //prepares socket to accept incoming data
	  			ServerSocket clientSocket = new ServerSocket(1999); 
                Socket personalSocket = clientSocket.accept(); 
                //accepts the input
                DataInputStream incomingFile = new DataInputStream(personalSocket.getInputStream()); 
                String fileText = incomingFile.readUTF(); 
                      
            //    System.out.println("File Transferred"); 
                //copies data into the file
                FileOutputStream outPutStream = new FileOutputStream(file); 
                byte[] bits = fileText.getBytes(); 
                outPutStream.write(bits);
                      
                } catch (IOException ie) { 
                	ie.printStackTrace(); 
                }
          }
}