package lapr.project.controller;

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
        String expected = null;
        m_ctrl.logout();
        String real = ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail();
        assertEquals(expected,real);
    }

}