package lapr.project.controller;

import lapr.project.model.Platform;
import lapr.project.model.Scooter;
import lapr.project.model.registration.ScooterRegistration;

public class RegisterScooterController {

    private final ApplicationPOT m_oApp;
    private final Platform m_oPlatform;
    private Scooter m_oScooter;
    private ScooterRegistration m_oScooterRegistration;

    public RegisterScooterController() {
        this.m_oApp = ApplicationPOT.getInstance();
        this.m_oPlatform = m_oApp.getPlatform();
        //this.pharmacyRegistration = plat.getPharmacyRegistration();
        //this.scooterRegistration = plat.getScooterRegistration();
    }

    public boolean newScooter(int intBatteryPerc, int intCharginStatus, int intPotency,
                              int intWeight, int intBatteryCapacity)
    {
        try
        {
            /*this.m_oScooterRegistration = m_oPlatform.getScooterRegistration();
            Scooter oScooter = Pharmacy.newScooter(intBatteryPerc, intCharginStatus, intPotency, intWeight, intBatteryCapacity);
            return this.m_oScooterRegistration.validatesScooter(this.m_oScooter);*/
            return true;
        }
        catch(RuntimeException ex)
        {
            this.m_oScooter = null;
            return false;        }
    }
}
