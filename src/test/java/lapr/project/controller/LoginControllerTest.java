package lapr.project.controller;

import lapr.project.data.UserDB;
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
    private UserDB m_mockUserDB;

    LoginControllerTest() {

    }

    @BeforeEach
    void setUp() {
        this.m_ctrl = new LoginController("","","");
        this.m_mockUserDB = Mockito.mock(UserDB.class);
        initMocks(this);
    }

    @Test
    void login() {
        String email = "test@gmail.com";
        String pw = "testpassword";
        when(m_mockUserDB.login(email,pw)).thenReturn(true);
        boolean result = m_ctrl.login(email,pw);
        assertTrue(result);

        when(m_mockUserDB.login(email,pw)).thenReturn(false);
        result = m_ctrl.login(email,pw);
        assertFalse(result);
    }


}