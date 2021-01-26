package lapr.project.controller;

import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.service.DroneService;
import lapr.project.model.service.PharmacyService;
import java.util.ArrayList;
import java.util.Collections;
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

    public PharmacyService getPharmacyService() {
        return moPharmacyService;
    }

    public void setPharmacyService(PharmacyService oPharmacyService) {
        this.moPharmacyService = oPharmacyService;
    }

    public DroneService getDroneService() {
        return moDroneService;
    }

    public void setDroneService(DroneService oDroneService) {
        this.moDroneService = oDroneService;
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
            return Collections.emptyList();
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
