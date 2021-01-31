package lapr.project.ui.console;

import lapr.project.controller.RegisterDeliveryRunController;
import lapr.project.model.Order;
import lapr.project.model.OrderStatus;
import lapr.project.model.Pharmacy;
import lapr.project.model.service.OrderService;
import lapr.project.model.service.PharmacyService;
import lapr.project.ui.Menu;
import lapr.project.ui.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterDeliveryRunUI implements UI {

    private static final Logger LOGGER = Logger.getLogger(RegisterDeliveryRunUI.class.getName());

    public void run() {
        try {
            Scanner sc = new Scanner(System.in);
            RegisterDeliveryRunController oCtrl = new RegisterDeliveryRunController();
            PharmacyService oPharmacyService = new PharmacyService();

            boolean flag;

            do {
                Menu.clear();
                List<Integer> lstIds = new ArrayList<>();
                String strPharmacyEmail;
                Integer intOrderId;
                Double dblTotalWeight = 0.0;

                List<Pharmacy> lstPhar = oPharmacyService.getPharmacies();
                System.out.println("Delivery Run:\n");
                for (Pharmacy p : lstPhar) {
                    System.out.println(String.format("Pharmacy Name: %s || Email: %s || Address: %s || Stock: %s",
                            p.getName(), p.getEmail(), p.getAddress().getStreetName(), p.getStock().keySet()));
                }
                System.out.println();
                System.out.print("Choose a Pharmacy (by email) that refers to your new Delivery Run: ");

                strPharmacyEmail = sc.nextLine();
                Menu.clear();
                List<Order> lstOrdersByPharmacy = oCtrl.getOrdersList(strPharmacyEmail);
                List<Order> lstOrdered = new ArrayList<>();
                for (Order ord : lstOrdersByPharmacy) {
                    if (ord.getStatus().equalsIgnoreCase(OrderStatus.ORDERED.getDesignation()) && !ord.isHomeDelivery()) {
                        lstOrdered.add(ord);
                    }
                }

                if(lstOrdered.isEmpty()){throw new Exception();}

                List<Order> lstTotalOrders = new ArrayList<>(lstOrdered);
                do {
                    Menu.clear();
                    if (lstOrdered.isEmpty()) break;

                    System.out.println();
                    for (Order o : lstOrdered) {
                        System.out.println(String.format("Order ID: %d || Client: %s || Date: %s || Description: %s || Weight: %.2f kg", o.getId(),
                                o.getClient().getName(), o.getOrderDate(), o.getDescription(), o.getTotalWeight()));
                    }
                    System.out.println();
                    System.out.println("Current Orders: " + lstIds);
                    System.out.println(String.format("Current Order Total Weight: %.2f kg", dblTotalWeight));
                    System.out.println();
                    System.out.print("Choose an Order's Id to add to this Delivery Run (Type 0 to end the insertion): ");

                    intOrderId = Integer.parseInt(sc.nextLine());
                    System.out.println();

                    if (intOrderId == 0) {
                        break;
                    }


                    if (!lstIds.contains(intOrderId)) {
                        boolean addFlag = false;
                        for (Order order : lstOrdered) {
                            if (order.getId() == intOrderId) {
                                dblTotalWeight += order.getTotalWeight();
                                addFlag = true;
                                lstOrdered.remove(order);
                                break;
                            }
                        }
                        if (addFlag) {
                            lstIds.add(intOrderId);
                        }
                    }

                } while (intOrderId != 0);

                List<Order> lstOrders = new ArrayList<>(); // MUDAR ISTO PARA O NOVO METODO

                for (Integer i : lstIds) {
                    for (Order order : lstTotalOrders) {
                        if (i == order.getId()) lstOrders.add(order);
                    }

                }

                if (lstOrders == null) {
                    LOGGER.log(Level.WARNING, "The Orders Must be from the same pharmacy");
                }

                String choice;
                do {
                    Menu.clear();
                    System.out.print("Do you wish to calculate the path by Time or Energy (T/E): ");
                    choice = sc.nextLine();
                    System.out.println();

                } while (!choice.equalsIgnoreCase("t") && !choice.equalsIgnoreCase("e"));

                boolean timeOrEnergy = choice.equalsIgnoreCase("e");

                do {
                    Menu.clear();
                    System.out.print("Do you wish to use extensive backtrack? (Y/N): ");
                    choice = sc.nextLine();
                    System.out.println();

                } while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));

                boolean extensiveBacktrack = choice.equalsIgnoreCase("y");

                if (oCtrl.registerDeliveryRun(lstOrders, timeOrEnergy, extensiveBacktrack)) {
                    LOGGER.log(Level.INFO, "Delivery Run Registered with success.");
                    flag = true;
                } else {
                    LOGGER.log(Level.INFO, "Something went wrong, try again. Delivery Run not Registered. If you need any help, please contact us using help@teamlisa.com.");
                    flag = false;
                }
            } while (!flag);

        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Something went wrong, try again. Delivery Run not Registered. If you need any help, please contact us using help@teamlisa.com.");
        }
    }
}
