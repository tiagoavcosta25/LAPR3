package lapr.project.controller;

import lapr.project.data.registration.ClientRegistration;
import lapr.project.data.registration.OrderRegistration;
import lapr.project.model.ChargingSlot;
import lapr.project.model.Client;
import lapr.project.model.Order;
import lapr.project.model.UserSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class NotifyAndRemoveControllerTest {

    @InjectMocks
    private NotifyAndRemoveController notifyAndRemoveController;

    @Mock
    private OrderRegistration mockOrderRegistration;

    @Mock
    private ClientRegistration mockClientRegistration;

    private boolean assertTrue;

    @BeforeEach
    void setUp() {
        this.assertTrue = true;
        this.notifyAndRemoveController = new NotifyAndRemoveController();
        this.mockOrderRegistration = Mockito.mock(OrderRegistration.class);
        this.mockClientRegistration = Mockito.mock(ClientRegistration.class);
        initMocks(this);
    }

    @Test
    void notifyAndRemove() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        when(mockClientRegistration.getClientByEmail("email3@gmail.com")).thenReturn(new Client());
        when(mockOrderRegistration.getLatestOrder(new Client())).thenReturn(new Order());
        when(mockOrderRegistration.notifyAndRemove(new Order())).thenReturn(assertTrue);
        boolean result = notifyAndRemoveController.notifyAndRemove();
        assertEquals(assertTrue, result);
    }
}