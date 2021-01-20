package lapr.project.controller;

import lapr.project.model.Drone;
import lapr.project.model.Pharmacy;
import lapr.project.model.VehicleModel;
import lapr.project.model.service.DroneService;
import lapr.project.model.service.PharmacyService;

import java.util.List;

public class RegisterDroneController {

    /**
     * Drone class instance
     */
    private Drone moDrone;

    /**
     * Pharmacy Management class
     */
    private PharmacyService moPharmacyService;

    /**
     * Drone Management class
     */
    private DroneService moDroneService;

    /**
     * An empty constructor of RegisterDroneController.
     */
    public RegisterDroneController() {
        this.moPharmacyService = new PharmacyService();
        this.moDroneService = new DroneService();
    }

    /**
     * The method receives Drone's battery percentage, charging status, potency, weight, battery capacity and a pharmacy.
     * Initiates the Pharmacy instance and the Scooter instance with the provided data.
     * The method returns the validation of that instance of Drone. True if the data is correct and false if
     * it doesn't.
     * @param oVehicleModel Drone's Vehicle Model
     * @param oPharmacy Pharmacy's instance
     */
    public boolean newDrone(VehicleModel oVehicleModel, Pharmacy oPharmacy) {
        try {
            this.moDrone = moDroneService.newDrone(oVehicleModel, oPharmacy);
            return true;
        }
        catch(Exception ex) {
            this.moDrone = null;
        }
        return false;
    }

    /**
     * The method registers an order to the database.
     */
    public boolean registersDrone() {
        return this.moDroneService.registerDrone(moDrone);
    }

    /**
     * The method returns the list of pharmacies.
     */
    public List<Pharmacy> showPharmacies() {
        return this.moPharmacyService.getPharmacies();
    }

    /**
     * The method sets the scooter.
     */
    public void setDrone(Drone oDrone) {
        this.moDrone = oDrone;
    }
}
