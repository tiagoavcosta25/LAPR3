package lapr.project.model;

public enum OrderStatus {

    ORDERED("Ordered"),
    DELIVERING("Delivering"),
    DELIVERED("Delivered");

    private String m_strDesignation;

    OrderStatus(String designation) {
        this.m_strDesignation = designation;
    }

    public String getDesignation() {
        return m_strDesignation;
    }


    @Override
    public String toString() {
        return "OrderStatus{" +
                "m_strDesignation='" + m_strDesignation + '\'' +
                '}';
    }
}
