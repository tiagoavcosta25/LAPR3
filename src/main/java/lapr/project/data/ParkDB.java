package lapr.project.data;

import lapr.project.model.Park;
import lapr.project.model.VehicleType;

import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 * Park Database.
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

public class ParkDB extends  DataHandler{

    /**
     * Calls the method thats adds a Park to the Database.
     * @param strPharmacyEmail Park's Pharmacy Email.
     * @param p Park.
     * @param intNonChargingSlots Park's Non Charging Slots.
     * @param intChargingSlots Park's Charging Slots.
     * @return true if the Park is added. False if otherwise.
     */
    public boolean addParkToDB(String strPharmacyEmail, Park p, int intNonChargingSlots, int intChargingSlots) {
        return addParkToDB(strPharmacyEmail, p.getVehicleType(), p.getMaxSlotsNumber(), p.getTotalOutputCurrent(),
                intNonChargingSlots, intChargingSlots);
    }

    /**
     * Adds the Park to the Database.
     * @param strPharmacyEmail Park's Pharmacy Email.
     * @param enumVehicleType Park's Vehicle Type.
     * @param intMaxSlotsNumber Park's Max Slots Number.
     * @param fltOutputCurrent Park's Current Output.
     * @param intNonChargingSlots Park's Non Charging Slots.
     * @param intChargingSlots Park's Charging Slots.
     * @return true if the Park is added. False if otherwise.
     */
    private boolean addParkToDB(String strPharmacyEmail, VehicleType enumVehicleType, int intMaxSlotsNumber,
                                Double fltOutputCurrent, int intNonChargingSlots, int intChargingSlots) {
        boolean flag = true;
        try(CallableStatement callStmt = getConnection().prepareCall("{ call addPark(?,?,?,?,?,?) }");) {

            callStmt.setString(1, strPharmacyEmail);
            callStmt.setString(2, enumVehicleType.getDesignation());
            callStmt.setInt(3, intMaxSlotsNumber);
            callStmt.setDouble(4, fltOutputCurrent);
            callStmt.setInt(5, intNonChargingSlots);
            callStmt.setInt(6, intChargingSlots);
            callStmt.execute();

        } catch (SQLException e) {
            flag = false;
        } finally {
            closeAll();
        }
        return flag;
    }
}
