package JUnit;

import GUI.GUI;
import Stations.Station;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Test Station and GUI.
 *
 * @author Ford Nguyen
 */
class StationTest {

    @Test
    void runStation() throws IOException {
        new Station();
        GUI test = new GUI();
        boolean run = true;
        while (run) {
            test.collectWSData(9876);
            test.collectWSData(9877);
            test.collectWSData(9878);
            test.collectWSData(9879);
            test.collectWSData(9880);
            run = false;
        }
        // clean up
        final String[][] myFileArray = { { "Outside1.txt", "Inside1.txt" }, { "Outside2.txt", "Inside2.txt" },
                { "Outside3.txt", "Inside3.txt" }, { "Outside4.txt", "Inside4.txt" }, { "Outside5.txt", "Inside5.txt" } };
        for (int i = 0; i < 5; i++) {
            Files.delete(Paths.get(myFileArray[i][0]));
            Files.delete(Paths.get(myFileArray[i][1]));
        }
    }

}