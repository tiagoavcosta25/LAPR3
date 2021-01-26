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

/**
 * Client Service.
 * <p>
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 *
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class ClientService {

    /**
     * Logger which is used to generate warnings or information, with
     * a custom message
     */
    private static final Logger LOGGER = Logger.getLogger(ClientService.class.getName());

    /**
     * Client Database
     */
    private ClientDB moClientDB;

    /**
     * Empty Constructor of ClientService, which instantiates a new
     * Client Database
     */
    public ClientService() {
        moClientDB = new ClientDB();
    }

    /**
     * Returns the Client Database
     *
     * @return Client Database
     */
    public ClientDB getClientDB() {
        return moClientDB;
    }

    /**
     * Sets the Client Database to the one given by parameter
     *
     * @param oClientDB new Client Database
     */
    public void setClientDB(ClientDB oClientDB) {
        this.moClientDB = oClientDB;
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


        return password.length() >= 6;
    }

    /**
     * Creates a new Client basing on the atributes given by parameter
     *
     * @param name          Client name
     * @param nif           Client NIF
     * @param email         Client email
     * @param password      Client password
     * @param latitude      Client´s Address latitude
     * @param longitude     Client's Address longitude
     * @param altitude      Client's Address altitude
     * @param streetName    Client's Address street name
     * @param doorNumber    Client's Address door number
     * @param postalCode    Client's Address postal code
     * @param locality      Client's Address locality
     * @param country       Client's Address country
     * @param lstCreditCard Client's List of Credit Cards
     * @return Client containing the atributes given by parameter
     * @throws NoSuchAlgorithmException Exception raised when the encryptation of the password
     *                                  doesn't find an algorithm
     */
    public Client newClient(String name, Integer nif, String email, String password, Double latitude, Double longitude, Double altitude,
                            String streetName, String doorNumber, String postalCode, String locality, String country,
                            List<CreditCard> lstCreditCard) throws NoSuchAlgorithmException {
        return new Client(name, nif, email, password, latitude, longitude, altitude, streetName, doorNumber, postalCode, locality, country,
                lstCreditCard);
    }

    /**
     * Registers a new Client, which is given by parameter
     *
     * @param c Client to be registered
     * @return True if the Client was registered, false if otherwise
     */
    public boolean registerNewClient(Client c) {
        if (moClientDB.addClientToDB(c)) {
            LOGGER.log(Level.INFO, "Successfully registered!");
            StringBuilder creditCardBody = new StringBuilder();
            for (CreditCard cc : c.getLstCreditCard()) {
                creditCardBody.append(cc.getCreditCardNr()).append(", ");
            }
            creditCardBody = new StringBuilder(creditCardBody.substring(0, creditCardBody.length() - 2));
            String body = String.format("Account Information:%n%nName: %s%nNIF: %s%nAddress: %s, %s, %s, %s%nCredit Card(/s): %s%n%n"
                    , c.getName(), c.getNif(), c.getAddress().getStreetName(),
                    c.getAddress().getDoorNumber(), c.getAddress().getLocality(), c.getAddress().getCountry(), creditCardBody.toString());
            EmailSender.sendEmail(c.getEmail(), "Account Creation", body
            );
            WriteFile.write("ClientRegistration_" + c.getEmail(), body);
            return true;
        } else return false;
    }

    /**
     * Returns the Client containing the email given by parameter
     *
     * @param strEmail Client email
     * @return Client containing the email given by parameter
     */
    public Client getClientByEmail(String strEmail) {
        return moClientDB.getClientByEmail(strEmail);
    }

    /**
     * Returns the List of Credit Cards of the Client containing
     * the email given by parameter
     *
     * @param strEmail Client's email
     * @return List of Credit Cards regarding the Client
     */
    public List<CreditCard> getCreditCardsByClient(String strEmail) {
        return this.moClientDB.getCreditCardsByClient(strEmail);
    }

    /**
     * Updates a Client's Credits, basing on the Client's email and the
     * amount of Credits to be added
     *
     * @param strEmail   Client's email
     * @param intCredits Client's new credits
     * @return True if the Client's Credits were updated, false if otherwise
     */
    public boolean updateClientCredits(String strEmail, Integer intCredits) {
        return this.moClientDB.updateClientCredits(strEmail, intCredits);
    }
}
