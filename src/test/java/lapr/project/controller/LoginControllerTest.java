package lapr.project.controller;

import lapr.project.data.registration.UserRegistration;
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
    private UserRegistration m_mockUserRegistration;

    LoginControllerTest() {

    }

    @BeforeEach
    void setUp() {
        this.m_ctrl = new LoginController();
        this.m_mockUserRegistration = Mockito.mock(UserRegistration.class);
        initMocks(this);
    }

    @Test
    void login() {
        String email = "test@gmail.com";
        String pw = "testpassword";
        when(m_mockUserRegistration.login(email,pw)).thenReturn(true);
        boolean result = m_ctrl.login(email,pw);
        assertTrue(result);
    }


}