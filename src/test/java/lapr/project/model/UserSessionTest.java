package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserSessionTest {

    private UserSession userSession;

    UserSessionTest() {
        userSession = new UserSession("email",1);
    }

    @Test
    void getCurrentUserEmail() {
        String expected = "email";
        String real = userSession.getCurrentUserEmail();
        assertEquals(expected,real);
    }

    @Test
    void getRole() {
        UserSession.Role expected = UserSession.Role.CLIENT;
        UserSession.Role real = userSession.getRole();
        assertEquals(expected,real);
    }

    @Test
    void setRole() {
        UserSession.Role expected = UserSession.Role.ADMIN;
        userSession.setRole(UserSession.Role.ADMIN);
        UserSession.Role real = userSession.getRole();
        assertEquals(expected,real);
    }
}