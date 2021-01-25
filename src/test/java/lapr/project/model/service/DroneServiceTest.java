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
    void updateDrone() {
        System.out.println("updateDrone");
        when(m_mockDroneDB.updateDroneFromDB(1, 100d, "Test",
                2.0d, 2.0d, 30d, 100, 20d,
                10d)).thenReturn(true);

        boolean result = m_oDroneService.updateDroneFromDB(1, 100d, "Test",
                2.0d, 2.0d, 30d, 100, 20d,
                10d);
        assertTrue(result);

        result = m_oDroneService.updateDroneFromDB(-1, 100d, "Test",
                2.0d, 2.0d, 30d, 100, 20d,
                10d);
        assertFalse(result);

        when(m_mockDroneDB.updateDroneFromDB(12, 100d, "Test",
                2.0d, 2.0d, 30d, 100, 20d,
                10d)).thenReturn(false);
        result = m_oDroneService.updateDroneFromDB(12, 100d, "Test",
                2.0d, 2.0d, 30d, 100, 20d,
                10d);
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
        when(m_mockDroneDB.registerDrone(expectedDrone)).thenReturn(1);
        int result = m_oDroneService.registerDrone(expectedDrone);
        assertEquals(1,result);

        when(m_mockDroneDB.registerDrone(null)).thenReturn(-1);
        result = m_oDroneService.registerDrone(null);
        assertEquals(-1, result);
    }

    @Test
    void getDronesList() {
        System.out.println("getScootersList");
        List<Drone> expectedListDrones = new ArrayList<>(Arrays.asList(new Drone()));
        when(m_oDroneService.getDronesList("email")).thenReturn(expectedListDrones);
        List<Drone> result = m_oDroneService.getDronesList("email");
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
    void startDelivery2(){
        System.out.println("startDelivery2");
        DeliveryRun dr = new DeliveryRun(new Courier(), new ArrayList<>());
        dr.setStatus(DeliveryStatus.INPROGRESS);
        boolean result = m_oDroneService.startDelivery(dr);
        assertFalse(result);
    }


}