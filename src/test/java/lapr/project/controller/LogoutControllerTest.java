package lapr.project.controller;

import lapr.project.model.UserSession;
import lapr.project.model.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class LogoutControllerTest {

    private LogoutController m_ctrl;

    private UserService m_mockUserService;

    @BeforeEach
    void setUp() {
        this.m_ctrl = new LogoutController();
        m_mockUserService = new UserService();
    }


    @Test
    void logout() {
        UserSession expected = null;
        m_ctrl.logout();
        UserSession real = ApplicationPOT.getInstance().getCurrentSession();
        assertEquals(expected,real);
    }

    @Test
    void getUserService() {
        UserService expected = new UserService();
        m_ctrl.setUserService(expected);
        UserService real = m_ctrl.getUserService();
        assertEquals(expected,real);
    }

    @Test
    void setUserService() {
        UserService expected = new UserService();
        m_ctrl.setUserService(expected);
        UserService real = m_ctrl.getUserService();
        assertEquals(expected,real);
    }
}