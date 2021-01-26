package lapr.project.utils;

import javafx.util.Pair;

public class EnergyCalculator {

    public static double velocityByPower = Constants.DEFAULT_VELOCITY;
    public static double droneVelocityByPower = 0.0;

    private EnergyCalculator() {
    }

    public static Pair<Double, Double> calculateScooterEnergy(double distanceUsingCoordinates, double windDegree, double windSpeed,
                                                              double localHeightDifference, double totalMass, double kineticFrictionCoefficient,
                                                              double scooterPower) {

        /**
         * o coef de atrito está negativo pq esta força tem q ser negativa por contrariar o movimento
         */
        double atrito = totalMass * Constants.GRAVITIC_ACCELERATION * kineticFrictionCoefficient;

        /**
         * Wind effective speed on the scooter.
         * a local height difference tem q ser obrigatoriamente com sinal, pois é esta q dá o sinal à força
         */
        double windEffectiveSpeed = windSpeed * Math.cos(Math.toRadians(windDegree));
        double totalVelocity = windEffectiveSpeed + velocityByPower;
        double windFriction = (Constants.DRAG_COEFFICIENT * Constants.DEFAULT_SCOOTER_AREA * Constants.AIR_DENSITY
                * Math.pow((totalVelocity), 2) / 2);
        if (totalVelocity < 0) {
            windFriction = windFriction * (-1);
        }
        /**
         * Aerodynamic with friction.
         * Velocidade default é positiva mas neste caso é negativa pq está a ser considerada a velocidade do ar/scooter, logo o valor negativo da velocidade
         */

        double trabalhoPeso = totalMass * Constants.GRAVITIC_ACCELERATION * localHeightDifference / distanceUsingCoordinates;
        /**
         * Total energy without the wind factor.
         */
        double totalForca = windFriction + atrito + trabalhoPeso;

        if (totalForca < 0) {
            velocityByPower = Constants.DEFAULT_VELOCITY;
            return new Pair<>(0d, distanceUsingCoordinates / totalVelocity);
        }
        if (velocityByPower < 0) {
            velocityByPower = Constants.DEFAULT_VELOCITY;
            return new Pair<>(Double.MAX_VALUE, Double.MAX_VALUE);
        }
        if (totalForca * totalVelocity > scooterPower) {
            velocityByPower = velocityByPower - 0.01;
            return calculateScooterEnergy(distanceUsingCoordinates, windDegree, windSpeed, localHeightDifference, totalMass, kineticFrictionCoefficient, scooterPower);
        }
        double time = distanceUsingCoordinates / totalVelocity;
        velocityByPower = Constants.DEFAULT_VELOCITY;
        return new Pair<>(totalForca * distanceUsingCoordinates / Constants.KILOWATTHOUR, time);     //kWh ou J
    }

    public static Pair<Double, Double> calculateDroneEnergy(double totalWeight, double windSpeed, double windDegree, double distance, double dronePower) {
        //velocidade otimizada para o lift/drag ratio
        double totalEnergy = 0;
        double velocity = Math.pow((2 * Math.pow(totalWeight, 2)) / (Constants.DRAG_COEFFICIENT * Constants.DEFAULT_DRONE_AREA * Math.pow(Constants.DRONE_WIDTH, 2) * Math.pow(Constants.AIR_DENSITY, 2)), 0.25) - droneVelocityByPower;
        double windEffectiveSpeed = windSpeed * Math.cos(Math.toRadians(windDegree));
        double totalVelocity = velocity + windEffectiveSpeed;
        double force = (0.5 * Constants.DRAG_COEFFICIENT * Constants.DEFAULT_DRONE_AREA * Constants.AIR_DENSITY * Math.pow(totalVelocity, 2)) + ((Math.pow(totalWeight, 2)) / (Constants.AIR_DENSITY * Math.pow(Constants.DRONE_WIDTH, 2) * Math.pow(totalVelocity, 2)));
        double liftEnergy = (Math.pow((totalWeight * Constants.GRAVITIC_ACCELERATION), 1.5) / (Math.sqrt(2 * Constants.AIR_DENSITY * Constants.DEFAULT_DRONE_AREA))) * (Constants.DEFAULT_HEIGHT / totalVelocity);

        if (force < 0) {
            droneVelocityByPower = 0.0;
            return new Pair<>(0d, 0d);
        } else if (velocity < 0 || (force * totalVelocity) > Constants.MAX_DRONE_POWER) {
            droneVelocityByPower = 0.0;
            return new Pair<>(Double.MAX_VALUE, Double.MAX_VALUE);
        }
        if (force * totalVelocity > dronePower) {
            droneVelocityByPower = droneVelocityByPower + 0.01;
            return calculateDroneEnergy(totalWeight, windSpeed, windDegree, distance, dronePower);
        }
        totalEnergy = (force * distance) + (2 * liftEnergy);
        double time = distance / totalVelocity;
        droneVelocityByPower = 0.0;
        return new Pair<>(totalEnergy / Constants.KILOWATTHOUR, time);
    }
}
