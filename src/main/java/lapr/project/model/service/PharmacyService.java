package lapr.project.model.service;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.ParkDB;
import lapr.project.data.PharmacyDB;
import lapr.project.graph.map.Graph;
import lapr.project.graph.map.GraphAlgorithms;
import lapr.project.model.*;

import java.util.LinkedList;
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

    public Pharmacy getClosestPharmacyToClient(Client oClient) {
        return this.getClosestPharmacy(oClient.getAddress(), moPharmacyDB.getPharmacies());
    }

    public Pharmacy getClosestPharmacyWithStock(Order oOrder, Product oProduct, Integer intQuantity) {
        List<Pharmacy> lstPharmacies = moPharmacyDB.getPharmaciesWithStock(oOrder, oProduct, intQuantity);
        lstPharmacies.remove(oOrder.getPharmacy());
        return this.getClosestPharmacy(oOrder.getPharmacy().getAddress(), lstPharmacies);
    }

    private Pharmacy getClosestPharmacy(Address oAddress, List<Pharmacy> lstPharmacies){
        Graph<Address, Path> worldMap = ApplicationPOT.getInstance().getWorldMap().getGraph();
        double minDistance = Double.MAX_VALUE;
        Pharmacy oClosestPharmacy = null;

        for(Pharmacy p : lstPharmacies){
            LinkedList<Address> path = new LinkedList<>();
            double distance = GraphAlgorithms.shortestPath(worldMap, oAddress, p.getAddress(), path);

            if(distance < minDistance && distance > 0){
                oClosestPharmacy = p;
                minDistance = distance;
            }
        }
        return oClosestPharmacy;
    }

    public Courier getSuitableCourier() {
        return moPharmacyDB.getSuitableCourier();
    }

    public boolean addPark(String strPharmacyEmail, Park p, int intNonChargingSlots, int intChargingSlots) {
        return this.moParkDB.addParkToDB(strPharmacyEmail, p, intNonChargingSlots, intChargingSlots);
    }
}
