package lapr.project.model.service;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.UserDB;
import lapr.project.model.UserSession;
import lapr.project.utils.EncryptPassword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;

import java.security.NoSuchAlgorithmException;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class UserServiceTest {

    @InjectMocks
    private UserService m_service;

    @Mock
    private UserDB m_oUserDB;

    @BeforeEach
    void setUp() {
        this.m_service = new UserService();
        this.m_oUserDB = Mockito.mock(UserDB.class);
        initMocks(this);
    }

    @Test
    void login() throws NoSuchAlgorithmException {
        when (m_oUserDB.login("as@", EncryptPassword.encryptPasswordMD5("asdaas"))).thenReturn(true);
        boolean real = m_service.login("as@","asdaas");
        assertTrue(real);

        when (m_oUserDB.login("as@",EncryptPassword.encryptPasswordMD5("asdaas"))).thenReturn(false);
        real = m_service.login("as@","asdaas");
        assertFalse(real);

        when (m_oUserDB.login("a","a")).thenThrow(new IllegalArgumentException());
        real = m_service.login("a","a");
        assertFalse(real);
    }

    @Test
    void newUserSession() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession());
        assertTrue(m_service.newUserSession());
    }
}