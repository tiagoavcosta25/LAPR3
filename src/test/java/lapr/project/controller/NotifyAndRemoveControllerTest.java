package lapr.project.controller;

import lapr.project.data.ClientDB;
import lapr.project.data.OrderDB;
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
    private OrderDB mockOrderDB;

    @Mock
    private ClientDB mockClientDB;

    @BeforeEach
    void setUp() {
        this.notifyAndRemoveController = new NotifyAndRemoveController("","","");
        this.mockOrderDB = Mockito.mock(OrderDB.class);
        this.mockClientDB = Mockito.mock(ClientDB.class);
        initMocks(this);
    }

    @Test
    void notifyAndRemove() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        when(mockClientDB.getClientByEmail("email3@gmail.com")).thenReturn(new Client());
        when(mockOrderDB.getLatestOrder(new Client())).thenReturn(new Order());
        when(mockOrderDB.notifyAndRemove(new Order())).thenReturn(true);
        boolean result = notifyAndRemoveController.notifyAndRemove();
        assertTrue(result);

        when(mockOrderDB.notifyAndRemove(new Order())).thenReturn(false);
        result = notifyAndRemoveController.notifyAndRemove();
        assertFalse(result);
    }
}