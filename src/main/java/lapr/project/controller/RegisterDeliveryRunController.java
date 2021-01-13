package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.service.DeliveryRunService;
import lapr.project.model.service.OrderService;
import lapr.project.model.service.PharmacyService;
import java.util.List;

public class RegisterDeliveryRunController {

    private DeliveryRunService m_oDeliveryRunService;
    private PharmacyService m_oPharmacyService;
    private OrderService m_oOrderService;

    public RegisterDeliveryRunController() {
        m_oDeliveryRunService = new DeliveryRunService();
        m_oPharmacyService = new PharmacyService();
        m_oOrderService = new OrderService();
    }

    public boolean registerDeliveryRun(List<Integer> lstOrderId){
        if (ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN)) {
            List<Order> lstOrder = m_oOrderService.getOrderList(lstOrderId);
            Courier courier = m_oPharmacyService.getSuitableCourier();
            DeliveryRun deliveryRun = m_oDeliveryRunService.newDeliveryRun(courier,lstOrder);
            return m_oDeliveryRunService.addNewDeliveryRun(deliveryRun);
        }else{
            System.out.println("User is NOT Authorized!");
            return false;
        }
    }


}
