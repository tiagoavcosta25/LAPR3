package lapr.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnergyCalculatorTest {

    @Test
    void testCalculoEnergia1() {
        double result = EnergyCalculator.calculateScooterEnergy(250, 180, 20, -17, 80, 0.8, 3000).getKey();
        double expResult = 0.03793188255208334;
        assertEquals(expResult, result);
    }

    @Test
    void testCalculoEnergia2() {
        double expResult = 0;
        double result = EnergyCalculator.calculateScooterEnergy(250, 0, 10, -300, 80, 0.8, 100).getKey();
        assertEquals(result, expResult);
    }

    @Test
    void testCalculoEnergia3() {
        double expResult = 0;
        double result = EnergyCalculator.calculateScooterEnergy(250, 180, 30, -300, 80, 0.8, 7000).getKey();
        assertEquals(result, expResult);
    }

    @Test
    void testCalculoEnergia4() {
        double expResult = 0.4389569202464038;
        double result = EnergyCalculator.calculateScooterEnergy(2500, 50, 5, -17, 80, 0.8, 4500).getKey();
        assertEquals(result, expResult);
    }

    @Test
    void testCalucloEnergia5() {
        //vento de frente
        double expResult = 0.16376688678941728;
        double result = EnergyCalculator.calculateDroneEnergy(20, 7, 0, 5000, 3500).getKey();
        assertEquals(expResult, result);
    }

    @Test
    void testCalucloEnergia7() {
        //vento de frente
        double expResult = 0.08472570547420596;
        double result = EnergyCalculator.calculateDroneEnergy(10, 3, 180, 5000, 500).getKey();
        assertEquals(expResult, result);
    }

    @Test
    void testCalucloEnergia6() {
        //vento de trás
        double expResult = 0.6141017063566954;
        double result = EnergyCalculator.calculateDroneEnergy(10, 7, 190, 10000, 3000).getKey();
        assertEquals(expResult, result);
    }

    @Test
    void testCalucloEnergia8() {
        double expResult = 0;
        double result = EnergyCalculator.calculateScooterEnergy(10, 180, 100, -100, 100, 0.8, 100).getKey();
        assertEquals(expResult, result);
    }

    @Test
    void testCalucloEnergia9() {
        double expResult = 0.01578746946607639;
        double result = EnergyCalculator.calculateScooterEnergy(10, 180, 9.89, 50, 100, 0.8, 300).getKey();
        assertEquals(expResult, result);
    }

    @Test
    void testCalculoEnergia10() {
        double expResult = Double.MAX_VALUE;
        double result = EnergyCalculator.calculateScooterEnergy(1000, 0, 5, -10, 100, 0.5, 20).getKey();
        assertEquals(expResult, result);
    }

    @Test
    void testCalculoEnergia11() {
        double expResult = Double.MAX_VALUE;
        double result = EnergyCalculator.calculateDroneEnergy(100, 5, 0, 1000, 10).getKey();
        assertEquals(expResult, result);
    }

    @Test
    void testCalculoEnergia12() {
        double expResult = 0.11025000000000001;
        double result = EnergyCalculator.calculateScooterEnergy(1000, 180, 8.3, 10, 50, 0.8, 4000).getKey();
        assertEquals(expResult, result);
    }

    @Test
    void testCalculoEnergia13() {
        double expResult = 0;
        double result = EnergyCalculator.calculateScooterEnergy(1000, 180, 8.3, 0, 50, 0, 4000).getKey();
        assertEquals(expResult, result);
    }

    @Test
    void testGetVelocityByPower() {
        double expResult = Constants.DEFAULT_VELOCITY;
        double result = EnergyCalculator.getVelocityByPower();
        assertEquals(expResult, result);
    }

    @Test
    void testSetVelocityByPower() {
        EnergyCalculator.setVelocityByPower(Constants.DEFAULT_VELOCITY);
        double result = EnergyCalculator.getVelocityByPower();
        double expResult = Constants.DEFAULT_VELOCITY;
        assertEquals(expResult, result);
    }

    @Test
    void testGetDroneVelocityByPower() {
        double expResult = 0.0;
        double result = EnergyCalculator.getDroneVelocityByPower();
        assertEquals(expResult, result);
    }

    @Test
    void testSetDroneVelocityByPower() {
        EnergyCalculator.setDroneVelocityByPower(0.0);
        double result = EnergyCalculator.getDroneVelocityByPower();
        double expResult = 0.0;
        assertEquals(expResult, result);
    }


}
