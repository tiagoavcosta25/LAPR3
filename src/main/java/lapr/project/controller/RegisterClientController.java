package lapr.project.controller;

import lapr.project.model.Client;
import lapr.project.model.CreditCard;
import lapr.project.model.service.ClientService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RegisterClientController {

    private ClientService m_oClientService;

    public RegisterClientController() {
        m_oClientService = new ClientService();
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
    public boolean registerNewClient(String name, Integer nif, String email, String password, Double latitude, Double longitude, String streetName,
                                     String doorNumber, String postalCode, String locality, String country, List<CreditCard> lstCreditCardNr) throws Exception {

        try {
            if (m_oClientService.validateInput(name, nif, email, password, latitude, longitude, streetName, doorNumber, postalCode, locality, country,
                    lstCreditCardNr)) {
                Client c = m_oClientService.newClient(name, nif, email, password, latitude, longitude, streetName, doorNumber, postalCode, locality, country,
                        lstCreditCardNr);
                return m_oClientService.registerNewClient(c);
            }
        } catch (Exception e) {
            return false;
        }
        return false;

    }

}
