package lapr.project.model.service;

import lapr.project.data.UserDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.Drone;
import lapr.project.model.Scooter;
import lapr.project.model.Vehicle;
import lapr.project.utils.EncryptPassword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class VehicleServiceTest {

    @InjectMocks
    private VehicleService m_service;

    @Mock
    private VehicleDB m_oVehicleDB;

    @BeforeEach
    void setUp() {
        this.m_service = new VehicleService();
        this.m_oVehicleDB = Mockito.mock(VehicleDB.class);
        initMocks(this);
    }

    @Test
    void getSuitableVehicle() {
        when (m_oVehicleDB.getSuitableVehicle(-1d,-1d,"email3@gmail.com")).thenReturn(new Scooter());
        Vehicle real = m_service.getSuitableVehicle(-1d,-1d,"email3@gmail.com");
        assertEquals(new Scooter(),real);

        when (m_oVehicleDB.getSuitableVehicle(-1d,-1d,"email3@gmail.com")).thenReturn(new Drone());
        real = m_service.getSuitableVehicle(-1d,-1d,"email3@gmail.com");
        assertEquals(new Drone(),real);
    }

    @Test
    void getPharmacyModel() {
        when (m_oVehicleDB.getPharmacyModel("email3@gmail.com")).thenReturn(new ArrayList<>());
        ArrayList real = m_service.getPharamcyModel("email3@gmail.com");
        assertEquals(new ArrayList(),real);
    }
}