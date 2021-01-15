package lapr.project.controller;

import lapr.project.data.CourierDB;
import lapr.project.model.ChargingSlot;
import lapr.project.model.UserSession;
import lapr.project.model.service.CourierService;
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
    private CourierService mockCourierService;

    @BeforeEach
    void setUp() {
        this.availableChargingSlotController = new AvailableChargingSlotController();
        this.mockCourierService = Mockito.mock(CourierService.class);
        initMocks(this);
    }


    @Test
    void getAvailableChargingSlot() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        when(mockCourierService.getAvailableChargingSlot("email3@gmail.com","drone")).thenReturn(new ChargingSlot());
        ChargingSlot expectedChargingSlot = new ChargingSlot();
        ChargingSlot result = availableChargingSlotController.getAvailableChargingSlot("drone");
        assertEquals(expectedChargingSlot, result);
    }
}