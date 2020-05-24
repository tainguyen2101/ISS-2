package Driver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;

import Adapter.WS1Adapter;
import Adapter.WS5Adapter;

/**
 * Driver class.
 * @author Ford Nguyen.
 */
public class Station {

    // Update interval for station.
    private static int UPDATE_INTERVAL_S1 = 20;
    private static int UPDATE_INTERVAL_S2 = 20;
    private static int UPDATE_INTERVAL_S3 = 20;
    private static int UPDATE_INTERVAL_S4 = 20;
    private static int UPDATE_INTERVAL_S5 = 20;

    private static String[][] myFileArray = { { "WeatherStation1.txt", "WeatherStation1Inside.txt" }, 
    		{ "Outside2.txt", "Inside2.txt" },
            { "Outside3.txt", "Inside3.txt" }, { "Outside4.txt", "Inside4.txt" }, 
            {  "WeatherStation5.txt", "WeatherStation5Inside.txt" } };

    // Constructor.
    public Station() throws IOException {
        runStation();
    }
    
    /**
     * Create 5 threads for 5 stations Define what each thread will run based on the
     * stationte
     * 
     * @throws IOException
     */
    private static void runStation() throws IOException {
        final RandomSensorDataGenerator generator = new RandomSensorDataGenerator();
        
        //Weather Station 1 Code
        WS1Adapter adapter = new WS1Adapter();
        adapter.generateData();
      
      //Weather Station 2 Code
//      WS2Adapter adapter2 = new WS2Adapter();
//      adapter2.generateData();
      
      //Weather Station 3 Code
//      WS3Adapter adapter3 = new WS3Adapter();
//      adapter3.generateData();
      
      //Weather Station 4 Code
//      WS4Adapter adapter4 = new WS4Adapter();
//      adapter4.generateData();
      
      //Weather Station 5 Code
        WS5Adapter adapter5 = new WS5Adapter();
        adapter5.generateData();

        // generate 5 set of data for 5 weather station
        for (int i = 1; i < 4; i++) {
            generator.createISSData(myFileArray[i][0], myFileArray[i][1]);
            generator.createEnvoyData(myFileArray[i][1]);
        }

        // Run station 1.
        final Thread station1 = new Thread() {

            @Override
            public void run() {
                try (var listener = new ServerSocket(9876)) {
                    while (true) {
                            final BufferedReader outRdr = new BufferedReader(new FileReader(myFileArray[0][0]));
                            final BufferedReader inRdr = new BufferedReader(new FileReader(myFileArray[0][1]));
                            String dataIn;
                            String dataOut;
                            while ((dataIn = inRdr.readLine()) != null && (dataOut = outRdr.readLine()) != null) {
                                String dataSent = dataOut + " " + dataIn;
                                try (var socket = listener.accept()) {
                                    var out1 = new PrintWriter(socket.getOutputStream(), true);
                                    out1.println(dataSent);
                                synchronized (this) {
                                    this.wait(UPDATE_INTERVAL_S1);
                                }
                            }

                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        } 
                        outRdr.close();
                        inRdr.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Run station2. 
        final Thread station2 = new Thread() {

            @Override
            public void run() {
                try (var listener = new ServerSocket(9877)) {
                    while (true) {
                            final BufferedReader outRdr = new BufferedReader(new FileReader(myFileArray[1][0]));
                            final BufferedReader inRdr = new BufferedReader(new FileReader(myFileArray[1][1]));
                            String dataIn;
                            String dataOut;
                            while ((dataIn = inRdr.readLine()) != null && (dataOut = outRdr.readLine()) != null) {
                                String dataSent = dataOut + " " + dataIn;
                                try (var socket = listener.accept()) {
                                    var out1 = new PrintWriter(socket.getOutputStream(), true);
                                    out1.println(dataSent);
                                synchronized (this) {
                                    this.wait(UPDATE_INTERVAL_S2);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } 
                        outRdr.close();
                        inRdr.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Run station 3.
        final Thread station3 = new Thread() {

            @Override
            public void run() {
                try (var listener = new ServerSocket(9878)) {
                    while (true) {
                            final BufferedReader outRdr = new BufferedReader(new FileReader(myFileArray[0][0]));
                            final BufferedReader inRdr = new BufferedReader(new FileReader(myFileArray[0][1]));
                            String dataIn;
                            String dataOut;
                            while ((dataIn = inRdr.readLine()) != null && (dataOut = outRdr.readLine()) != null) {
                                String dataSent = dataOut + " " + dataIn;
                                try (var socket = listener.accept()) {
                                    var out1 = new PrintWriter(socket.getOutputStream(), true);
                                    out1.println(dataSent);
                                synchronized (this) {
                                    this.wait(UPDATE_INTERVAL_S3);
                                }
                            }

                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        } 
                        outRdr.close();
                        inRdr.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Run station 4.
        final Thread station4 = new Thread() {

            @Override
            public void run() {
                try (var listener = new ServerSocket(9879)) {
                    while (true) {
                            final BufferedReader outRdr = new BufferedReader(new FileReader(myFileArray[0][0]));
                            final BufferedReader inRdr = new BufferedReader(new FileReader(myFileArray[0][1]));
                            String dataIn;
                            String dataOut;
                            while ((dataIn = inRdr.readLine()) != null && (dataOut = outRdr.readLine()) != null) {
                                String dataSent = dataOut + " " + dataIn;
                                try (var socket = listener.accept()) {
                                    var out1 = new PrintWriter(socket.getOutputStream(), true);
                                    out1.println(dataSent);
                                synchronized (this) {
                                    this.wait(UPDATE_INTERVAL_S4);
                                }
                            }

                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        } 
                        outRdr.close();
                        inRdr.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Run station 5.
        final Thread station5 = new Thread() {

            @Override
            public void run() {
                try (var listener = new ServerSocket(9880)) {
                    while (true) {
                            final BufferedReader outRdr = new BufferedReader(new FileReader(myFileArray[0][0]));
                            final BufferedReader inRdr = new BufferedReader(new FileReader(myFileArray[0][1]));
                            String dataIn;
                            String dataOut;
                            while ((dataIn = inRdr.readLine()) != null && (dataOut = outRdr.readLine()) != null) {
                                String dataSent = dataOut + " " + dataIn;
                                try (var socket = listener.accept()) {
                                    var out1 = new PrintWriter(socket.getOutputStream(), true);
                                    out1.println(dataSent);
                                synchronized (this) {
                                    this.wait(UPDATE_INTERVAL_S5);
                                }
                            }

                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        } 
                        outRdr.close();
                        inRdr.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        station1.start();
        station2.start();
        station3.start();
        station4.start();
        station5.start();
        
    }

}