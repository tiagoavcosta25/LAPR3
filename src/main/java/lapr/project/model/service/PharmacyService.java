package lapr.project.model.service;

import lapr.project.data.PharmacyDB;
import lapr.project.model.*;
import java.util.List;

public class PharmacyService {

    private PharmacyDB m_oPharmacyDB;

    public PharmacyService() {
        this.m_oPharmacyDB = new PharmacyDB();
    }

    public Pharmacy getPharmacy(int strId) {
        return this.m_oPharmacyDB.getPharmacy(strId);
    }

    public boolean removePharmacy(int intId) {
        return this.m_oPharmacyDB.removePharmacy(intId);
    }

    public boolean registerPharmacy(Pharmacy oPharmacy) {
        return this.m_oPharmacyDB .registerPharmacy(oPharmacy);
    }

    public Pharmacy newPharmacy(String strName, PharmacyManager oPharmacyManager,Double dblLatitude,Double dblLongitude,
                                String strStreetName, String strDoorNumber, String strPostalCode, String strLocality, String strCountry) {
        return new Pharmacy(strName, oPharmacyManager, new Address(dblLatitude, dblLongitude, strStreetName, strDoorNumber, strPostalCode,
                strLocality, strCountry));
    }

    public boolean registerPharmacyProduct(Pharmacy oPharmacy, Product oProduct, Integer m_intStock) {
        return this.m_oPharmacyDB.registerPharmacyProduct(oPharmacy, oProduct, m_intStock);
    }

    public List<Pharmacy> getPharmacies() {
        return this.m_oPharmacyDB.getPharmacies();
    }

    public Pharmacy getPharmacyByManagerEmail(String email) {
        return this.m_oPharmacyDB.getPharmacyByManagerEmail(email);
    }
}
