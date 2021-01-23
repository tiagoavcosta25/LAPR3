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
        return Objects.equals(mdblLatitudeA, path.mdblLatitudeA) &&
                Objects.equals(mdblLongitudeA, path.mdblLongitudeA) &&
                Objects.equals(mdblLatitudeB, path.mdblLatitudeB) &&
                Objects.equals(mdblLongitudeB, path.mdblLongitudeB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mdblLatitudeA, mdblLongitudeA, mdblLatitudeB, mdblLongitudeB);
    }

    @Override
    public String toString() {
        return "Path{" +
                "m_dblLatitudeA=" + mdblLatitudeA +
                ", m_dblLongitudeA=" + mdblLongitudeA +
                ", m_dblLatitudeB=" + mdblLatitudeB +
                ", m_dblLongitudeB=" + mdblLongitudeB +
                ", m_strName='" + mstrName + '\'' +
                ", m_dblWindSpeed=" + mdblWindSpeed +
                ", m_dblWindAngle=" + mdblWindAngle +
                ", m_dblKineticFrictionCoefficient=" + mdblKineticFrictionCoefficient +
                '}';
    }
}
