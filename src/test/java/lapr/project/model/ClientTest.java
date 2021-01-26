package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private Client c1;
    private Client c2;
    private Client c3;
    private Client c4;

    ClientTest() throws ParseException, NoSuchAlgorithmException {
        List<CreditCard> lst = new ArrayList<>();
        lst.add(new CreditCard(192193L,new SimpleDateFormat("MM/yy").
                parse("10/20"),123));
        c1 = new Client("name", 123456789, "email@", "pw1234", 102030.23, 103121.01,10d,
                "Rua 1", "2esq", "4444-111",
                "Mafamude", "Portugal", lst);
        c2 = new Client(2, "name", 123456789, "email@", "pw1234", 2, 102030.23, 103121.01,10d,
                "Rua 1", "2esq", "4444-111",
                "Mafamude", "Portugal",lst);
        c3 = new Client(2, "name", 123456789, "email@", "pw1234", 2, new Address(102030.23, 103121.01,10d,
                "Rua 1", "2esq", "4444-111",
                "Mafamude", "Portugal"), lst);
        c4 = new Client();
    }

    @Test
    void getAddress() {
        Address expected = new Address(c3.getAddress().getLatitude(), c3.getAddress().getLongitude(),c3.getAddress().getAltitude(),
                c3.getAddress().getStreetName(), c3.getAddress().getDoorNumber(), c3.getAddress().getPostalCode(),
                c3.getAddress().getLocality(), c3.getAddress().getCountry());
        Address real = c3.getAddress();
        assertEquals(expected, real);
    }

    @Test
    void getCredits() {
        Integer expected = 0;
        Integer real = c4.getCredits();
        assertEquals(expected, real);
    }

    @Test
    void getLstCreditCard() {
        List<CreditCard> expected = new ArrayList<>();
        List<CreditCard> real = c4.getLstCreditCard();
        assertEquals(expected, real);
    }

    @Test
    void setCredits() {
        Integer expected = 3;
        c1.setCredits(3);
        Integer real = c1.getCredits();
        assertEquals(expected, real);
    }

    @Test
    void setAddress() {
        Address expected = new Address(423232.77, 523236.53,20d, "Rua 2", "5dir", "4222-131",
                "Ermesinde", "Portugal");
        c1.setAddress(new Address(423232.77, 523236.53,20d, "Rua 2", "5dir", "4222-131",
                "Ermesinde", "Portugal"));
        Address real = c1.getAddress();
        assertEquals(expected, real);
    }

    @Test
    void setLstCreditCard() {
        List<CreditCard> expected = new ArrayList<>();
        c1.setLstCreditCard(new ArrayList<>());
        List<CreditCard> real = c1.getLstCreditCard();
        assertEquals(expected, real);
    }

    @Test
    void addCredits() {
        Client c = new Client();
        Integer expected = 1;
        c.addCredits(1);
        Integer real = c.getCredits();
        assertEquals(expected, real);

        Integer expected1 = 5;
        c.addCredits(4);
        Integer real1 = c.getCredits();
        assertEquals(expected1, real1);
    }

    @Test
    void testToString() {
        Client oClient= new Client();
        String expected = "User{m_intId=null, m_strEmail='No Email Registered', m_strPassword='";
        String real = oClient.toString().substring(0,68);
        assertEquals(expected, real);
    }

    @Test
    void testEquals() {
        Client oClient = new Client();
        Client oClient2 = new Client();
        oClient.setAddress(new Address());
        oClient2.setAddress(new Address());
        assertEquals(oClient,oClient2);

        oClient2.setAddress(null);
        assertNotEquals(oClient,oClient2);
    }
}