package lapr.project.model.service;

import lapr.project.data.ParkDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.*;
import java.util.List;

public class PharmacyService {

    private PharmacyDB moPharmacyDB;
    private ParkDB moParkDB;

    public PharmacyService() {
        this.moPharmacyDB = new PharmacyDB();
        this.moParkDB = new ParkDB();
    }

    public Pharmacy getPharmacy(String strEmail) {
        return this.moPharmacyDB.getPharmacy(strEmail);
    }

    public boolean removePharmacy(String strEmail) {
        return this.moPharmacyDB.removePharmacy(strEmail);
    }

    public boolean registerPharmacy(Pharmacy oPharmacy) {
        return this.moPharmacyDB.registerPharmacy(oPharmacy);
    }

    public Pharmacy newPharmacy(String strName, String strEmail, Double dblLatitude,Double dblLongitude, Double dblAltitude,
                                String strStreetName, String strDoorNumber, String strPostalCode, String strLocality, String strCountry) {
        return new Pharmacy(strName, strEmail, new Address(dblLatitude, dblLongitude, dblAltitude, strStreetName, strDoorNumber, strPostalCode,
                strLocality, strCountry));
    }

    public boolean registerPharmacyProduct(Pharmacy oPharmacy, Product oProduct, Integer m_intStock) {
        return this.moPharmacyDB.registerPharmacyProduct(oPharmacy, oProduct, m_intStock);
    }

    public List<Pharmacy> getPharmacies() {
        return this.moPharmacyDB.getPharmacies();
    }

    public Pharmacy getPharmacyByManagerEmail(String email) {
        return this.moPharmacyDB.getPharmacyByManagerEmail(email);
    }

    public Pharmacy getClosestPharmacyWithStock(Order oOrder, Product oProduct, Integer intQuantity) {
        return moPharmacyDB.getClosestPharmacyWithStock(oOrder, oProduct, intQuantity);
    }

    public Courier getSuitableCourier() {
        return moPharmacyDB.getSuitableCourier();
    }

    public boolean addPark(int intPharmacyId, Park p, int intNonChargingSlots, int intChargingSlots) {
        return this.moParkDB.addParkToDB(intPharmacyId, p, intNonChargingSlots, intChargingSlots);
    }
}
