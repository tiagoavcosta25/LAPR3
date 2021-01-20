/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import static lapr.project.utils.Constants.*;

public class EnergyCalculator {

    public static double calculoEnergia(double distanceUsingCoordinates, double windDegree, double windSpeed,
                                              double localHeightDifference, double totalMass, double kineticFrictionCoefficient) {

        /**
         * Distance between the two addresses.
         * tem em conta a altura
         */
        double distance = Math.sqrt((Math.pow(localHeightDifference, 2) + Math.pow(distanceUsingCoordinates, 2)));

        /**
         * o coef de atrito está negativo pq esta força tem q ser negativa por contrariar o movimento
         */
        double atrito = totalMass * GRAVITICACCELERATION * kineticFrictionCoefficient;

        /**
         * Wind effective speed on the scooter.
         * a local height difference tem q ser obrigatoriamente com sinal, pois é esta q dá o sinal à força
         */
        double windEffectiveSpeed = windSpeed * Math.cos(Math.toRadians(windDegree));
        /**
         * Aerodynamic with friction.
         * Velocidade default é positiva mas neste caso é negativa pq está a ser considerada a velocidade do ar/scooter, logo o valor negativo da velocidade
         */
        double windFriction = (Constants.DRAGCOEFFICIENT * Constants.DEFAULTSCOOTERAREA * Constants.AIRDENSITY
                * Math.pow((Constants.DEFAULTVELOCITY +windEffectiveSpeed), 2) / 2);

        /**
         * Total energy without the wind factor.
         */
        double totalForca = windFriction + atrito;
        double totalEnergy = totalForca * distance / 3600000;      //kWh ou J

        return totalEnergy;
    }
}
