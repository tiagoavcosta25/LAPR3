package lapr.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnergyCalculatorTest {

    @Test
    void testCalculoEnergia1() {
        double result = EnergyCalculator.calculoEnergia(250, 180, 20, -17, 80, 0.8);
        double expResult = 0.038577291666666666;
        assertEquals(result, expResult);
    }

    @Test
    void testCalculoEnergia2() {
        double expResult = 0;
        double result = EnergyCalculator.calculoEnergia(250, 0, 10, -300, 80, 0.8);
        assertEquals(result, expResult);
    }

    @Test
    void testCalculoEnergia3() {
        double expResult = 0;
        double result = EnergyCalculator.calculoEnergia(250, 180, 30, -300, 80, 0.8);
        assertEquals(result, expResult);
    }

    @Test
    void testCalculoEnergia4() {
        double expResult = 0.45413406192354966;
        double result = EnergyCalculator.calculoEnergia(2500, 50, 5, -17, 80, 0.8);
        assertEquals(result, expResult);
    }

}
