package lapr.project.controller;

import lapr.project.data.CourierDB;
import lapr.project.model.ChargingSlot;
import lapr.project.model.UserSession;
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
    private AvailableChargingSlotController availableChargingSlotController;

    @Mock
    private CourierDB mockCourierDB;

    @BeforeEach
    void setUp() {
        this.availableChargingSlotController = new AvailableChargingSlotController("","","");
        this.mockCourierDB = Mockito.mock(CourierDB.class);
        initMocks(this);
    }


    @Test
    void getAvailableChargingSlot() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        when(mockCourierDB.getAvailableChargingSlot("email3@gmail.com")).thenReturn(new ChargingSlot());
        ChargingSlot expectedChargingSlot = new ChargingSlot();
        ChargingSlot result = availableChargingSlotController.getAvailableChargingSlot();
        assertEquals(expectedChargingSlot, result);
    }
}