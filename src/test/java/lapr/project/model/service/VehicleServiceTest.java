package lapr.project.model.service;

import com.google.zxing.WriterException;
import lapr.project.data.UserDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.*;
import lapr.project.utils.EncryptPassword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
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

    private VehicleModel expectedVehicleModel;

    @BeforeEach
    void setUp() {
        this.expectedVehicleModel = new VehicleModel(-1,"No Designation", -1d, 1d,
                -1, new Battery(-1, -1d, -1d), VehicleType.SCOOTER);
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
        System.out.println("getPharmacyModel");
        when (m_oVehicleDB.getPharmacyModel("email3@gmail.com")).thenReturn(new ArrayList<>());
        ArrayList real = m_service.getPharamcyModel("email3@gmail.com");
        assertEquals(new ArrayList(),real);
    }

    @Test
    void newVehicleModel() {
        System.out.println("newVehicleModel");
        VehicleModel result = m_service.newVehicleModel("No Designation", -1d, -1d, 1d,
                -1, -1d, -1d, VehicleType.SCOOTER);
        assertEquals(expectedVehicleModel, result);
    }

    @Test
    void generateQRCode() throws IOException, WriterException {
        System.out.println("generateQRCode");
        boolean real = m_service.generateQRCode(new Scooter());
        assertTrue(real);
    }

    @Test
    void getVehicleModel() {
        System.out.println("getVehicleModel");
        when (m_oVehicleDB.getVehicleModel("email3@gmail.com")).thenReturn(new VehicleModel());
        VehicleModel real = m_service.getVehicleModel("email3@gmail.com");
        assertEquals(new VehicleModel(),real);
    }

    @Test
    void registerVehicleModel() {
        System.out.println("registerVehicleModel");
        when(m_oVehicleDB.registerVehicleModel(expectedVehicleModel)).thenReturn(1);
        int result = m_service.registerVehicleModel(expectedVehicleModel);
        assertEquals(1, result);

        when(m_oVehicleDB.registerVehicleModel(null)).thenReturn(-1);
        result = m_service.registerVehicleModel(null);
        assertEquals(-1, result);
    }
}