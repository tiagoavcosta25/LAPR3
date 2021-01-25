package lapr.project.ui.console;

import lapr.project.controller.IssueDeliveryNoteController;
import lapr.project.ui.UI;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IssueDeliveryNoteUI implements UI {

    private static final Logger LOGGER = Logger.getLogger(IssueDeliveryNoteUI.class.getName());

    public void run(){
        try {

            IssueDeliveryNoteController oCtrl = new IssueDeliveryNoteController();

            Scanner sc = new Scanner(System.in);
            System.out.print("Pharmacy Transfer ID: ");
            Integer intPharmacyTransferId = Integer.parseInt(sc.nextLine());

            if (!oCtrl.issueDeliveryNote(intPharmacyTransferId)){
                throw new Exception();
            }

            LOGGER.log(Level.INFO, "Delivery note issued with success.");

        }catch (Exception e){
            LOGGER.log(Level.WARNING, "There was a problem issuing your delivery note.");
        }
    }
}
