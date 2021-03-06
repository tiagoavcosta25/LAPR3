package lapr.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectoryVerificationTest {

    @Test
    void verifyFileCreation() {
        String expResult = "Readme";
        String result = DirectoryVerification.verifyFileCreation(".", ".md", 10);
        assertEquals(expResult, result);

        expResult = "";
        result = DirectoryVerification.verifyFileCreation("doesntexist/" ,"1", 200);
        assertEquals(expResult, result);

        result = DirectoryVerification.verifyFileCreation("doesntexist/" ,"1", 5);
        assertEquals(expResult, result);

        result = DirectoryVerification.verifyFileCreation("doesntexist/" ,"1", 0);
        assertEquals(expResult, result);

        result = DirectoryVerification.verifyFileCreation("doesntexist/" ,"1", -10);
        assertEquals(expResult, result);

        result = DirectoryVerification.verifyFileCreation(".", ".nonexistant", 10);
        assertEquals(expResult, result);
    }
}