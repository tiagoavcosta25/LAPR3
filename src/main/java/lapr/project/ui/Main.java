package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.data.ParkDB;
import lapr.project.model.Address;
import lapr.project.model.Park;
import lapr.project.model.UserSession;
import lapr.project.model.VehicleType;
import lapr.project.ui.console.MakeAnOrderUI;
import lapr.project.utils.DirectoryVerification;
import lapr.project.utils.EnergyCalculator;

import java.security.NoSuchAlgorithmException;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    private RegisterDeliveryRunController m_oRegisterDeliveryRunController;
    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {
        m_oRegisterDeliveryRunController = new RegisterDeliveryRunController();
    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello world\n");




        System.out.println("\nGoodbye world");
    }
}