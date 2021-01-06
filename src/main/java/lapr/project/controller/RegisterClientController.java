package lapr.project.controller;

public class RegisterClientController {

    private ClientRegistration clientReg = new ClientRegistration();


    public void registerNewUser(String name, String email, String password, float latitude, float longitude, String streetName,
                                Integer doorNumber, String postalCode, String locality, String country, Integer creditCardNr,
                                String validityDate, Integer CCV) {

        if (validateInput(name,email,password,latitude,longitude,streetName,doorNumber,postalCode,locality,country,
                creditCardNr,validityDate,CCV)) {
            ApplicationPOT app = ApplicationPOT.getInstance();
            Platform plat = app.getPlatform();
            ClientRegistration clientReg = plat.getClientReg();
            clientReg.registerNewUser(name,email,password,latitude,longitude,streetName,doorNumber,postalCode,locality,country,
                    creditCardNr,validityDate,CCV);
        } else {

        }

    }

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
