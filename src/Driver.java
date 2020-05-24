import GUI.GUI;
import Stations.Station;

import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException {
        new Station();
        GUI test = new GUI();
        while (true) {
            test.collectWSData(9876);
            test.collectWSData(9877);
            test.collectWSData(9878);
            test.collectWSData(9879);
            test.collectWSData(9880);
        }
    }

}