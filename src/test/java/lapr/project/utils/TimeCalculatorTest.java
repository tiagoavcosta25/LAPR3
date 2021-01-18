package lapr.project.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimeCalculatorTest {

    @Test
    void calculateTime() {
        System.out.println("Calculate Time");
        double timeInHours = 5;
        int hours = 5;
        int minutes = 0;
        int seconds = 0;
        List<Integer> expResult = new ArrayList<>();
        expResult.add(hours);
        expResult.add(minutes);
        expResult.add(seconds);
        List<Integer> result = TimeCalculator.calculateTime(timeInHours);
        assertEquals(expResult, result);

        timeInHours = 2.5;
        hours = 2;
        minutes = 30;
        expResult = new ArrayList<>();
        expResult.add(hours);
        expResult.add(minutes);
        expResult.add(seconds);
        result = TimeCalculator.calculateTime(timeInHours);
        assertEquals(expResult, result);

        timeInHours = 2.353;
        hours = 2;
        minutes = 21;
        seconds = 10;
        expResult = new ArrayList<>();
        expResult.add(hours);
        expResult.add(minutes);
        expResult.add(seconds);
        result = TimeCalculator.calculateTime(timeInHours);
        assertEquals(expResult, result);
    }
}