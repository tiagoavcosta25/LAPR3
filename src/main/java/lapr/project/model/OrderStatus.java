package lapr.project.model;

public enum OrderStatus {

    ORDERED("Ordered"),
    DELIVERING("Delivering"),
    DELIVERED("Delivered");

    private String mstrDesignation;

    OrderStatus(String designation) {
        this.mstrDesignation = designation;
    }

    public String getDesignation() {
        return mstrDesignation;
    }


    @Override
    public String toString() {
        return "OrderStatus{" +
                "m_strDesignation='" + mstrDesignation + '\'' +
                '}';
    }
}
