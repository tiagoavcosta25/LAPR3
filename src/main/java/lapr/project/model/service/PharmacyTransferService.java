package lapr.project.model.service;

import lapr.project.data.PharmacyTransferDB;
import lapr.project.model.*;

public class PharmacyTransferService {

    private PharmacyTransferDB m_oPharmacyTransferDB;

    public PharmacyTransferService() {
        this.m_oPharmacyTransferDB = new PharmacyTransferDB();
    }

    public PharmacyTransfer getPharmacyTransfer(int strId) {
        return this.m_oPharmacyTransferDB.getPharmacyTransfer(strId);
    }

    public boolean removePharmacyTransfer(int intId) {
        return this.m_oPharmacyTransferDB.removePharmacyTransfer(intId);
    }

    public boolean registerPharmacyTransfer(PharmacyTransfer oPharmacyTransfer) {
        return this.m_oPharmacyTransferDB .registerPharmacyTransfer(oPharmacyTransfer);
    }

    public PharmacyTransfer newPharmacyTransfer(Order oOrder, Product oProduct, Integer intQuantity, Pharmacy oPharmacy) {
        return new PharmacyTransfer(oOrder, oProduct, intQuantity, oPharmacy);
    }
}
