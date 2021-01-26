package lapr.project.utils;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class EmailSenderTest {

    @Test
    void emailSender() {
        boolean result = EmailSender.sendEmail("", "Test", "Test");
        assertTrue(result);

        result = EmailSender.sendEmail("teste@gmail.com", "Test", "");
        assertTrue(result);

        result = EmailSender.sendEmail("csxmutilation@gmail.com", "", "Test");
        assertFalse(result);

        result = EmailSender.sendEmail("csxmutilation@gmail.com", "Test", "");
        assertTrue(result);
    }
}