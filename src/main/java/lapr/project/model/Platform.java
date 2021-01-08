package lapr.project.model;

import lapr.project.graph.map.Graph;
import lapr.project.model.registration.*;

public class Platform {
    private String m_designation;
    private UserRegistration m_userReg;
    private ClientRegistration m_clientReg;
    private CourierRegistration m_courReg;
    private OrderRegistration m_orderReg;
    private ProductRegistration m_productReg;
    private ScooterRegistration m_scooterReg;
    private PharmacyRegistration m_pharmacyReg;
    private DeliveryRegistration m_delReg;

    public Platform() {
        this.m_designation = "plat";
        this.m_userReg = new UserRegistration("jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_G21", "g21rumoAo20");
            this.m_clientReg = new ClientRegistration("jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_G21", "g21rumoAo20");
        this.m_courReg = new CourierRegistration("jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_G21", "g21rumoAo20");
        this.m_productReg = new ProductRegistration("jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_G21", "qwerty");
        this.m_scooterReg = new ScooterRegistration();
        this.m_pharmacyReg = new PharmacyRegistration();
        this.m_delReg = new DeliveryRegistration();
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

    public ScooterRegistration getScooterReg() {
        return m_scooterReg;
    }

    public PharmacyRegistration getPharmacyReg() {
        return m_pharmacyReg;
    }

    public DeliveryRegistration getDelReg() {
        return m_delReg;
    }

}
