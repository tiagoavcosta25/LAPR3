package lapr.project.controller;

import lapr.project.model.Client;
import lapr.project.data.registration.ClientRegistration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RegisterClientControllerTest {

    @InjectMocks
    private RegisterClientController m_ctrl;


    @Mock
    private ClientRegistration m_mockClientRegistration;

    RegisterClientControllerTest() {
    }

    @BeforeEach
    void setUp() {
        this.m_ctrl = new RegisterClientController();
        this.m_mockClientRegistration = Mockito.mock(ClientRegistration.class);
        initMocks(this);
    }

    @Test
    void registerNewClient() throws Exception {
        Client c = new Client("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/20"),123);

        when(m_mockClientRegistration.registerNewClient(c)).thenReturn(true);
        boolean result = m_ctrl.registerNewClient("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,"10/20",123);
        assertTrue(result);
    }

    @Test
    void validateInput() throws ParseException {
        /**
         * Validate correct format input
         */
        boolean real = m_ctrl.validateInput("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/23"),
                123);

        assertTrue(real);

        /**
         * Validate incorrect nif (few figures)
         */
        boolean real2 = m_ctrl.validateInput("TestName",12345678,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/23"),
                123);

        assertFalse(real2);

        /**
         * Validate incorrect email
         */
        boolean real3 = m_ctrl.validateInput("TestName",123456788,"teste","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/23"),
                123);

        assertFalse(real3);

        /**
         * Validate incorrect password (length smaller than 6)
         */
        boolean real4 = m_ctrl.validateInput("TestName",12345678,"test@gmail.com","test",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/23"),
                123);

        assertFalse(real4);

    }
}