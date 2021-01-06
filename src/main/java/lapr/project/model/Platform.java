package lapr.project.model;

import lapr.project.model.registration.CourierRegistration;

public class Platform {
    private String m_designation;
    private YUse
    private ClientRegistration m_clientReg;
    private CourierRegistration m_courReg;

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
}
