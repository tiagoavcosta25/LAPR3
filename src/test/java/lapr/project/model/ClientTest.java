package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private Client c1;
    private Client c2;
    private Client c3;
    private Client c4;

    ClientTest() throws ParseException, NoSuchAlgorithmException {
        c1 = new Client("name", 123456789, "email@", "pw1234", 102030.23, 103121.01,
                "Rua 1", "2esq", "4444-111",
                "Mafamude", "Portugal", 1234123412341234L, new SimpleDateFormat("MM/yy").
                parse("10/20"), 123);
        c2 = new Client(2, "name", 123456789, "email@", "pw1234", 2, 102030.23, 103121.01,
                "Rua 1", "2esq", "4444-111",
                "Mafamude", "Portugal", 1234123412341234L, new SimpleDateFormat("MM/yy").
                parse("10/20"), 123);
        c3 = new Client(2, "name", 123456789, "email@", "pw1234", 2, new Address(102030.23, 103121.01,
                "Rua 1", "2esq", "4444-111",
                "Mafamude", "Portugal"), new CreditCard(1234123412341234L, new SimpleDateFormat("MM/yy").
                parse("10/20"), 123));
        c4 = new Client();
    }

    @Test
    void getM_address() {
        Address expected = new Address(c3.getAddress().getLatitude(), c3.getAddress().getLongitude(),
                c3.getAddress().getStreetName(), c3.getAddress().getDoorNumber(), c3.getAddress().getPostalCode(),
                c3.getAddress().getLocality(), c3.getAddress().getCountry());
        Address real = c3.getAddress();
        assertEquals(expected, real);
    }

    @Test
    void getM_credits() {
        Integer expected = 0;
        Integer real = c4.getCredits();
        assertEquals(expected, real);
    }

    @Test
    void getM_creditCard() {
        CreditCard expected = new CreditCard(c2.getCreditCard().getCreditCardNr(), c2.getCreditCard().getValidityDate(),
                c2.getCreditCard().getCCV());
        CreditCard real = c2.getCreditCard();
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
        Address expected = new Address(10, 423232.77, 523236.53, "Rua 2", "5dir", "4222-131",
                "Ermesinde", "Portugal");
        c1.setAddress(new Address(10, 423232.77, 523236.53, "Rua 2", "5dir", "4222-131",
                "Ermesinde", "Portugal"));
        Address real = c1.getAddress();
        assertEquals(expected, real);
    }

    @Test
    void setCreditCard() {
        CreditCard expected = new CreditCard(c1.getCreditCard().getCreditCardNr(), c1.getCreditCard().getValidityDate(),
                c1.getCreditCard().getCCV());
        c1.setCreditCard(new CreditCard(c1.getCreditCard().getCreditCardNr(), c1.getCreditCard().getValidityDate(),
                c1.getCreditCard().getCCV()));
        CreditCard real = c1.getCreditCard();
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
        String expected = "Client{" +
                "m_credits=" + oClient.getCredits() +
                ", m_address=" + oClient.getAddress() +
                ", m_creditCard=" + oClient.getCreditCard() +
                '}';
        String real = oClient.toString().substring(125);
        assertEquals(expected, real);
    }
}