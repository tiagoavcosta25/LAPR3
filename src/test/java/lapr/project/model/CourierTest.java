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
    void getM_id() {
        int expected = 1;
        int result = c1.getM_id();
        assertEquals(expected,result);
        int expected3 = -1;
        int result3 = c3.getM_id();
        assertEquals(expected3,result3);
    }

    @Test
    void getM_iban() {
        String expected = "PT501234567890987654321234";
        String result = c1.getM_iban();
        assertEquals(expected,result);
        String expected2 = "PT501234567890987654321234";
        String result2 = c2.getM_iban();
        assertEquals(expected2,result2);
        String expected3 = "No Iban";
        String result3 = c3.getM_iban();
        assertEquals(expected3,result3);
    }

    @Test
    void getM_Pharmacy() {
        Pharmacy expected = new Pharmacy();
        Pharmacy result = c1.getM_Pharmacy();
        assertEquals(expected,result);
        Pharmacy expected2 = new Pharmacy();
        Pharmacy result2 = c2.getM_Pharmacy();
        assertEquals(expected2,result2);
        Pharmacy expected3 = new Pharmacy();
        Pharmacy result3 = c3.getM_Pharmacy();
        assertEquals(expected3,result3);
    }

    @Test
    void setM_id() {
        c1.setM_id(2);
        int expected = 2;
        int result = c1.getM_id();
        assertEquals(expected,result);
    }

    @Test
    void setM_iban() {
        c1.setM_iban("123");
        c2.setM_iban("456");
        String expected = "123";
        String result = c1.getM_iban();
        assertEquals(expected,result);
        String expected2 = "456";
        String result2 = c2.getM_iban();
        assertEquals(expected2,result2);
    }

    @Test
    void setM_Pharmacy() {
        c1.setM_Pharmacy(new Pharmacy());
        c2.setM_Pharmacy(new Pharmacy());
        c3.setM_Pharmacy(new Pharmacy());
        Pharmacy expected = new Pharmacy();
        Pharmacy result = c1.getM_Pharmacy();
        assertEquals(expected,result);
        Pharmacy expected2 = new Pharmacy();
        Pharmacy result2 = c2.getM_Pharmacy();
        assertEquals(expected2,result2);
        Pharmacy expected3 = new Pharmacy();
        Pharmacy result3 = c3.getM_Pharmacy();
        assertEquals(expected3,result3);
    }
}