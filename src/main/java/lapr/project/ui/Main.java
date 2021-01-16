package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.utils.DirectoryVerification;

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
        System.out.println(DirectoryVerification.verifyFileCreation("src-C/estimate_files", ".flag", 45000));
    }
}