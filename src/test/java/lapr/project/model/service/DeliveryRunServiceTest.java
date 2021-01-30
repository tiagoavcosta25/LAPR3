package lapr.project.model.service;

import javafx.util.Pair;
import lapr.project.data.DeliveryRunDB;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class DeliveryRunServiceTest {

    @InjectMocks
    private DeliveryRunService m_oDeliveryRunService;

    @Mock
    private DeliveryRunDB m_oDeliveryRunDB;
    @Mock
    private GraphService m_oGraphService;

    @BeforeEach
    void setUp() {
        this.m_oDeliveryRunService = new DeliveryRunService();
        this.m_oDeliveryRunDB = Mockito.mock(DeliveryRunDB.class);
        this.m_oGraphService = Mockito.mock(GraphService.class);
        initMocks(this);
    }


    @Test
    void newDeliveryRun() {
        DeliveryRun expected = new DeliveryRun();
        DeliveryRun real = m_oDeliveryRunService.newDeliveryRun(new Courier(), new ArrayList<>(),new Scooter());
        assertEquals(expected, real);
    }

    @Test
    void addNewDeliveryRun() {
        VehicleModel vm = new VehicleModel(1,"as",23,43,21,new Battery(),
                VehicleType.SCOOTER);
        Scooter scooter = new Scooter(1,vm,new Pharmacy());
        DeliveryRun deliveryRun = new DeliveryRun(new Courier(),new ArrayList<>(),scooter);
        when(m_oDeliveryRunDB.addNewDeliveryRun(new DeliveryRun())).thenReturn(true);
        boolean real = m_oDeliveryRunService.addNewDeliveryRun(deliveryRun);
        assertTrue(real);

        when(m_oDeliveryRunDB.addNewDeliveryRun(new DeliveryRun())).thenReturn(false);
        real = m_oDeliveryRunService.addNewDeliveryRun(new DeliveryRun());
        assertFalse(real);
    }

    @Test
    void getAddressesByDeliveryRunId() {
        when(m_oDeliveryRunDB.getAddressesByDeliveryRunId("email")).thenReturn(new ArrayList<>());
        List<Address> result = m_oDeliveryRunService.getAddressesByDeliveryRunId("email");
        assertEquals(result, new ArrayList<>());

        when(m_oDeliveryRunDB.getAddressesByDeliveryRunId("email")).thenReturn(null);
        result = m_oDeliveryRunService.getAddressesByDeliveryRunId("email");
        assertEquals(null, result);

        List<Address> r = new ArrayList<>();
        r.add(new Address());
        when(m_oDeliveryRunDB.getAddressesByDeliveryRunId("email")).thenReturn(r);
        result = m_oDeliveryRunService.getAddressesByDeliveryRunId("email");
        assertEquals(r, result);
    }


    @Test
    void startDeliveryRun() {
        Map<String,String> lst = new TreeMap<>();
        when(m_oDeliveryRunDB.startDeliveryRun("123")).thenReturn(lst);
        Map<String,String> result = m_oDeliveryRunService.startDeliveryRun("123");
        assertEquals(lst, result);

    }

    @Test
    void sendsEmail() {
        Map<String,String> lst = new TreeMap<>();
        boolean result = m_oDeliveryRunService.sendsEmail(lst);
        assertTrue(result);
    }

    @Test
    void registerPath() {
        when (m_oDeliveryRunDB.addPathToDB(new Path(1,2,3,4,"a"
                ,1,2,3,VehicleType.SCOOTER))).thenReturn(true);
        boolean real = m_oDeliveryRunService.registerPath(1,2,3,4,"a"
                ,1,2,3,VehicleType.SCOOTER);
        assertTrue(real);

        when (m_oDeliveryRunDB.addPathToDB(new Path(1,2,3,4,"a"
                ,1,2,3,VehicleType.SCOOTER))).thenReturn(false);
        real = m_oDeliveryRunService.registerPath(1,2,3,4,"a"
                ,1,2,3,VehicleType.SCOOTER);
        assertFalse(real);


    }

    /*
    @Test
    void calculateMostEfficientPath() {
        ApplicationPOT.getInstance().getWorldMap().setGraph(new Graph<>(true));
        when (m_oWorldMap.calculateMostEfficientPath(new Address(),new Address(),new ArrayList<>())).thenReturn(new LinkedList<>());
        LinkedList<Address> real = m_oDeliveryRunService.calculateMostEfficientPath(new Address(),new Address(),new LinkedList<>());
        assertEquals(new LinkedList<>(),real);
    }
     */

    @Test
    void testSendsEmail() {
        Map<String,Order> lstClients = new HashMap<String,Order>();
        lstClients.put("email@gmail.com",new Order());
        boolean real = m_oDeliveryRunService.sendsEmail(lstClients);
        assertTrue(real);

        real = m_oDeliveryRunService.sendsEmail(null);
        assertFalse(real);
    }

    @Test
    void getMostChargedScooter() {
        when(m_oDeliveryRunDB.getMostChargedScooterFromModel(new VehicleModel())).thenReturn(new Scooter());
        Scooter real = m_oDeliveryRunService.getMostChargedScooter(new VehicleModel());
        assertEquals(new Scooter(),real);
    }

    @Test
    void getMostChargedDrone() {
        when(m_oDeliveryRunDB.getMostChargedDroneFromModel(new VehicleModel())).thenReturn(new Drone());
        Drone real = m_oDeliveryRunService.getMostChargedDrone(new VehicleModel());
        assertEquals(new Drone(),real);
    }

    @Test
    void getMostEfficientVehicleModel() {
        List<Route> lst = new ArrayList<>();
        List<Double> dbl = new ArrayList<>();
        dbl.add(1002d);
        Route r1 = new Route(VehicleType.SCOOTER,new ArrayList<>());
        Route r2 = new Route(VehicleType.SCOOTER,new ArrayList<>());
        r1.setVehicleModel(new VehicleModel());
        r1.setEnergyList(dbl);
        r2.setVehicleModel(new VehicleModel());
        lst.add(r2);
        lst.add(r1);

        VehicleModel real = m_oDeliveryRunService.getMostEfficientVehicleModel(lst);
        assertEquals(new VehicleModel(),real);

        lst = new ArrayList<>();
        dbl = new ArrayList<>();
        dbl.add(1002d);
        r1 = new Route(VehicleType.SCOOTER,new ArrayList<>());
        r2 = new Route(VehicleType.SCOOTER,new ArrayList<>());
        r1.setVehicleModel(new VehicleModel());
        r1.setEnergyList(dbl);
        r2.setVehicleModel(new VehicleModel());
        lst.add(r1);
        lst.add(r2);

        real = m_oDeliveryRunService.getMostEfficientVehicleModel(lst);
        assertEquals(new VehicleModel(),real);

        lst = new ArrayList<>();
        lst.add(new Route(VehicleType.SCOOTER,new ArrayList<>()));
        lst.add(new Route(VehicleType.SCOOTER,new ArrayList<>()));

        real = m_oDeliveryRunService.getMostEfficientVehicleModel(lst);
        assertNull(real);

        lst = new ArrayList<>();
        lst.add(null);
        lst.add(new Route(VehicleType.SCOOTER,new ArrayList<>()));

        real = m_oDeliveryRunService.getMostEfficientVehicleModel(lst);
        assertNull(real);

        lst = new ArrayList<>();
        lst.add(new Route(VehicleType.SCOOTER,new ArrayList<>()));
        lst.add(null);

        real = m_oDeliveryRunService.getMostEfficientVehicleModel(lst);
        assertNull(real);

        lst = new ArrayList<>();
        lst.add(null);
        lst.add(null);

        real = m_oDeliveryRunService.getMostEfficientVehicleModel(lst);
        assertNull(real);

        real = m_oDeliveryRunService.getMostEfficientVehicleModel(new ArrayList<>());
        assertNull(real);

    }

    @Test
    void getDeliveryRunDB() {
        DeliveryRunDB expected = m_oDeliveryRunDB;
        DeliveryRunDB real = m_oDeliveryRunService.getDeliveryRunDB();
        assertEquals(expected,real);
    }

    @Test
    void setDeliveryRunDB() {
        DeliveryRunDB expected = new DeliveryRunDB();
        m_oDeliveryRunService.setDeliveryRunDB(expected);
        DeliveryRunDB real = m_oDeliveryRunService.getDeliveryRunDB();
        assertEquals(expected,real);
    }
}