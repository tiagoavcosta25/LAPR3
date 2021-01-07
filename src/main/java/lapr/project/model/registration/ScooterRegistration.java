package lapr.project.model.registration;


import lapr.project.data.DataHandler;
import lapr.project.model.Scooter;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class ScooterRegistration extends DataHandler {


    /**
     * Registers a new Scooter in the Database
     *
     * @param intBatteryPerc        Scooter's battery percentage
     * @param intCharginStatus      Scooter's charging status
     * @param intPotency            Scooter's potency
     * @param intWeight             Scooter's weight
     * @param intBatteryCapacity    Scooter's battery capacity
     * @return True if Scooter was registered, false if otherwise
     */
    public boolean registerNewScooter(int intBatteryPerc, int intCharginStatus, int intPotency,
                                      int intWeight, int intBatteryCapacity) {
        Scooter scooter = new Scooter(intBatteryPerc, intCharginStatus, intPotency, intWeight, intBatteryCapacity);
        return addScooterToDB(scooter);

    }


    /**
     * DATABASE
     */


    public boolean addScooterToDB(Scooter s) {
        return addScooterToDB(s.getBatteryPerc(), s.getCharginStatus(), s.getPotency(), s.getWeight(), s.getBatteryCapacity());
    }

    private boolean addScooterToDB(int intBatteryPerc, int intCharginStatus, int intPotency, int intWeight,
                                   int intBatteryCapacity) {
        boolean flag = true;
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{ call addProduct(?,?,?,?,?) }");

            callStmt.setInt(1, intBatteryPerc);
            callStmt.setInt(2, intCharginStatus);
            callStmt.setInt(3, intPotency);
            callStmt.setInt(4, intWeight);
            callStmt.setInt(5, intBatteryCapacity);

            callStmt.execute();

            closeAll();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

}
