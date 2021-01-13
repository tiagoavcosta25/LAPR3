package lapr.project.model;

import java.util.LinkedList;
import java.util.Objects;

public class Route implements Comparable<Route>{

    private LinkedList<Address> m_lstAddress;
    private Double m_dblDistance;

    public Route(LinkedList<Address> m_lstAddress, Double m_dblDistance) {
        this.m_lstAddress = m_lstAddress;
        this.m_dblDistance = m_dblDistance;
    }

    public LinkedList<Address> getLstAddress() {
        return m_lstAddress;
    }

    public void setLstAddress(LinkedList<Address> m_lstAddress) {
        this.m_lstAddress = m_lstAddress;
    }

    public Double getDistance() {
        return m_dblDistance;
    }

    public void setDistance(Double m_dblDistance) {
        this.m_dblDistance = m_dblDistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(m_lstAddress, route.m_lstAddress) &&
                Objects.equals(m_dblDistance, route.m_dblDistance);
    }

    @Override
    public int compareTo(Route o) {
        if (this.m_dblDistance > o.m_dblDistance) {
            return 1;
        }else return -1;
    }

    @Override
    public String toString() {
        return "Route{" +
                "m_lstAddress=" + m_lstAddress +
                ", m_dblDistance=" + m_dblDistance +
                '}';
    }



}
