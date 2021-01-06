package lapr.project.controller;

import lapr.project.model.Platform;
import lapr.project.model.registration.ClientRegistration;

public class RegisterClientController {

    private ClientRegistration clientReg = new ClientRegistration();

    public void registerNewUser(String name, String email, String password, String address,
                                String gpsLocation, Integer creditCardNr, Integer validityDate, Integer CCV) {

        ApplicationPOT app = ApplicationPOT.getInstance();
        Platform plat = app.getPlatform();
        ClientRegistration clientReg = plat.getClientReg();
        clientReg.registerNewUser(name,email,password,address,gpsLocation,creditCardNr,validityDate,CCV);

    }
}
