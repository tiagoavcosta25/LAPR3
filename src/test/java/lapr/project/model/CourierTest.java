package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class CourierTest {
    private Courier c1;
    private Courier c2;
    private Courier c3;

    public CourierTest() throws NoSuchAlgorithmException {
        c1 = new Courier(1,"name","e@gmail.com","123",
                123456789,"PT501234567890987654321234",new Pharmacy());
        c2 = new Courier("name","e@gmail.com","123",
        123456789,"PT501234567890987654321234",new Pharmacy());
        c3 = new Courier();
    }

    @Test
    void getM_iban() {
        String expected = "PT501234567890987654321234";
        String result = c1.getIban();
        assertEquals(expected,result);
        String expected2 = "PT501234567890987654321234";
        String result2 = c2.getIban();
        assertEquals(expected2,result2);
        String expected3 = "No Iban";
        String result3 = c3.getIban();
        assertEquals(expected3,result3);
    }

    @Test
    void getM_Pharmacy() {
        Pharmacy expected = new Pharmacy();
        Pharmacy result = c1.getPharmacy();
        assertEquals(expected,result);
        Pharmacy expected2 = new Pharmacy();
        Pharmacy result2 = c2.getPharmacy();
        assertEquals(expected2,result2);
        Pharmacy expected3 = new Pharmacy();
        Pharmacy result3 = c3.getPharmacy();
        assertEquals(expected3,result3);
    }

    @Test
    void setM_iban() {
        c1.setIban("123");
        c2.setIban("456");
        String expected = "123";
        String result = c1.getIban();
        assertEquals(expected,result);
        String expected2 = "456";
        String result2 = c2.getIban();
        assertEquals(expected2,result2);
    }

    @Test
    void setM_Pharmacy() {
        c1.setPharmacy(new Pharmacy());
        c2.setPharmacy(new Pharmacy());
        c3.setPharmacy(new Pharmacy());
        Pharmacy expected = new Pharmacy();
        Pharmacy result = c1.getPharmacy();
        assertEquals(expected,result);
        Pharmacy expected2 = new Pharmacy();
        Pharmacy result2 = c2.getPharmacy();
        assertEquals(expected2,result2);
        Pharmacy expected3 = new Pharmacy();
        Pharmacy result3 = c3.getPharmacy();
        assertEquals(expected3,result3);
    }

    @Test
    void testEquals() {
        Courier oCourier = new Courier();
        Courier oCourier2 = new Courier();
        oCourier.setIban("a");
        oCourier2.setIban("a");

        assertEquals(oCourier,oCourier2);

        oCourier2.setIban("b");
        assertNotEquals(oCourier,oCourier2);
    }
}