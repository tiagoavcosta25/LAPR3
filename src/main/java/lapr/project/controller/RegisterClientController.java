package lapr.project.controller;

import lapr.project.model.Client;
import lapr.project.model.CreditCard;
import lapr.project.model.service.ClientService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterClientController {

    private static final Logger LOGGER = Logger.getLogger(RegisterClientController.class.getName());
    private ClientService moClientService;


    public RegisterClientController() {
        moClientService = new ClientService();
    }

    public ClientService getClientService() {
        return moClientService;
    }

    public void setClientService(ClientService oClientService) {
        this.moClientService = oClientService;
    }

    /**
     * Registers a new Client in the Database
     *
     * @param name         Client's name
     * @param email        Client's email
     * @param password     Client's password
     * @param latitude     Client's latitude
     * @param longitude    Client's longitude
     * @param streetName   Client's street name
     * @param doorNumber   Client's door number
     * @param postalCode   Client's postal code
     * @param locality     Client's locality
     * @param country      Client's country
     * @param lstCreditCardNr Client's credit card list
     * @param validityDate Client's credit card's validity date
     * @param CCV          Client's credit card's CCV
     * @return True if Client was registered, false if otherwise
     */
    public boolean registerNewClient(String name, Integer nif, String email, String password, Double latitude, Double longitude, Double altitude, String streetName,
                                     String doorNumber, String postalCode, String locality, String country, List<CreditCard> lstCreditCardNr) {

        try {
            if (moClientService.validateInput(name, nif, email, password, latitude, longitude, altitude, streetName, doorNumber, postalCode, locality, country,
                    lstCreditCardNr)) {
                Client c = moClientService.newClient(name, nif, email, password, latitude, longitude, altitude, streetName, doorNumber, postalCode, locality, country,
                        lstCreditCardNr);
                return moClientService.registerNewClient(c);
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Client was not successfully registered!");
            return false;
        }
        return false;

    }

}
