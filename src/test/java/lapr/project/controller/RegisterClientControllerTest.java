package lapr.project.controller;


import lapr.project.model.Client;
import lapr.project.model.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RegisterClientControllerTest {

    @InjectMocks
    private RegisterClientController m_ctrl;


    @Mock
    private ClientService m_mockClientService;

    RegisterClientControllerTest() {
    }

    @BeforeEach
    void setUp() {
        this.m_ctrl = new RegisterClientController();
        this.m_mockClientService = Mockito.mock(ClientService.class);
        initMocks(this);
    }

    @Test
    void registerNewClient() throws Exception {
        Date d = new Date((new SimpleDateFormat("MM/yy").
                parse("10/23")).getTime());
        when (m_mockClientService.newClient("as",123456789,"aas@","1234567",123d,12355d,"asd"
                ,"as","4433-112","loc","country",1234567890123456L,d,123)).thenReturn(new Client("as",123456789,"aas@","1234567",123d,12355d,"asd"
                ,"as","4433-112","loc","country",1234567890123456L,d,123));
        when (m_mockClientService.validateInput("as",123456789,"aas@","1234567",123d,12355d,"asd"
                ,"as","4433-112","loc","country",1234567890123456L,d,123)).thenReturn(true);
        when (m_mockClientService.registerNewClient(new Client("as",123456789,"aas@","1234567",123d,12355d,"asd"
                ,"as","4433-112","loc","country",1234567890123456L,d,123))).thenReturn(true);
        boolean real = m_ctrl.registerNewClient("as",123456789,"aas@","1234567",123d,12355d,"asd"
        ,"as","4433-112","loc","country",1234567890123456L,"10/23",123);
        assertTrue(real);

        real = m_ctrl.registerNewClient("as",123456789,"aas@","1234567",123d,12355d,"asd"
                ,"as","4433-112","loc","country",1234567890123456L,"10123qwasdcxz_|323",123);
        assertFalse(real);

        real = m_ctrl.registerNewClient("as",123456789,"a@","1234567",1231d,124d,"asda",
                "asd","434-243","loca","country",1234567890123456L,"0/22",123);

        assertFalse(real);

        real = m_ctrl.registerNewClient("as",123456789,"a@","1234567",1231d,124d,"asda",
                "asd","434-243","loca","country",1234567890123456L,"13/22",123);
        assertFalse(real);

        real = m_ctrl.registerNewClient("as",123456789,"a@","1234567",1231d,124d,"asda",
                "asd","434-243","loca","country",1234567890123456L,"11/22",123);
        assertFalse(real);

        when (m_mockClientService.validateInput("as",123456789,"a@","1234567",1231d,124d,"asda",
                "asd","434-243","loca","country",1234567890123456L,new SimpleDateFormat("MM/yy").
                        parse("11/22"),123)).thenReturn(false);

        real = m_ctrl.registerNewClient("as",123456789,"a@","1234567",1231d,124d,"asda",
                "asd","434-243","loca","country",1234567890123456L,"11/22",123);

        assertFalse(real);
    }

}