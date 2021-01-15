package lapr.project.controller;

import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.service.DroneService;
import lapr.project.model.service.PharmacyService;

import java.util.List;

public class RegisterDroneController {

    /**
     * Drone class instance
     */
    private Drone m_oDrone;

    /**
     * Pharmacy Management class
     */
    private PharmacyService m_oPharmacyService;

    /**
     * Drone Management class
     */
    private DroneService m_oDroneService;

    /**
     * An empty constructor of RegisterDroneController.
     */
    public RegisterDroneController() {
        this.m_oPharmacyService = new PharmacyService();
        this.m_oDroneService = new DroneService();
    }

    /**
     * The method receives Drone's battery percentage, charging status, potency, weight, battery capacity and a pharmacy.
     * Initiates the Pharmacy instance and the Scooter instance with the provided data.
     * The method returns the validation of that instance of Drone. True if the data is correct and false if
     * it doesn't.
     * @param fltBatteryPerc Scooter's battery percentage
     * @param strCharginStatus Scooter's charging status
     * @param fltPotency Scooter's potency
     * @param fltWeight Scooter's weight
     * @param intBatteryCapacity Scooter's battery capacity
     * @param oPharmacy Pharmacy's instance
     */
    public boolean newDrone( Float fltBatteryPerc, String strCharginStatus, Float fltPotency,
                               Float fltWeight, int intBatteryCapacity, Float fltBatteryVoltage,
                               Float fltMaxPayload, Pharmacy oPharmacy) {
        try {
            this.m_oDrone = m_oDroneService.newDrone(fltBatteryPerc,strCharginStatus, fltPotency, fltWeight,
                    intBatteryCapacity, fltBatteryVoltage, fltMaxPayload, oPharmacy);
            return true;
        }
        catch(Exception ex) {
            this.m_oDrone = null;
        }
        return false;
    }

    /**
     * The method registers an order to the database.
     */
    public boolean registersDrone() {
        return this.m_oDroneService.registerDrone(m_oDrone);
    }

    /**
     * The method returns the list of pharmacies.
     */
    public List<Pharmacy> showPharmacies() {
        return this.m_oPharmacyService.getPharmacies();
    }

    /**
     * The method sets the scooter.
     */
    public void setDrone(Drone oDrone) {
        this.m_oDrone = oDrone;
    }
}