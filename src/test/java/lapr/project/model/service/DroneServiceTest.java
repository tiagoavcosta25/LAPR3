package lapr.project.model.service;

import lapr.project.data.DroneDB;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class DroneServiceTest {

    @InjectMocks
    private DroneService m_oDroneService;

    @Mock
    private DroneDB m_mockDroneDB;

    private Drone expectedDrone;
    private float expPayload;

    @BeforeEach
    void setUp() {
        this.expectedDrone = new Drone(new VehicleModel(), new Pharmacy());
        this.expPayload = 10f;
        this.m_oDroneService = new DroneService();
        this.m_mockDroneDB = Mockito.mock(DroneDB.class);
        initMocks(this);
    }

    @Test
    void validate() {
        boolean expected = false;
        boolean real = m_oDroneService.validate(-2f, 1, 1f, 21f, 21d,
                12f, 121f, "aas", 152);
        assertEquals(expected, real);

        expected = false;
        real = m_oDroneService.validate(2f, -1, 1f, 21f, 21d,
                12f, 21f, "asa", 12);
        assertEquals(expected, real);


        expected = false;
        real = m_oDroneService.validate(2f, 1, -1f, 21f, 21d,
                12f, 21f, "asa", 12);
        assertEquals(expected, real);

        expected = false;
        real = m_oDroneService.validate(2f, 1, 1f, -1f, 21d,
                12f, 21f, "asa", 12);
        assertEquals(expected, real);

        expected = false;
        real = m_oDroneService.validate(2f, 1, 1f, 2f, -1d,
                12f, 21f, "asa", 12);
        assertEquals(expected, real);

        expected = false;
        real = m_oDroneService.validate(2f, 1, 1f, 23f, 21d,
                -1f, 21f, "asa", 12);
        assertEquals(expected, real);

        expected = false;
        real = m_oDroneService.validate(2f, 1, 1f, 32f, 21d,
                12f, -1f, "asa", 12);
        assertEquals(expected, real);

        expected = false;
        real = m_oDroneService.validate(2f, 1, 1f, 232f, 21d,
                12f, 21f, "", 12);
        assertEquals(expected, real);

        real = m_oDroneService.validate(2f, 1, 1f, 232f, 21d,
                12f, 21f, "Das", -1);
        assertEquals(expected, real);

        expected = true;
        real = m_oDroneService.validate(2f, 1, 1f, 232f, 21d,
                12f, 21f, "Das", 113);
        assertEquals(expected, real);

        expected = false;
        real = m_oDroneService.validate(101f, 1, 1f, 232f, 21d,
                12f, 21f, "Das", 3);
        assertEquals(expected, real);

        expected = true;
        real = m_oDroneService.validate(100f, 1, 1f, 232f, 21d,
                12f, 21f, "Das", 3);
        assertEquals(expected, real);


        real = m_oDroneService.validate(0f, 1, 1f, 232f, 21d,
                12f, 21f, "Das", 3);
        assertEquals(expected, real);

        expected = false;
        real = m_oDroneService.validate(32f, 0, 1f, 232f, 21d,
                12f, 21f, "Das", 3);
        assertEquals(expected, real);

        real = m_oDroneService.validate(32f, 1, 0f, 232f, 21d,
                12f, 21f, "Das", 3);
        assertEquals(expected, real);

        real = m_oDroneService.validate(32f, 1, 1f, 0f, 21d,
                12f, 21f, "Das", 3);
        assertEquals(expected, real);

        real = m_oDroneService.validate(32f, 1, 1f, 232f, 0d,
                12f, 21f, "Das", 3);
        assertEquals(expected, real);

        real = m_oDroneService.validate(32f, 1, 1f, 232f, 21d,
                0f, 21f, "Das", 3);
        assertEquals(expected, real);

        real = m_oDroneService.validate(32f, 1, 1f, 232f, 21d,
                12f, 0f, "Das", 3);
        assertEquals(expected, real);

        real = m_oDroneService.validate(32f, 1, 1f, 232f, 21d,
                12f, 21f, "Das", 0);
        assertEquals(expected, real);


    }

    @Test
    void updateDrone() {
        when(m_mockDroneDB.updateDrone(1f, 1, 1f, 1f, 1d,
                1f, 1f, "As", 2)).thenReturn(true);
        boolean result = m_oDroneService.updateDrone(1f, 1, 1f, 1f, 1d,
                1f, 1f, "As", 2);
        assertTrue(result);

        when(m_mockDroneDB.updateDrone(1f, 1, 1f, 1f, 1d,
                1f, 1f, "As", 2)).thenReturn(false);
        result = m_oDroneService.updateDrone(1f, 1, 1f, 1f, 1d,
                1f, 1f, "As", 2);

        assertFalse(result);
    }

    @Test
    void newDrone() {
        System.out.println("newScooter");
        Drone result = m_oDroneService.newDrone(new VehicleModel(), new Pharmacy());
        assertEquals(expectedDrone, result);
    }

    @Test
    void registerDrone() {
        System.out.println("registerDrone");
        when(m_mockDroneDB.registerDrone(expectedDrone)).thenReturn(true);
        boolean result = m_oDroneService.registerDrone(expectedDrone);
        assertTrue(result);

        when(m_mockDroneDB.registerDrone(null)).thenReturn(false);
        result = m_oDroneService.registerDrone(null);
        assertFalse(result);
    }

    @Test
    void getDronesList() {
        System.out.println("getScootersList");
        List<Drone> expectedListDrones = new ArrayList<>(Arrays.asList(new Drone()));
        when(m_oDroneService.getDronesList(-1)).thenReturn(expectedListDrones);
        List<Drone> result = m_oDroneService.getDronesList(-1);
        assertEquals(expectedListDrones, result);
    }

    @Test
    void removeDroneFromDB() {
        System.out.println("removeDroneFromDB");
        when(m_mockDroneDB.removeDroneFromDB(1)).thenReturn(true);
        boolean result = m_oDroneService.removeDroneFromDB(1);
        assertTrue(result);

        when(m_mockDroneDB.removeDroneFromDB(-1)).thenReturn(false);
        result = m_oDroneService.removeDroneFromDB(2);
        assertFalse(result);
    }

    @Test
    void checkEnergy() {
        System.out.println("checkEnergy");
        List<Order> list = new ArrayList<>();
        list.add(new Order(1f, 0.25f,0, new Date(System.currentTimeMillis()), null, null, false, new Client(), new Pharmacy(), new TreeMap<>()));
        list.add(new Order(1f, 1f,0, new Date(System.currentTimeMillis()), null, null, false, new Client(), new Pharmacy(),new TreeMap<>()));
        DeliveryRun dr = new DeliveryRun(new Courier(),list);
        dr.setVehicle(new Drone(new VehicleModel("Modelo1", 250,10, 10, new Battery(10,4,80), VehicleType.DRONE), new Pharmacy()));
        dr.setStatus(DeliveryStatus.IDLE);
        boolean result = m_oDroneService.checkEnergy(10000, dr);
        assertTrue(result);
    }

    @Test
    void checkEnergy2() {
        System.out.println("checkEnergy2");
        DeliveryRun dr = new DeliveryRun(new Courier(), new ArrayList<>());
        dr.setVehicle(new Drone());
        boolean result2 = m_oDroneService.checkEnergy(10, dr);
        assertFalse(result2);
    }
     @Test
     void checkEnergy3(){
         System.out.println("checkEnergy3");
         List<Order> list = new ArrayList<>();
         list.add(new Order(1f, 0.25f,0, new Date(System.currentTimeMillis()), null, null, false, new Client(), new Pharmacy(), new TreeMap<>()));
         list.add(new Order(1f, 1f,0, new Date(System.currentTimeMillis()), null, null, false, new Client(), new Pharmacy(),new TreeMap<>()));
         DeliveryRun dr = new DeliveryRun(new Courier(),list);
         dr.setVehicle(new Scooter(new VehicleModel("Modelo2", 350,15, 10, new Battery(200,40,80), VehicleType.SCOOTER), new Pharmacy()));
         dr.setStatus(DeliveryStatus.IDLE);
         boolean result = m_oDroneService.checkEnergy(25000, dr);
         assertTrue(result);
     }
     @Test
     void checkEnergy4(){
         System.out.println("checkEnergy4");
         DeliveryRun dr = new DeliveryRun(new Courier(), new ArrayList<>());
         dr.setVehicle(new Scooter());
         boolean result2 = m_oDroneService.checkEnergy(10, dr);
         assertFalse(result2);
        }

    @Test
    void startDelivery() {
        System.out.println("startDelivery");
        DeliveryRun dr = new DeliveryRun(new Courier(), new ArrayList<>());
        boolean result = m_oDroneService.startDelivery(dr);
        assertTrue(result);
    }

    @Test
    void getDronePayload() {
        System.out.println("getDronePayload");
        when(m_mockDroneDB.getDronePayload(1)).thenReturn(expPayload);
        float result = this.m_oDroneService.getDronePayload(1);
        assertEquals(expPayload, result);
    }

}