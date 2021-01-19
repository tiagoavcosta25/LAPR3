/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import static lapr.project.utils.Constants.*;

public class EnergyCalculator {

    /**
     * This method calculates the total amount of energy spent on the delivery between
     * the original and final addresses (parks or pois).
     *
     * @param distanceUsingCoordinates
     * @param movDegree
     * @param windDegree
     * @param windSpeed
     * @param dragCoefficient
     * @param localHeightDifference
     * @param userWeight
     * @return Total amount of energy spent on the delivery.
     */
    public static double calculateTotalEnergy(double distanceUsingCoordinates,
                                              int movDegree, int windDegree, double windSpeed, double dragCoefficient,
                                              double localHeightDifference, double userWeight, double orderWeight) {

        /**
         * Distance between the two addresses.
         */
        double distance = Math.sqrt((Math.pow(localHeightDifference, 2) + Math.pow(distanceUsingCoordinates, 2)));

        /**
         * Friction caused by the weight.
         */
        double weightFriction = (userWeight + SCOOTER_TOTAL_WEIGHT + orderWeight) * GRAVITIC_ACCELERATION * (localHeightDifference / distance) * distance;

        /**
         * Friction caused by the pavement.
         */
        double pavementFriction = dragCoefficient * ((userWeight + SCOOTER_TOTAL_WEIGHT + orderWeight) * GRAVITIC_ACCELERATION * (distanceUsingCoordinates / distance)) * distance;

        /**
         * Degree made by the wind degree and movement degree.
         */
        double windAsFactorDegree = Math.abs(movDegree - windDegree);

        if (windAsFactorDegree > 180) {
            windAsFactorDegree -= ((windAsFactorDegree - 180) * 2);
        }

        /**
         * Wind effective speed on the scooter.
         */
        double windEffectiveSpeed = windSpeed * Math.cos(windAsFactorDegree);

        /**
         * Aerodynamic with friction.
         */
        double windFriction = (DRAG_COEFFICIENT * DEFAULT_SCOOTER_AREA * AIR_DENSITY * Math.pow(windEffectiveSpeed, 2) / 2) * distance;

        /**
         * Total energy without the wind factor.
         */
        double totalEnergy = weightFriction + pavementFriction;

        if (windAsFactorDegree > 90) {
            totalEnergy += windFriction;
        } else {
            if (windAsFactorDegree < 90) {
                totalEnergy -= windFriction;
            }
        }

        /**
         * In case that the courier does not need to spend any energy on the delivery,
         * the energy spent can't also be negative.
         */
        //Caso, num caso extremo, o vento seja forte o suficiente para realizar a viagem à velocidade desejada, o total gasto de energia seria igual a 0.
        if (totalEnergy < 0) {
            totalEnergy = 0;
        }

        return totalEnergy;
    }







    public static double calculoEnergia(double distanceUsingCoordinates, int windDegree, double windSpeed,
                                              double localHeightDifference, double userWeight, double orderWeight,
                                              double scooterWeight, double kineticFrictionCoefficient) {

        /**
         * Distance between the two addresses.
         * tem em conta a altura
         */
        double distance = Math.sqrt((Math.pow(localHeightDifference, 2) + Math.pow(distanceUsingCoordinates, 2)));

        double totalMass = (userWeight + scooterWeight + orderWeight);
        /**
         * o coef de atrito está negativo pq esta força tem q ser negativa por contrariar o movimento
         */
        double atrito = totalMass * GRAVITIC_ACCELERATION * kineticFrictionCoefficient;

        /**
         * Wind effective speed on the scooter.
         * a local height difference tem q ser obrigatoriamente com sinal, pois é esta q dá o sinal à força
         */
        double windEffectiveSpeed = windSpeed * Math.cos(Math.toRadians(windDegree));
        /**
         * Aerodynamic with friction.
         * Velocidade default é positiva mas neste caso é negativa pq está a ser considerada a velocidade do ar/scooter, logo o valor negativo da velocidade
         */
        double windFriction = (Constants.DRAG_COEFFICIENT * Constants.DEFAULT_SCOOTER_AREA * Constants.AIR_DENSITY
                * Math.pow((Constants.DEFAULT_VELOCITY+windEffectiveSpeed), 2) / 2);

        /**
         * Total energy without the wind factor.
         */
        double totalForca = windFriction + atrito;
        double totalEnergy = totalForca * distance / 3600000;      //kWh ou J

        return totalEnergy;
    }










    /**
     * Calculates the degrees between two locals.
     *
     * Code extracted from https://stackoverflow.com/questions/9457988/bearing-from-one-coordinate-to-another
     */
    public static int bearing(double lat2, double lon2, double lat1, double lon1) {
        double longitude1 = lon2;
        double longitude2 = lon1;
        double latitude1 = Math.toRadians(lat2);
        double latitude2 = Math.toRadians(lat1);
        double longDiff = Math.toRadians(longitude2 - longitude1);
        double y = Math.sin(longDiff) * Math.cos(latitude2);
        double x = Math.cos(latitude1) * Math.sin(latitude2) - Math.sin(latitude1) * Math.cos(latitude2) * Math.cos(longDiff);

        return (int) ((Math.toDegrees(Math.atan2(y, x)) + 360) % 360);
    }
}
