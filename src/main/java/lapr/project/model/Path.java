package lapr.project.model;

import java.util.Objects;

public class Path {
    private int m_intIdAddressA;
    private int m_intIdAddressB;
    private String m_strName;

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
        return Objects.hash(m_intIdAddressA, m_intIdAddressB, m_strName);
    }
}
