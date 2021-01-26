package lapr.project.utils;

import java.util.ArrayList;
import java.util.List;

public class TimeCalculator {

    private TimeCalculator() {
    }

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
