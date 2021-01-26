package lapr.project.controller;

import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.service.DroneService;
import lapr.project.model.service.PharmacyService;

import java.util.Collections;
import java.util.List;

public class UpdateDroneController {

    /**
     * Pharmacy Service
     */
    private PharmacyService moPharmacyService;

    /**
     * Drone Service
     */
    private DroneService moDroneService;

    /**
     * An empty constructor of RegisterDroneController that initiates the platform variable
     * by getting it from the ApplicationPOT.
     */
    public UpdateDroneController() {
        this.moPharmacyService = new PharmacyService();
        this.moDroneService = new DroneService();
    }

    /**
     * Returns the Pharmacy Service
     *
     * @return Pharmacy Service
     */
    public PharmacyService getPharmacyService() {
        return moPharmacyService;
    }

    /**
     * Sets the Pharmacy Service to the one given by parameter
     *
     * @param oPharmacyService mew Pharmacy Service
     */
    public void setPharmacyService(PharmacyService oPharmacyService) {
        this.moPharmacyService = oPharmacyService;
    }

    /**
     * Returns the Drone Service
     *
     * @return Drone Service
     */
    public DroneService getDroneService() {
        return moDroneService;
    }

    /**
     * Sets the Drone Service to the one given by parameter
     *
     * @param oDroneService new Drone Service
     */
    public void setDroneService(DroneService oDroneService) {
        this.moDroneService = oDroneService;
    }

    /**
     * Returns the List of Pharmacies
     *
     * @return List of Pharmacies
     */
    public List<Pharmacy> showPharmacies() {
        return this.moPharmacyService.getPharmacies();
    }

    /**
     * Returns the List of Drones related to the Pharmacy with the email
     * given by parameter
     *
     * @param strPharmacyEmail Pharmacy Email
     * @return List of Drones
     */
    public List<Drone> showDronesList(String strPharmacyEmail) {
        try {
            return moDroneService.getDronesList(strPharmacyEmail);
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    /**
     * Updates the information of the Drone basing on the atributes
     * given by parameter
     *
     * @param intId              Drone ID
     * @param dblBatteryPerc     Drone Battery Capacity
     * @param strDesignation     Drone Designation
     * @param dblPotency         Drone Potency
     * @param dblWeight          Drone Weigth
     * @param dblMaxPayload      Drone Max Payload
     * @param intBatteryCapacity Drone Battery Capacity
     * @param dblBatteryVoltage  Drone Batteru Voltage
     * @param dblEfficiency      Drone Battery Efficiency
     * @return True if the Drone was updated, false if otherwise
     */
    public boolean updateDrone(int intId, double dblBatteryPerc, String strDesignation, double dblPotency, double dblWeight,
                               double dblMaxPayload, int intBatteryCapacity, double dblBatteryVoltage,
                               double dblEfficiency) {

        return moDroneService.updateDroneFromDB(intId, dblBatteryPerc, strDesignation, dblPotency, dblWeight, dblMaxPayload,
                intBatteryCapacity, dblBatteryVoltage, dblEfficiency);
    }
}
