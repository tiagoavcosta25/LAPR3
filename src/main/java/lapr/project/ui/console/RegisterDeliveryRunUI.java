package lapr.project.ui.console;

import lapr.project.controller.RegisterDeliveryRunController;
import lapr.project.model.Order;
import lapr.project.ui.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterDeliveryRunUI implements UI {

    private static final Logger LOGGER = Logger.getLogger(RegisterDeliveryRunUI.class.getName());

    public void run(){
        try{
            Scanner sc = new Scanner(System.in);
            RegisterDeliveryRunController oCtrl = new RegisterDeliveryRunController();

            boolean flag;

            do{

                List<Integer> lstIds = new ArrayList<>();
                Integer intOrderId;

                System.out.println("Delivery Run:\n");

                do{
                    System.out.print("Choose an Order's Id to add to this Delivery Run (Type 0 to end the insertion): ");
                    intOrderId = Integer.parseInt(sc.nextLine());
                    System.out.println();

                    if(intOrderId == 0){
                        break;
                    }

                    lstIds.add(intOrderId);

                    //TODO: Arranjar
                }while(intOrderId != 0);

                List<Order> lstOrders = new ArrayList<>(); // MUDAR ISTO PARA O NOVO METODO

                //TODO: Arranjar
                if(lstOrders == null){
                    LOGGER.log(Level.WARNING,"The Orders Must be from the same pharmacy");
                }

                String choice;
                do {

                    System.out.print("Do you wish to calculate the path by Time or Energy (T/E)");
                    choice = sc.nextLine();
                    System.out.println();

                } while(!choice.equalsIgnoreCase("t") && !choice.equalsIgnoreCase("e"));

                boolean timeOrEnergy = choice.equalsIgnoreCase("e");

                do {

                    System.out.print("Do you wish to use extensive backtrack? (Y/N)");
                    choice = sc.nextLine();
                    System.out.println();

                } while(!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));

                boolean extensiveBacktrack = choice.equalsIgnoreCase("y");

                if(oCtrl.registerDeliveryRun(lstOrders,timeOrEnergy,extensiveBacktrack)){
                    LOGGER.log(Level.INFO, "Delivery Run Registered with success.");
                    flag = true;
                } else {
                    LOGGER.log(Level.INFO,"Something went wrong, try again. Delivery Run not Registered. If you need any help, please contact us using help@teamlisa.com.");
                    flag = false;
                }
            } while(!flag);

        } catch (Exception e){
            LOGGER.log(Level.INFO,"Something went wrong, try again. Delivery Run not Registered. If you need any help, please contact us using help@teamlisa.com.");
        }
    }
}
