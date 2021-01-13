package lapr.project.model.service;

import lapr.project.data.ClientDB;
import lapr.project.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ClientServiceTest {

    @InjectMocks
    private ClientService m_service;

    @Mock
    private ClientDB m_ClientDB;

    @BeforeEach
    void setUp() {
        this.m_service = new ClientService();
        this.m_ClientDB = Mockito.mock(ClientDB.class);
        initMocks(this);
    }

    @Test
    void validateInput() throws ParseException, NoSuchAlgorithmException {
        /**
         * Validate correct format input
         */
        boolean real = m_service.validateInput("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/23"),
                123);

        assertTrue(real);

        /**
         * Validate incorrect nif (few figures)
         */
        boolean real2 = m_service.validateInput("TestName",12345678,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/23"),
                123);

        assertFalse(real2);

        /**
         * Validate incorrect email
         */
        boolean real3 = m_service.validateInput("TestName",123456788,"teste","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/23"),
                123);

        assertFalse(real3);

        /**
         * Validate incorrect password (length smaller than 6)
         */
        boolean real4 = m_service.validateInput("TestName",12345678,"test@gmail.com","test",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/23"),
                123);

        assertFalse(real4);

        Client c = new Client("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/20"),123);


        boolean result = m_service.validateInput("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertTrue(result);

        boolean result2 = m_service.validateInput("TestName",12345,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertFalse(result2);

        boolean result3 = m_service.validateInput("TestName",123456789,"testgmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertFalse(result3);

        boolean result4 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),523535);
        assertFalse(result4);

        boolean result5 = m_service.validateInput("",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertFalse(result5);

        boolean result6 = m_service.validateInput("TestName",123456789,"","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertFalse(result6);

        boolean result7 = m_service.validateInput("TestName",123456789,"test@gmail.com","",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertFalse(result7);

        boolean result8 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,"","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertFalse(result8);

        boolean result9 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertFalse(result9);

        boolean result10 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertFalse(result10);

        boolean result11 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertFalse(result11);

        boolean result12 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",0,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertFalse(result12);

        boolean result13 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertTrue(result13);

        boolean result14 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),0);
        assertFalse(result14);

        boolean result15 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("14/23"),123);
        assertTrue(result15);

        boolean result17 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertFalse(result17);

        boolean result19 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                0d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertFalse(result19);

        boolean result20 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                1032323d,0d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertFalse(result20);

        boolean result21 = m_service.validateInput("TestName",123456789,"test@gmail.com","tttttt",
                1032323d,123456d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertTrue(result21);

        boolean result22 = m_service.validateInput("TestName",0,"test@gmail.com","t",
                1032323d,123456d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertFalse(result22);

        boolean result23 = m_service.validateInput("TestName",-1,"test@gmail.com","t",
                1032323d,123456d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertFalse(result23);

        boolean result24 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                -1d,123456d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertFalse(result24);

        boolean result25 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                1032323d,-1d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertFalse(result25);

        boolean result26 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                1032323d,123456d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",-1L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertFalse(result26);

        boolean result27 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                1032323d,123456d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("01/21"),123);
        assertFalse(result27);

        boolean result28 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                1032323d,123456d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("01/21"),-1);
        assertFalse(result28);

        boolean result29 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                1032323d,123456d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("00/21"),123);
        assertFalse(result29);

        boolean result30 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                1032323d,123456d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("12/21"),123);
        assertFalse(result30);

        boolean result31 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                1032323d,123456d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("13/21"),123);
        assertFalse(result31);

        when(m_ClientDB.addClientToDB(c)).thenReturn(false);
        boolean result32 = m_service.validateInput("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertTrue(result32);

        boolean result33 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                1032323d,123456d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("11/21"),123);
        assertFalse(result33);

        boolean result34 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","","5432-234","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123);
        assertFalse(result34);

    }

    @Test
    void newClient() throws NoSuchAlgorithmException {
        Client expected = new Client("",123456789,"","",0d,0d,"",
                "","","","",12345678901234L,new Date(1,1,1),123);
        Client real = m_service.newClient("",123456789,"","",0d,0d,"",
                "","","","",12345678901234L,new Date(1,1,1),123);
        assertEquals(expected,real);
    }

    @Test
    void registerNewClient() throws NoSuchAlgorithmException, ParseException {
        Client c = new Client("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/20"),123);

        when(m_ClientDB.addClientToDB(c)).thenReturn(true);
        boolean result = m_service.registerNewClient(new Client("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,new SimpleDateFormat("MM/yy").parse("10/22"),123));
        assertTrue(result);

    }
}