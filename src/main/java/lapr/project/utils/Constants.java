package lapr.project.utils;

/**
 * Constants.
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

public class Constants {
    /**
     * An empty constructor of Constants.
     */
    private Constants() {
    }

    /**
     * Sleep Time.
     */
    public static final int SLEEP_TIME = 5000;

    /**
     * Gravitic Acceleration.
     */
    public static final double GRAVITIC_ACCELERATION = 9.8;             //  m.s^-2

    /**
     * Default Height.
     */
    public static final double DEFAULT_HEIGHT = 150;                    //  m

    /**
     * Air Density.
     */
    public static final double AIR_DENSITY = 1.225;                     //  kg.m^-3

    /**
     * Default Drone Area.
     */
    public static final double DEFAULT_DRONE_AREA = 0.3;                //  m^2 (0.2 m radius rotor, PI*r^2*4)

    /**
     * KiloWatt/Hour.
     */
    public static final double KILOWATTHOUR = 3600000.0;

    /**
     * Drag Coefficient.
     */
    public static final double DRAG_COEFFICIENT = 1.1;

    /**
     * Default Velocity.
     */
    public static final double DEFAULT_VELOCITY = 8.3;                   //  m/s

    /**
     * Default Courier Weight.
     */
    public static final double DEFAULT_COURIER_WEIGHT = 80;

    /**
     * Max Drone Power.
     */
    public static final double MAX_DRONE_POWER = 3000;

    /**
     * Kinetic Friction Coefficient.
     */
    public static final double KINETIC_FRICTION_COEFFICIENT = 0.8;

    /**
     * Default Scooter Area.
     */
    public static final double DEFAULT_SCOOTER_AREA = 0.3;                // m^2

    /**
     * Scooter Total Weight.
     */
    public static final double SCOOTER_TOTAL_WEIGHT = 10;

    /**
     * Drone Width.
     */
    public static final double DRONE_WIDTH = 0.2;

    /**
     * Estimate File Path.
     */
    public static final String ESTIMATE_FILE_PATH = "src-C/estimate_files/";

    /**
     * Lock File Path.
     */
    public static final String LOCK_FILE_PATH = "src-C/lock_files/";

    /**
     * Filter.
     */
    public static final String FILTER = ".flag";

    /**
     * Email Body Type.
     */
    public static final String EMAIL_BODY_TYPE = "text/html; charset=utf-8";

}
