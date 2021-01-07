package lapr.project.model.registration;

import lapr.project.data.DataHandler;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.Order;
import lapr.project.model.Pharmacy;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PharmacyRegistration extends DataHandler {

    /**
     * Registers a new Pharmacy in the Database
     *
     * @param strName           Pharmacy's name
     * @return True if Pharmacy was registered, false if otherwise
     */
    public boolean registerNewPharmacy(String strName, float latitude, float longitude, String streetName,
                                       String doorNumber, String postalCode, String locality, String country) {
        Pharmacy pharmacy = new Pharmacy(strName, new Address(latitude, longitude, streetName, doorNumber, postalCode, locality, country));
        return addPharmacyToDB(pharmacy);

    }

    public Pharmacy getPharmacy(int id) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPharmacy(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(id, 1);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                int intId = rSet.getInt(1);
                String strName = rSet.getString(2);
                Float fltLatitude = rSet.getFloat(4);
                Float fltLongitude = rSet.getFloat(5);
                String strStreetName = rSet.getString(6);
                String strDoorNumber = rSet.getString(7);
                String strPostalCode = rSet.getString(8);
                String strLocality = rSet.getString(9);
                String strCountry = rSet.getString(10);

                return new Pharmacy(intId, strName, new Address(fltLatitude, fltLongitude, strStreetName, strDoorNumber, strPostalCode, strLocality, strCountry));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Order with ID:" + id);
    }


    /**
     * DATABASE
     */


    public boolean addPharmacyToDB(Pharmacy p) {
        return addPharmacyToDB(p.getName(), p.getAddress());
    }

    private boolean addPharmacyToDB(String strName, Address oAddress) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addPharmacy(?,?,?,?,?) }");

            callStmt.setString(1, strName);
            callStmt.setFloat(2, oAddress.getM_latitude());
            callStmt.setFloat(3, oAddress.getM_longitude());
            callStmt.setString(4, oAddress.getM_streetName());
            callStmt.setString(5, oAddress.getM_doorNumber());
            callStmt.setString(6, oAddress.getM_postalCode());
            callStmt.setString(7, oAddress.getM_locality());
            callStmt.setString(8, oAddress.getM_country());

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

}
