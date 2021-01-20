package lapr.project.model;

import java.util.Objects;

public class Path {

    private double m_dblLatitudeA;
    private double m_dblLongitudeA;
    private double m_dblLatitudeB;
    private double m_dblLongitudeB;
    private String m_strName;
    private double m_dblWindSpeed;
    private double m_dblWindAngle;
    private double m_dblKineticFrictionCoefficient;

    public Path() {
        this.m_dblLatitudeA = 0;
        this.m_dblLongitudeA = 0;
        this.m_dblLatitudeB = 0;
        this.m_dblLongitudeB = 0;
        this.m_strName = "No name";
        this.m_dblWindSpeed = -1;
        this.m_dblWindAngle = -1;
        this.m_dblKineticFrictionCoefficient = -1;
    }

    public Path(double dblLatitudeA, double dblLongitudeA, double dblLatitudeB, double dblLongitudeB,
                String strName, double dblWindSpeed, double dblWindAngle, double dblKineticFrictionCoefficient) {
        this.m_dblLatitudeA = dblLatitudeA;
        this.m_dblLongitudeA = dblLongitudeA;
        this.m_dblLatitudeB = dblLatitudeB;
        this.m_dblLongitudeB = dblLongitudeB;
        this.m_strName = strName;
        this.m_dblWindSpeed = dblWindSpeed;
        this.m_dblWindAngle = dblWindAngle;
        this.m_dblKineticFrictionCoefficient = dblKineticFrictionCoefficient;
    }

    public double getLatitudeA() {
        return m_dblLatitudeA;
    }

    public void setLatitudeA(double m_dblLatitudeA) {
        this.m_dblLatitudeA = m_dblLatitudeA;
    }

    public double getLongitudeA() {
        return m_dblLongitudeA;
    }

    public void setLongitudeA(double m_dblLongitudeA) {
        this.m_dblLongitudeA = m_dblLongitudeA;
    }

    public double getLatitudeB() {
        return m_dblLatitudeB;
    }

    public void setLatitudeB(double m_dblLatitudeB) {
        this.m_dblLatitudeB = m_dblLatitudeB;
    }

    public double getLongitudeB() {
        return m_dblLongitudeB;
    }

    public void setLongitudeB(double m_dblLongitudeB) {
        this.m_dblLongitudeB = m_dblLongitudeB;
    }

    public String getName() {
        return m_strName;
    }

    public void setName(String m_strName) {
        this.m_strName = m_strName;
    }

    public double getWindSpeed() {
        return m_dblWindSpeed;
    }

    public void setWindSpeed(double m_dblWindSpeed) {
        this.m_dblWindSpeed = m_dblWindSpeed;
    }

    public double getWindAngle() {
        return m_dblWindAngle;
    }

    public void setWindAngle(double m_dblWindAngle) {
        this.m_dblWindAngle = m_dblWindAngle;
    }

    public double getKineticFrictionCoefficient() {
        return m_dblKineticFrictionCoefficient;
    }

    public void setKineticFrictionCoefficient(double m_dblKineticFrictionCoefficient) {
        this.m_dblKineticFrictionCoefficient = m_dblKineticFrictionCoefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path = (Path) o;
        return Objects.equals(m_dblLatitudeA, path.m_dblLatitudeA) &&
                Objects.equals(m_dblLongitudeA, path.m_dblLongitudeA) &&
                Objects.equals(m_dblLatitudeB, path.m_dblLatitudeB) &&
                Objects.equals(m_dblLongitudeB, path.m_dblLongitudeB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_dblLatitudeA, m_dblLongitudeA, m_dblLatitudeB, m_dblLongitudeB);
    }

    @Override
    public String toString() {
        return "Path{" +
                "m_dblLatitudeA=" + m_dblLatitudeA +
                ", m_dblLongitudeA=" + m_dblLongitudeA +
                ", m_dblLatitudeB=" + m_dblLatitudeB +
                ", m_dblLongitudeB=" + m_dblLongitudeB +
                ", m_strName='" + m_strName + '\'' +
                ", m_dblWindSpeed=" + m_dblWindSpeed +
                ", m_dblWindAngle=" + m_dblWindAngle +
                ", m_dblKineticFrictionCoefficient=" + m_dblKineticFrictionCoefficient +
                '}';
    }
}
