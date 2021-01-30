package lapr.project.model;

import java.util.Objects;

/**
 * Path.
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
public class Path {
    /**
     * Path's  first latitude
     */
    private double mdblLatitudeA;

    /**
     * Path's  first longitude
     */
    private double mdblLongitudeA;

    /**
     * Path's  second latitude
     */
    private double mdblLatitudeB;

    /**
     * Path's  second longitude
     */
    private double mdblLongitudeB;

    /**
     * Path name
     */
    private String mstrName;

    /**
     * Path wind speed
     */
    private double mdblWindSpeed;

    /**
     * Path wind angle
     */
    private double mdblWindAngle;

    /**
     * Path kinetic friction coefficient
     */
    private double mdblKineticFrictionCoefficient;

    /**
     * Path Vehicle Type
     */
    private VehicleType moVehicleType;


    /**
     * Empty constructor of Path, which sets all the atributes to
     * default values
     */
    public Path() {
        this.mdblLatitudeA = 0;
        this.mdblLongitudeA = 0;
        this.mdblLatitudeB = 0;
        this.mdblLongitudeB = 0;
        this.mstrName = "No name";
        this.mdblWindSpeed = -1;
        this.mdblWindAngle = -1;
        this.mdblKineticFrictionCoefficient = -1;
    }

    /**
     * Constructor of path, which sets all the atributes to the ones
     * given by parameter
     *
     * @param dblLatitudeA  Path's first latitude
     * @param dblLongitudeA Path's first longitude
     * @param dblLatitudeB  Path's second latitude
     * @param dblLongitudeB Path's second longitude
     * @param strName       Path's name
     * @param dblWindSpeed  Path's wind speed
     * @param dblWindAngle  Path's wind angle
     * @param dblKineticFrictionCoefficient Path's kinetic friction coefficient
     * @param oVehicleType                  Path's  Vehicle Type
     */
    public Path(double dblLatitudeA, double dblLongitudeA, double dblLatitudeB, double dblLongitudeB,
                String strName, double dblWindSpeed, double dblWindAngle, double dblKineticFrictionCoefficient,
                VehicleType oVehicleType) {
        this.mdblLatitudeA = dblLatitudeA;
        this.mdblLongitudeA = dblLongitudeA;
        this.mdblLatitudeB = dblLatitudeB;
        this.mdblLongitudeB = dblLongitudeB;
        this.mstrName = strName;
        this.mdblWindSpeed = dblWindSpeed;
        this.mdblWindAngle = dblWindAngle;
        this.mdblKineticFrictionCoefficient = dblKineticFrictionCoefficient;
        this.moVehicleType = oVehicleType;
    }

    /**
     * Returns the Path's Vehicle Type
     *
     * @return  Path's Vehicle Type
     */
    public VehicleType getVehicleType() {
        return moVehicleType;
    }

    /**
     * Returns the Path's first latitude
     *
     * @return  Path's first latitude
     */
    public double getLatitudeA() {
        return mdblLatitudeA;
    }

    /**
     * Sets the Path's first latitude to the one given by parameter
     *
     * @param dblLatitudeA new Path's first latitude
     */
    public void setLatitudeA(double dblLatitudeA) {
        this.mdblLatitudeA = dblLatitudeA;
    }

    /**
     * Returns the Path's first longitude
     *
     * @return  Path's first longitude
     */
    public double getLongitudeA() {
        return mdblLongitudeA;
    }

    /**
     * Sets the Path's first longitude to the one given by parameter
     *
     * @param dblLongitudeA new Path's first longitude
     */
    public void setLongitudeA(double dblLongitudeA) {
        this.mdblLongitudeA = dblLongitudeA;
    }

    /**
     * Returns the Path's second latitude
     *
     * @return  Path's second latitude
     */
    public double getLatitudeB() {
        return mdblLatitudeB;
    }

    /**
     * Sets the Path's second latitude to the one given by parameter
     *
     * @param dblLatitudeB  Path's second latitude
     */
    public void setLatitudeB(double dblLatitudeB) {
        this.mdblLatitudeB = dblLatitudeB;
    }

    /**
     * Returns the Path's second longitude
     *
     * @return  Path's second longitude
     */
    public double getLongitudeB() {
        return mdblLongitudeB;
    }

    /**
     * Sets the Path's second longitude to the one given by parameter
     *
     * @param dblLongitudeB new Path's second longitude
     */
    public void setLongitudeB(double dblLongitudeB) {
        this.mdblLongitudeB = dblLongitudeB;
    }

    /**
     * Returns the Path's name
     *
     * @return  Path's name
     */
    public String getName() {
        return mstrName;
    }

    /**
     * Sets the Path's name to the one given by parameter
     *
     * @param strName   new Path's name
     */
    public void setName(String strName) {
        this.mstrName = strName;
    }

    /**
     * Returns the Path's wind speed
     *
     * @return  Path's wind speed
     */
    public double getWindSpeed() {
        return mdblWindSpeed;
    }

    /**
     * Sets the Path's wind speed to the one given by parameter
     *
     * @param dblWindSpeed  new Path's wind speed
     */
    public void setWindSpeed(double dblWindSpeed) {
        this.mdblWindSpeed = dblWindSpeed;
    }

    /**
     * Returns the Path's wind angle
     *
     * @return  Path's wind angle
     */
    public double getWindAngle() {
        return mdblWindAngle;
    }

    /**
     * Sets the Path's wind angle to the one given by parameter
     *
     * @param dblWindAngle  new Path's wind angle
     */
    public void setWindAngle(double dblWindAngle) {
        this.mdblWindAngle = dblWindAngle;
    }

    /**
     * Returns the Path's kinetic friction coefficient
     *
     * @return  Path's kinetic friction coefficient
     */
    public double getKineticFrictionCoefficient() {
        return mdblKineticFrictionCoefficient;
    }

    /**
     * Sets the Path's kinetic friction coefficient to the one given
     * by parameter
     *
     * @param dblKineticFrictionCoefficient new Path's kinetic friction coefficient
     */
    public void setKineticFrictionCoefficient(double dblKineticFrictionCoefficient) {
        this.mdblKineticFrictionCoefficient = dblKineticFrictionCoefficient;
    }

    /**
     * Equals method, which compares two 'Path's
     *
     * @param o Other Object
     * @return True if both 'Path's are equal, false if otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path = (Path) o;
        return Double.compare(path.mdblLatitudeA, mdblLatitudeA) == 0 &&
                Double.compare(path.mdblLongitudeA, mdblLongitudeA) == 0 &&
                Double.compare(path.mdblLatitudeB, mdblLatitudeB) == 0 &&
                Double.compare(path.mdblLongitudeB, mdblLongitudeB) == 0 &&
                moVehicleType == path.moVehicleType;
    }

    /**
     * HashCode
     *
     * @return Integer hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(mdblLatitudeA, mdblLongitudeA, mdblLatitudeB, mdblLongitudeB, moVehicleType);
    }

    /**
     * ToString method, which formats a string to
     * print a formatted message
     *
     * @return formatted String
     */
    @Override
    public String toString() {
        return "Path{" +
                "mdblLatitudeA=" + mdblLatitudeA +
                ", mdblLongitudeA=" + mdblLongitudeA +
                ", mdblLatitudeB=" + mdblLatitudeB +
                ", mdblLongitudeB=" + mdblLongitudeB +
                ", mstrName='" + mstrName + '\'' +
                ", mdblWindSpeed=" + mdblWindSpeed +
                ", mdblWindAngle=" + mdblWindAngle +
                ", mdblKineticFrictionCoefficient=" + mdblKineticFrictionCoefficient +
                ", moVehicleType=" + moVehicleType +
                '}';
    }

    public String toStringRoute() {
        StringBuilder str = new StringBuilder();
        if(this.getVehicleType().equals(VehicleType.SCOOTER))
            str.append("\t\t").append(String.format("Path kinetic friction coefficient is %.2f.", this.mdblWindAngle)).append("\n");
        str.append("\t\t").append(String.format("Path wind speed is %.2f.", this.mdblWindSpeed));
        str.append("\n\t\t").append(String.format("Path wind angle is %.2f.", this.mdblWindAngle));
        return str.toString();
    }
}
