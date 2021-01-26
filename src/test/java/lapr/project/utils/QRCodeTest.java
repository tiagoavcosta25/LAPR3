package lapr.project.utils;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class QRCodeTest {


    @Test
    void createQR() throws IOException, WriterException {
        boolean real = QRCode.generateQRCode("this is a test", "test");

        assertTrue(real);

        real = QRCode.generateQRCode(null, null);
        assertFalse(real);
    }

    @Test
    void readQR() throws IOException, WriterException, NotFoundException {
        String expected = "this is a test";
        QRCode.generateQRCode(expected, "test");
        String real = QRCode.readQRCode("test");

        assertEquals(expected, real);

        real = QRCode.readQRCode(null);
        assertEquals(null,real);
    }

}