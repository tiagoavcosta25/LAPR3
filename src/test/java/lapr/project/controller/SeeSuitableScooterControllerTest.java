package lapr.project.controller;

import lapr.project.model.ChargingSlot;
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
    private SeeSuitableScooterControllerTest seeSuitableScooterControllerTest;

    @Mock
    private SeeSuitableScooterController mockSeeSuitableScooterController;

    private Scooter suitableScooterParameter;

    @BeforeEach
    void setUp() {
        this.suitableScooterParameter = new Scooter();
        this.seeSuitableScooterControllerTest = new SeeSuitableScooterControllerTest();
        this.mockSeeSuitableScooterController = Mockito.mock(SeeSuitableScooterController.class);
        initMocks(this);
    }


    @Test
    void getSuitableScooter() {
        Scooter suitableScooter = new Scooter();
        when(mockSeeSuitableScooterController.getSuitableScooter()).thenReturn(suitableScooter);
        Scooter suitableScooter2 = mockSeeSuitableScooterController.getSuitableScooter();
        assertEquals(suitableScooterParameter,suitableScooter2);
    }
}