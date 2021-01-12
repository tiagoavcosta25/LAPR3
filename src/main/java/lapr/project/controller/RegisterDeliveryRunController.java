package lapr.project.controller;

import lapr.project.data.DeliveryRunDB;
import lapr.project.data.OrderDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Order;
import lapr.project.model.Pharmacy;

import java.util.List;

public class RegisterDeliveryRunController {

    private DeliveryRunDB m_oDeliveryRunDB;
    private PharmacyDB m_oPharmacyDB;
    private OrderDB m_oOrderDB;
    private List<Order> m_lstOrders;

    public RegisterDeliveryRunController(String jdbcUrl, String username, String password) {
        m_oDeliveryRunDB = new DeliveryRunDB(jdbcUrl, username, password);
        m_oPharmacyDB = new PharmacyDB(jdbcUrl, username, password);
        m_oOrderDB = new OrderDB(jdbcUrl, username, password);
    }

    public List<Order> getOrders() {
        String email = ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail();
        Pharmacy p = m_oPharmacyDB.getPharmacyByManagerEmail(email);
        return m_oOrderDB.getOrdersFromPharmacy(p);
    }


}
