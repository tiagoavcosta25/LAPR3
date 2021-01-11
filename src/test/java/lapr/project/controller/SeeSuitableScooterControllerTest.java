package lapr.project.controller;

import lapr.project.data.registration.DeliveryRegistration;
import lapr.project.data.registration.ScooterRegistration;
import lapr.project.model.ChargingSlot;
import lapr.project.model.Client;
import lapr.project.model.Order;
import lapr.project.model.Scooter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class SeeSuitableScooterControllerTest {

    @InjectMocks
    private SeeSuitableScooterController seeSuitableScooterController;

    @Mock
    private ScooterRegistration mockScooterRegistration;

    @Mock
    private DeliveryRegistration mockDeliveryRegistration;

    @BeforeEach
    void setUp() {
        this.seeSuitableScooterController = new SeeSuitableScooterController();
        this.mockScooterRegistration = Mockito.mock(ScooterRegistration.class);
        this.mockDeliveryRegistration = Mockito.mock(DeliveryRegistration.class);
        initMocks(this);
    }


    @Test
    void getSuitableScooter() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        when(mockDeliveryRegistration.getDeliveryEnergy(-1d)).thenReturn(-1f);
        when(mockScooterRegistration.getSuitableScooter(-1f, "email3@gmail.com")).thenReturn(new Scooter());

        Scooter expectedScooter = new Scooter();
        Scooter result = seeSuitableScooterController.getSuitableScooter(-1d);
        assertEquals(expectedScooter, result);
    }
}