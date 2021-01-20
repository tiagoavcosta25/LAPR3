package lapr.project.controller;

import lapr.project.data.DeliveryDB;
import lapr.project.data.ScooterDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.*;
import lapr.project.model.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class SeeSuitableVehicleControllerTest {

    @InjectMocks
    private SeeSuitableVehicleController seeSuitableVehicleController;

    @Mock
    private VehicleService mockVehicleService;


    @BeforeEach
    void setUp() {
        this.seeSuitableVehicleController = new SeeSuitableVehicleController();
        this.mockVehicleService = Mockito.mock(VehicleService.class);
        initMocks(this);
    }


    @Test
    void getSuitableVehicle() {
        when(mockVehicleService.getSuitableVehicle(-1d,-1d, "email3@gmail.com")).thenReturn(new Scooter());

        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));
        Vehicle expectedScooter = new Scooter();
        Vehicle result = seeSuitableVehicleController.getSuitableVehicle(-1d,-1d);
        assertEquals(expectedScooter, result);
    }

    @Test
    void getPharmacyModel() {
        when(mockVehicleService.getPharamcyModel("email3@gmail.com")).thenReturn(new ArrayList<>());
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("email3@gmail.com"));

        ArrayList result = seeSuitableVehicleController.getPharmacyModel("email3@gmail.com");
        assertEquals(new ArrayList(), result);
    }
}