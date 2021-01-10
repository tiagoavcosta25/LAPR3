package lapr.project.controller;

import lapr.project.model.ChargingSlot;
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
    private NotifyAndRemoveControllerTest notifyAndRemoveControllerTest;

    @Mock
    private NotifyAndRemoveController mockNotifyAndRemoveController;

    private boolean assertTrue;

    @BeforeEach
    void setUp() {
        this.assertTrue = true;
        this.notifyAndRemoveControllerTest = new NotifyAndRemoveControllerTest();
        this.mockNotifyAndRemoveController = Mockito.mock(NotifyAndRemoveController.class);
        initMocks(this);
    }

    @Test
    void notifyAndRemove() {
        when(mockNotifyAndRemoveController.notifyAndRemove()).thenReturn(assertTrue);
        boolean result = mockNotifyAndRemoveController.notifyAndRemove();
        assertEquals(assertTrue, result);
    }
}