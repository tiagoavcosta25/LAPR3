package lapr.project.controller;

import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.DeliveryRun;
import lapr.project.model.UserSession;
import lapr.project.model.service.DeliveryRunService;
import lapr.project.model.service.PharmacyService;

import java.util.List;

public class RegisterDeliveryRunController {

    private DeliveryRunService m_oDeliveryRunService;
    private PharmacyService m_oPharmacyService;

    public RegisterDeliveryRunController() {
        m_oDeliveryRunService = new DeliveryRunService();
        m_oPharmacyService = new PharmacyService();
    }

    public boolean registerDeliveryRun(List<Integer> lstOrderId){
        if (ApplicationPOT.getInstance().getCurrentSession().getRole().equals(UserSession.Role.ADMIN)) {
            List<Address> lstAddress = m_oDeliveryRunService.getAddressesFromOrdersList(lstOrderId);
            Courier courier = m_oPharmacyService.getSuitableCourier();
            List<Address> path = m_oDeliveryRunService.calculateShortestPath(lstAddress);
            DeliveryRun deliveryRun = m_oDeliveryRunService.newDeliveryRun(courier,path);
            return m_oDeliveryRunService.addNewDeliveryRun(deliveryRun);
        }else{
            System.out.println("User is NOT Authorized!");
            return false;
        }
    }


}
