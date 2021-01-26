package lapr.project.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPassword{

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
        String hashText = bigInt.toString(16);
        return hashText;
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