package lapr.project.controller;

import lapr.project.model.service.CourierService;
import lapr.project.model.service.DroneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DronePayloadControllerTest {
    @InjectMocks
    private DronePayloadController dronePayloadController;

    @Mock
    private DroneService mockDroneService;

    private float expResult;
    @BeforeEach
    void setUp() {
        this.expResult = 10f;
        this.dronePayloadController = new DronePayloadController();
        this.mockDroneService = Mockito.mock(DroneService.class);
        initMocks(this);
    }
    @Test
    void getDronePayload(){
        when(mockDroneService.getDronePayload(1)).thenReturn(expResult);
        float result = this.dronePayloadController.getDronePayload(1);
        assertEquals(expResult,result);
    }
}
