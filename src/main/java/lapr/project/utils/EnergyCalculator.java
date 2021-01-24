package lapr.project.utils;

import static lapr.project.utils.Constants.*;

public class EnergyCalculator {

    public static double calculateScooterEnergy(double distanceUsingCoordinates, double windDegree, double windSpeed,
                                                double localHeightDifference, double totalMass, double kineticFrictionCoefficient) {

        /**
         * o coef de atrito está negativo pq esta força tem q ser negativa por contrariar o movimento
         */
        double atrito = totalMass * GRAVITIC_ACCELERATION * kineticFrictionCoefficient;

        /**
         * Wind effective speed on the scooter.
         * a local height difference tem q ser obrigatoriamente com sinal, pois é esta q dá o sinal à força
         */
        double windEffectiveSpeed = windSpeed * Math.cos(Math.toRadians(windDegree));
        double totalVelocity = windEffectiveSpeed + DEFAULT_VELOCITY;
        double windFriction = (Constants.DRAG_COEFFICIENT * Constants.DEFAULT_SCOOTER_AREA * Constants.AIR_DENSITY
                * Math.pow((totalVelocity), 2) / 2);
        if (totalVelocity < 0) {
            windFriction = windFriction * (-1);
        }
        /**
         * Aerodynamic with friction.
         * Velocidade default é positiva mas neste caso é negativa pq está a ser considerada a velocidade do ar/scooter, logo o valor negativo da velocidade
         */

        double trabalhoPeso = totalMass * GRAVITIC_ACCELERATION * localHeightDifference / distanceUsingCoordinates;
        /**
         * Total energy without the wind factor.
         */
        double totalForca = windFriction + atrito + trabalhoPeso;

        if (totalForca < 0) {
            return 0;
        }

        return totalForca * distanceUsingCoordinates / KILOWATTHOUR;     //kWh ou J
    }
    public static double calculateDroneEnergy(double totalWeight, double windSpeed, double windDegree, double distance){
        //velocidade otimizada para o lift/drag ratio
        double totalEnergy=0;
        double velocity = Math.pow((2 * Math.pow(totalWeight, 2)) / (Constants.DRAG_COEFFICIENT * Constants.DEFAULT_DRONE_AREA * Math.pow(Constants.DRONE_WIDTH, 2) * Math.pow(Constants.AIR_DENSITY, 2)), 0.25);
        double windEffectiveSpeed = windSpeed * Math.cos(Math.toRadians(windDegree));
        double totalVelocity  = velocity+windEffectiveSpeed;
        double force = (0.5*Constants.DRAG_COEFFICIENT*Constants.DEFAULT_DRONE_AREA*Constants.AIR_DENSITY*Math.pow(totalVelocity,2))+((Math.pow(totalWeight,2))/(Constants.AIR_DENSITY*Math.pow(Constants.DRONE_WIDTH,2)*Math.pow(velocity,2)));
        double liftEnergy = (Math.pow((totalWeight*Constants.GRAVITIC_ACCELERATION),1.5)/(Math.sqrt(2*Constants.AIR_DENSITY*Constants.DEFAULT_DRONE_AREA)))*(Constants.DEFAULT_HEIGHT/velocity);
        totalEnergy = (force*distance)+(2*liftEnergy);
        return totalEnergy/KILOWATTHOUR;
    }
}
