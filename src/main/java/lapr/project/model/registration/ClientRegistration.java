package lapr.project.model.registration;

import lapr.project.model.Client;

public class ClientRegistration {


    public void registerNewUser(String name, String email, String password, String address,
                                String gpsLocation, Integer creditCardNr, Integer validityDate, Integer CCV) {

        Client client = new Client(name,email,password,address,gpsLocation,creditCardNr,validityDate,CCV);
        client.save();

    }
}
