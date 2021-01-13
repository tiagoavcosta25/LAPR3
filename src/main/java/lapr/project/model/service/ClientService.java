package lapr.project.model.service;

import lapr.project.data.ClientDB;
import lapr.project.model.Client;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class ClientService {

    private ClientDB m_oClientDB;

    public ClientService() {
        m_oClientDB = new ClientDB();
    }


    /**
     * Validates the input information regarding
     * a Client
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
     * @param creditCardNr Client's credit card number
     * @param validityDate Client's credit card's validity date
     * @param CCV          Client's credit card's CCV
     * @return True if input is valid, false if otherwise
     */
    public boolean validateInput(String name, Integer nif, String email, String password, Double latitude, Double longitude, String streetName,
                                 String doorNumber, String postalCode, String locality, String country, long creditCardNr,
                                 Date validityDate, Integer CCV) {

        if (name.isEmpty() || nif <= 0 || email.isEmpty() || password.isEmpty() || streetName.isEmpty() || postalCode.isEmpty()
                || locality.isEmpty() || country.isEmpty() || doorNumber.isEmpty() || latitude <= 0 || longitude <= 0
                || creditCardNr <= 0 || validityDate.before(new Date(System.currentTimeMillis())) || CCV <= 0) return false;


        if (!email.contains("@")) return false;

        if ((int) (Math.log10(CCV) + 1) != 3
                || (int) (Math.log10(nif) + 1) != 9) return false;

        if (password.length() < 6) return false;

        return true;
    }



    public Client newClient(String name, Integer nif, String email, String password, Double latitude, Double longitude, String streetName,
                             String doorNumber, String postalCode, String locality, String country, long creditCardNr,
                             Date validityDate, Integer CCV) throws NoSuchAlgorithmException {
        return new Client(name, nif, email, password, latitude, longitude, streetName, doorNumber, postalCode, locality, country,
                creditCardNr, validityDate, CCV);
    }

    public boolean registerNewClient(Client c) {
        return m_oClientDB.addClientToDB(c);
    }

}
