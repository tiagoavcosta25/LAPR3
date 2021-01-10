package lapr.project.controller;

import lapr.project.model.registration.UserRegistration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class LoginControllerTest {

    @InjectMocks
    private LoginController m_ctrl;


    @Mock
    private UserRegistration m_userRegistration;

    LoginControllerTest() {

    }

    @BeforeEach
    void setUp() {
        this.m_ctrl = new LoginController();
        this.m_userRegistration = Mockito.mock(UserRegistration.class);
        initMocks(this);
    }

    @Test
    void login() {
        String email = "test@gmail.com";
        String pw = "testpassword";
        when(m_userRegistration.login(email,pw)).thenReturn(true);
        boolean result = m_ctrl.login(email,pw);
        assertTrue(result);
    }


}