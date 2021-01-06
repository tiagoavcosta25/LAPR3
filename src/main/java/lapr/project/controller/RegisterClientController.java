package lapr.project.controller;

import lapr.project.model.Platform;
import lapr.project.model.registration.ClientRegistration;

public class RegisterClientController {


    /**
     * Registers a new Client in the Database
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
     * @param creditCardNr  Client's credit card number
     * @param validityDate  Client's credit card's validity date
     * @param CCV           Client's credit card's CCV
     * @return              True if Client was registered, false if otherwise
     */
    public boolean registerNewClient(String name, String email, String password, float latitude, float longitude, String streetName,
                                     Integer doorNumber, String postalCode, String locality, String country, Integer creditCardNr,
                                     String validityDate, Integer CCV) {

        if (validateInput(name,email,password,latitude,longitude,streetName,doorNumber,postalCode,locality,country,
                creditCardNr,validityDate,CCV)) {
            ApplicationPOT app = ApplicationPOT.getInstance();
            Platform plat = app.getPlatform();
            ClientRegistration clientReg = plat.getClientReg();
            return clientReg.registerNewClient(name,email,password,latitude,longitude,streetName,doorNumber,postalCode,locality,country,
                    creditCardNr,validityDate,CCV);
        }
        return false;

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
     * @param creditCardNr  Client's credit card number
     * @param validityDate  Client's credit card's validity date
     * @param CCV           Client's credit card's CCV
     * @return              True if input is valid, false if otherwise
     */
    public boolean validateInput(String name, String email, String password, float latitude, float longitude, String streetName,
                                 Integer doorNumber, String postalCode, String locality, String country, Integer creditCardNr,
                                 String validityDate, Integer CCV) {

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || streetName.isEmpty() || postalCode.isEmpty()
                || locality.isEmpty() || country.isEmpty() || doorNumber <= 0 || latitude <= 0 || longitude <= 0
                || creditCardNr <= 0 || validityDate.isEmpty() || CCV <= 0) return false;

        if (!email.contains("@")) return false;

        if ((int) (Math.log10(creditCardNr) + 1) != 16 || (int) (Math.log10(CCV) + 1) != 3 &&
                validityDate.length() != 5) return false;

        return true;
    }
}
