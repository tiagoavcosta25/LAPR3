package lapr.project.data;

import lapr.project.model.Park;
import lapr.project.model.VehicleType;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class ParkDB extends  DataHandler{

    public boolean addParkToDB(int intPharmacyId, Park p, int intNonChargingSlots, int intChargingSlots) {
        return addParkToDB(intPharmacyId, p.getVehicleType(), p.getMaxSlotsNumber(), p.getTotalOutputCurrent(),
                intNonChargingSlots, intChargingSlots);
    }

    private boolean addParkToDB(int intPharmacyId, VehicleType enumVehicleType, int intMaxSlotsNumber,
                                float fltOutputCurrent, int intNonChargingSlots, int intChargingSlots) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addPark(?,?,?,?,?,?) }");

            callStmt.setInt(1, intPharmacyId);
            callStmt.setString(2, enumVehicleType.getDesignation());
            callStmt.setInt(3, intMaxSlotsNumber);
            callStmt.setFloat(4, fltOutputCurrent);
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
