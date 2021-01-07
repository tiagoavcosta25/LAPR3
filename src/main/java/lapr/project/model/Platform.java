package lapr.project.model;

import lapr.project.model.registration.*;

public class Platform {
    private String m_designation;
    private UserRegistration m_userReg;
    private ClientRegistration m_clientReg;
    private CourierRegistration m_courReg;
    private OrderRegistration m_orderReg;
    private ProductRegistration m_productReg;

    public Platform() {
        this.m_designation = "plat";
        this.m_userReg = new UserRegistration("jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_G21", "qwerty");
        this.m_clientReg = new ClientRegistration("jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_G21", "qwerty");
        this.m_courReg = new CourierRegistration();
        this.m_productReg = new ProductRegistration("jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_G21", "qwerty");
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

    public ProductRegistration getProductReg() {
        return m_productReg;
    }
}
