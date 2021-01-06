package lapr.project.model;

import lapr.project.model.registration.ClientRegistration;

public class Platform {
    private String m_designation;
    private ClientRegistration m_clientReg;

    public Platform() {
        this.m_designation = "plat";
    }

    public ClientRegistration getClientReg() {
        return this.m_clientReg;
    }
}
