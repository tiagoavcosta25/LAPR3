package lapr.project.ui;

import lapr.project.controller.IssueDeliveryNoteController;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.Order;
import lapr.project.model.Pharmacy;
import lapr.project.utils.FileReader;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("Hello world\n");


        Pharmacy oPharmacy = new Pharmacy("Pharmacy Trindade","info@trindade.com",new Address(41.15227d,-8.60929d,104d,
                "Rua da Trindade","123","4000-123","Porto","Portugal"));
        List<Order> lstOrders = new ArrayList<>();
        lstOrders.add(new Order("Para Presente, Enviar Embrulhado.", true, new Client("", 123456789, "fernando@gmail.com", "pass",
                41.14582d,-8.61398d,87.0d,"Clerigos","2esq","4444-111","Porto","Portugal", new ArrayList<>()),
                oPharmacy, new TreeMap<>()));

        lstOrders.add(new Order("Para Presente, Enviar Embrulhado.", true, new Client("", 123456781, "joana@gmail.com", "pass",
                41.14063d,-8.61118d,25.0d,"Cais da Ribeira","3esq","4000-555","Porto","Portugal", new ArrayList<>()),
                oPharmacy, new TreeMap<>()));

        System.out.println("\nGoodbye world");
    }
}