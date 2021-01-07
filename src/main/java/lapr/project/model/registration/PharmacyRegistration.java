package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.Pharmacy;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class PharmacyRegistration extends DataHandler {

    /**
     * Registers a new Pharmacy in the Database
     *
     * @param strName           Pharmacy's name
     * @param intParkingSlots   Pharmacy's parking slots
     * @param intChargingSlots  Pharmacy's charging slots
     * @return True if Pharmacy was registered, false if otherwise
     */
    public boolean registerNewPharmacy(String strName, int intParkingSlots, int intChargingSlots) {
        Pharmacy pharmacy = new Pharmacy(strName, intParkingSlots, intChargingSlots);
        return addPharmacyToDB(pharmacy);

    }


    /**
     * DATABASE
     */


    public boolean addPharmacyToDB(Pharmacy p) {
        return addPharmacyToDB(p.getName(), p.getParkingSlots(), p.getChargingSlots());
    }

    private boolean addPharmacyToDB(String strName, int intParkingSlots, int intChargingSlots) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addProduct(?,?,?,?,?) }");

            callStmt.setString(1, strName);
            callStmt.setInt(2, intParkingSlots);
            callStmt.setInt(3, intChargingSlots);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

}
