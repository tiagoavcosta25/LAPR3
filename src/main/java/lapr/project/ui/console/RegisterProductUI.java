package lapr.project.ui.console;

import lapr.project.controller.RegisterProductController;
import lapr.project.ui.UI;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterProductUI implements UI {

    private static final Logger LOGGER = Logger.getLogger(RegisterProductUI.class.getName());

    public void run(){
        RegisterProductController oCtrl = new RegisterProductController();
        Scanner sc = new Scanner(System.in);
        do{
            try{
                System.out.print("Register a Product:\n\nName: ");
                String strName = sc.nextLine();
                System.out.print("Description: ");
                String strDescription = sc.nextLine();
                System.out.print("Unitary Price: ");
                Double fltUnitaryPrice = Double.parseDouble(sc.nextLine());
                System.out.print("Unitary Weight: ");
                Double fltUnitaryWeight = Double.parseDouble(sc.nextLine());

                if(oCtrl.registerProduct(strName, strDescription, fltUnitaryPrice, fltUnitaryWeight)){
                    LOGGER.log(Level.INFO, "Product Registered with success.");
                    break;
                }
                LOGGER.log(Level.WARNING, "Error Registering a Product.");
            } catch (Exception e){
                LOGGER.log(Level.WARNING, "Error Registering a Product.");
            }
        }while (true);
    }
}
