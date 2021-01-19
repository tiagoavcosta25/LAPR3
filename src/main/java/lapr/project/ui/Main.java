package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.data.ParkDB;
import lapr.project.model.*;
import lapr.project.ui.console.MakeAnOrderUI;
import lapr.project.utils.DirectoryVerification;
import lapr.project.utils.EnergyCalculator;
import lapr.project.utils.FileReader;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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


        FileReader.readFiles();


        System.out.println("\nGoodbye world");
    }
}