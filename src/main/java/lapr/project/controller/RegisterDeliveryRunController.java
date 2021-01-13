package lapr.project.controller;

import lapr.project.data.DeliveryRunDB;
import lapr.project.data.OrderDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Address;
import lapr.project.model.Order;
import lapr.project.model.Pharmacy;
import lapr.project.model.UserSession;

import java.util.ArrayList;
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

    public boolean registerDeliveryRun(List<Integer> lstOrderId){
        String email = ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail();
        if (ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN)) {
            List<Address> lstAddresses = null;
        }
        //return m_oOrderDB.getOrdersFromPharmacy(p);
        return false;
    }


}
