package lapr.project.ui.console;

import lapr.project.controller.ApplicationPOT;
import lapr.project.controller.RemoveProductController;
import lapr.project.model.UserSession;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemoveProductUI {

    private static final Logger LOGGER = Logger.getLogger(RemoveProductUI.class.getName());

    public void run() {
        RemoveProductController oCtrl = new RemoveProductController();
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Remove a Product:\n\nName: ");
            String strName = sc.nextLine();
            ApplicationPOT.getInstance().setCurrentSession(new UserSession("admin1@gmail.com", UserSession.Role.ADMIN));
            if (oCtrl.removeProductFromDB(strName)) {
                LOGGER.log(Level.INFO, "Product Removed with success.");
            }else{
                LOGGER.log(Level.WARNING, "There was a problem removing the Product");
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "There was a problem removing the Product");
        }
    }

}
