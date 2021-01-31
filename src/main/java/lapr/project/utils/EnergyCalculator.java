package lapr.project.utils;

import javafx.util.Pair;

/**
 * Energy Calculator
 * <p>
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 *
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class EnergyCalculator {

    public static double velocityByPower = Constants.DEFAULT_VELOCITY;
    public static double droneVelocityByPower = 0.0;

    private EnergyCalculator() {
    }

    /**
     * Calculates the energy necessary to make an specific path, given the specs of the path
     *
     * @param distanceUsingCoordinates   the distance between two given points
     * @param windDegree                 the degree of the angle with the pair air/direction vector
     * @param windSpeed                  the wind speed
     * @param localHeightDifference      the local heigh difference
     * @param totalMass                  the total mass
     * @param kineticFrictionCoefficient the kinetic friction coefficient of the path
     * @param scooterPower               the scooter motor power
     * @return pair with the energy and the time spent
     */
    public static Pair<Double, Double> calculateScooterEnergy(double distanceUsingCoordinates, double windDegree, double windSpeed,
                                                              double localHeightDifference, double totalMass, double kineticFrictionCoefficient,
                                                              double scooterPower) {

        /**
         * Calculates the friction force, using the mass, the gravitic acceleration and the kinetic friction coefficient.
         */

        double frictionForce = totalMass * Constants.GRAVITIC_ACCELERATION * kineticFrictionCoefficient;

        /**
         * Wind effective speed on the scooter, considering the angle (it consideres if is against or in favor of the speed with the signal).
         */

        double windEffectiveSpeed = windSpeed * Math.cos(Math.toRadians(windDegree));

        /**
         * Total velocity that sums the wind speed and the effective speed
         */

        double totalVelocity = windEffectiveSpeed + velocityByPower;

        /**
         * Calculates the wind friction force with the total velocity
         */

        double windFrictionForce = (Constants.DRAG_COEFFICIENT * Constants.DEFAULT_SCOOTER_AREA * Constants.AIR_DENSITY
                * Math.pow((totalVelocity), 2) / 2);
        /**
         * Makes the wind friction negative or positive, depending of the signal of the velocity
         */

        if (totalVelocity < 0) {
            windFrictionForce = windFrictionForce * (-1);
        }

        /**
         * Calculates the weight work, regarding the inclination angle
         */

        double weightWork = totalMass * Constants.GRAVITIC_ACCELERATION * localHeightDifference / distanceUsingCoordinates;

        /**
         * Total force (sum of all forces)
         */

        double totalForce = windFrictionForce + frictionForce + weightWork;

        /**
         * If the force is less than 0, it means that there isn't necessary spend energy
         * (wind speed is in total favor or energy down the slope), so it returns energy being 0.
         */

        if (totalForce < 0) {
            velocityByPower = Constants.DEFAULT_VELOCITY;
            return new Pair<>(0d, distanceUsingCoordinates / totalVelocity);
        }

        /**
         * If the velocity of the movement is 0 (due to the recursive method decrementations) (explained in that method),
         * the method return Double max value, so that the program knows that is impossible to make that path.
         */

        if (velocityByPower < 0) {
            velocityByPower = Constants.DEFAULT_VELOCITY;
            return new Pair<>(Double.MAX_VALUE, Double.MAX_VALUE);
        }

        /**
         * If the scooter motor power isn't capable of moving the scooter at that velocity, the velocity is decremented
         * and the method is called again in a recursive way to know if with that decrement of velocity, the scooter
         * can make the path.
         */

        if (totalForce * totalVelocity > scooterPower) {
            velocityByPower = velocityByPower - 0.01;
            return calculateScooterEnergy(distanceUsingCoordinates, windDegree, windSpeed, localHeightDifference, totalMass, kineticFrictionCoefficient, scooterPower);
        }

        /**
         * Calculates the time with the formula v= d/t
         */

        double time = distanceUsingCoordinates / totalVelocity;

        /**
         * Resets the velocityByPower to the constant value.
         */

        velocityByPower = Constants.DEFAULT_VELOCITY;

        /**
         * Returns the energy spent in kWh and the time.
         */

        return new Pair<>(totalForce * distanceUsingCoordinates / Constants.KILOWATTHOUR, time);     //kWh ou J
    }

    /**
     * Calculates the energy necessary to make an specific path, given the specs of the path
     *
     * @param totalWeight the total mass of the drone+orders
     * @param windSpeed   the wind speed
     * @param windDegree  the degree of the angle with the pair air/direction vector
     * @param distance    the distance between two given points
     * @param dronePower  the drone motor power
     * @return pair with the energy and the time spent
     */
    public static Pair<Double, Double> calculateDroneEnergy(double totalWeight, double windSpeed, double windDegree, double distance, double dronePower) {

        /**
         * Creates the total energy variable
         */

        double totalEnergy = 0;

        /**
         * Calculates the hover velocity with the forumla explained on the report.
         */

        double velocity = Math.pow((2 * Math.pow(totalWeight, 2)) / (Constants.DRAG_COEFFICIENT * Constants.DEFAULT_DRONE_AREA * Math.pow(Constants.DRONE_WIDTH, 2) * Math.pow(Constants.AIR_DENSITY, 2)), 0.25) - droneVelocityByPower;

        /**
         * Wind effective speed on the scooter, considering the angle (it consideres if is against or in favor of the speed with the signal).
         */

        double windEffectiveSpeed = windSpeed * Math.cos(Math.toRadians(windDegree));

        /**
         * Total velocity that sums the wind speed and the effective speed
         */

        double totalVelocity = velocity + windEffectiveSpeed;

        /**
         * Calculates the air friction force, using the foruma explained on the report.
         */

        double airFrictionForce = (0.5 * Constants.DRAG_COEFFICIENT * Constants.DEFAULT_DRONE_AREA * Constants.AIR_DENSITY * Math.pow(totalVelocity, 2)) + ((Math.pow(totalWeight, 2)) / (Constants.AIR_DENSITY * Math.pow(Constants.DRONE_WIDTH, 2) * Math.pow(totalVelocity, 2)));

        /**
         * Calculates the lift energy, using the foruma explained on the report.
         */

        double liftEnergy = (Math.pow((totalWeight * Constants.GRAVITIC_ACCELERATION), 1.5) / (Math.sqrt(2 * Constants.AIR_DENSITY * Constants.DEFAULT_DRONE_AREA))) * (Constants.DEFAULT_HEIGHT / totalVelocity);

        /**
         * If the force is less than 0, it means that there isn't necessary spend energy
         * (wind speed is in total favor), so it returns energy being 0.
         * The second if verifies if the power needed isn't superior to the drone max power
         */

        if (airFrictionForce < 0) {
            droneVelocityByPower = 0.0;
            return new Pair<>(0d, 0d);
        } else if (velocity < 0 || (airFrictionForce * totalVelocity) > Constants.MAX_DRONE_POWER) {
            droneVelocityByPower = 0.0;
            return new Pair<>(Double.MAX_VALUE, Double.MAX_VALUE);
        }

        /**
         * If the drone motor power isn't capable of moving the drone at that velocity, the velocity is decremented
         * and the method is called again in a recursive way to know if with that decrement of velocity, the drone
         * can make the path.
         */

        if (airFrictionForce * totalVelocity > dronePower) {
            droneVelocityByPower = droneVelocityByPower + 0.01;
            return calculateDroneEnergy(totalWeight, windSpeed, windDegree, distance, dronePower);
        }

        /**
         * Calculates the total energy with the E=F.d formula and using 2x the lift energy.
         */

        totalEnergy = (airFrictionForce * distance) + (2 * liftEnergy);

        /**
         * Calculates the time with the formula v= d/t
         */

        double time = distance / totalVelocity;

        /**
         * Resets the droneVelocityByPower to the constant decrement value.
         */

        droneVelocityByPower = 0.0;

        /**
         * Returns the energy spent in kWh and the time.
         */

        return new Pair<>(totalEnergy / Constants.KILOWATTHOUR, time);
    }

    public static double getVelocityByPower() {
        return velocityByPower;
    }

    public static void setVelocityByPower(double velocityByPower) {
        EnergyCalculator.velocityByPower = velocityByPower;
    }

    public static double getDroneVelocityByPower() {
        return droneVelocityByPower;
    }

    public static void setDroneVelocityByPower(double droneVelocityByPower) {
        EnergyCalculator.droneVelocityByPower = droneVelocityByPower;
    }
}
