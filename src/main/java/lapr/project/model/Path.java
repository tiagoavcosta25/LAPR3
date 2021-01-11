package lapr.project.model;

import java.util.Objects;

public class Path {
    private int m_intIdAddressA;
    private int m_intIdAddressB;
    private String m_strName;

    public Path() {
        this.m_intIdAddressA = -1;
        this.m_intIdAddressB = -1;
        this.m_strName = "No Path Name";
    }

    public Path(int m_intIdAddressA, int m_intIdAddressB, String m_strName) {
        this.m_intIdAddressA = m_intIdAddressA;
        this.m_intIdAddressB = m_intIdAddressB;
        this.m_strName = m_strName;
    }

    public int getIdAddressA() {
        return m_intIdAddressA;
    }

    public int getIdAddressB() {
        return m_intIdAddressB;
    }

    public String getName() {
        return m_strName;
    }

    public void setIdAddressA(int idAdressA) {
        this.m_intIdAddressA = idAdressA;
    }

    public void setIdAddressB(int idAddressB) {
        this.m_intIdAddressB = idAddressB;
    }

    public void setName(String name) {
        this.m_strName = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path = (Path) o;
        return m_intIdAddressA == path.m_intIdAddressA &&
                m_intIdAddressB == path.m_intIdAddressB &&
                Objects.equals(m_strName, path.m_strName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_intIdAddressA);
    }

    @Override
    public String toString() {
        return "Path{" +
                "m_intIdAddressA=" + m_intIdAddressA +
                ", m_intIdAddressB=" + m_intIdAddressB +
                ", m_strName='" + m_strName + '\'' +
                '}';
    }
}
