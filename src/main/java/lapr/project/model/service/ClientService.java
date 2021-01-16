package lapr.project.model.service;

import lapr.project.data.ClientDB;
import lapr.project.model.Client;
import lapr.project.model.CreditCard;
import lapr.project.utils.EmailSender;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
     * @param lstCreditCard Client's credit card list
     * @param validityDate Client's credit card's validity date
     * @param CCV          Client's credit card's CCV
     * @return True if input is valid, false if otherwise
     */
    public boolean validateInput(String name, Integer nif, String email, String password, Double latitude, Double longitude, String streetName,
                                 String doorNumber, String postalCode, String locality, String country, List<CreditCard> lstCreditCard) {

        if (name.isEmpty() || nif <= 0 || email.isEmpty() || password.isEmpty() || streetName.isEmpty() || postalCode.isEmpty()
                || locality.isEmpty() || country.isEmpty() || doorNumber.isEmpty() || latitude <= 0 || longitude <= 0
                || lstCreditCard.isEmpty()) return false;


        if (!email.contains("@")) return false;

        if ((int) (Math.log10(nif) + 1) != 9) return false;

        for (CreditCard cc : lstCreditCard) {
            if ((int) (Math.log10(cc.getCCV()) + 1) != 3);
        }


        if (password.length() < 6) return false;

        return true;
    }



    public Client newClient(String name, Integer nif, String email, String password, Double latitude, Double longitude, String streetName,
                             String doorNumber, String postalCode, String locality, String country, List<CreditCard> lstCreditCard) throws NoSuchAlgorithmException {
        return new Client(name, nif, email, password, latitude, longitude, streetName, doorNumber, postalCode, locality, country,
                lstCreditCard);
    }

    public boolean registerNewClient(Client c) {
        if (m_oClientDB.addClientToDB(c)) {
            EmailSender.emailSender(c.getEmail(),"Account Creation",
                    String.format("Your brand new account has been registered to the System!\n___________________________________________________________________\n" +
                            "Account Information:\n\nName: %s\nNIF: %s\nAddress: %s, %s, %s, %s\nFirst Credit Card: %d\n\n___________________________________________________________________\n\n" +
                            "Thank you for choosing us.\nKing regards,\nPharmacy Service G21.",c.getName(),c.getNif(),c.getAddress().getStreetName(),
                            c.getAddress().getDoorNumber(), c.getAddress().getLocality(),c.getAddress().getCountry(),c.getLstCreditCard().get(0).getCreditCardNr()));
            return true;
        }else return false;
    }

    public Client getClientByEmail(String strEmail) {
        return m_oClientDB.getClientByEmail(strEmail);
    }

    public List<CreditCard> getCreditCardsByClient(String strEmail) {
        return this.m_oClientDB.getCreditCardsByClient(strEmail);
    }
}
