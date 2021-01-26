package lapr.project.model.service;

import lapr.project.data.ClientDB;
import lapr.project.model.Client;
import lapr.project.model.CreditCard;
import lapr.project.utils.EmailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ClientServiceTest {

    @InjectMocks
    private ClientService m_service;

    @Mock
    private ClientDB m_ClientDB;
    @Mock
    private EmailSender m_EmailSender;

    @BeforeEach
    void setUp() {
        this.m_service = new ClientService();
        this.m_ClientDB = Mockito.mock(ClientDB.class);
        this.m_EmailSender = Mockito.mock(EmailSender.class);
        initMocks(this);
    }

    @Test
    void validateInput() throws NoSuchAlgorithmException, ParseException {
        List<CreditCard> lst = new ArrayList<>();
        lst.add(new CreditCard(102301L,new SimpleDateFormat("MM-yy").parse("11-24"),123));
        /**
         * Validate correct format input
         */
        boolean real = m_service.validateInput("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);

        assertTrue(real);

        /**
         * Validate incorrect nif (few figures)
         */
        boolean real2 = m_service.validateInput("TestName",12345678,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",new ArrayList<>());

        assertFalse(real2);

        /**
         * Validate incorrect email
         */
        boolean real3 = m_service.validateInput("TestName",123456788,"teste","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);

        assertFalse(real3);

        /**
         * Validate incorrect password (length smaller than 6)
         */
        boolean real4 = m_service.validateInput("TestName",12345678,"test@gmail.com","test",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);

        assertFalse(real4);

        Client c = new Client("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",new ArrayList<>());


        boolean result = m_service.validateInput("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertTrue(result);

        boolean result2 = m_service.validateInput("TestName",12345,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertFalse(result2);

        boolean result3 = m_service.validateInput("TestName",123456789,"testgmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertFalse(result3);

        boolean result4 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",new ArrayList<>());
        assertFalse(result4);

        boolean result5 = m_service.validateInput("",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertFalse(result5);

        boolean result6 = m_service.validateInput("TestName",123456789,"","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertFalse(result6);

        boolean result7 = m_service.validateInput("TestName",123456789,"test@gmail.com","",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertFalse(result7);

        boolean result8 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertFalse(result8);

        boolean result9 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","","Gaia",
                "Portugal",lst);
        assertFalse(result9);

        boolean result10 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","",
                "Portugal",lst);
        assertFalse(result10);

        boolean result11 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "",lst);
        assertFalse(result11);

        boolean result12 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",new ArrayList<>());
        assertFalse(result12);

        boolean result13 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertTrue(result13);

        boolean result14 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",new ArrayList<>());
        assertFalse(result14);

        boolean result15 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertTrue(result15);

        boolean result17 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertFalse(result17);

        boolean result19 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                0d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertFalse(result19);

        boolean result20 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,0d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertFalse(result20);

        boolean result21 = m_service.validateInput("TestName",123456789,"test@gmail.com","tttttt",
                1032323d,123456d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertTrue(result21);

        boolean result22 = m_service.validateInput("TestName",0,"test@gmail.com","t",
                1032323d,123456d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertFalse(result22);

        boolean result23 = m_service.validateInput("TestName",-1,"test@gmail.com","t",
                1032323d,123456d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertFalse(result23);

        boolean result24 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                -1d,123456d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertTrue(result24);

        boolean result25 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,-1d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertTrue(result25);

        boolean result26 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                1032323d,123456d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",new ArrayList<>());
        assertFalse(result26);

        boolean result27 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                1032323d,123456d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",new ArrayList<>());
        assertFalse(result27);

        boolean result28 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                1032323d,123456d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",new ArrayList<>());
        assertFalse(result28);

        boolean result29 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                1032323d,123456d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",new ArrayList<>());
        assertFalse(result29);

        boolean result30 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                1032323d,123456d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",new ArrayList<>());
        assertFalse(result30);

        boolean result31 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                1032323d,123456d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",new ArrayList<>());
        assertFalse(result31);

        when(m_ClientDB.addClientToDB(c)).thenReturn(false);
        boolean result32 = m_service.validateInput("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertTrue(result32);

        boolean result33 = m_service.validateInput("TestName",123456789,"test@gmail.com","t",
                1032323d,123456d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertFalse(result33);

        boolean result34 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","","5432-234","Gaia",
                "Portugal",lst);
        assertFalse(result34);

        boolean result35 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,-1d,"Test street","2esq","5432-234","Gaia",
                "Portugal",lst);
        assertFalse(result35);

        boolean result36 = m_service.validateInput("TestName",123456789,"test@gmail.com","testpassword",
                1032323d,1999392d,0d,"Test street","2esq","5432-234","Gaia",
                "Portugal",lst);
        assertFalse(result36);

        lst.add(new CreditCard(102301L,new SimpleDateFormat("MM-yy").parse("11-24"),1234));
        boolean result37 = m_service.validateInput("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertFalse(result37);

        lst.add(new CreditCard(102301L,new SimpleDateFormat("MM-yy").parse("11-24"),1234));
        result37 = m_service.validateInput("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);
        assertFalse(result37);
    }

    @Test
    void newClient() throws NoSuchAlgorithmException {
        Client expected = new Client("",123456789,"","",0d,0d,0d,"",
                "","","","",new ArrayList<>());
        Client real = m_service.newClient("",123456789,"","",0d,0d,0d,"",
                "","","","",new ArrayList<>());
        assertEquals(expected,real);
    }

    @Test
    void registerNewClient() throws NoSuchAlgorithmException, ParseException {
        List<CreditCard> lst = new ArrayList<>();
        lst.add(new CreditCard(102301L,new SimpleDateFormat("MM-yy").parse("11-24"),123));
        Client c = new Client("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst);

        when(m_ClientDB.addClientToDB(c)).thenReturn(true);
        boolean result = m_service.registerNewClient(new Client("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",lst));
        assertTrue(result);

        when(m_ClientDB.addClientToDB(c)).thenReturn(false);
        result = m_service.registerNewClient(new Client("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,10d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",new ArrayList<>()));
        assertFalse(result);

    }


    @Test
    void getClientByEmail() {
        Client expected = new Client();
        when(m_ClientDB.getClientByEmail("")).thenReturn(new Client());
        Client real = m_service.getClientByEmail("");
        assertEquals(expected,real);
    }

    @Test
    void getCreditCardsByClient() {
        List<CreditCard> expected = new ArrayList<>();
        when(m_ClientDB.getCreditCardsByClient("")).thenReturn(new ArrayList<>());
        List<CreditCard> real = m_service.getCreditCardsByClient("");
        assertEquals(expected,real);

        expected = new ArrayList<>();
        expected.add(new CreditCard());
        when(m_ClientDB.getCreditCardsByClient("")).thenReturn(expected);

        real = m_service.getCreditCardsByClient("");
        assertEquals(expected,real);


        when(m_ClientDB.getCreditCardsByClient("")).thenReturn(null);
        expected = null;
        real = m_service.getCreditCardsByClient("");
        assertEquals(expected,real);



    }

    @Test
    void updateClientCredits() {
        when(m_ClientDB.updateClientCredits("", 1)).thenReturn(true);
        boolean real = m_service.updateClientCredits("", 1);
        assertTrue(real);

        when(m_ClientDB.updateClientCredits("", 1)).thenReturn(false);
        real = m_service.updateClientCredits("", 1);
        assertFalse(real);
    }

    @Test
    void getClientDB() {
        ClientDB expected = m_ClientDB;
        ClientDB real = m_service.getClientDB();
        assertEquals(expected,real);
    }

    @Test
    void setClientDB() {
        ClientDB expected = new ClientDB();
        m_service.setClientDB(expected);
        ClientDB real = m_service.getClientDB();
        assertEquals(expected,real);
    }
}