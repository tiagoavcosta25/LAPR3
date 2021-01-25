package lapr.project.model;

import java.util.Objects;

public class Path {

    private double mdblLatitudeA;
    private double mdblLongitudeA;
    private double mdblLatitudeB;
    private double mdblLongitudeB;
    private String mstrName;
    private double mdblWindSpeed;
    private double mdblWindAngle;
    private double mdblKineticFrictionCoefficient;
    private VehicleType moVehicleType;



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

    public VehicleType getVehicleType() {
        return moVehicleType;
    }

    public double getLatitudeA() {
        return mdblLatitudeA;
    }

    public void setLatitudeA(double dblLatitudeA) {
        this.mdblLatitudeA = dblLatitudeA;
    }

    public double getLongitudeA() {
        return mdblLongitudeA;
    }

    public void setLongitudeA(double dblLongitudeA) {
        this.mdblLongitudeA = dblLongitudeA;
    }

    public double getLatitudeB() {
        return mdblLatitudeB;
    }

    public void setLatitudeB(double dblLatitudeB) {
        this.mdblLatitudeB = dblLatitudeB;
    }

    public double getLongitudeB() {
        return mdblLongitudeB;
    }

    public void setLongitudeB(double dblLongitudeB) {
        this.mdblLongitudeB = dblLongitudeB;
    }

    public String getName() {
        return mstrName;
    }

    public void setName(String strName) {
        this.mstrName = strName;
    }

    public double getWindSpeed() {
        return mdblWindSpeed;
    }

    public void setWindSpeed(double dblWindSpeed) {
        this.mdblWindSpeed = dblWindSpeed;
    }

    public double getWindAngle() {
        return mdblWindAngle;
    }

    public void setWindAngle(double dblWindAngle) {
        this.mdblWindAngle = dblWindAngle;
    }

    public double getKineticFrictionCoefficient() {
        return mdblKineticFrictionCoefficient;
    }

    public void setKineticFrictionCoefficient(double dblKineticFrictionCoefficient) {
        this.mdblKineticFrictionCoefficient = dblKineticFrictionCoefficient;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(mdblLatitudeA, mdblLongitudeA, mdblLatitudeB, mdblLongitudeB, moVehicleType);
    }

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
}
