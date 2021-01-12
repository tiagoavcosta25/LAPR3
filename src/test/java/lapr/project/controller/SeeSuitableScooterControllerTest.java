package lapr.project.controller;

import lapr.project.data.DeliveryDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.*;
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
    private ScooterDB mockScooterDB;

    @Mock
    private DeliveryDB mockDeliveryDB;

    @BeforeEach
    void setUp() {
        this.seeSuitableScooterController = new SeeSuitableScooterController();
        this.mockScooterDB = Mockito.mock(ScooterDB.class);
        this.mockDeliveryDB = Mockito.mock(DeliveryDB.class);
        initMocks(this);
    }


    @Test
    void getSuitableScooter() {
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        when(mockScooterDB.getSuitableScooter(-1d, "email3@gmail.com")).thenReturn(new Scooter());

        Scooter expectedScooter = new Scooter();
        Scooter result = seeSuitableScooterController.getSuitableScooter(-1d);
        assertEquals(expectedScooter, result);
    }
}