package lapr.project.ui;

import lapr.project.controller.RegisterClientController;

public class RegisterClientUI implements Runnable{


    public void run() {

        RegisterClientController m_ctrl = new RegisterClientController();

        try {
            if(m_ctrl.registerNewClient("Rodrigo", 123456789, "slow@gmail.com", "abcdef", 180090980d,
                    234567899d, "Street", "2esq", "4430-183", "Mafamude",
                    "Portugal", 1234123412341234L, "10/23", 123)) {
                System.out.println("Operation WAS Successfull!");

            }else System.out.println("Operation was NOT Successfull!");
        } catch (Exception e) {
            System.out.println("Operation was NOT Successfull!");
        } finally {
            Menu.menu();
        }
    }
}

