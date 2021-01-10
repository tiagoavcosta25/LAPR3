package lapr.project.controller;

import lapr.project.data.registration.CourierRegistration;
import lapr.project.model.ChargingSlot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class AvailableChargingSlotControllerTest {

    @InjectMocks
    private AvailableChargingSlotControllerTest availableChargingSlotControllerTest;

    @Mock
    private AvailableChargingSlotController mockAvailableChargingSlotController;

    private ChargingSlot chargingSlotParameter;

    @BeforeEach
    void setUp() {
        this.chargingSlotParameter = new ChargingSlot();
        this.availableChargingSlotControllerTest = new AvailableChargingSlotControllerTest();
        this.mockAvailableChargingSlotController = Mockito.mock(AvailableChargingSlotController.class);
        initMocks(this);
    }


    @Test
    void getAvailableChargingSlot() {
        ChargingSlot chargingSlot = new ChargingSlot();
        when(mockAvailableChargingSlotController.getAvailableChargingSlot()).thenReturn(chargingSlot);
        ChargingSlot chargingSlot2 = mockAvailableChargingSlotController.getAvailableChargingSlot();
        assertEquals(chargingSlotParameter,chargingSlot2);
    }
}