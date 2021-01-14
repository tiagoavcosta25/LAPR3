package lapr.project.controller;

import lapr.project.model.UserSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LogoutControllerTest {

    private LogoutController m_ctrl;

    @BeforeEach
    void setUp() {
        this.m_ctrl = new LogoutController();
    }


    @Test
    void logout() {
        UserSession expected = null;
        m_ctrl.logout();
        UserSession real = ApplicationPOT.getInstance().getCurrentSession();
        assertEquals(expected,real);
    }

}