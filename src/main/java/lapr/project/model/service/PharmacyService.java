package lapr.project.model.service;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.ParkDB;
import lapr.project.data.PharmacyDB;
import lapr.project.graph.map.Graph;
import lapr.project.graph.map.GraphAlgorithms;
import lapr.project.model.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Pharmacy Service.
 *
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class PharmacyService {

    /**
     * Pharmacy Database.
     */
    private PharmacyDB moPharmacyDB;

    /**
     * Park Database.
     */
    private ParkDB moParkDB;

    /**
     * An empty constructor of Pharmacy Service.
     */
    public PharmacyService() {
        this.moPharmacyDB = new PharmacyDB();
        this.moParkDB = new ParkDB();
    }

    /**
     * Returns Pharmacy Database.
     * @return Pharmacy Database.
     */
    public PharmacyDB getPharmacyDB() {
        return moPharmacyDB;
    }

    /**
     * Modifies Pharmacy Database.
     * @param moPharmacyDB Pharmacy Database.
     */
    public void setPharmacyDB(PharmacyDB moPharmacyDB) {
        this.moPharmacyDB = moPharmacyDB;
    }

    /**
     * Returns Park Database.
     * @return Park Database.
     */
    public ParkDB getParkDB() {
        return moParkDB;
    }

    /**
     * Modifies Park Database.
     * @param oParkDB Park Database.
     */
    public void setParkDB(ParkDB oParkDB) {
        this.moParkDB = oParkDB;
    }

    /**
     * Returns Pharmacy By Email.
     * @param strEmail Pharmacy Email.
     * @return Pharmacy.
     */
    public Pharmacy getPharmacy(String strEmail) {
        return this.moPharmacyDB.getPharmacy(strEmail);
    }

    /**
     * Remove a Pharmacy.
     * @param strEmail Pharmacy Email.
     * @return true if the Pharmacy is removed. False if otherwise.
     */
    public boolean removePharmacy(String strEmail) {
        return this.moPharmacyDB.removePharmacy(strEmail);
    }

    /**
     * Register a Pharmacy.
     * @param oPharmacy Pharmacy.
     * @return true if the Pharmacy is registered. False if otherwise.
     */
    public boolean registerPharmacy(Pharmacy oPharmacy) {
        return this.moPharmacyDB.registerPharmacy(oPharmacy);
    }

    /**
     * Create a new Pharmacy Instance.
     * @param strName Pharmacy Name.
     * @param strEmail Pharmacy Email.
     * @param dblLatitude Pharmacy Latitude.
     * @param dblLongitude Pharmacy Longitude.
     * @param dblAltitude Pharmacy Altitude.
     * @param strStreetName Pharmacy Street Name.
     * @param strDoorNumber Pharmacy Door Number.
     * @param strPostalCode Pharmacy Postal Code.
     * @param strLocality Pharmacy Locality.
     * @param strCountry Pharmacy Country.
     * @return Pharmacy Instance.
     */
    public Pharmacy newPharmacy(String strName, String strEmail, Double dblLatitude,Double dblLongitude, Double dblAltitude,
                                String strStreetName, String strDoorNumber, String strPostalCode, String strLocality, String strCountry) {
        return new Pharmacy(strName, strEmail, new Address(dblLatitude, dblLongitude, dblAltitude, strStreetName, strDoorNumber, strPostalCode,
                strLocality, strCountry));
    }

    /**
     * Register a Pharmacy Product.
     * @param oPharmacy Pharmacy.
     * @param oProduct Product.
     * @param mintStock Pharmacy Stock.
     * @return true if the Pharmacy is registered. False if otherwise.
     */
    public boolean registerPharmacyProduct(Pharmacy oPharmacy, Product oProduct, Integer mintStock) {
        return this.moPharmacyDB.registerPharmacyProduct(oPharmacy, oProduct, mintStock);
    }

    /**
     * Returns a List of Pharmacies.
     * @return List of Pharmacies.
     */
    public List<Pharmacy> getPharmacies() {
        return this.moPharmacyDB.getPharmacies();
    }

    /**
     * Returns the closest Pharmacy to a Client.
     * @param oClient Client.
     * @return Closest Pharmacy.
     */
    public Pharmacy getClosestPharmacyToClient(Client oClient) {
        return this.getClosestPharmacy(oClient.getAddress(), moPharmacyDB.getPharmacies());
    }

    /**
     * Returns the closest Pharmacy with Stock to a Client.
     * @param oOrder Client's Order.
     * @param oProduct Order's Product.
     * @param intQuantity Product Quantity.
     * @return Closest Pharmacy with Stock.
     */
    public Pharmacy getClosestPharmacyWithStock(Order oOrder, Product oProduct, Integer intQuantity) {
        List<Pharmacy> lstPharmacies = moPharmacyDB.getPharmaciesWithStock(oOrder, oProduct, intQuantity);
        lstPharmacies.remove(oOrder.getPharmacy());
        return this.getClosestPharmacy(oOrder.getPharmacy().getAddress(), lstPharmacies);
    }

    /**
     * Returns the closest Pharmacy to an Address.
     * @param oAddress Address.
     * @param lstPharmacies List of Pharmacies.
     * @return Closest Pharmacy .
     */
    private Pharmacy getClosestPharmacy(Address oAddress, List<Pharmacy> lstPharmacies){
        Graph<Address, Path> worldMap = ApplicationPOT.getInstance().getWorldMap().getScooterGraph();
        double minDistance = Double.MAX_VALUE;
        Pharmacy oClosestPharmacy = null;

        for(Pharmacy p : lstPharmacies){
            LinkedList<Address> path = new LinkedList<>();
            double distance = GraphAlgorithms.shortestPath(worldMap, p.getAddress(), oAddress, path);

            if(distance < minDistance && distance > 0){
                oClosestPharmacy = p;
                minDistance = distance;
            }
        }
        return oClosestPharmacy;
    }

    /**
     * Returns the Suitable Courier.
     * @return Suitable Courier.
     */
    public Courier getSuitableCourier() {
        return moPharmacyDB.getSuitableCourier();
    }

    /**
     * Returns List of Orders By Pharmacy.
     * @param strPharmacyEmail Pharmacy Email.
     * @return List of Orders.
     */
    public List<Order> getOrdersByPharmacyEmail(String strPharmacyEmail) {
        return moPharmacyDB.getOrdersByPharmacyEmail(getPharmacy(strPharmacyEmail));
    }

    /**
     * Add a new Park.
     * @param strPharmacyEmail Pharmacy Email.
     * @param p Park.
     * @param intNonChargingSlots Park Non Charging Slots.
     * @param intChargingSlots Park Charging Slots.
     * @return true if the Park is added. False if otherwise.
     */
    public boolean addPark(String strPharmacyEmail, Park p, int intNonChargingSlots, int intChargingSlots) {
        return this.moParkDB.addParkToDB(strPharmacyEmail, p, intNonChargingSlots, intChargingSlots);
    }
}
