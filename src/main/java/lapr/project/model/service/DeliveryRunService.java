package lapr.project.model.service;

import lapr.project.data.DeliveryRunDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.DeliveryRun;
import java.util.ArrayList;
import java.util.List;

public class DeliveryRunService {

    private DeliveryRunDB m_oDeliveryRunDB;
    private PharmacyDB m_oPharmacyDB;

    public DeliveryRunService() {
        m_oDeliveryRunDB = new DeliveryRunDB();
        m_oPharmacyDB = new PharmacyDB();
    }

    public List<Address> getAddressesFromOrdersList(List<Integer> lstOrderId) {
        return m_oDeliveryRunDB.getAddressesFromOrdersList(lstOrderId);
    }


    public DeliveryRun newDeliveryRun(Courier oCourier, List<Address> oLstAddress) {
        return new DeliveryRun(oCourier,oLstAddress);
    }

    public boolean addNewDeliveryRun(DeliveryRun oDeliveryRun) {
        return m_oDeliveryRunDB.addNewDeliveryRun(oDeliveryRun);
    }

    public List<Address> calculateShortestPath(List<Address> lst) {
        return new ArrayList<>();
    }


}
