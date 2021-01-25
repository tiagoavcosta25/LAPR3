package lapr.project.model;

import lapr.project.data.DeliveryRunDB;
import lapr.project.data.PharmacyDB;
import lapr.project.data.ProductDB;
import lapr.project.data.VehicleDB;
import lapr.project.graph.map.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class WorldMapTest {

    @InjectMocks
    private WorldMap world;

    @Mock
    private DeliveryRunDB mockDeliveryRunDB;

    @Mock
    private VehicleDB mockVehicleDB;

    @Mock
    private PharmacyDB mockPharmacyDB;

    @BeforeEach
    void setUp() {
        this.world = new WorldMap();
        this.mockDeliveryRunDB = Mockito.mock(DeliveryRunDB.class);
        this.mockVehicleDB = Mockito.mock(VehicleDB.class);
        this.mockPharmacyDB = Mockito.mock(PharmacyDB.class);
        initMocks(this);
    }

    @Test
    void setScooterGraph() {
        Graph<Address, Path> g = new Graph<>(true);
        g.insertVertex(new Address());
        assertNotEquals(g, world.getScooterGraph());

        world.setScooterGraph(g);
        assertEquals(g, world.getScooterGraph());
    }

    @Test
    void getScooterGraph() {
        Graph<Address, Path> g = new Graph<>(true);
        g.insertVertex(new Address());
        assertNotEquals(g, world.getScooterGraph());

        world.setScooterGraph(g);
        assertEquals(g, world.getScooterGraph());
    }

    @Test
    void setDroneGraph() {
        Graph<Address, Path> g = new Graph<>(true);
        g.insertVertex(new Address());
        assertNotEquals(g, world.getDroneGraph());

        world.setDroneGraph(g);
        assertEquals(g, world.getDroneGraph());
    }

    @Test
    void getDroneGraph() {
        Graph<Address, Path> g = new Graph<>(true);
        g.insertVertex(new Address());
        assertNotEquals(g, world.getDroneGraph());

        world.setDroneGraph(g);
        assertEquals(g, world.getDroneGraph());
    }

    @Test
    void createGraph() {
        List<Address> lAddresses = new ArrayList<>();
        Address a1 = new Address(10d, 10d, 2d, "", "", "", "", "");
        Address a2 = new Address(20d, 20d, 4d, "", "", "", "", "");
        lAddresses.add(a1);
        lAddresses.add(a2);
        List<Path> lPaths = new ArrayList<>();
        Path p1 = new Path(10d, 10d, 20d, 20d, "", 1d, 1d, 1d, VehicleType.SCOOTER);
        Path p2 = new Path(10d, 10d, 20d, 20d, "", 1d, 1d, 1d, VehicleType.DRONE);
        lPaths.add(p1);
        lPaths.add(p2);
        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(lAddresses);
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(lPaths);
        world.createGraph();

        Graph<Address, Path> gScooter = new Graph<>(true);
        Graph<Address, Path> gDrone = new Graph<>(true);

        assertNotEquals(gScooter, world.getScooterGraph());
        assertNotEquals(gDrone, world.getDroneGraph());

        gScooter.insertVertex(a1);
        gScooter.insertVertex(a2);
        gScooter.insertEdge(a1, a2, p1, a1.distanceTo(a2));

        gDrone.insertVertex(a1);
        gDrone.insertVertex(a2);
        gDrone.insertEdge(a1, a2, p2, a1.distanceTo(a2));

        assertEquals(gScooter, world.getScooterGraph());
        assertEquals(gDrone, world.getDroneGraph());
    }

    @Test
    void getListOfPaths() {
        List<Address> lAddresses = new ArrayList<>();
        Address a1 = new Address(10d, 10d, 2d, "", "", "", "", "");
        Address a2 = new Address(20d, 20d, 4d, "", "", "", "", "");
        lAddresses.add(a1);
        lAddresses.add(a2);
        List<Path> lPaths = new ArrayList<>();
        Path p1 = new Path(10d, 10d, 20d, 20d, "", 1d, 1d, 1d, VehicleType.SCOOTER);
        Path p2 = new Path(10d, 10d, 20d, 20d, "", 1d, 1d, 1d, VehicleType.DRONE);
        lPaths.add(p1);
        lPaths.add(p2);
        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(lAddresses);
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(lPaths);
        world.createGraph();
        assertNotEquals(world.getListOfPaths(world.getScooterGraph()), lPaths);
        assertNotEquals(world.getListOfPaths(world.getDroneGraph()), lPaths);

        lPaths = new ArrayList<>();
        lPaths.add(p1);
        assertEquals(world.getListOfPaths(world.getScooterGraph()), lPaths);

        lPaths = new ArrayList<>();
        lPaths.add(p2);
        assertEquals(world.getListOfPaths(world.getDroneGraph()), lPaths);

    }

    @Test
    void getPathFromAddresses() {
        List<Address> lAddresses = new ArrayList<>();
        Address a1 = new Address(10d, 10d, 2d, "", "", "", "", "");
        Address a2 = new Address(20d, 20d, 4d, "", "", "", "", "");
        lAddresses.add(a1);
        lAddresses.add(a2);
        List<Path> lPaths = new ArrayList<>();
        Path p1 = new Path(10d, 10d, 20d, 20d, "", 1d, 1d, 1d, VehicleType.SCOOTER);
        Path p2 = new Path(10d, 10d, 20d, 20d, "", 1d, 1d, 1d, VehicleType.DRONE);
        lPaths.add(p1);
        lPaths.add(p2);
        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(lAddresses);
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(lPaths);
        world.createGraph();
        assertNotEquals(world.getPathFromAddresses(world.getScooterGraph(), a1, a2), p2);
        assertNotEquals(world.getPathFromAddresses(world.getDroneGraph(), a1, a2), p1);

        assertEquals(world.getPathFromAddresses(world.getScooterGraph(), a1, a2), p1);
        assertEquals(world.getPathFromAddresses(world.getDroneGraph(), a1, a2), p2);
    }

    @Test
    void checkIfAddressIsPharmacy() {
        Address a1 = new Address(10d, 10d, 2d, "", "", "", "", "");
        Address a2 = new Address(20d, 20d, 4d, "", "", "", "", "");
        Map<Product,Integer> map = new TreeMap<>();
        Pharmacy p1 = new Pharmacy(1,"testName", "email",a1,map);
        List<Pharmacy> phList = new ArrayList<>();
        phList.add(p1);
        when(mockPharmacyDB.getPharmacies()).thenReturn(phList);
        assertFalse(world.checkIfAddressIsPharmacy(a2));
        assertTrue(world.checkIfAddressIsPharmacy(a1));
    }

    @Test
    void checkIfPathGoesByIntermediates() throws NoSuchAlgorithmException {
        List<Order> lst = new ArrayList<>();
        List<CreditCard> order1CC = new ArrayList<>();

        order1CC.add(new CreditCard(9412348029177724L, Date.valueOf("2023-10-01"),123));
        order1CC.add(new CreditCard(5913620127165361L, Date.valueOf("2023-01-01"),929));

        Map<Product, Integer> pharmacy1Products = new TreeMap<>();

        Product vacina = new Product(1,"COVID-19 Vaccine","Keep this product at -20 degrees",40.0,0.5);
        pharmacy1Products.put(vacina,150);
        Product benuron =new Product(2,"Ben-u-ron","Keep this away from children",3.0,1.0);
        pharmacy1Products.put(benuron, 123);
        pharmacy1Products.put(benuron, 123);

        Pharmacy pharmacy = new Pharmacy("Pharmacy Trindade", "info@trindade.com",new Address(41.15227,-8.60929,104.0,"Rua da Trindade","123","4000-123","Porto","Portugal"),pharmacy1Products);


        Map<Product, Integer> orderMapProduct = new TreeMap<>();
        orderMapProduct.put(vacina,2);
        orderMapProduct.put(benuron,5);

        lst.add(new Order(1,95,6,2,new Date(Calendar.getInstance().getTimeInMillis()),
                "order1","ordered",true,
                new Client("fernando", 140803565, "fernando@gmail.com","clpassword",41.14582,-8.61398,87.0,"Clerigos","2esq","4444-111","Porto","Portugal", order1CC),
                pharmacy,
                orderMapProduct));

        List<CreditCard> order2CC = new ArrayList<>();

        order2CC.add(new CreditCard(6997327669161303L,Date.valueOf("2024-09-01"),135));
        order2CC.add(new CreditCard(7734437051662226L,Date.valueOf("2023-02-01"),293));

        Map<Product, Integer> order2MapProduct = new TreeMap<>();
        order2MapProduct.put(vacina,1);
        order2MapProduct.put(benuron,3);

        lst.add(new Order(2,49,3.5,2,new Date(Calendar.getInstance().getTimeInMillis()),
                "order2","ordered",true,
                new Client("manuel", 232134936, "manuel@gmail.com","clpassword",41.14723,-8.60657,91.0,"Majestic","3esq","4333-222","Porto","Portugal", order2CC),
                pharmacy,
                order2MapProduct));

        Address a1 = new Address(41.14723, -8.60657, 2d, "", "", "", "", "");
        Address a2 = new Address(41.14582, -8.61398, 2d, "", "", "", "", "");
        List<Address> path = new ArrayList<>();
        assertFalse(world.checkIfPathGoesByIntermediates(path, lst));
        path.add(a1);
        path.add(a2);
        assertTrue(world.checkIfPathGoesByIntermediates(path, lst));
    }

    @Test
    void pathsWithPharmacies() {
    }

    @Test
    void calculateBestVehicleAndBestPath() {
    }

    @Test
    void calculateBestVehicleForMostEficientPath() {
    }

    @Test
    void getBestPossibleModel() {
    }

    @Test
    void calculatePathCost() {
    }

    @Test
    void calculateMostEfficientPath() {
    }

    @Test
    void calculatePermutationPaths() {
    }

    @Test
    void calculatePermutations() {
    }
}