import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;

/**
 * Driver class.
 * @author Ford Nguyen.
 */
public class Station {

    private static String[][] myFileArray = { { "Outside1.txt", "Inside1.txt" }, { "Outside2.txt", "Inside2.txt" },
            { "Outside3.txt", "Inside3.txt" }, { "Outside4.txt", "Inside4.txt" }, { "Outside5.txt", "Inside5.txt" } };

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

        // generate 5 set of data for 5 weather station
        for (int i = 0; i < 5; i++) {
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
                                    this.wait(1000);
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
                                    this.wait(1000);
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
                                    this.wait(1000);
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
                                    this.wait(1000);
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
                                    this.wait(1000);
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
