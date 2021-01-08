package lapr.project.ui;

import lapr.project.controller.NotifyAndRemoveController;

public class NotifyAndRemoveUI {


    public static void main(String[] args) throws Exception {

        NotifyAndRemoveController m_ctrl = new NotifyAndRemoveController();

        if(m_ctrl.notifyAndRemove()) {
            System.out.println("The order cannot be entirely fufilled");
        }else System.out.println("Operation was NOT Successfull!");

    }
}

