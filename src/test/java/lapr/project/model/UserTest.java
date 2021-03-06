package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private Client u;

    public UserTest() throws ParseException, NoSuchAlgorithmException {
        u = new Client(5,"name",123456789,"email@","pw1234",0,102030.23, 103121.01,10d,
                "Rua 1", "2esq", "4444-111",
                "Mafamude", "Portugal",new ArrayList<>());

    }

    @Test
    void getStrEmail() {
        String expected = "email@";
        String real = u.getEmail();
        assertEquals(expected,real);
    }

    @Test
    void setStrEmail() {
        String expected = "newemail@.com";
        u.setEmail("newemail@.com");
        String real = u.getEmail();
        assertEquals(expected,real);
    }

    @Test
    void getPw() {
        String expected = "pw1234";
        String real = u.getPw();
        assertEquals(expected,real);
    }

    @Test
    void setPw() {
        String expected = "newpw123";
        u.setPw("newpw123");
        String real = u.getPw();
        assertEquals(expected,real);
    }

    @Test
    void getM_id() {
        Integer expected =  5;
        Integer real = u.getId();
        assertEquals(expected,real);
    }

    @Test
    void setM_id() {
        Integer expected = 10;
        u.setId(10);
        Integer real = u.getId();
        assertEquals(expected,real);
    }

    @Test
    void getM_nif() {
        Integer expected = 123456789;
        Integer real = u.getNif();
        assertEquals(expected,real);
    }

    @Test
    void setM_nif() {
        Integer expected = 234567890;
        u.setNif(234567890);
        Integer real = u.getNif();
        assertEquals(expected,real);
    }

    @Test
    void getM_name() {
        String expected = "name";
        String real = u.getName();
        assertEquals(expected,real);
    }

    @Test
    void setM_name() {
        String expected = "newname";
        u.setName("newname");
        String real = u.getName();
        assertEquals(expected,real);
    }

    @Test
    void testEquals() throws ParseException {
        Client oUser = new Client(5,"name",123456789,"email@","pw1234",0,102030.23, 103121.01,10d,
                "Rua 1", "2esq", "4444-111",
                "Mafamude", "Portugal",new ArrayList<>());
        boolean real = u.equals(oUser);
        assertTrue(real);

        assertEquals(u,u);

        Client oUser1 = new Client(2,"name",123456789,"em","pw1234",0,102030.23, 103121.01,10d,
                "Rua 1", "2esq", "4444-111",
                "Mafamude", "Portugal",new ArrayList<>());

        assertNotEquals(oUser1, u);

        String s = "";
        assertNotEquals(u,s);

        Client oUser2 = null;
        assertNotEquals(u,oUser2);

        real = u.equals(oUser1);
        assertFalse(real);

    }

    @Test
    void toStringTest() {
        Client c = new Client();
        String expResult = "User{m_intId=null, m_strEmail='No Email Registered', m_strPassword='";
        String result = c.toString().substring(0,68);
        assertEquals(expResult, result);
    }
}