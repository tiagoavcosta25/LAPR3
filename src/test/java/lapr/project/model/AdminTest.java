package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {
    Admin a1;
    Admin a2;
    Admin a3;

    public AdminTest() throws NoSuchAlgorithmException {
        a1 = new Admin();
        a2 = new Admin("test","email@gmail.com","pass",123456789);
        a3 = new Admin(1,"test","email@gmail.com","pass",123456789);
    }

    @Test
    void getM_id() {
        Integer expected = 1;
        Integer real = a3.getId();
        assertEquals(expected, real);
    }

    @Test
    void setM_id() {
        a3.setId(2);
        Integer expected = 2;
        Integer real = a3.getId();
        assertEquals(expected,real);
    }

    @Test
    void testEquals() {
        Admin oAdmin = new Admin();
        Admin oAdmin2 = new Admin();

        assertEquals(oAdmin,oAdmin);

        assertEquals(oAdmin,oAdmin2);

        assertNotEquals(oAdmin,null);
        String s = "";
        assertNotEquals(oAdmin,s);

        oAdmin = new Admin();
        oAdmin2 = new Admin();
        oAdmin.setEmail("asa");
        oAdmin2.setEmail("gaca");
        assertNotEquals(oAdmin,oAdmin2);
    }
}