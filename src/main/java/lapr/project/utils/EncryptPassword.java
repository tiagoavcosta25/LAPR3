package lapr.project.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Encrypt Password.
 *
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */

public class EncryptPassword{

    /**
     * An empty constructor of EncryptPassword.
     */
    private EncryptPassword(){
    }

    /**
     * Represents the type of the algorithm
     */
    private static String mstrAlgorithmType = "MD5";

    /**
     * Code generated based on this web search
     * MD5 :
     * https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
     * https://stackoverflow.com/questions/6592010/encrypt-and-decrypt-a-password-in-java
     *
     * @param pass the password to be encrypted
     * @return the encrypted password
     * @throws java.security.NoSuchAlgorithmException
     */
    public static String encryptPasswordMD5(String pass) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance(mstrAlgorithmType.trim());
        m.update(pass.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        return bigInt.toString(16);
    }

    /**
     * Allows to change the algorith type used in the encrypt method
     *
     * @param algorithmType The new algorith Type
     */
    public static void setAlgorithmType(String algorithmType) {
        EncryptPassword.mstrAlgorithmType = algorithmType;
    }


}