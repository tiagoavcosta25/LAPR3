package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.utils.EmailSender;

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
        EmailSender.emailSender("farmacyservice.g21@gmail.com", "Subject do email", "Olá!\nO meu nome é Tiago e bem vindo à forex!");

    }
}