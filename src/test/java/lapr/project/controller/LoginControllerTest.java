package lapr.project.controller;

import lapr.project.model.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class LoginControllerTest {

    @InjectMocks
    private LoginController m_ctrl;


    @Mock
    private UserService m_mockUserService;

    @BeforeEach
    void setUp() {
        this.m_ctrl = new LoginController();
        this.m_mockUserService = Mockito.mock(UserService.class);
        initMocks(this);
    }

    @Test
    void login() throws NoSuchAlgorithmException {
        String email = "test@gmail.com";
        String pw = "testpassword";
        when(m_mockUserService.login(email,pw)).thenReturn(true);
        boolean result = m_ctrl.login(email,pw);
        assertTrue(result);

        when(m_mockUserService.login(email,pw)).thenReturn(false);
        result = m_ctrl.login(email,pw);
        assertFalse(result);
    }


    @Test
    void getUserService() {
        UserService expected = m_mockUserService;
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