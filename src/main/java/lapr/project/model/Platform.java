package lapr.project.model;

import lapr.project.model.registration.ClientRegistration;
import lapr.project.model.registration.CourierRegistration;
import lapr.project.model.registration.OrderRegistration;

public class Platform {
    private String m_designation;
    private ClientRegistration m_clientReg;
    private CourierRegistration m_courReg;
    private OrderRegistration m_orderReg;

    public Platform() {
        this.m_designation = "plat";
        this.m_clientReg = new ClientRegistration();
        this.m_courReg = new CourierRegistration();
    }

    public ClientRegistration getClientReg() {
        return this.m_clientReg;
    }
    public CourierRegistration getCourReg() {
        return this.m_courReg;
    }
    public OrderRegistration getOrderReg() {
        return this.m_orderReg;
    }
}
