package lapr.project.controller;

import lapr.project.data.registration.CourierRegistration;
import lapr.project.data.registration.DeliveryRegistration;
import lapr.project.data.registration.ScooterRegistration;
import lapr.project.model.ChargingSlot;
import lapr.project.model.Scooter;
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
    private CourierRegistration mockCourierRegistration;

    @BeforeEach
    void setUp() {
        this.availableChargingSlotController = new AvailableChargingSlotController();
        this.mockCourierRegistration = Mockito.mock(CourierRegistration.class);
        initMocks(this);
    }


    @Test
    void getAvailableChargingSlot() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        when(mockCourierRegistration.getAvailableChargingSlot("email3@gmail.com")).thenReturn(new ChargingSlot());
        ChargingSlot expectedChargingSlot = new ChargingSlot();
        ChargingSlot result = availableChargingSlotController.getAvailableChargingSlot();
        assertEquals(expectedChargingSlot, result);
    }
}