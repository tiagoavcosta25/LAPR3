package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserSessionTest {

    private UserSession userSession1;
    private UserSession userSession2;
    private UserSession userSession3;
    private UserSession userSession4;
    private UserSession userSession5;
    private UserSession userSession6;
    private UserSession userSession7;

    UserSessionTest() {
        userSession1 = new UserSession("email",1);
        userSession2 = new UserSession("em",2);
        userSession3 = new UserSession("ee",3);
        userSession4 = new UserSession("emmaail",4);
        userSession5 = new UserSession();
        userSession6 = new UserSession("e",7);
        userSession7 = new UserSession("a");
    }

    @Test
    void getCurrentUserEmail() {
        String expected = "email";
        String real = userSession1.getCurrentUserEmail();
        assertEquals(expected,real);
    }

    @Test
    void getRole() {
        UserSession.Role expected = UserSession.Role.CLIENT;
        UserSession.Role real = userSession1.getRole();
        assertEquals(expected,real);

        assertNotEquals(userSession1.getRole(), UserSession.Role.ADMIN);

        assertEquals(UserSession.Role.CLIENT,userSession1.getRole());

        assertNotEquals(UserSession.Role.ADMIN,userSession1.getRole());
    }

    @Test
    void setRole() {
        UserSession.Role expected = UserSession.Role.ADMIN;
        userSession1.setRole(UserSession.Role.ADMIN);
        UserSession.Role real = userSession1.getRole();
        assertEquals(expected,real);
    }

    @Test
    void testGetRole() {
        String expected = "Administrator";
        String real = UserSession.Role.ADMIN.getRole();
        assertEquals(expected,real);
    }
}