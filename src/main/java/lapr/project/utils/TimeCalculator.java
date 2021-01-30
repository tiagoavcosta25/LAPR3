package lapr.project.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Time Calculator.
 *
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class TimeCalculator {
    /**
     * An empty constructor of ParkScooterController.
     */
    private TimeCalculator() {
    }

    /**
     * Calculates the Time.
     * @param timeInHours Hours.
     * @return Time List.
     */
    public static List<Integer> calculateTime(double timeInHours) {
        List<Integer> result = new ArrayList<>();
        double rest;
        double temp = timeInHours;

        int horas = (int) temp;
        result.add(horas);
        rest = temp - horas;

        temp = rest * 60;
        int minutos = (int) temp;
        result.add(minutos);
        rest = temp - minutos;

        temp = rest * 60;
        int segundos = (int) temp;
        result.add(segundos);
        return result;
    }

}
