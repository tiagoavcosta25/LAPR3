package lapr.project.model.service;

import lapr.project.data.ClientDB;
import lapr.project.model.Client;
import lapr.project.model.CreditCard;
import lapr.project.utils.EmailSender;
import lapr.project.utils.WriteFile;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientService {

    private static final Logger LOGGER = Logger.getLogger(ClientService.class.getName());
    private ClientDB moClientDB;

    public ClientService() {
        moClientDB = new ClientDB();
    }


    /**
     * Validates the input information regarding
     * a Client
     *
     * @param name          Client's name
     * @param email         Client's email
     * @param password      Client's password
     * @param latitude      Client's latitude
     * @param longitude     Client's longitude
     * @param streetName    Client's street name
     * @param doorNumber    Client's door number
     * @param postalCode    Client's postal code
     * @param locality      Client's locality
     * @param country       Client's country
     * @param lstCreditCard Client's credit card list
     * @return True if input is valid, false if otherwise
     */
    public boolean validateInput(String name, Integer nif, String email, String password, Double latitude, Double longitude,
                                 Double altitude, String streetName, String doorNumber, String postalCode, String locality,
                                 String country, List<CreditCard> lstCreditCard) {

        if (name.isEmpty() || nif <= 0 || email.isEmpty() || password.isEmpty() || streetName.isEmpty() || postalCode.isEmpty()
                || locality.isEmpty() || country.isEmpty() || doorNumber.isEmpty() || latitude == 0 || longitude == 0 || altitude <= 0
                || lstCreditCard.isEmpty()) return false;


        if (!email.contains("@")) return false;

        if ((int) (Math.log10(nif) + 1) != 9) return false;

        for (CreditCard cc : lstCreditCard) {
            if ((int) (Math.log10(cc.getCCV()) + 1) != 3) return false;
        }


        if (password.length() < 6) return false;

        return true;
    }


    public Client newClient(String name, Integer nif, String email, String password, Double latitude, Double longitude, Double altitude,
                            String streetName, String doorNumber, String postalCode, String locality, String country,
                            List<CreditCard> lstCreditCard) throws NoSuchAlgorithmException {
        return new Client(name, nif, email, password, latitude, longitude, altitude, streetName, doorNumber, postalCode, locality, country,
                lstCreditCard);
    }

    public boolean registerNewClient(Client c) {
        if (moClientDB.addClientToDB(c)) {
            LOGGER.log(Level.INFO, "Successfully registered!");
            String creditCardBody = "";
            for (CreditCard cc : c.getLstCreditCard()) {
                creditCardBody += cc.getCreditCardNr() + ", ";
            }
            creditCardBody = creditCardBody.substring(0, creditCardBody.length() - 2);
            String body = String.format("Account Information:\n\nName: %s\nNIF: %s\nAddress: %s, %s, %s, %s\nCredit Card(/s): %s\n\n"
                    , c.getName(), c.getNif(), c.getAddress().getStreetName(),
                    c.getAddress().getDoorNumber(), c.getAddress().getLocality(), c.getAddress().getCountry(), creditCardBody);
            EmailSender.sendEmail(c.getEmail(), "Account Creation", body
            );
            WriteFile.write("ClientRegistration_" + c.getEmail(), body);
            return true;
        } else return false;
    }

    public Client getClientByEmail(String strEmail) {
        return moClientDB.getClientByEmail(strEmail);
    }

    public List<CreditCard> getCreditCardsByClient(String strEmail) {
        return this.moClientDB.getCreditCardsByClient(strEmail);
    }


    public boolean updateClientCredits(String strEmail, Integer intCredits) {
        return this.moClientDB.updateClientCredits(strEmail, intCredits);
    }
}
