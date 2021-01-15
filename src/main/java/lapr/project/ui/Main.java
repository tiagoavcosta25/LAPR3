package lapr.project.ui;

import javafx.util.Pair;
import lapr.project.controller.*;
import lapr.project.data.ClientDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Address;
import lapr.project.model.Pharmacy;
import lapr.project.utils.DirectoryVerification;
import lapr.project.utils.EmailSender;

import java.util.ArrayList;
import java.util.LinkedList;
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
        System.out.println("Hello");
        System.out.println(DirectoryVerification.verifyFileCreation("src-C/estimate_files", ".data.flag", 45000));
    }
}