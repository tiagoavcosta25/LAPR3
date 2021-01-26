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
import java.util.List;

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
    void getPharmacyModel() {
        System.out.println("getPharmacyModel");
        when (m_oVehicleDB.getPharmacyModel("email3@gmail.com")).thenReturn(new ArrayList<>());
        List<VehicleModel> real = m_service.getPharamcyModel("email3@gmail.com");
        assertEquals(new ArrayList<>(),real);
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
    @Test
    void getVehiclePayload() {
        System.out.println("getVehiclePayload");
        when(m_oVehicleDB.getVehiclePayload(1)).thenReturn(10.0);
        double result = m_service.getVehiclePayload(1);
        assertEquals(10.0, result);
    }

    @Test
    void getVehicleDB() {
        VehicleDB expected = m_oVehicleDB;
        VehicleDB real = m_service.getVehicleDB();
        assertEquals(expected,real);
    }

    @Test
    void setVehicleDB() {
        VehicleDB expected = new VehicleDB();
        m_service.setVehicleDB(expected);
        VehicleDB real = m_service.getVehicleDB();
        assertEquals(expected,real);
    }
}