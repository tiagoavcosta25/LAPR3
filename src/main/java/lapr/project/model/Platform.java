package lapr.project.model;

import lapr.project.model.registration.ClientRegistration;
import lapr.project.model.registration.CourierRegistration;

public class Platform {
    private String m_designation;
    private final ClientRegistration m_clientReg;
    private final CourierRegistration m_courReg;

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
