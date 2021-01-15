package lapr.project.ui;

import javafx.util.Pair;
import lapr.project.controller.*;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Address;
import lapr.project.model.Pharmacy;
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
        //EmailSender.emailSender("farmacyservice.g21@gmail.com", "Subject do email", "Olá!\nO meu nome é Tiago e bem vindo à forex!");
        ApplicationPOT.getInstance().getWorldMap().createGraph();
        System.out.println(ApplicationPOT.getInstance().getWorldMap().getGraph());
        PharmacyDB pDB = new PharmacyDB();


        Address pharmacy = pDB.getPharmacy(1).getAddress();
        List<Address> lst = new ArrayList<>();
        lst.add(new Address(13,41.1810722,-8.5877482,"574","1","4747-857",
                "loc1","Portugal"));
        lst.add(new Address(14,41.1185442,-8.5874845,"252","1","4747-857",
                "loc1","Portugal"));
        Pair<LinkedList<Address>, Double> pair = ApplicationPOT.getInstance().getWorldMap().calculateMostEfficientPath(pharmacy,pharmacy,lst);
        System.out.println(pair.getKey());
        System.out.println(pair.getValue());
    }
}