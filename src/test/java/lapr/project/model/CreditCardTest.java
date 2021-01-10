package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {
    CreditCard c;

    public CreditCardTest() throws ParseException {
        c = new CreditCard(1234123412341234L,new SimpleDateFormat("MM/yy").parse("10/20"),
                123);
    }

    @Test
    void getM_creditCardNr() {
        Long expected = 1234123412341234L;
        Long real = c.getCreditCardNr();
        assertEquals(expected,real);
    }

    @Test
    void setM_creditCardNr() {
        Long expected = 2345234523452345L;
        c.setCreditCardNr(2345234523452345L);
        Long real = c.getCreditCardNr();
        assertEquals(expected,real);
    }

    @Test
    void getM_validityDate() throws ParseException {
        Date expected = new SimpleDateFormat("MM/yy").parse("10/20");
        Date real = c.getValidityDate();
        assertEquals(expected,real);
    }

    @Test
    void setM_validityDate() throws ParseException {
        Date expected = new SimpleDateFormat("MM/yy").parse("11/28");
        c.setValidityDate(new SimpleDateFormat("MM/yy").parse("11/28"));
        Date real = c.getValidityDate();
        assertEquals(expected,real);
    }

    @Test
    void getM_CCV() {
        Integer expected = 123;
        Integer real = c.getCCV();
        assertEquals(expected,real);
    }

    @Test
    void setM_CCV() {
        Integer expected = 456;
        c.setCCV(456);
        Integer real = c.getCCV();
        assertEquals(expected,real);
    }
}