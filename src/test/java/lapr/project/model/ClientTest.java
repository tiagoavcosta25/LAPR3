package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    Client c1;

    ClientTest() throws ParseException, NoSuchAlgorithmException {
        c1 = new Client("name",123456789,"email@","pw1234",102030.23, 103121.01,
                "Rua 1", "2esq", "4444-111",
                "Mafamude", "Portugal",1234123412341234L,new SimpleDateFormat("MM/yy").
                parse("10/20"),123);
    }

    @Test
    void getM_address() {
        Address expected = new Address(c1.getM_address().getM_latitude(),c1.getM_address().getM_longitude(),
                c1.getM_address().getM_streetName(),c1.getM_address().getM_doorNumber(),c1.getM_address().getM_postalCode(),
                c1.getM_address().getM_locality(),c1.getM_address().getM_country());
        Address real = c1.getM_address();
        assertEquals(expected,real);
    }

    @Test
    void getM_credits() {
        Integer expected = 0;
        Integer real = c1.getM_credits();
        assertEquals(expected,real);
    }

    @Test
    void getM_creditCard() {
        CreditCard expected = new CreditCard(c1.getM_creditCard().getM_creditCardNr(),c1.getM_creditCard().getM_validityDate(),
                c1.getM_creditCard().getM_CCV());
        CreditCard real = c1.getM_creditCard();
        assertEquals(expected,real);
    }

    @Test
    void setCredits() {
        Integer expected = 3;
        c1.setCredits(3);
        Integer real = c1.getM_credits();
        assertEquals(expected,real);
    }

    @Test
    void setAddress() {
        Address expected = new Address(10, 423232.77, 523236.53, "Rua 2", "5dir", "4222-131",
                "Ermesinde", "Portugal");
        c1.setAddress(new Address(10, 423232.77, 523236.53, "Rua 2", "5dir", "4222-131",
                "Ermesinde", "Portugal"));
        Address real = c1.getM_address();
        assertEquals(expected,real);
    }

    @Test
    void setCreditCard() {
        CreditCard expected = new CreditCard(c1.getM_creditCard().getM_creditCardNr(),c1.getM_creditCard().getM_validityDate(),
                c1.getM_creditCard().getM_CCV());
        c1.setCreditCard(new CreditCard(c1.getM_creditCard().getM_creditCardNr(),c1.getM_creditCard().getM_validityDate(),
                c1.getM_creditCard().getM_CCV()));
        CreditCard real = c1.getM_creditCard();
        assertEquals(expected,real);
    }

    @Test
    void addCredits() {
        Client c = new Client();
        Integer expected = 1;
        c.addCredits(1);
        Integer real = c.getM_credits();
        assertEquals(expected,real);

        Integer expected1 = 5;
        c.addCredits(4);
        Integer real1 = c.getM_credits();
        assertEquals(expected1,real1);
    }
}