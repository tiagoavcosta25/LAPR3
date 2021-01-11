package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private Client u;

    public UserTest() throws ParseException, NoSuchAlgorithmException {
        u = new Client(5,"name",123456789,"email@","pw1234",0,102030.23, 103121.01,
                "Rua 1", "2esq", "4444-111",
                "Mafamude", "Portugal",1234123412341234L,new SimpleDateFormat("MM/yy").
                parse("10/20"),123);

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
        Client oUser = new Client(5,"name",123456789,"email@","pw1234",0,102030.23, 103121.01,
                "Rua 1", "2esq", "4444-111",
                "Mafamude", "Portugal",1234123412341234L,new SimpleDateFormat("MM/yy").
                parse("10/20"),123);
        boolean real = u.equals(oUser);
        assertTrue(real);
    }

}