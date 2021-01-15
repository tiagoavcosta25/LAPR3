package lapr.project.utils;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class EmailSenderTest {

    @Test
    void emailSender() {
        boolean result = EmailSender.emailSender("csxmutilation@gmail.com", "Test", "Test");
        assertTrue(result);

        result = EmailSender.emailSender("teste@gmail.com", "Test", "");
        assertFalse(result);

        result = EmailSender.emailSender("csxmutilation@gmail.com", "", "Test");
        assertFalse(result);

        result = EmailSender.emailSender("csxmutilation@gmail.com", "Test", "");
        assertFalse(result);
    }
}