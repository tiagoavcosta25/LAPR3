package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {

    private CreditCard c;

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

    @Test
    void testEquals() throws ParseException {
        CreditCard oCreditCard = new CreditCard(1234123412341234L,new SimpleDateFormat("MM/yy").parse("10/20"),
                123);
        boolean real = c.equals(oCreditCard);
        assertTrue(real);

        assertEquals(c, c);

        CreditCard oCreditCard1 = new CreditCard(1234123412341239L,new SimpleDateFormat("MM/yy").parse("10/20"),
                123);
        real = c.equals(oCreditCard1);
        assertFalse(real);

        String s = "";
        real = c.equals(s);
        assertFalse(real);

        CreditCard oCreditCard2 = null;
        real = c.equals(oCreditCard2);
        assertFalse(real);

        String s2 = null;
        real = c.equals(s2);
        assertFalse(real);
    }

    @Test
    void testHashCode() {
        CreditCard oCreditCard = new CreditCard();
        int expected = 31;
        int real = oCreditCard.hashCode();
        assertEquals(expected, real);
    }

    @Test
    void testToString() {
        CreditCard oCreditCard = new CreditCard();
        String expected = "CreditCard{" +
                "m_creditCardNr=" + oCreditCard.getCreditCardNr() +
                ", m_validityDate=" + oCreditCard.getValidityDate() +
                ", m_CCV=" + oCreditCard.getCCV() +
                '}';
        String real = oCreditCard.toString();
        assertEquals(expected, real);
    }

    @Test
    void hasNumber() {
        CreditCard oCreditCard = new CreditCard();
        oCreditCard.setCreditCardNr(1L);
        assertTrue(oCreditCard.hasNumber(1L));
        assertFalse(oCreditCard.hasNumber(2L));
    }

    @Test
    void compareTo() {
        CreditCard c1 = new CreditCard(9999999999999L,new Date(),123);
        CreditCard c2 = new CreditCard(111111111111L,new Date(),123);

        assertEquals(1,c1.compareTo(c2));

        assertEquals(-1,c2.compareTo(c1));

        assertEquals(0,c1.compareTo(c1));

    }
}