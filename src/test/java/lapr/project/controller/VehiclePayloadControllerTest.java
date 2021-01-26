package lapr.project.controller;

import lapr.project.model.service.DroneService;
import lapr.project.model.service.OrderService;
import lapr.project.model.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class VehiclePayloadControllerTest {
    @InjectMocks
    private VehiclePayloadController vehiclePayloadController;

    @Mock
    private VehicleService mockVehicleService;

    private double expResult;
    @BeforeEach
    void setUp() {
        this.expResult = 10.0;
        this.vehiclePayloadController = new VehiclePayloadController();
        this.mockVehicleService = Mockito.mock(VehicleService.class);
        initMocks(this);
    }
    @Test
    void getVehiclePayload(){
        when(mockVehicleService.getVehiclePayload(1)).thenReturn(expResult);
        double result = this.vehiclePayloadController.getVehiclePayload(1);
        assertEquals(expResult,result);
    }
    @Test
    void testGetVehicleService(){
        VehiclePayloadController ctrl = new VehiclePayloadController();
        VehicleService expected = new VehicleService();
        ctrl.setMoVehicleServ(expected);
        VehicleService real = ctrl.getMoVehicleServ();
        assertEquals(expected, real);
    }
    @Test
    void testSetVehicleService(){
        VehiclePayloadController ctrl = new VehiclePayloadController();
        VehicleService expected = new VehicleService();
        ctrl.setMoVehicleServ(expected);
        VehicleService real = ctrl.getMoVehicleServ();
        assertEquals(expected, real);
    }
}
