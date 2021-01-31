package lapr.project.utils;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class EmailSenderTest {

    @Test
    void emailSender() {
        boolean result = EmailSender.sendEmail("", "Test", "Test");
        assertFalse(result);

        result = EmailSender.sendEmail("g21@trash-mail.com", "Test", "");
        assertFalse(result);

        result = EmailSender.sendEmail("g21@trash-mail.com", "", "Test");
        assertFalse(result);

        result = EmailSender.sendEmail("g21@trash-mail.com", "Test", "asdas");
        assertTrue(result);
    }

    @Test
    void htmlBodyTest() {
        String expResult = "";
        String result = EmailSender.htmlBody("asdadsa\n");
        assertNotEquals(expResult, result);
        expResult = "asdadsa<br />";
        assertEquals(expResult, result);

        expResult = "";
        result = EmailSender.htmlBody("asdadsa");
        assertNotEquals(expResult, result);
        expResult = "asdadsa";
        assertEquals(expResult, result);

        expResult = "asda";
        result = EmailSender.htmlBody("");
        assertNotEquals(expResult, result);
        expResult = "";
        assertEquals(expResult, result);
    }
}