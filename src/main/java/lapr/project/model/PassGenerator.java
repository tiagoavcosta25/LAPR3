package lapr.project.model;

import lapr.project.GeneratePassword;

import java.io.Serializable;

/**
 * Password Generator Method.
 *
 * Group: Team Lisa [G-037]
 * ______________________________________________________
 *
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 */
public class PassGenerator implements GeneratePassword, Serializable {

    /**
     * Password Length.
     */
    private static final int PASSWORD_LENGTH = 7;

    /**
     * An empty constructor of PassGenerator that initiates/creates the Password.
     */
    public PassGenerator(){}

    /**
     * Generates a random password for a User
     *
     * @return the generated Password
     */
    @Override
    public String generatePassword() {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {

            int c = (int)(str.length() * Math.random());

            sb.append(str.charAt(c));
        }
        return sb.toString();
    }
}
