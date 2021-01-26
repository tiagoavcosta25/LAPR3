package lapr.project.utils;

public class Constants {

    private Constants() {
    }

    public static final int SLEEP_TIME = 5000;
    public static final double GRAVITIC_ACCELERATION = 9.8;             //  m.s^-2
    public static final double DEFAULT_HEIGHT = 150;                    //  m
    public static final double AIR_DENSITY = 1.225;                     //  kg.m^-3
    public static final double DEFAULT_DRONE_AREA = 0.3;                //  m^2 (0.2 m radius rotor, PI*r^2*4)
    public static final double KILOWATTHOUR = 3600000.0;
    public static final double DRAG_COEFFICIENT = 0.4;
    public static final double DEFAULT_VELOCITY = 10;                   //  m/s
    public static final double DEFAULT_COURIER_WEIGHT = 75;
    public static final double KINETIC_FRICTION_COEFFICIENT = 0.8;
    public static final double DEFAULT_SCOOTER_AREA = 0.75;                // m^2
    public static final double SCOOTER_TOTAL_WEIGHT = 10;
    public static final double DRONE_WIDTH = 0.2;
    public static final String ESTIMATE_FILE_PATH = "src-C/estimate_files/";
    public static final String LOCK_FILE_PATH = "src-C/lock_files/";
    public static final String FILTER = ".flag";
    public static final String EMAIL_BODY_TYPE = "text/html; charset=utf-8";

}
