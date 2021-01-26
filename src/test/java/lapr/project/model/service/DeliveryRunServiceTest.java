package lapr.project.model.service;

import javafx.util.Pair;
import lapr.project.data.DeliveryDB;
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
    private DeliveryDB m_oDeliveryDB;
    @Mock
    private GraphService m_oGraphService;

    @BeforeEach
    void setUp() {
        this.m_oDeliveryRunService = new DeliveryRunService();
        this.m_oDeliveryRunDB = Mockito.mock(DeliveryRunDB.class);
        this.m_oDeliveryDB = Mockito.mock(DeliveryDB.class);
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
        when(m_oDeliveryRunDB.addNewDeliveryRun(new DeliveryRun())).thenReturn(true);
        boolean real = m_oDeliveryRunService.addNewDeliveryRun(new DeliveryRun());
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
        when(m_oDeliveryDB.startDeliveryRun("123")).thenReturn(lst);
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
        List<Pair<Pair<VehicleModel, Double>, List<Address>>> lst = new ArrayList<>();
        lst.add(new Pair<Pair<VehicleModel,Double>,List<Address>>(new Pair<VehicleModel,Double>
                (new VehicleModel(),2d), new ArrayList<>()));
        lst.add(new Pair<Pair<VehicleModel,Double>,List<Address>>(new Pair<VehicleModel,Double>
                (new VehicleModel(),5d), new ArrayList<>()));

        VehicleModel real = m_oDeliveryRunService.getMostEfficientVehicleModel(lst);
        assertEquals(new VehicleModel(),real);

        lst = new ArrayList<>();
        lst.add(new Pair<Pair<VehicleModel,Double>,List<Address>>(new Pair<VehicleModel,Double>
                (new VehicleModel(),5d), new ArrayList<>()));
        lst.add(new Pair<Pair<VehicleModel,Double>,List<Address>>(new Pair<VehicleModel,Double>
                (new VehicleModel(),2d), new ArrayList<>()));

        real = m_oDeliveryRunService.getMostEfficientVehicleModel(lst);
        assertEquals(new VehicleModel(),real);

        lst = new ArrayList<>();
        lst.add(new Pair<Pair<VehicleModel,Double>,List<Address>>(new Pair<VehicleModel,Double>
                (new VehicleModel(),2d), new ArrayList<>()));
        lst.add(new Pair<Pair<VehicleModel,Double>,List<Address>>(new Pair<VehicleModel,Double>
                (new VehicleModel(),2d), new ArrayList<>()));

        real = m_oDeliveryRunService.getMostEfficientVehicleModel(lst);
        assertEquals(new VehicleModel(),real);

        lst = new ArrayList<>();
        lst.add(null);
        lst.add(new Pair<Pair<VehicleModel,Double>,List<Address>>(new Pair<VehicleModel,Double>
                (new VehicleModel(),2d), new ArrayList<>()));

        real = m_oDeliveryRunService.getMostEfficientVehicleModel(lst);
        assertEquals(new VehicleModel(),real);

        lst = new ArrayList<>();
        lst.add(new Pair<Pair<VehicleModel,Double>,List<Address>>(new Pair<VehicleModel,Double>
                (new VehicleModel(),5d), new ArrayList<>()));
        lst.add(null);

        real = m_oDeliveryRunService.getMostEfficientVehicleModel(lst);
        assertEquals(new VehicleModel(),real);

        lst = new ArrayList<>();
        lst.add(null);
        lst.add(null);

        real = m_oDeliveryRunService.getMostEfficientVehicleModel(lst);
        assertEquals(null,real);

        real = m_oDeliveryRunService.getMostEfficientVehicleModel(new ArrayList<>());
        assertEquals(null,real);

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

    @Test
    void getDeliveryDB() {
        DeliveryDB expected = m_oDeliveryDB;
        DeliveryDB real = m_oDeliveryRunService.getDeliveryDB();
        assertEquals(expected,real);
    }

    @Test
    void setDeliveryDB() {
        DeliveryDB expected = new DeliveryDB();
        m_oDeliveryRunService.setDeliveryDB(expected);
        DeliveryDB real = m_oDeliveryRunService.getDeliveryDB();
        assertEquals(expected,real);
    }
}