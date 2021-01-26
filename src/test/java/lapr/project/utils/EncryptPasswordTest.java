package lapr.project.utils;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class EncryptPasswordTest {

    @Test
    void setAlgorithmType() throws NoSuchAlgorithmException {
        EncryptPassword.setAlgorithmType("md5");
        String password = "a";
        String real = EncryptPassword.encryptPasswordMD5(password);
        assertEquals("cc175b9c0f1b6a831c399e269772661",real);
    }
}