package lapr.project.controller;

import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.service.DroneService;
import lapr.project.model.service.PharmacyService;

import java.util.List;

public class UpdateDroneController {

    /**
     * Pharmacy Management class
     */
    private PharmacyService moPharmacyService;

    /**
     * Drone Management class
     */
    private DroneService moDroneService;

    /**
     * An empty constructor of RegisterDroneController that initiates the platform variable by getting it from the ApplicationPOT.
     */
    public UpdateDroneController() {
        this.moPharmacyService = new PharmacyService();
        this.moDroneService = new DroneService();
    }

    /**
     * The method returns the list of pharmacies.
     */
    public List<Pharmacy> showPharmacies() {
        return this.moPharmacyService.getPharmacies();
    }

    /**
     * The method returns the list of drones.
     */
    public List<Drone> showDronesList (String strPharmacyEmail) {
        try {
            return moDroneService.getDronesList(strPharmacyEmail);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * The method updates a drone from the database.
     */
    public boolean updateDrone(int intId, double dblBatteryPerc, String strDesignation, double dblPotency, double dblWeight,
                                 double dblMaxPayload, int intBatteryCapacity, double dblBatteryVoltage,
                                 double dblEfficiency){

        return moDroneService.updateDroneFromDB(intId, dblBatteryPerc, strDesignation, dblPotency, dblWeight, dblMaxPayload,
                intBatteryCapacity, dblBatteryVoltage, dblEfficiency);
    }
}
