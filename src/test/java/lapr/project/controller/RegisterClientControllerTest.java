package lapr.project.controller;


import lapr.project.model.Client;
import lapr.project.model.CreditCard;
import lapr.project.model.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        List<CreditCard> lst = new ArrayList<>();
        lst.add(new CreditCard());
        when (m_mockClientService.newClient("as",123456789,"aas@","1234567",123d,12355d,10d,"asd"
                ,"as","4433-112","loc","country",new ArrayList<>())).thenReturn(new Client("as",123456789,"aas@","1234567",123d,12355d,"asd"
                ,"as","4433-112","loc","country",lst));
        when (m_mockClientService.validateInput("as",123456789,"aas@","1234567",123d,12355d,10d,"asd"
                ,"as","4433-112","loc","country",new ArrayList<>())).thenReturn(true);
        when (m_mockClientService.registerNewClient(new Client("as",123456789,"aas@","1234567",123d,12355d,10d,"asd"
                ,"as","4433-112","loc","country",new ArrayList<>()))).thenReturn(true);
        boolean real = m_ctrl.registerNewClient("as",123456789,"aas@","1234567",123d,12355d,10d,"asd"
        ,"as","4433-112","loc","country",new ArrayList<>());
        assertTrue(real);

        real = m_ctrl.registerNewClient("as",123456789,"aas@","1234567",123d,12355d,10d,"asd"
                ,"as","4433-112","loc","country",new ArrayList<>());
        assertTrue(real);

        real = m_ctrl.registerNewClient("as",123456789,"a@","1234567",1231d,124d,10d,"asda",
                "asd","434-243","loca","country",new ArrayList<>());

        assertFalse(real);

        real = m_ctrl.registerNewClient("as",123456789,"a@","1234567",1231d,124d,10d,"asda",
                "asd","434-243","loca","country",new ArrayList<>());
        assertFalse(real);

        real = m_ctrl.registerNewClient("as",123456789,"a@","1234567",1231d,124d,10d,"asda",
                "asd","434-243","loca","country",new ArrayList<>());
        assertFalse(real);

        when (m_mockClientService.validateInput("as",123456789,"a@","1234567",1231d,124d,10d,"asda",
                "asd","434-243","loca","country",new ArrayList<>())).thenReturn(false);

        real = m_ctrl.registerNewClient("as",123456789,"a@","1234567",1231d,124d,10d,"asda",
                "asd","434-243","loca","country",new ArrayList<>());

        assertFalse(real);
    }

}