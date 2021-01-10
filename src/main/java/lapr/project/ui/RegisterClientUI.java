package lapr.project.ui;

import lapr.project.controller.RegisterClientController;

public class RegisterClientUI {


    public static void main(String[] args) throws Exception {

        RegisterClientController m_ctrl = new RegisterClientController();
        String name = "slow";
        Integer nif = 123456786;
        String email = "slow@gmail.co";
        String password = "pw1234";
        Double latitude = 12345678d;
        Double longitude = 12345672d;
        String streetName = "Rua das Flores";
        String doorNumber = "2º Esquerdo";
        String postalCode = "4444-121";
        String locality = "Mafamude";
        String country = "Portugal";
        Long creditCardNr = 1234123412341234L;
        String validityDate = "10/23";
        Integer ccv = 123;
        if (m_ctrl.registerNewClient(name, nif, email, password, latitude, longitude, streetName, doorNumber,
                postalCode, locality, country, creditCardNr, validityDate, ccv)) {
            System.out.println("Operation WAS Successfull!");

        } else System.out.println("Operation was NOT Successfull!");


    }
}



