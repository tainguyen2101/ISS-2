package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import iss.ISS;

class ISSTest {

    @Test
    public void runWithoutExceptions() {
        ISS iss = new ISS();
        try {
            iss.run();
            long timeA = System.currentTimeMillis();
            long timeB;
            do {
                timeB = System.currentTimeMillis();
            } while (timeB - timeA >= 10_000);
        } catch (Exception e) {
            fail("The ISS should not experience an exception "
                    + "within 10 seconds of operation");
        }
    }
}