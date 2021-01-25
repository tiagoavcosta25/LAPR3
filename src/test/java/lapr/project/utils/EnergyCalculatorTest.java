package lapr.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnergyCalculatorTest {

    @Test
    void testCalculoEnergia1() {
        double result = EnergyCalculator.calculateScooterEnergy(250, 180, 20, -17, 80, 0.8).getKey();
        double expResult = 0.038577291666666666;
        assertEquals(result, expResult);
    }

    @Test
    void testCalculoEnergia2() {
        double expResult = 0;
        double result = EnergyCalculator.calculateScooterEnergy(250, 0, 10, -300, 80, 0.8).getKey();
        assertEquals(result, expResult);
    }

    @Test
    void testCalculoEnergia3() {
        double expResult = 0;
        double result = EnergyCalculator.calculateScooterEnergy(250, 180, 30, -300, 80, 0.8).getKey();
        assertEquals(result, expResult);
    }

    @Test
    void testCalculoEnergia4() {
        double expResult = 0.45413406192354966;
        double result = EnergyCalculator.calculateScooterEnergy(2500, 50, 5, -17, 80, 0.8).getKey();
        assertEquals(result, expResult);
    }

    @Test
    void testCalucloEnergia5(){
        //vento de frente
        double expResult = 0.09344927883620688;
        double result = EnergyCalculator.calculateDroneEnergy(20,7,0,5000).getKey();
        assertEquals(expResult,result);
    }
    @Test
    void testCalucloEnergia7(){
        //vento de frente
        double expResult = 0.12612497292789387;
        double result = EnergyCalculator.calculateDroneEnergy(20,7,180,5000).getKey();
        assertEquals(expResult,result);
    }

    @Test
    void testCalucloEnergia6(){
        //vento de trás
        double expResult = 0.17975317864280832;
        double result = EnergyCalculator.calculateDroneEnergy(10,7,190,10000).getKey();
        assertEquals(expResult,result);
    }


}
