package lapr.project.model;

import lapr.project.model.registration.ClientRegistration;
import lapr.project.model.registration.CourierRegistration;
import lapr.project.model.registration.OrderRegistration;
import lapr.project.model.registration.UserRegistration;

public class Platform {
    private String m_designation;
    private UserRegistration m_userReg;
    private ClientRegistration m_clientReg;
    private CourierRegistration m_courReg;
    private OrderRegistration m_orderReg;

    public Platform() {
        this.m_designation = "plat";
        this.m_userReg = new UserRegistration();
        this.m_clientReg = new ClientRegistration();
        this.m_courReg = new CourierRegistration();
    }

    public UserRegistration getUserReg() {
        return this.m_userReg;
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
