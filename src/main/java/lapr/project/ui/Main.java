package lapr.project.ui;

import lapr.project.controller.IssueDeliveryNoteController;
import lapr.project.utils.FileReader;

class Main {

    public static void main(String[] args) {
        System.out.println("Hello world\n");
        IssueDeliveryNoteController ctrl = new IssueDeliveryNoteController();
        ctrl.issueDeliveryNote(1);
        System.out.println("\nGoodbye world");
    }
}