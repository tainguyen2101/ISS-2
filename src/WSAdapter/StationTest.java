package WSAdapter;

import Console.GUI;
import Generator.RandomSensorDataGenerator;
import Prj2Driver.Station;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Driver;
import java.util.Objects;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

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
        
    }

}