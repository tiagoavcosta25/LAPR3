package lapr.project.controller;

import lapr.project.data.DataHandler;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class RegisterClientControllerTest {
    RegisterClientController m_ctrl = new RegisterClientController();

    @Test
    void registerNewClient() throws Exception {
        /**
         * Create new Client = Works
         */
        boolean real = m_ctrl.registerNewClient("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,"10/23",123);

        assertTrue(real);

        /**
         * Create same Client = Error
         */
        boolean real2 = m_ctrl.registerNewClient("TestName",123456788,"test@gmail.com","testpassword",
                1032323d,1999392d,"Test street","2ºesq","4444-111","Gaia",
                "Portugal",1234123412341233L,"10/23",123);

        assertFalse(real2);

        /**
         * Creat Client with same email
         */
        boolean real3 = m_ctrl.registerNewClient("TestName",987654321,"test@gmail.com","testpassword",
                3923219d,2132414d,"Test street","2ºesq","4333-222","Gaia",
                "Portugal",1234123412341233L,"10/23",123);

        assertFalse(real3);

        /**
         * Create Client with same nif
         */
        boolean real4 = m_ctrl.registerNewClient("TestName",123456788,"different@gmail.com","testpassword",
                9319494d,3333331d,"Test street","2ºesq","4222-333","Gaia",
                "Portugal",2345234523452345L,"10/23",123);

        assertFalse(real4);

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