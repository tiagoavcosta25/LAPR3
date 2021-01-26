package lapr.project.model.service;

import javafx.util.Pair;
import lapr.project.data.DeliveryRunDB;
import lapr.project.data.PharmacyDB;
import lapr.project.data.ProductDB;
import lapr.project.data.VehicleDB;
import lapr.project.graph.map.Graph;
import lapr.project.model.*;
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

class GraphServiceTest {

    @InjectMocks
    private GraphService world;

    @Mock
    private DeliveryRunDB mockDeliveryRunDB;

    @Mock
    private VehicleDB mockVehicleDB;

    @Mock
    private PharmacyDB mockPharmacyDB;

    @BeforeEach
    void setUp() {
        this.world = new GraphService();
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
    void setMoDeliveryRunDB() {
        assertNotEquals(new ProductDB(), world.getMoDeliveryRunDB());

        DeliveryRunDB dvDB = new DeliveryRunDB();
        world.setMoDeliveryRunDB(dvDB);
        assertEquals(dvDB, world.getMoDeliveryRunDB());
    }

    @Test
    void setMoVehicleDB() {
        assertNotEquals(new ProductDB(), world.getMoVehicleDB());

        VehicleDB dvDB = new VehicleDB();
        world.setMoVehicleDB(dvDB);
        assertEquals(dvDB, world.getMoVehicleDB());
    }

    @Test
    void setMoPharmacyDB() {
        assertNotEquals(new ProductDB(), world.getMoPharmacyDB());

        PharmacyDB dvDB = new PharmacyDB();
        world.setMoPharmacyDB(dvDB);
        assertEquals(dvDB, world.getMoPharmacyDB());
    }

    @Test
    void createGraph() {
        Graph<Address, Path> tempGraphScooter = world.getScooterGraph();
        Graph<Address, Path> tempGraphDrone = world.getDroneGraph();
        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(new ArrayList<>());
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(new ArrayList<>());
        world.createGraph();
        assertEquals(tempGraphScooter, world.getScooterGraph());
        assertEquals(tempGraphDrone, world.getDroneGraph());




        List<Address> lAddresses = new ArrayList<>();
        Address a1 = new Address(10d, 10d, 2d, "", "", "", "", "");
        Address a2 = new Address(20d, 20d, 4d, "", "", "", "", "");
        lAddresses.add(a1);
        lAddresses.add(a2);
        List<Path> lPaths = new ArrayList<>();
        Path p1 = new Path(10d, 10d, 20d, 20d, "", 1d, 1d, 1d, VehicleType.SCOOTER);
        Path p2 = new Path(10d, 10d, 20d, 20d, "", 1d, 1d, 1d, VehicleType.DRONE);
        Path p4 = new Path(10d, 10d, 20d, 20d, "", 1d, 1d, 1d, VehicleType.NOTDEFINED);
        Path p3 = new Path(12d, 10d, 11d, 20d, "", 1d, 1d, 1d, VehicleType.SCOOTER);
        Path p5 = new Path(12d, 10d, 11d, 20d, "", 1d, 1d, 1d, VehicleType.DRONE);
        Path p6 = new Path(10d, 10d, 11d, 20d, "", 1d, 1d, 1d, VehicleType.DRONE);
        Path p7 = new Path(10d, 10d, 12d, 20d, "", 1d, 1d, 1d, VehicleType.DRONE);
        Path p8 = new Path(10d, 10d, 20d, 12d, "", 1d, 1d, 1d, VehicleType.DRONE);
        Path p9 = new Path(10d, 14d, 11d, 20d, "", 1d, 1d, 1d, VehicleType.SCOOTER);


        lPaths.add(p1);
        lPaths.add(p2);
        lPaths.add(p3);
        lPaths.add(p4);
        lPaths.add(p5);
        lPaths.add(p6);
        lPaths.add(p7);
        lPaths.add(p8);
        lPaths.add(p9);
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
        Path p3 = new Path(11d, 10d, 20d, 20d, "", 1d, 1d, 1d, VehicleType.NOTDEFINED);
        lPaths.add(p1);
        lPaths.add(p2);
        lPaths.add(p3);
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
        Address a3 = new Address(11d, 10d, 4d, "", "", "", "", "");
        Address a4 = new Address(10d, 11d, 4d, "", "", "", "", "");
        Address a5 = new Address(21d, 20d, 4d, "", "", "", "", "");
        Address a6 = new Address(20d, 22d, 4d, "", "", "", "", "");

        lAddresses.add(a1);
        lAddresses.add(a2);
        assertNull(this.world.getPathFromAddresses(world.getScooterGraph(), a1, a2));


        List<Path> lPaths = new ArrayList<>();
        Path p1 = new Path(10d, 10d, 20d, 20d, "", 1d, 1d, 1d, VehicleType.SCOOTER);
        Path p3 = new Path(20d, 20d, 10d, 10d, "", 1d, 1d, 1d, VehicleType.SCOOTER);

        Path p2 = new Path(10d, 10d, 20d, 20d, "", 1d, 1d, 1d, VehicleType.DRONE);
        lPaths.add(p1);
        lPaths.add(p2);
        lPaths.add(p3);
        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(lAddresses);
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(lPaths);
        world.createGraph();
        assertNotEquals(world.getPathFromAddresses(world.getScooterGraph(), a1, a2), p2);
        assertNotEquals(world.getPathFromAddresses(world.getDroneGraph(), a1, a2), p1);
        assertNotEquals(world.getPathFromAddresses(world.getScooterGraph(), a1, a2), p3);

        assertNotEquals(world.getPathFromAddresses(world.getScooterGraph(), a3, a2), p1);
        assertNotEquals(world.getPathFromAddresses(world.getScooterGraph(), a4, a2), p1);
        assertNotEquals(world.getPathFromAddresses(world.getScooterGraph(), a1, a5), p1);
        assertNotEquals(world.getPathFromAddresses(world.getScooterGraph(), a1, a6), p1);


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
    void checkIfInList3Times() {
        List<Address> addresses = new ArrayList<>();
        Address a1 = new Address(20d, 20d, 4d, "", "", "", "", "");
        Address a2 = new Address(30d, 20d, 4d, "", "", "", "", "");
        assertFalse(world.checkIfInListThreeTimes(a1, addresses));

        addresses.add(a1);
        assertFalse(world.checkIfInListThreeTimes(a1, addresses));

        addresses.add(a1);
        addresses.add(a1);
        assertTrue(world.checkIfInListThreeTimes(a1, addresses));

        addresses = new ArrayList<>();
        addresses.add(a2);
        addresses.add(a1);
        addresses.add(a1);
        addresses.add(a1);
        assertTrue(world.checkIfInListThreeTimes(a1, addresses));

        assertFalse(world.checkIfInListThreeTimes(a2, addresses));
    }

    //TODO:ACABAR ESTE
    @Test
    void pathsWithPharmacies() throws NoSuchAlgorithmException {
        Address trindade = new Address();
        trindade.setStreetName("Pharmacy Trindade");
        trindade.setLatitude(41.15227);
        trindade.setLongitude(-8.60929);
        trindade.setAltitude(104d);

        Address saBandeira = new Address();
        saBandeira.setStreetName("Pharmacy Sa da Bandeira");
        saBandeira.setLatitude(40.741895);
        saBandeira.setLongitude(-7.989308);
        saBandeira.setAltitude(10d);

        Address casteloQueijo = new Address();
        casteloQueijo.setStreetName("Pharmacy Castelo do Queijo");
        casteloQueijo.setLatitude(41.16875);
        casteloQueijo.setLongitude(-8.68995);
        casteloQueijo.setAltitude(4d);

        Address clerigos = new Address();
        clerigos.setStreetName("Clerigos");
        clerigos.setLatitude(41.14582);
        clerigos.setLongitude(-8.61398);
        clerigos.setAltitude(87.0);

        Address majestic = new Address();
        majestic.setStreetName("Majestic");
        majestic.setLatitude(41.14723);
        majestic.setLongitude(-8.60657);
        majestic.setAltitude(91.0);

        Address bolhao = new Address();
        bolhao.setStreetName("Bolhao");
        bolhao.setLatitude(41.14871);
        bolhao.setLongitude(-8.60746);
        bolhao.setAltitude(87.0);

        Address se = new Address();
        se.setStreetName("Sé");
        se.setLatitude(41.14331);
        se.setLongitude(-8.60914);
        se.setAltitude(82.0);

        Address caisDaRibeira = new Address();
        caisDaRibeira.setStreetName("Cais da Ribeira");
        caisDaRibeira.setLatitude(41.14063);
        caisDaRibeira.setLongitude(-8.61118);
        caisDaRibeira.setAltitude(25.0);

        Path p1 = new Path( 41.15227, -8.60929,40.741895, -7.989308, "trindade - sa", 1,
                1, 1, VehicleType.SCOOTER);

        Path p2 = new Path(40.741895, -7.989308, 41.15227, -8.60929, "sa - trindade", 1,
                1, 1, VehicleType.SCOOTER);

        Path p3 = new Path(40.741895, -7.989308, 41.16875, -8.68995, "sa - quejo", 1,
                1, 1, VehicleType.SCOOTER);

        Path p4 = new Path( 41.16875, -8.68995,40.741895, -7.989308, "quejo - sa", 1,
                1, 1, VehicleType.SCOOTER);

        Path p5 = new Path( 41.16875, -8.68995,41.14582, -8.61398, "quejo - clerigos", 1,
                1, 1, VehicleType.SCOOTER);

        Path p6 = new Path( 41.14582, -8.61398, 41.16875, -8.68995, "clerigos - queijo", 1,
                1, 1, VehicleType.SCOOTER);

        Path p7 = new Path( 41.14582, -8.61398, 41.14723, -8.60657, "clerigos - majestic", 1,
                1, 1, VehicleType.SCOOTER);

        Path p8 = new Path( 41.14723, -8.60657,41.14582, -8.61398,  "majestic - clerigos", 1,
                1, 1, VehicleType.SCOOTER);

        Path p9 = new Path( 41.14723, -8.60657,41.14871, -8.60746,  "majestic - bolhao", 1,
                1, 1, VehicleType.SCOOTER);

        Path p10 = new Path( 41.14871, -8.60746,41.14723, -8.60657,  "bolhao - majestic", 1,
                1, 1, VehicleType.SCOOTER);

        Path p11 = new Path( 41.14871, -8.60746,41.14331, -8.60914,  "bolhao - se", 1,
                1, 1, VehicleType.SCOOTER);

        Path p12 = new Path( 41.14331, -8.60914,41.14871, -8.60746,  "se - bolhao", 1,
                1, 1, VehicleType.SCOOTER);

        Path p13 = new Path( 41.14331, -8.60914,41.14063, -8.61118,  "se - cais", 1,
                1, 1, VehicleType.SCOOTER);

        Path p14 = new Path( 41.14063, -8.61118,41.14331, -8.60914,  "cais - se", 1,
                1, 1, VehicleType.SCOOTER);

        Path p15 = new Path( 41.14063, -8.61118,41.15227, -8.60929,  "cais - trindade", 1,
                1, 1, VehicleType.SCOOTER);

        List<Address> lstA = new LinkedList<>();
        lstA.add(trindade);
        lstA.add(saBandeira);
        lstA.add(casteloQueijo);
        lstA.add(clerigos);
        lstA.add(majestic);
        lstA.add(bolhao);
        lstA.add(se);
        lstA.add(caisDaRibeira);

        List<Path> lstP = new LinkedList<>();
        lstP.add(p1);
        lstP.add(p2);
        lstP.add(p3);
        lstP.add(p4);
        lstP.add(p5);
        lstP.add(p6);
        lstP.add(p7);
        lstP.add(p8);
        lstP.add(p9);
        lstP.add(p10);
        lstP.add(p11);
        lstP.add(p12);
        lstP.add(p13);
        lstP.add(p14);
        lstP.add(p15);



        //ORDER
        Pharmacy oPharmacy = new Pharmacy("Pharmacy Trindade","info@trindade.com",new Address(41.15227d,-8.60929d,104d,
                "Rua da Trindade","123","4000-123","Porto","Portugal"));
        List<Order> lstOrders = new ArrayList<>();
        lstOrders.add(new Order("Para Presente, Enviar Embrulhado.", true, new Client("", 123456789, "fernando@gmail.com", "pass",
                41.14582d,-8.61398d,87.0d,"Clerigos","2esq","4444-111","Porto","Portugal", new ArrayList<>()),
                oPharmacy, new TreeMap<>()));

        lstOrders.add(new Order("Para Presente, Enviar Embrulhado.", true, new Client("", 123456781, "joana@gmail.com", "pass",
                41.14063d,-8.61118d,25.0d,"Cais da Ribeira","3esq","4000-555","Porto","Portugal", new ArrayList<>()),
                oPharmacy, new TreeMap<>()));
        //ORDER

        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(lstA);
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(lstP);

        //world.createGraph();


    }

    @Test
    void calculateBestVehicleAndBestPath() {
    }

    @Test
    void calculateBestVehicleForMostEficientPath() throws NoSuchAlgorithmException {
        Address trindade = new Address();
        trindade.setStreetName("Pharmacy Trindade");
        trindade.setLatitude(41.15227);
        trindade.setLongitude(-8.60929);
        trindade.setAltitude(104d);

        Address saBandeira = new Address();
        saBandeira.setStreetName("Pharmacy Sa da Bandeira");
        saBandeira.setLatitude(40.741895);
        saBandeira.setLongitude(-7.989308);
        saBandeira.setAltitude(10d);

        Address casteloQueijo = new Address();
        casteloQueijo.setStreetName("Pharmacy Castelo do Queijo");
        casteloQueijo.setLatitude(41.16875);
        casteloQueijo.setLongitude(-8.68995);
        casteloQueijo.setAltitude(4d);

        Address clerigos = new Address();
        clerigos.setStreetName("Clerigos");
        clerigos.setLatitude(41.14582);
        clerigos.setLongitude(-8.61398);
        clerigos.setAltitude(87.0);

        Address majestic = new Address();
        majestic.setStreetName("Majestic");
        majestic.setLatitude(41.14723);
        majestic.setLongitude(-8.60657);
        majestic.setAltitude(91.0);

        Address bolhao = new Address();
        bolhao.setStreetName("Bolhao");
        bolhao.setLatitude(41.14871);
        bolhao.setLongitude(-8.60746);
        bolhao.setAltitude(87.0);

        Address se = new Address();
        se.setStreetName("Sé");
        se.setLatitude(41.14331);
        se.setLongitude(-8.60914);
        se.setAltitude(82.0);

        Address caisDaRibeira = new Address();
        caisDaRibeira.setStreetName("Cais da Ribeira");
        caisDaRibeira.setLatitude(41.14063);
        caisDaRibeira.setLongitude(-8.61118);
        caisDaRibeira.setAltitude(25.0);

        Path p1 = new Path( 41.15227, -8.60929,40.741895, -7.989308, "trindade - sa", 1,
                1, 1, VehicleType.SCOOTER);

        Path p2 = new Path(40.741895, -7.989308, 41.15227, -8.60929, "sa - trindade", 1,
                1, 1, VehicleType.SCOOTER);

        Path p3 = new Path(40.741895, -7.989308, 41.16875, -8.68995, "sa - quejo", 1,
                1, 1, VehicleType.SCOOTER);

        Path p4 = new Path( 41.16875, -8.68995,40.741895, -7.989308, "quejo - sa", 1,
                1, 1, VehicleType.SCOOTER);

        Path p5 = new Path( 41.16875, -8.68995,41.14582, -8.61398, "quejo - clerigos", 1,
                1, 1, VehicleType.SCOOTER);

        Path p6 = new Path( 41.14582, -8.61398, 41.16875, -8.68995, "clerigos - queijo", 1,
                1, 1, VehicleType.SCOOTER);

        Path p7 = new Path( 41.14582, -8.61398, 41.14723, -8.60657, "clerigos - majestic", 1,
                1, 1, VehicleType.SCOOTER);

        Path p8 = new Path( 41.14723, -8.60657,41.14582, -8.61398,  "majestic - clerigos", 1,
                1, 1, VehicleType.SCOOTER);

        Path p9 = new Path( 41.14723, -8.60657,41.14871, -8.60746,  "majestic - bolhao", 1,
                1, 1, VehicleType.SCOOTER);

        Path p10 = new Path( 41.14871, -8.60746,41.14723, -8.60657,  "bolhao - majestic", 1,
                1, 1, VehicleType.SCOOTER);

        Path p11 = new Path( 41.14871, -8.60746,41.14331, -8.60914,  "bolhao - se", 1,
                1, 1, VehicleType.SCOOTER);

        Path p12 = new Path( 41.14331, -8.60914,41.14871, -8.60746,  "se - bolhao", 1,
                1, 1, VehicleType.SCOOTER);

        Path p13 = new Path( 41.14331, -8.60914,41.14063, -8.61118,  "se - cais", 1,
                1, 1, VehicleType.SCOOTER);

        Path p14 = new Path( 41.14063, -8.61118,41.14331, -8.60914,  "cais - se", 1,
                1, 1, VehicleType.SCOOTER);

        Path p15 = new Path( 41.14063, -8.61118,41.15227, -8.60929,  "cais - trindade", 1,
                1, 1, VehicleType.SCOOTER);


        Path p21 = new Path( 41.15227, -8.60929,40.741895, -7.989308, "trindade - sa", 1,
                1, 1, VehicleType.DRONE);

        Path p22 = new Path(40.741895, -7.989308, 41.15227, -8.60929, "sa - trindade", 1,
                1, 1, VehicleType.DRONE);

        Path p23 = new Path(40.741895, -7.989308, 41.16875, -8.68995, "sa - quejo", 1,
                1, 1, VehicleType.DRONE);

        Path p24 = new Path( 41.16875, -8.68995,40.741895, -7.989308, "quejo - sa", 1,
                1, 1, VehicleType.DRONE);

        Path p25 = new Path( 41.16875, -8.68995,41.14582, -8.61398, "quejo - clerigos", 1,
                1, 1, VehicleType.DRONE);

        Path p26 = new Path( 41.14582, -8.61398, 41.16875, -8.68995, "clerigos - queijo", 1,
                1, 1, VehicleType.DRONE);

        Path p27 = new Path( 41.14582, -8.61398, 41.14723, -8.60657, "clerigos - majestic", 1,
                1, 1, VehicleType.DRONE);

        Path p28 = new Path( 41.14723, -8.60657,41.14582, -8.61398,  "majestic - clerigos", 1,
                1, 1, VehicleType.DRONE);

        Path p29 = new Path( 41.14723, -8.60657,41.14871, -8.60746,  "majestic - bolhao", 1,
                1, 1, VehicleType.DRONE);

        Path p30 = new Path( 41.14871, -8.60746,41.14723, -8.60657,  "bolhao - majestic", 1,
                1, 1, VehicleType.DRONE);

        Path p31 = new Path( 41.14871, -8.60746,41.14331, -8.60914,  "bolhao - se", 1,
                1, 1, VehicleType.DRONE);

        Path p32 = new Path( 41.14331, -8.60914,41.14871, -8.60746,  "se - bolhao", 1,
                1, 1, VehicleType.DRONE);

        Path p33 = new Path( 41.14331, -8.60914,41.14063, -8.61118,  "se - cais", 1,
                1, 1, VehicleType.DRONE);

        Path p34 = new Path( 41.14063, -8.61118,41.14331, -8.60914,  "cais - se", 1,
                1, 1, VehicleType.DRONE);

        Path p35 = new Path( 41.14063, -8.61118,41.15227, -8.60929,  "cais - trindade", 1,
                1, 1, VehicleType.DRONE);



        List<Address> lstA = new LinkedList<>();
        lstA.add(trindade);
        lstA.add(saBandeira);
        lstA.add(casteloQueijo);
        lstA.add(clerigos);
        lstA.add(majestic);
        lstA.add(bolhao);
        lstA.add(se);
        lstA.add(caisDaRibeira);

        List<Path> lstP = new LinkedList<>();
        lstP.add(p1);
        lstP.add(p2);
        lstP.add(p3);
        lstP.add(p4);
        lstP.add(p5);
        lstP.add(p6);
        lstP.add(p7);
        lstP.add(p8);
        lstP.add(p9);
        lstP.add(p10);
        lstP.add(p11);
        lstP.add(p12);
        lstP.add(p13);
        lstP.add(p14);
        lstP.add(p15);

        lstP.add(p21);
        lstP.add(p22);
        lstP.add(p23);
        lstP.add(p24);
        lstP.add(p25);
        lstP.add(p26);
        lstP.add(p27);
        lstP.add(p28);
        lstP.add(p29);
        lstP.add(p30);
        lstP.add(p31);
        lstP.add(p32);
        lstP.add(p33);
        lstP.add(p34);
        lstP.add(p35);

        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(lstA);
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(lstP);

        world.createGraph();

        List<Address> pathList = new LinkedList<>();
        pathList.add(trindade);
        pathList.add(saBandeira);
        pathList.add(casteloQueijo);
        pathList.add(clerigos);
        pathList.add(majestic);
        pathList.add(bolhao);
        pathList.add(se);
        pathList.add(caisDaRibeira);
        pathList.add(trindade);


        //ORDER
        Pharmacy oPharmacy = new Pharmacy("Pharmacy Trindade","info@trindade.com",new Address(41.15227d,-8.60929d,104d,
                "Rua da Trindade","123","4000-123","Porto","Portugal"));
        List<Order> lstOrders = new ArrayList<>();
        lstOrders.add(new Order("Para Presente, Enviar Embrulhado.", true, new Client("", 123456789, "fernando@gmail.com", "pass",
                41.14582d,-8.61398d,87.0d,"Clerigos","2esq","4444-111","Porto","Portugal", new ArrayList<>()),
                oPharmacy, new TreeMap<>()));

        lstOrders.add(new Order("Para Presente, Enviar Embrulhado.", true, new Client("", 123456781, "joana@gmail.com", "pass",
                41.14063d,-8.61118d,25.0d,"Cais da Ribeira","3esq","4000-555","Porto","Portugal", new ArrayList<>()),
                oPharmacy, new TreeMap<>()));
        //ORDER

        Battery b1 = new Battery(1, 40000, 40, 100);

        Battery b2 = new Battery(2, -1, -4, 100);

        Battery b3 = new Battery(3, 40, 40, 0);

        List<VehicleModel> lstScooter = new ArrayList<>();
        List<VehicleModel> lstDrone = new ArrayList<>();

        VehicleModel scooter1 = new VehicleModel(1, "scooter1", 400, 10, 400, b1, VehicleType.SCOOTER);
        VehicleModel scooter2 = new VehicleModel(2, "scooter2", 400, 10, 400, b2, VehicleType.SCOOTER);
        VehicleModel scooter3 = new VehicleModel(3, "scooter3", 400, 10, 400, b3, VehicleType.SCOOTER);
        VehicleModel scooter4 = new VehicleModel(4, "scooter4", 400, 10, 0, b1, VehicleType.SCOOTER);
        VehicleModel scooter5 = new VehicleModel(5, "scooter5", 400, 4000, 400, b1, VehicleType.SCOOTER);
        VehicleModel scooter6 = new VehicleModel(6, "scooter6", 1, 10, 400, b1, VehicleType.SCOOTER);

        VehicleModel drone1 = new VehicleModel(1, "drone1", 400, 10, 400, b1, VehicleType.DRONE);
        VehicleModel drone2 = new VehicleModel(2, "drone2", 400, 10, 400, b2, VehicleType.DRONE);
        VehicleModel drone3 = new VehicleModel(3, "drone3", 400, 10, 400, b3, VehicleType.DRONE);
        VehicleModel drone4 = new VehicleModel(4, "drone4", 400, 10, 0, b1, VehicleType.DRONE);
        VehicleModel drone5 = new VehicleModel(5, "drone5", 400, 4000, 400, b1, VehicleType.DRONE);
        VehicleModel drone6 = new VehicleModel(6, "drone6", 1, 10, 400, b1, VehicleType.DRONE);

        lstScooter.add(scooter1);
        lstScooter.add(scooter2);
        lstScooter.add(scooter3);
        lstScooter.add(scooter4);
        lstScooter.add(scooter5);
        lstScooter.add(scooter6);
        lstDrone.add(drone1);
        lstDrone.add(drone2);
        lstDrone.add(drone3);
        lstDrone.add(drone4);
        lstDrone.add(drone5);
        lstDrone.add(drone6);
        when(mockVehicleDB.getEnergyByVehicleModel(1)).thenReturn(10000000d);
        when(mockVehicleDB.getEnergyByVehicleModel(2)).thenReturn(10000000d);
        when(mockVehicleDB.getEnergyByVehicleModel(3)).thenReturn(10000000d);
        when(mockVehicleDB.getEnergyByVehicleModel(4)).thenReturn(10000000d);
        when(mockVehicleDB.getEnergyByVehicleModel(5)).thenReturn(10000000d);
        when(mockVehicleDB.getEnergyByVehicleModel(6)).thenReturn(10000000d);
        when(mockDeliveryRunDB.checkValidChargingSlot(trindade)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(saBandeira)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(casteloQueijo)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(clerigos)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(majestic)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(bolhao)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(se)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(caisDaRibeira)).thenReturn(true);

        List<Address> path = new ArrayList<>();
        path.add(majestic);
        path.add(clerigos);
        path.add(majestic);
        path.add(bolhao);
        path.add(se);
        path.add(caisDaRibeira);
        path.add(se);
        path.add(bolhao);
        path.add(majestic);

        List<Pair<Pair<VehicleModel, Double>, List<Address>>> expResult = new ArrayList<>();
        Pair<Pair<VehicleModel, Double>, List<Address>> exp1 = new Pair<>(new Pair<>(scooter1, 0.8483898598685257), path);
        path = new ArrayList<>();
        path.add(majestic);
        path.add(clerigos);
        path.add(majestic);
        path.add(bolhao);
        path.add(se);
        path.add(caisDaRibeira);
        path.add(se);
        path.add(bolhao);
        path.add(majestic);

        Pair<Pair<VehicleModel, Double>, List<Address>> exp2 = new Pair<>(new Pair<>(drone1, 0.07881060321902969), path);
        expResult.add(exp1);
        expResult.add(exp2);

        Pharmacy ph1 = new Pharmacy(1, "Ph1", "a1@.com", majestic, new TreeMap<>());

        List<Pair<Pair<VehicleModel, Double>, List<Address>>> result = world.calculateBestVehicleForMostEficientPath(lstOrders, ph1, lstScooter, lstDrone);
        assertEquals(expResult, result);

        when(mockVehicleDB.getEnergyByVehicleModel(1)).thenReturn(0d);
        when(mockVehicleDB.getEnergyByVehicleModel(2)).thenReturn(1001d);
        when(mockVehicleDB.getEnergyByVehicleModel(3)).thenReturn(3000d);
        when(mockVehicleDB.getEnergyByVehicleModel(4)).thenReturn(10000d);
        when(mockVehicleDB.getEnergyByVehicleModel(5)).thenReturn(10000000d);
        when(mockVehicleDB.getEnergyByVehicleModel(6)).thenReturn(10000000d);




        when(mockVehicleDB.getEnergyByVehicleModel(1)).thenReturn(0d);
        when(mockVehicleDB.getEnergyByVehicleModel(2)).thenReturn(0d);
        when(mockVehicleDB.getEnergyByVehicleModel(3)).thenReturn(0d);
        when(mockVehicleDB.getEnergyByVehicleModel(4)).thenReturn(0d);
        when(mockVehicleDB.getEnergyByVehicleModel(5)).thenReturn(0d);
        when(mockVehicleDB.getEnergyByVehicleModel(6)).thenReturn(0d);

    }

    @Test
    void getBestPossibleModel() throws NoSuchAlgorithmException {
        Address trindade = new Address();
        trindade.setStreetName("Pharmacy Trindade");
        trindade.setLatitude(41.15227);
        trindade.setLongitude(-8.60929);
        trindade.setAltitude(104d);

        Address saBandeira = new Address();
        saBandeira.setStreetName("Pharmacy Sa da Bandeira");
        saBandeira.setLatitude(40.741895);
        saBandeira.setLongitude(-7.989308);
        saBandeira.setAltitude(10d);

        Address casteloQueijo = new Address();
        casteloQueijo.setStreetName("Pharmacy Castelo do Queijo");
        casteloQueijo.setLatitude(41.16875);
        casteloQueijo.setLongitude(-8.68995);
        casteloQueijo.setAltitude(4d);

        Address clerigos = new Address();
        clerigos.setStreetName("Clerigos");
        clerigos.setLatitude(41.14582);
        clerigos.setLongitude(-8.61398);
        clerigos.setAltitude(87.0);

        Address majestic = new Address();
        majestic.setStreetName("Majestic");
        majestic.setLatitude(41.14723);
        majestic.setLongitude(-8.60657);
        majestic.setAltitude(91.0);

        Address bolhao = new Address();
        bolhao.setStreetName("Bolhao");
        bolhao.setLatitude(41.14871);
        bolhao.setLongitude(-8.60746);
        bolhao.setAltitude(87.0);

        Address se = new Address();
        se.setStreetName("Sé");
        se.setLatitude(41.14331);
        se.setLongitude(-8.60914);
        se.setAltitude(82.0);

        Address caisDaRibeira = new Address();
        caisDaRibeira.setStreetName("Cais da Ribeira");
        caisDaRibeira.setLatitude(41.14063);
        caisDaRibeira.setLongitude(-8.61118);
        caisDaRibeira.setAltitude(25.0);

        Path p1 = new Path( 41.15227, -8.60929,40.741895, -7.989308, "trindade - sa", 1,
                1, 1, VehicleType.SCOOTER);

        Path p2 = new Path(40.741895, -7.989308, 41.15227, -8.60929, "sa - trindade", 1,
                1, 1, VehicleType.SCOOTER);

        Path p3 = new Path(40.741895, -7.989308, 41.16875, -8.68995, "sa - quejo", 1,
                1, 1, VehicleType.SCOOTER);

        Path p4 = new Path( 41.16875, -8.68995,40.741895, -7.989308, "quejo - sa", 1,
                1, 1, VehicleType.SCOOTER);

        Path p5 = new Path( 41.16875, -8.68995,41.14582, -8.61398, "quejo - clerigos", 1,
                1, 1, VehicleType.SCOOTER);

        Path p6 = new Path( 41.14582, -8.61398, 41.16875, -8.68995, "clerigos - queijo", 1,
                1, 1, VehicleType.SCOOTER);

        Path p7 = new Path( 41.14582, -8.61398, 41.14723, -8.60657, "clerigos - majestic", 1,
                1, 1, VehicleType.SCOOTER);

        Path p8 = new Path( 41.14723, -8.60657,41.14582, -8.61398,  "majestic - clerigos", 1,
                1, 1, VehicleType.SCOOTER);

        Path p9 = new Path( 41.14723, -8.60657,41.14871, -8.60746,  "majestic - bolhao", 1,
                1, 1, VehicleType.SCOOTER);

        Path p10 = new Path( 41.14871, -8.60746,41.14723, -8.60657,  "bolhao - majestic", 1,
                1, 1, VehicleType.SCOOTER);

        Path p11 = new Path( 41.14871, -8.60746,41.14331, -8.60914,  "bolhao - se", 1,
                1, 1, VehicleType.SCOOTER);

        Path p12 = new Path( 41.14331, -8.60914,41.14871, -8.60746,  "se - bolhao", 1,
                1, 1, VehicleType.SCOOTER);

        Path p13 = new Path( 41.14331, -8.60914,41.14063, -8.61118,  "se - cais", 1,
                1, 1, VehicleType.SCOOTER);

        Path p14 = new Path( 41.14063, -8.61118,41.14331, -8.60914,  "cais - se", 1,
                1, 1, VehicleType.SCOOTER);

        Path p15 = new Path( 41.14063, -8.61118,41.15227, -8.60929,  "cais - trindade", 1,
                1, 1, VehicleType.SCOOTER);


        Path p21 = new Path( 41.15227, -8.60929,40.741895, -7.989308, "trindade - sa", 1,
                1, 1, VehicleType.DRONE);

        Path p22 = new Path(40.741895, -7.989308, 41.15227, -8.60929, "sa - trindade", 1,
                1, 1, VehicleType.DRONE);

        Path p23 = new Path(40.741895, -7.989308, 41.16875, -8.68995, "sa - quejo", 1,
                1, 1, VehicleType.DRONE);

        Path p24 = new Path( 41.16875, -8.68995,40.741895, -7.989308, "quejo - sa", 1,
                1, 1, VehicleType.DRONE);

        Path p25 = new Path( 41.16875, -8.68995,41.14582, -8.61398, "quejo - clerigos", 1,
                1, 1, VehicleType.DRONE);

        Path p26 = new Path( 41.14582, -8.61398, 41.16875, -8.68995, "clerigos - queijo", 1,
                1, 1, VehicleType.DRONE);

        Path p27 = new Path( 41.14582, -8.61398, 41.14723, -8.60657, "clerigos - majestic", 1,
                1, 1, VehicleType.DRONE);

        Path p28 = new Path( 41.14723, -8.60657,41.14582, -8.61398,  "majestic - clerigos", 1,
                1, 1, VehicleType.DRONE);

        Path p29 = new Path( 41.14723, -8.60657,41.14871, -8.60746,  "majestic - bolhao", 1,
                1, 1, VehicleType.DRONE);

        Path p30 = new Path( 41.14871, -8.60746,41.14723, -8.60657,  "bolhao - majestic", 1,
                1, 1, VehicleType.DRONE);

        Path p31 = new Path( 41.14871, -8.60746,41.14331, -8.60914,  "bolhao - se", 1,
                1, 1, VehicleType.DRONE);

        Path p32 = new Path( 41.14331, -8.60914,41.14871, -8.60746,  "se - bolhao", 1,
                1, 1, VehicleType.DRONE);

        Path p33 = new Path( 41.14331, -8.60914,41.14063, -8.61118,  "se - cais", 1,
                1, 1, VehicleType.DRONE);

        Path p34 = new Path( 41.14063, -8.61118,41.14331, -8.60914,  "cais - se", 1,
                1, 1, VehicleType.DRONE);

        Path p35 = new Path( 41.14063, -8.61118,41.15227, -8.60929,  "cais - trindade", 1,
                1, 1, VehicleType.DRONE);



        List<Address> lstA = new LinkedList<>();
        lstA.add(trindade);
        lstA.add(saBandeira);
        lstA.add(casteloQueijo);
        lstA.add(clerigos);
        lstA.add(majestic);
        lstA.add(bolhao);
        lstA.add(se);
        lstA.add(caisDaRibeira);

        List<Path> lstP = new LinkedList<>();
        lstP.add(p1);
        lstP.add(p2);
        lstP.add(p3);
        lstP.add(p4);
        lstP.add(p5);
        lstP.add(p6);
        lstP.add(p7);
        lstP.add(p8);
        lstP.add(p9);
        lstP.add(p10);
        lstP.add(p11);
        lstP.add(p12);
        lstP.add(p13);
        lstP.add(p14);
        lstP.add(p15);

        lstP.add(p21);
        lstP.add(p22);
        lstP.add(p23);
        lstP.add(p24);
        lstP.add(p25);
        lstP.add(p26);
        lstP.add(p27);
        lstP.add(p28);
        lstP.add(p29);
        lstP.add(p30);
        lstP.add(p31);
        lstP.add(p32);
        lstP.add(p33);
        lstP.add(p34);
        lstP.add(p35);

        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(lstA);
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(lstP);

        world.createGraph();

        List<Address> pathList = new LinkedList<>();
        pathList.add(trindade);
        pathList.add(saBandeira);
        pathList.add(casteloQueijo);
        pathList.add(clerigos);
        pathList.add(majestic);
        pathList.add(bolhao);
        pathList.add(se);
        pathList.add(caisDaRibeira);
        pathList.add(trindade);


        //ORDER
        Pharmacy oPharmacy = new Pharmacy("Pharmacy Trindade","info@trindade.com",new Address(41.15227d,-8.60929d,104d,
                "Rua da Trindade","123","4000-123","Porto","Portugal"));
        List<Order> lstOrders = new ArrayList<>();
        lstOrders.add(new Order("Para Presente, Enviar Embrulhado.", true, new Client("", 123456789, "fernando@gmail.com", "pass",
                41.14582d,-8.61398d,87.0d,"Clerigos","2esq","4444-111","Porto","Portugal", new ArrayList<>()),
                oPharmacy, new TreeMap<>()));

        lstOrders.add(new Order("Para Presente, Enviar Embrulhado.", true, new Client("", 123456781, "joana@gmail.com", "pass",
                41.14063d,-8.61118d,25.0d,"Cais da Ribeira","3esq","4000-555","Porto","Portugal", new ArrayList<>()),
                oPharmacy, new TreeMap<>()));
        //ORDER

        Battery b1 = new Battery(1, 40000, 40, 100);

        Battery b2 = new Battery(2, -1, -4, 100);

        Battery b3 = new Battery(3, 40, 40, 0);

        List<VehicleModel> lstModel = new ArrayList<>();

        VehicleModel scooter1 = new VehicleModel(1, "scooter1", 400, 10, 400, b1, VehicleType.SCOOTER);
        VehicleModel scooter2 = new VehicleModel(2, "scooter2", 400, 10, 400, b2, VehicleType.SCOOTER);
        VehicleModel scooter3 = new VehicleModel(3, "scooter3", 400, 10, 400, b3, VehicleType.SCOOTER);
        VehicleModel scooter4 = new VehicleModel(4, "scooter4", 400, 10, 0, b1, VehicleType.SCOOTER);
        VehicleModel scooter5 = new VehicleModel(5, "scooter5", 400, 4000, 400, b1, VehicleType.SCOOTER);
        VehicleModel scooter6 = new VehicleModel(6, "scooter6", 1, 10, 400, b1, VehicleType.SCOOTER);

        VehicleModel drone1 = new VehicleModel(1, "drone1", 400, 10, 400, b1, VehicleType.DRONE);
        VehicleModel drone2 = new VehicleModel(2, "drone2", 400, 10, 400, b2, VehicleType.DRONE);
        VehicleModel drone3 = new VehicleModel(3, "drone3", 400, 10, 400, b3, VehicleType.DRONE);
        VehicleModel drone4 = new VehicleModel(4, "drone4", 400, 10, 0, b1, VehicleType.DRONE);
        VehicleModel drone5 = new VehicleModel(5, "drone5", 400, 4000, 400, b1, VehicleType.DRONE);
        VehicleModel drone6 = new VehicleModel(6, "drone6", 1, 10, 400, b1, VehicleType.DRONE);

        lstModel.add(scooter1);
        lstModel.add(scooter2);
        lstModel.add(scooter3);
        lstModel.add(scooter4);
        lstModel.add(scooter5);
        lstModel.add(scooter6);
        lstModel.add(drone1);
        lstModel.add(drone2);
        lstModel.add(drone3);
        lstModel.add(drone4);
        lstModel.add(drone5);
        lstModel.add(drone6);
        when(mockVehicleDB.getEnergyByVehicleModel(1)).thenReturn(10000000d);
        when(mockVehicleDB.getEnergyByVehicleModel(2)).thenReturn(10000000d);
        when(mockVehicleDB.getEnergyByVehicleModel(3)).thenReturn(10000000d);
        when(mockVehicleDB.getEnergyByVehicleModel(4)).thenReturn(10000000d);
        when(mockVehicleDB.getEnergyByVehicleModel(5)).thenReturn(10000000d);
        when(mockVehicleDB.getEnergyByVehicleModel(6)).thenReturn(10000000d);
        when(mockDeliveryRunDB.checkValidChargingSlot(trindade)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(saBandeira)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(casteloQueijo)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(clerigos)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(majestic)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(bolhao)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(se)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(caisDaRibeira)).thenReturn(true);
        Pair<VehicleModel, Double> expResult = new Pair<>(drone1, 1.1190973594436533);
        assertEquals(expResult, world.getBestPossibleModel(lstModel, pathList, lstOrders));
        when(mockVehicleDB.getEnergyByVehicleModel(1)).thenReturn(0d);
        when(mockVehicleDB.getEnergyByVehicleModel(2)).thenReturn(1001d);
        when(mockVehicleDB.getEnergyByVehicleModel(3)).thenReturn(3000d);
        when(mockVehicleDB.getEnergyByVehicleModel(4)).thenReturn(10000d);
        when(mockVehicleDB.getEnergyByVehicleModel(5)).thenReturn(10000000d);
        when(mockVehicleDB.getEnergyByVehicleModel(6)).thenReturn(10000000d);
        assertNotEquals(expResult, world.getBestPossibleModel(lstModel, pathList, lstOrders));
        expResult = new Pair<>(drone3, 1.1190973594436533);
        assertEquals(expResult, world.getBestPossibleModel(lstModel, pathList, lstOrders));

        when(mockVehicleDB.getEnergyByVehicleModel(1)).thenReturn(0d);
        when(mockVehicleDB.getEnergyByVehicleModel(2)).thenReturn(0d);
        when(mockVehicleDB.getEnergyByVehicleModel(3)).thenReturn(0d);
        when(mockVehicleDB.getEnergyByVehicleModel(4)).thenReturn(0d);
        when(mockVehicleDB.getEnergyByVehicleModel(5)).thenReturn(0d);
        when(mockVehicleDB.getEnergyByVehicleModel(6)).thenReturn(0d);
        assertNotEquals(expResult, world.getBestPossibleModel(lstModel, pathList, lstOrders));
        expResult = new Pair<>(null, Double.MAX_VALUE);
        assertEquals(expResult, world.getBestPossibleModel(lstModel, pathList, lstOrders));
    }

    @Test
    void calculatePathCost() throws NoSuchAlgorithmException {
        Address trindade = new Address();
        trindade.setStreetName("Pharmacy Trindade");
        trindade.setLatitude(41.15227);
        trindade.setLongitude(-8.60929);
        trindade.setAltitude(104d);

        Address saBandeira = new Address();
        saBandeira.setStreetName("Pharmacy Sa da Bandeira");
        saBandeira.setLatitude(40.741895);
        saBandeira.setLongitude(-7.989308);
        saBandeira.setAltitude(10d);

        Address casteloQueijo = new Address();
        casteloQueijo.setStreetName("Pharmacy Castelo do Queijo");
        casteloQueijo.setLatitude(41.16875);
        casteloQueijo.setLongitude(-8.68995);
        casteloQueijo.setAltitude(4d);

        Address clerigos = new Address();
        clerigos.setStreetName("Clerigos");
        clerigos.setLatitude(41.14582);
        clerigos.setLongitude(-8.61398);
        clerigos.setAltitude(87.0);

        Address majestic = new Address();
        majestic.setStreetName("Majestic");
        majestic.setLatitude(41.14723);
        majestic.setLongitude(-8.60657);
        majestic.setAltitude(91.0);

        Address bolhao = new Address();
        bolhao.setStreetName("Bolhao");
        bolhao.setLatitude(41.14871);
        bolhao.setLongitude(-8.60746);
        bolhao.setAltitude(87.0);

        Address se = new Address();
        se.setStreetName("Sé");
        se.setLatitude(41.14331);
        se.setLongitude(-8.60914);
        se.setAltitude(82.0);

        Address caisDaRibeira = new Address();
        caisDaRibeira.setStreetName("Cais da Ribeira");
        caisDaRibeira.setLatitude(41.14063);
        caisDaRibeira.setLongitude(-8.61118);
        caisDaRibeira.setAltitude(25.0);

        Path p1 = new Path( 41.15227, -8.60929,40.741895, -7.989308, "trindade - sa", 1,
                1, 1, VehicleType.SCOOTER);

        Path p2 = new Path(40.741895, -7.989308, 41.15227, -8.60929, "sa - trindade", 1,
                1, 1, VehicleType.SCOOTER);

        Path p3 = new Path(40.741895, -7.989308, 41.16875, -8.68995, "sa - quejo", 1,
                1, 1, VehicleType.SCOOTER);

        Path p4 = new Path( 41.16875, -8.68995,40.741895, -7.989308, "quejo - sa", 1,
                1, 1, VehicleType.SCOOTER);

        Path p5 = new Path( 41.16875, -8.68995,41.14582, -8.61398, "quejo - clerigos", 1,
                1, 1, VehicleType.SCOOTER);

        Path p6 = new Path( 41.14582, -8.61398, 41.16875, -8.68995, "clerigos - queijo", 1,
                1, 1, VehicleType.SCOOTER);

        Path p7 = new Path( 41.14582, -8.61398, 41.14723, -8.60657, "clerigos - majestic", 1,
                1, 1, VehicleType.SCOOTER);

        Path p8 = new Path( 41.14723, -8.60657,41.14582, -8.61398,  "majestic - clerigos", 1,
                1, 1, VehicleType.SCOOTER);

        Path p9 = new Path( 41.14723, -8.60657,41.14871, -8.60746,  "majestic - bolhao", 1,
                1, 1, VehicleType.SCOOTER);

        Path p10 = new Path( 41.14871, -8.60746,41.14723, -8.60657,  "bolhao - majestic", 1,
                1, 1, VehicleType.SCOOTER);

        Path p11 = new Path( 41.14871, -8.60746,41.14331, -8.60914,  "bolhao - se", 1,
                1, 1, VehicleType.SCOOTER);

        Path p12 = new Path( 41.14331, -8.60914,41.14871, -8.60746,  "se - bolhao", 1,
                1, 1, VehicleType.SCOOTER);

        Path p13 = new Path( 41.14331, -8.60914,41.14063, -8.61118,  "se - cais", 1,
                1, 1, VehicleType.SCOOTER);

        Path p14 = new Path( 41.14063, -8.61118,41.14331, -8.60914,  "cais - se", 1,
                1, 1, VehicleType.SCOOTER);

        Path p15 = new Path( 41.14063, -8.61118,41.15227, -8.60929,  "cais - trindade", 1,
                1, 1, VehicleType.SCOOTER);


        Path p21 = new Path( 41.15227, -8.60929,40.741895, -7.989308, "trindade - sa", 1,
                1, 1, VehicleType.DRONE);

        Path p22 = new Path(40.741895, -7.989308, 41.15227, -8.60929, "sa - trindade", 1,
                1, 1, VehicleType.DRONE);

        Path p23 = new Path(40.741895, -7.989308, 41.16875, -8.68995, "sa - quejo", 1,
                1, 1, VehicleType.DRONE);

        Path p24 = new Path( 41.16875, -8.68995,40.741895, -7.989308, "quejo - sa", 1,
                1, 1, VehicleType.DRONE);

        Path p25 = new Path( 41.16875, -8.68995,41.14582, -8.61398, "quejo - clerigos", 1,
                1, 1, VehicleType.DRONE);

        Path p26 = new Path( 41.14582, -8.61398, 41.16875, -8.68995, "clerigos - queijo", 1,
                1, 1, VehicleType.DRONE);

        Path p27 = new Path( 41.14582, -8.61398, 41.14723, -8.60657, "clerigos - majestic", 1,
                1, 1, VehicleType.DRONE);

        Path p28 = new Path( 41.14723, -8.60657,41.14582, -8.61398,  "majestic - clerigos", 1,
                1, 1, VehicleType.DRONE);

        Path p29 = new Path( 41.14723, -8.60657,41.14871, -8.60746,  "majestic - bolhao", 1,
                1, 1, VehicleType.DRONE);

        Path p30 = new Path( 41.14871, -8.60746,41.14723, -8.60657,  "bolhao - majestic", 1,
                1, 1, VehicleType.DRONE);

        Path p31 = new Path( 41.14871, -8.60746,41.14331, -8.60914,  "bolhao - se", 1,
                1, 1, VehicleType.DRONE);

        Path p32 = new Path( 41.14331, -8.60914,41.14871, -8.60746,  "se - bolhao", 1,
                1, 1, VehicleType.DRONE);

        Path p33 = new Path( 41.14331, -8.60914,41.14063, -8.61118,  "se - cais", 1,
                1, 1, VehicleType.DRONE);

        Path p34 = new Path( 41.14063, -8.61118,41.14331, -8.60914,  "cais - se", 1,
                1, 1, VehicleType.DRONE);

        Path p35 = new Path( 41.14063, -8.61118,41.15227, -8.60929,  "cais - trindade", 1,
                1, 1, VehicleType.DRONE);



        List<Address> lstA = new LinkedList<>();
        lstA.add(trindade);
        lstA.add(saBandeira);
        lstA.add(casteloQueijo);
        lstA.add(clerigos);
        lstA.add(majestic);
        lstA.add(bolhao);
        lstA.add(se);
        lstA.add(caisDaRibeira);

        List<Path> lstP = new LinkedList<>();
        lstP.add(p1);
        lstP.add(p2);
        lstP.add(p3);
        lstP.add(p4);
        lstP.add(p5);
        lstP.add(p6);
        lstP.add(p7);
        lstP.add(p8);
        lstP.add(p9);
        lstP.add(p10);
        lstP.add(p11);
        lstP.add(p12);
        lstP.add(p13);
        lstP.add(p14);
        lstP.add(p15);

        lstP.add(p21);
        lstP.add(p22);
        lstP.add(p23);
        lstP.add(p24);
        lstP.add(p25);
        lstP.add(p26);
        lstP.add(p27);
        lstP.add(p28);
        lstP.add(p29);
        lstP.add(p30);
        lstP.add(p31);
        lstP.add(p32);
        lstP.add(p33);
        lstP.add(p34);
        lstP.add(p35);

        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(lstA);
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(lstP);

        world.createGraph();

        List<Address> lstPermutation = new LinkedList<>();
        lstPermutation.add(bolhao);


        List<Address> pathList = new LinkedList<>();
        pathList.add(trindade);
        pathList.add(saBandeira);
        pathList.add(casteloQueijo);
        pathList.add(clerigos);
        pathList.add(majestic);
        pathList.add(bolhao);
        pathList.add(se);
        pathList.add(caisDaRibeira);
        pathList.add(trindade);


        //ORDER
        Pharmacy oPharmacy = new Pharmacy("Pharmacy Trindade","info@trindade.com",new Address(41.15227d,-8.60929d,104d,
                "Rua da Trindade","123","4000-123","Porto","Portugal"));
        List<Order> lstOrders = new ArrayList<>();
        lstOrders.add(new Order("Para Presente, Enviar Embrulhado.", true, new Client("", 123456789, "fernando@gmail.com", "pass",
                41.14582d,-8.61398d,87.0d,"Clerigos","2esq","4444-111","Porto","Portugal", new ArrayList<>()),
                oPharmacy, new TreeMap<>()));

        lstOrders.add(new Order("Para Presente, Enviar Embrulhado.", true, new Client("", 123456781, "joana@gmail.com", "pass",
                41.14063d,-8.61118d,25.0d,"Cais da Ribeira","3esq","4000-555","Porto","Portugal", new ArrayList<>()),
                oPharmacy, new TreeMap<>()));
        //ORDER

        Battery b1 = new Battery(1, 40, 40, 100);

        Battery b2 = new Battery(2, -1, -4, 100);

        Battery b3 = new Battery(3, 40, 40, 0);

        VehicleModel scooter1 = new VehicleModel(1, "scooter1", 400, 10, 400, b1, VehicleType.SCOOTER);
        VehicleModel scooter2 = new VehicleModel(2, "scooter2", 400, 10, 400, b2, VehicleType.SCOOTER);
        VehicleModel scooter3 = new VehicleModel(3, "scooter3", 400, 10, 400, b3, VehicleType.SCOOTER);
        VehicleModel scooter4 = new VehicleModel(4, "scooter4", 400, 10, 0, b1, VehicleType.SCOOTER);
        VehicleModel scooter5 = new VehicleModel(5, "scooter5", 400, 4000, 400, b1, VehicleType.SCOOTER);
        VehicleModel scooter6 = new VehicleModel(6, "scooter6", 1, 10, 400, b1, VehicleType.SCOOTER);

        VehicleModel drone1 = new VehicleModel(1, "drone1", 400, 10, 400, b1, VehicleType.DRONE);
        VehicleModel drone2 = new VehicleModel(2, "drone2", 400, 10, 400, b2, VehicleType.DRONE);
        VehicleModel drone3 = new VehicleModel(3, "drone3", 400, 10, 400, b3, VehicleType.DRONE);
        VehicleModel drone4 = new VehicleModel(4, "drone4", 400, 10, 0, b1, VehicleType.DRONE);
        VehicleModel drone5 = new VehicleModel(5, "drone5", 400, 4000, 400, b1, VehicleType.DRONE);
        VehicleModel drone6 = new VehicleModel(6, "drone6", 1, 10, 400, b1, VehicleType.DRONE);


        VehicleModel notdefined = new VehicleModel(1, "scooter1", 400, 10, 400, b1, VehicleType.NOTDEFINED);

        List<Pharmacy> listPh = new ArrayList<>();
        Pharmacy ph1 = new Pharmacy(1, "Ph1", "a1@.com", majestic, new TreeMap<>());
        Pharmacy ph2 = new Pharmacy(2, "Ph2", "a2@.com", bolhao, new TreeMap<>());
        Pharmacy ph3 = new Pharmacy(3, "Ph3", "a3@.com", se, new TreeMap<>());
        Pharmacy ph4 = new Pharmacy(4, "Ph4", "a4@.com", saBandeira, new TreeMap<>());
        Pharmacy ph5 = new Pharmacy(5, "Ph5", "a5@.com", trindade, new TreeMap<>());
        listPh.add(ph1);
        listPh.add(ph2);
        listPh.add(ph3);
        listPh.add(ph4);
        listPh.add(ph5);
        when(mockPharmacyDB.getPharmacies()).thenReturn(listPh);

        when(mockDeliveryRunDB.checkValidChargingSlot(trindade)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(saBandeira)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(casteloQueijo)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(clerigos)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(majestic)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(bolhao)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(se)).thenReturn(true);
        when(mockDeliveryRunDB.checkValidChargingSlot(caisDaRibeira)).thenReturn(true);

        assertEquals(36.76915790179582, world.calculatePathCost(pathList, lstOrders, scooter1, 20d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter1, 10d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter1, 0d));
        assertEquals(36.76915790179582, world.calculatePathCost(pathList, lstOrders, scooter1, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter2, 10d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter2, 0d));
        assertEquals(36.76915790179582, world.calculatePathCost(pathList, lstOrders, scooter2, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter3, 10d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter3, 0d));
        assertEquals(36.76915790179582, world.calculatePathCost(pathList, lstOrders, scooter3, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter4, 10d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter4, 0d));
        assertEquals(36.76915790179582, world.calculatePathCost(pathList, lstOrders, scooter4, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter5, 10d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter5, 0d));
        assertEquals(1717.8869193560959, world.calculatePathCost(pathList, lstOrders, scooter5, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter6, 10d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter6, 0d));
        assertEquals(36.76915790179582, world.calculatePathCost(pathList, lstOrders, scooter6, 100000d));


        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone1, 0.1d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone1, 0d));
        assertEquals(1.1190973594436533, world.calculatePathCost(pathList, lstOrders, drone1, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone2, 0.1d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone2, 0d));
        assertEquals(1.1190973594436533, world.calculatePathCost(pathList, lstOrders, drone2, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone3, 0.1d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone3, 0d));
        assertEquals(1.1190973594436533, world.calculatePathCost(pathList, lstOrders, drone3, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone4, 0.1d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone4, 0d));
        assertEquals(1.1190973594436533, world.calculatePathCost(pathList, lstOrders, drone4, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone5, 0.1));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone5, 0d));
        assertEquals(444.5442235362819, world.calculatePathCost(pathList, lstOrders, drone5, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone6, 0.1));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone6, 0d));
        assertEquals(1.1190973594436533, world.calculatePathCost(pathList, lstOrders, drone6, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, notdefined, 0.1));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, notdefined, 0d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, notdefined, 100000d));





        when(mockDeliveryRunDB.checkValidChargingSlot(trindade)).thenReturn(false);
        when(mockDeliveryRunDB.checkValidChargingSlot(saBandeira)).thenReturn(false);
        when(mockDeliveryRunDB.checkValidChargingSlot(casteloQueijo)).thenReturn(false);
        when(mockDeliveryRunDB.checkValidChargingSlot(clerigos)).thenReturn(false);
        when(mockDeliveryRunDB.checkValidChargingSlot(majestic)).thenReturn(false);
        when(mockDeliveryRunDB.checkValidChargingSlot(bolhao)).thenReturn(false);
        when(mockDeliveryRunDB.checkValidChargingSlot(se)).thenReturn(false);
        when(mockDeliveryRunDB.checkValidChargingSlot(caisDaRibeira)).thenReturn(false);

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter1, 10d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter1, 0d));
        assertEquals(36.76915790179582, world.calculatePathCost(pathList, lstOrders, scooter1, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter2, 10d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter2, 0d));
        assertEquals(36.76915790179582, world.calculatePathCost(pathList, lstOrders, scooter2, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter3, 10d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter3, 0d));
        assertEquals(36.76915790179582, world.calculatePathCost(pathList, lstOrders, scooter3, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter4, 10d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter4, 0d));
        assertEquals(36.76915790179582, world.calculatePathCost(pathList, lstOrders, scooter4, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter5, 10d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter5, 0d));
        assertEquals(1717.8869193560959, world.calculatePathCost(pathList, lstOrders, scooter5, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter6, 10d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, scooter6, 0d));
        assertEquals(36.76915790179582, world.calculatePathCost(pathList, lstOrders, scooter6, 100000d));


        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone1, 0.1d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone1, 0d));
        assertEquals(1.1190973594436533, world.calculatePathCost(pathList, lstOrders, drone1, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone2, 0.1d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone2, 0d));
        assertEquals(1.1190973594436533, world.calculatePathCost(pathList, lstOrders, drone2, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone3, 0.1d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone3, 0d));
        assertEquals(1.1190973594436533, world.calculatePathCost(pathList, lstOrders, drone3, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone4, 0.1d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone4, 0d));
        assertEquals(1.1190973594436533, world.calculatePathCost(pathList, lstOrders, drone4, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone5, 0.1));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone5, 0d));
        assertEquals(444.5442235362819, world.calculatePathCost(pathList, lstOrders, drone5, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone6, 0.1));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, drone6, 0d));
        assertEquals(1.1190973594436533, world.calculatePathCost(pathList, lstOrders, drone6, 100000d));

        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, notdefined, 0.1));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, notdefined, 0d));
        assertEquals(Double.MAX_VALUE, world.calculatePathCost(pathList, lstOrders, notdefined, 100000d));
    }

    @Test
    void calculateMostEfficientPath() {
        Address trindade = new Address();
        trindade.setStreetName("Pharmacy Trindade");
        trindade.setLatitude(41.15227);
        trindade.setLongitude(-8.60929);
        trindade.setAltitude(104d);

        Address saBandeira = new Address();
        saBandeira.setStreetName("Pharmacy Sa da Bandeira");
        saBandeira.setLatitude(40.741895);
        saBandeira.setLongitude(-7.989308);
        saBandeira.setAltitude(10d);

        Address casteloQueijo = new Address();
        casteloQueijo.setStreetName("Pharmacy Castelo do Queijo");
        casteloQueijo.setLatitude(41.16875);
        casteloQueijo.setLongitude(-8.68995);
        casteloQueijo.setAltitude(4d);

        Address clerigos = new Address();
        clerigos.setStreetName("Clerigos");
        clerigos.setLatitude(41.14582);
        clerigos.setLongitude(-8.61398);
        clerigos.setAltitude(87.0);

        Address majestic = new Address();
        majestic.setStreetName("Majestic");
        majestic.setLatitude(41.14723);
        majestic.setLongitude(-8.60657);
        majestic.setAltitude(91.0);

        Address bolhao = new Address();
        bolhao.setStreetName("Bolhao");
        bolhao.setLatitude(41.14871);
        bolhao.setLongitude(-8.60746);
        bolhao.setAltitude(87.0);

        Address se = new Address();
        se.setStreetName("Sé");
        se.setLatitude(41.14331);
        se.setLongitude(-8.60914);
        se.setAltitude(82.0);

        Address caisDaRibeira = new Address();
        caisDaRibeira.setStreetName("Cais da Ribeira");
        caisDaRibeira.setLatitude(41.14063);
        caisDaRibeira.setLongitude(-8.61118);
        caisDaRibeira.setAltitude(25.0);

        Path p1 = new Path( 41.15227, -8.60929,40.741895, -7.989308, "trindade - sa", 1,
                1, 1, VehicleType.SCOOTER);

        Path p2 = new Path(40.741895, -7.989308, 41.15227, -8.60929, "sa - trindade", 1,
                1, 1, VehicleType.SCOOTER);

        Path p3 = new Path(40.741895, -7.989308, 41.16875, -8.68995, "sa - quejo", 1,
                1, 1, VehicleType.SCOOTER);

        Path p4 = new Path( 41.16875, -8.68995,40.741895, -7.989308, "quejo - sa", 1,
                1, 1, VehicleType.SCOOTER);

        Path p5 = new Path( 41.16875, -8.68995,41.14582, -8.61398, "quejo - clerigos", 1,
                1, 1, VehicleType.SCOOTER);

        Path p6 = new Path( 41.14582, -8.61398, 41.16875, -8.68995, "clerigos - queijo", 1,
                1, 1, VehicleType.SCOOTER);

        Path p7 = new Path( 41.14582, -8.61398, 41.14723, -8.60657, "clerigos - majestic", 1,
                1, 1, VehicleType.SCOOTER);

        Path p8 = new Path( 41.14723, -8.60657,41.14582, -8.61398,  "majestic - clerigos", 1,
                1, 1, VehicleType.SCOOTER);

        Path p9 = new Path( 41.14723, -8.60657,41.14871, -8.60746,  "majestic - bolhao", 1,
                1, 1, VehicleType.SCOOTER);

        Path p10 = new Path( 41.14871, -8.60746,41.14723, -8.60657,  "bolhao - majestic", 1,
                1, 1, VehicleType.SCOOTER);

        Path p11 = new Path( 41.14871, -8.60746,41.14331, -8.60914,  "bolhao - se", 1,
                1, 1, VehicleType.SCOOTER);

        Path p12 = new Path( 41.14331, -8.60914,41.14871, -8.60746,  "se - bolhao", 1,
                1, 1, VehicleType.SCOOTER);

        Path p13 = new Path( 41.14331, -8.60914,41.14063, -8.61118,  "se - cais", 1,
                1, 1, VehicleType.SCOOTER);

        Path p14 = new Path( 41.14063, -8.61118,41.14331, -8.60914,  "cais - se", 1,
                1, 1, VehicleType.SCOOTER);

        Path p15 = new Path( 41.14063, -8.61118,41.15227, -8.60929,  "cais - trindade", 1,
                1, 1, VehicleType.SCOOTER);

        List<Address> lstA = new LinkedList<>();
        lstA.add(trindade);
        lstA.add(saBandeira);
        lstA.add(casteloQueijo);
        lstA.add(clerigos);
        lstA.add(majestic);
        lstA.add(bolhao);
        lstA.add(se);
        lstA.add(caisDaRibeira);

        List<Path> lstP = new LinkedList<>();
        lstP.add(p1);
        lstP.add(p2);
        lstP.add(p3);
        lstP.add(p4);
        lstP.add(p5);
        lstP.add(p6);
        lstP.add(p7);
        lstP.add(p8);
        lstP.add(p9);
        lstP.add(p10);
        lstP.add(p11);
        lstP.add(p12);
        lstP.add(p13);
        lstP.add(p14);
        lstP.add(p15);

        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(lstA);
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(lstP);

        world.createGraph();

        List<Address> lstPermutation = new LinkedList<>();
        lstPermutation.add(bolhao);


        List<Address> expResult = new LinkedList<>();
        expResult.add(trindade);
        expResult.add(saBandeira);
        expResult.add(casteloQueijo);
        expResult.add(clerigos);
        expResult.add(majestic);
        expResult.add(bolhao);
        expResult.add(se);
        expResult.add(caisDaRibeira);
        expResult.add(trindade);
        List<Address> result = world.calculateMostEfficientPath(VehicleType.SCOOTER, trindade, trindade, lstPermutation);
        assertNotEquals(new LinkedList<>(), result);
        assertEquals(expResult, result);
    }

    @Test
    void calculateMostEfficientPath2() {
        Address trindade = new Address();
        trindade.setStreetName("Pharmacy Trindade");
        trindade.setLatitude(41.15227);
        trindade.setLongitude(-8.60929);
        trindade.setAltitude(104d);

        Address saBandeira = new Address();
        saBandeira.setStreetName("Pharmacy Sa da Bandeira");
        saBandeira.setLatitude(40.741895);
        saBandeira.setLongitude(-7.989308);
        saBandeira.setAltitude(10d);

        Address casteloQueijo = new Address();
        casteloQueijo.setStreetName("Pharmacy Castelo do Queijo");
        casteloQueijo.setLatitude(41.16875);
        casteloQueijo.setLongitude(-8.68995);
        casteloQueijo.setAltitude(4d);

        Address clerigos = new Address();
        clerigos.setStreetName("Clerigos");
        clerigos.setLatitude(41.14582);
        clerigos.setLongitude(-8.61398);
        clerigos.setAltitude(87.0);

        Address majestic = new Address();
        majestic.setStreetName("Majestic");
        majestic.setLatitude(41.14723);
        majestic.setLongitude(-8.60657);
        majestic.setAltitude(91.0);

        Address bolhao = new Address();
        bolhao.setStreetName("Bolhao");
        bolhao.setLatitude(41.14871);
        bolhao.setLongitude(-8.60746);
        bolhao.setAltitude(87.0);

        Address se = new Address();
        se.setStreetName("Sé");
        se.setLatitude(41.14331);
        se.setLongitude(-8.60914);
        se.setAltitude(82.0);

        Address caisDaRibeira = new Address();
        caisDaRibeira.setStreetName("Cais da Ribeira");
        caisDaRibeira.setLatitude(41.14063);
        caisDaRibeira.setLongitude(-8.61118);
        caisDaRibeira.setAltitude(25.0);

        Path p1 = new Path( 41.15227, -8.60929,40.741895, -7.989308, "trindade - sa", 1,
                1, 1, VehicleType.SCOOTER);

        List<Address> lstA = new LinkedList<>();
        lstA.add(trindade);
        lstA.add(saBandeira);
        lstA.add(casteloQueijo);
        lstA.add(clerigos);
        lstA.add(majestic);
        lstA.add(bolhao);
        lstA.add(se);
        lstA.add(caisDaRibeira);

        List<Path> lstP = new LinkedList<>();
        lstP.add(p1);

        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(lstA);
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(lstP);

        world.createGraph();

        List<Address> lstPermutation = new LinkedList<>();
        lstPermutation.add(bolhao);


        List<Address> expResult = new LinkedList<>();
        expResult.add(trindade);
        expResult.add(saBandeira);
        expResult.add(casteloQueijo);
        expResult.add(clerigos);
        expResult.add(majestic);
        expResult.add(bolhao);
        expResult.add(se);
        expResult.add(caisDaRibeira);
        expResult.add(trindade);
        List<Address> result = world.calculateMostEfficientPath(VehicleType.SCOOTER, trindade, trindade, lstPermutation);
        assertNotEquals(expResult, result);

        expResult = new ArrayList<>();
        assertEquals(expResult, result);
    }

    @Test
    void calculateMostEfficientPath3() {
        Address trindade = new Address();
        trindade.setStreetName("Pharmacy Trindade");
        trindade.setLatitude(41.15227);
        trindade.setLongitude(-8.60929);
        trindade.setAltitude(104d);

        Address saBandeira = new Address();
        saBandeira.setStreetName("Pharmacy Sa da Bandeira");
        saBandeira.setLatitude(40.741895);
        saBandeira.setLongitude(-7.989308);
        saBandeira.setAltitude(10d);

        Address casteloQueijo = new Address();
        casteloQueijo.setStreetName("Pharmacy Castelo do Queijo");
        casteloQueijo.setLatitude(41.16875);
        casteloQueijo.setLongitude(-8.68995);
        casteloQueijo.setAltitude(4d);

        Address clerigos = new Address();
        clerigos.setStreetName("Clerigos");
        clerigos.setLatitude(41.14582);
        clerigos.setLongitude(-8.61398);
        clerigos.setAltitude(87.0);

        Address majestic = new Address();
        majestic.setStreetName("Majestic");
        majestic.setLatitude(41.14723);
        majestic.setLongitude(-8.60657);
        majestic.setAltitude(91.0);

        Address bolhao = new Address();
        bolhao.setStreetName("Bolhao");
        bolhao.setLatitude(41.14871);
        bolhao.setLongitude(-8.60746);
        bolhao.setAltitude(87.0);

        Address se = new Address();
        se.setStreetName("Sé");
        se.setLatitude(41.14331);
        se.setLongitude(-8.60914);
        se.setAltitude(82.0);

        Address caisDaRibeira = new Address();
        caisDaRibeira.setStreetName("Cais da Ribeira");
        caisDaRibeira.setLatitude(41.14063);
        caisDaRibeira.setLongitude(-8.61118);
        caisDaRibeira.setAltitude(25.0);

        Path p1 = new Path( 41.15227, -8.60929,40.741895, -7.989308, "trindade - sa", 1,
                1, 1, VehicleType.DRONE);

        List<Address> lstA = new LinkedList<>();
        lstA.add(trindade);
        lstA.add(saBandeira);
        lstA.add(casteloQueijo);
        lstA.add(clerigos);
        lstA.add(majestic);
        lstA.add(bolhao);
        lstA.add(se);
        lstA.add(caisDaRibeira);

        List<Path> lstP = new LinkedList<>();
        lstP.add(p1);

        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(lstA);
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(lstP);

        world.createGraph();

        List<Address> lstPermutation = new LinkedList<>();
        lstPermutation.add(bolhao);


        List<Address> expResult = new LinkedList<>();
        expResult.add(trindade);
        expResult.add(saBandeira);
        expResult.add(casteloQueijo);
        expResult.add(clerigos);
        expResult.add(majestic);
        expResult.add(bolhao);
        expResult.add(se);
        expResult.add(caisDaRibeira);
        expResult.add(trindade);
        List<Address> result = world.calculateMostEfficientPath(VehicleType.DRONE, trindade, trindade, lstPermutation);
        assertNotEquals(expResult, result);

        expResult = new ArrayList<>();
        assertEquals(expResult, result);
    }

    @Test
    void calculateMostEfficientPath4() {
        Address trindade = new Address();
        trindade.setStreetName("Pharmacy Trindade");
        trindade.setLatitude(41.15227);
        trindade.setLongitude(-8.60929);
        trindade.setAltitude(104d);

        Address saBandeira = new Address();
        saBandeira.setStreetName("Pharmacy Sa da Bandeira");
        saBandeira.setLatitude(40.741895);
        saBandeira.setLongitude(-7.989308);
        saBandeira.setAltitude(10d);

        Address casteloQueijo = new Address();
        casteloQueijo.setStreetName("Pharmacy Castelo do Queijo");
        casteloQueijo.setLatitude(41.16875);
        casteloQueijo.setLongitude(-8.68995);
        casteloQueijo.setAltitude(4d);

        Address clerigos = new Address();
        clerigos.setStreetName("Clerigos");
        clerigos.setLatitude(41.14582);
        clerigos.setLongitude(-8.61398);
        clerigos.setAltitude(87.0);

        Address majestic = new Address();
        majestic.setStreetName("Majestic");
        majestic.setLatitude(41.14723);
        majestic.setLongitude(-8.60657);
        majestic.setAltitude(91.0);

        Address bolhao = new Address();
        bolhao.setStreetName("Bolhao");
        bolhao.setLatitude(41.14871);
        bolhao.setLongitude(-8.60746);
        bolhao.setAltitude(87.0);

        Address se = new Address();
        se.setStreetName("Sé");
        se.setLatitude(41.14331);
        se.setLongitude(-8.60914);
        se.setAltitude(82.0);

        Address caisDaRibeira = new Address();
        caisDaRibeira.setStreetName("Cais da Ribeira");
        caisDaRibeira.setLatitude(41.14063);
        caisDaRibeira.setLongitude(-8.61118);
        caisDaRibeira.setAltitude(25.0);

        Path p1 = new Path( 41.15227, -8.60929,40.741895, -7.989308, "trindade - sa", 1,
                1, 1, VehicleType.DRONE);

        Path p2 = new Path(40.741895, -7.989308, 41.15227, -8.60929, "sa - trindade", 1,
                1, 1, VehicleType.DRONE);

        Path p3 = new Path(40.741895, -7.989308, 41.16875, -8.68995, "sa - quejo", 1,
                1, 1, VehicleType.DRONE);

        Path p4 = new Path( 41.16875, -8.68995,40.741895, -7.989308, "quejo - sa", 1,
                1, 1, VehicleType.DRONE);

        Path p5 = new Path( 41.16875, -8.68995,41.14582, -8.61398, "quejo - clerigos", 1,
                1, 1, VehicleType.DRONE);

        Path p6 = new Path( 41.14582, -8.61398, 41.16875, -8.68995, "clerigos - queijo", 1,
                1, 1, VehicleType.DRONE);

        Path p7 = new Path( 41.14582, -8.61398, 41.14723, -8.60657, "clerigos - majestic", 1,
                1, 1, VehicleType.DRONE);

        Path p8 = new Path( 41.14723, -8.60657,41.14582, -8.61398,  "majestic - clerigos", 1,
                1, 1, VehicleType.DRONE);

        Path p9 = new Path( 41.14723, -8.60657,41.14871, -8.60746,  "majestic - bolhao", 1,
                1, 1, VehicleType.DRONE);

        Path p10 = new Path( 41.14871, -8.60746,41.14723, -8.60657,  "bolhao - majestic", 1,
                1, 1, VehicleType.DRONE);

        Path p11 = new Path( 41.14871, -8.60746,41.14331, -8.60914,  "bolhao - se", 1,
                1, 1, VehicleType.DRONE);

        Path p12 = new Path( 41.14331, -8.60914,41.14871, -8.60746,  "se - bolhao", 1,
                1, 1, VehicleType.DRONE);

        Path p13 = new Path( 41.14331, -8.60914,41.14063, -8.61118,  "se - cais", 1,
                1, 1, VehicleType.DRONE);

        Path p14 = new Path( 41.14063, -8.61118,41.14331, -8.60914,  "cais - se", 1,
                1, 1, VehicleType.DRONE);

        Path p15 = new Path( 41.14063, -8.61118,41.15227, -8.60929,  "cais - trindade", 1,
                1, 1, VehicleType.DRONE);

        List<Address> lstA = new LinkedList<>();
        lstA.add(trindade);
        lstA.add(saBandeira);
        lstA.add(casteloQueijo);
        lstA.add(clerigos);
        lstA.add(majestic);
        lstA.add(bolhao);
        lstA.add(se);
        lstA.add(caisDaRibeira);

        List<Path> lstP = new LinkedList<>();
        lstP.add(p1);
        lstP.add(p2);
        lstP.add(p3);
        lstP.add(p4);
        lstP.add(p5);
        lstP.add(p6);
        lstP.add(p7);
        lstP.add(p8);
        lstP.add(p9);
        lstP.add(p10);
        lstP.add(p11);
        lstP.add(p12);
        lstP.add(p13);
        lstP.add(p14);
        lstP.add(p15);

        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(lstA);
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(lstP);

        world.createGraph();

        List<Address> lstPermutation = new LinkedList<>();
        lstPermutation.add(bolhao);


        List<Address> expResult = new LinkedList<>();
        expResult.add(trindade);
        expResult.add(saBandeira);
        expResult.add(casteloQueijo);
        expResult.add(clerigos);
        expResult.add(majestic);
        expResult.add(bolhao);
        expResult.add(se);
        expResult.add(caisDaRibeira);
        expResult.add(trindade);
        List<Address> result = world.calculateMostEfficientPath(VehicleType.DRONE, trindade, trindade, lstPermutation);
        assertNotEquals(new LinkedList<>(), result);
        assertEquals(expResult, result);
    }

    @Test
    void calculateMostEfficientPath5() {
        Address trindade = new Address();
        trindade.setStreetName("Pharmacy Trindade");
        trindade.setLatitude(41.15227);
        trindade.setLongitude(-8.60929);
        trindade.setAltitude(104d);

        Address saBandeira = new Address();
        saBandeira.setStreetName("Pharmacy Sa da Bandeira");
        saBandeira.setLatitude(40.741895);
        saBandeira.setLongitude(-7.989308);
        saBandeira.setAltitude(10d);

        Address casteloQueijo = new Address();
        casteloQueijo.setStreetName("Pharmacy Castelo do Queijo");
        casteloQueijo.setLatitude(41.16875);
        casteloQueijo.setLongitude(-8.68995);
        casteloQueijo.setAltitude(4d);

        Address clerigos = new Address();
        clerigos.setStreetName("Clerigos");
        clerigos.setLatitude(41.14582);
        clerigos.setLongitude(-8.61398);
        clerigos.setAltitude(87.0);

        Address majestic = new Address();
        majestic.setStreetName("Majestic");
        majestic.setLatitude(41.14723);
        majestic.setLongitude(-8.60657);
        majestic.setAltitude(91.0);

        Address bolhao = new Address();
        bolhao.setStreetName("Bolhao");
        bolhao.setLatitude(41.14871);
        bolhao.setLongitude(-8.60746);
        bolhao.setAltitude(87.0);

        Address se = new Address();
        se.setStreetName("Sé");
        se.setLatitude(41.14331);
        se.setLongitude(-8.60914);
        se.setAltitude(82.0);

        Address caisDaRibeira = new Address();
        caisDaRibeira.setStreetName("Cais da Ribeira");
        caisDaRibeira.setLatitude(41.14063);
        caisDaRibeira.setLongitude(-8.61118);
        caisDaRibeira.setAltitude(25.0);

        Path p1 = new Path( 41.15227, -8.60929,40.741895, -7.989308, "trindade - sa", 1,
                1, 1, VehicleType.DRONE);

        Path p2 = new Path(40.741895, -7.989308, 41.15227, -8.60929, "sa - trindade", 1,
                1, 1, VehicleType.DRONE);

        Path p3 = new Path(40.741895, -7.989308, 41.16875, -8.68995, "sa - quejo", 1,
                1, 1, VehicleType.DRONE);

        Path p4 = new Path( 41.16875, -8.68995,40.741895, -7.989308, "quejo - sa", 1,
                1, 1, VehicleType.DRONE);

        Path p5 = new Path( 41.16875, -8.68995,41.14582, -8.61398, "quejo - clerigos", 1,
                1, 1, VehicleType.DRONE);

        Path p6 = new Path( 41.14582, -8.61398, 41.16875, -8.68995, "clerigos - queijo", 1,
                1, 1, VehicleType.DRONE);

        Path p7 = new Path( 41.14582, -8.61398, 41.14723, -8.60657, "clerigos - majestic", 1,
                1, 1, VehicleType.DRONE);

        Path p8 = new Path( 41.14723, -8.60657,41.14582, -8.61398,  "majestic - clerigos", 1,
                1, 1, VehicleType.DRONE);

        Path p9 = new Path( 41.14723, -8.60657,41.14871, -8.60746,  "majestic - bolhao", 1,
                1, 1, VehicleType.DRONE);

        Path p10 = new Path( 41.14871, -8.60746,41.14723, -8.60657,  "bolhao - majestic", 1,
                1, 1, VehicleType.DRONE);

        Path p11 = new Path( 41.14871, -8.60746,41.14331, -8.60914,  "bolhao - se", 1,
                1, 1, VehicleType.DRONE);

        Path p12 = new Path( 41.14331, -8.60914,41.14871, -8.60746,  "se - bolhao", 1,
                1, 1, VehicleType.DRONE);

        Path p13 = new Path( 41.14331, -8.60914,41.14063, -8.61118,  "se - cais", 1,
                1, 1, VehicleType.DRONE);

        Path p14 = new Path( 41.14063, -8.61118,41.14331, -8.60914,  "cais - se", 1,
                1, 1, VehicleType.DRONE);

        Path p15 = new Path( 41.14063, -8.61118,41.15227, -8.60929,  "cais - trindade", 1,
                1, 1, VehicleType.DRONE);

        List<Address> lstA = new LinkedList<>();
        lstA.add(trindade);
        lstA.add(saBandeira);
        lstA.add(casteloQueijo);
        lstA.add(clerigos);
        lstA.add(majestic);
        lstA.add(bolhao);
        lstA.add(se);
        lstA.add(caisDaRibeira);

        List<Path> lstP = new LinkedList<>();
        lstP.add(p1);
        lstP.add(p2);
        lstP.add(p3);
        lstP.add(p4);
        lstP.add(p5);
        lstP.add(p6);
        lstP.add(p7);
        lstP.add(p8);
        lstP.add(p9);
        lstP.add(p10);
        lstP.add(p11);
        lstP.add(p12);
        lstP.add(p13);
        lstP.add(p14);
        lstP.add(p15);

        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(lstA);
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(lstP);

        world.createGraph();

        List<Address> lstPermutation = new LinkedList<>();
        lstPermutation.add(bolhao);


        List<Address> expResult = new LinkedList<>();
        expResult.add(trindade);
        expResult.add(saBandeira);
        expResult.add(casteloQueijo);
        expResult.add(clerigos);
        expResult.add(majestic);
        expResult.add(bolhao);
        expResult.add(se);
        expResult.add(caisDaRibeira);
        expResult.add(trindade);
        List<Address> result = world.calculateMostEfficientPath(VehicleType.NOTDEFINED, trindade, trindade, lstPermutation);
        assertNotNull(result);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void calculateMostEfficientPath6() {
        Address trindade = new Address();
        trindade.setStreetName("Pharmacy Trindade");
        trindade.setLatitude(41.15227);
        trindade.setLongitude(-8.60929);
        trindade.setAltitude(104d);

        Address saBandeira = new Address();
        saBandeira.setStreetName("Pharmacy Sa da Bandeira");
        saBandeira.setLatitude(40.741895);
        saBandeira.setLongitude(-7.989308);
        saBandeira.setAltitude(10d);

        Address casteloQueijo = new Address();
        casteloQueijo.setStreetName("Pharmacy Castelo do Queijo");
        casteloQueijo.setLatitude(41.16875);
        casteloQueijo.setLongitude(-8.68995);
        casteloQueijo.setAltitude(4d);

        Address clerigos = new Address();
        clerigos.setStreetName("Clerigos");
        clerigos.setLatitude(41.14582);
        clerigos.setLongitude(-8.61398);
        clerigos.setAltitude(87.0);

        Address majestic = new Address();
        majestic.setStreetName("Majestic");
        majestic.setLatitude(41.14723);
        majestic.setLongitude(-8.60657);
        majestic.setAltitude(91.0);

        Address bolhao = new Address();
        bolhao.setStreetName("Bolhao");
        bolhao.setLatitude(41.14871);
        bolhao.setLongitude(-8.60746);
        bolhao.setAltitude(87.0);

        Address se = new Address();
        se.setStreetName("Sé");
        se.setLatitude(41.14331);
        se.setLongitude(-8.60914);
        se.setAltitude(82.0);

        Address caisDaRibeira = new Address();
        caisDaRibeira.setStreetName("Cais da Ribeira");
        caisDaRibeira.setLatitude(41.14063);
        caisDaRibeira.setLongitude(-8.61118);
        caisDaRibeira.setAltitude(25.0);

        Path p1 = new Path( 41.15227, -8.60929,40.741895, -7.989308, "trindade - sa", 1,
                1, 1, VehicleType.NOTDEFINED);

        Path p2 = new Path(40.741895, -7.989308, 41.15227, -8.60929, "sa - trindade", 1,
                1, 1, VehicleType.NOTDEFINED);

        Path p3 = new Path(40.741895, -7.989308, 41.16875, -8.68995, "sa - quejo", 1,
                1, 1, VehicleType.NOTDEFINED);

        Path p4 = new Path( 41.16875, -8.68995,40.741895, -7.989308, "quejo - sa", 1,
                1, 1, VehicleType.NOTDEFINED);

        Path p5 = new Path( 41.16875, -8.68995,41.14582, -8.61398, "quejo - clerigos", 1,
                1, 1, VehicleType.NOTDEFINED);

        Path p6 = new Path( 41.14582, -8.61398, 41.16875, -8.68995, "clerigos - queijo", 1,
                1, 1, VehicleType.NOTDEFINED);

        Path p7 = new Path( 41.14582, -8.61398, 41.14723, -8.60657, "clerigos - majestic", 1,
                1, 1, VehicleType.NOTDEFINED);

        Path p8 = new Path( 41.14723, -8.60657,41.14582, -8.61398,  "majestic - clerigos", 1,
                1, 1, VehicleType.NOTDEFINED);

        Path p9 = new Path( 41.14723, -8.60657,41.14871, -8.60746,  "majestic - bolhao", 1,
                1, 1, VehicleType.NOTDEFINED);

        Path p10 = new Path( 41.14871, -8.60746,41.14723, -8.60657,  "bolhao - majestic", 1,
                1, 1, VehicleType.NOTDEFINED);

        Path p11 = new Path( 41.14871, -8.60746,41.14331, -8.60914,  "bolhao - se", 1,
                1, 1, VehicleType.NOTDEFINED);

        Path p12 = new Path( 41.14331, -8.60914,41.14871, -8.60746,  "se - bolhao", 1,
                1, 1, VehicleType.NOTDEFINED);

        Path p13 = new Path( 41.14331, -8.60914,41.14063, -8.61118,  "se - cais", 1,
                1, 1, VehicleType.NOTDEFINED);

        Path p14 = new Path( 41.14063, -8.61118,41.14331, -8.60914,  "cais - se", 1,
                1, 1, VehicleType.NOTDEFINED);

        Path p15 = new Path( 41.14063, -8.61118,41.15227, -8.60929,  "cais - trindade", 1,
                1, 1, VehicleType.NOTDEFINED);

        List<Address> lstA = new LinkedList<>();
        lstA.add(trindade);
        lstA.add(saBandeira);
        lstA.add(casteloQueijo);
        lstA.add(clerigos);
        lstA.add(majestic);
        lstA.add(bolhao);
        lstA.add(se);
        lstA.add(caisDaRibeira);

        List<Path> lstP = new LinkedList<>();
        lstP.add(p1);
        lstP.add(p2);
        lstP.add(p3);
        lstP.add(p4);
        lstP.add(p5);
        lstP.add(p6);
        lstP.add(p7);
        lstP.add(p8);
        lstP.add(p9);
        lstP.add(p10);
        lstP.add(p11);
        lstP.add(p12);
        lstP.add(p13);
        lstP.add(p14);
        lstP.add(p15);

        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(lstA);
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(lstP);

        world.createGraph();

        List<Address> lstPermutation = new LinkedList<>();
        lstPermutation.add(bolhao);


        List<Address> result = world.calculateMostEfficientPath(VehicleType.NOTDEFINED, trindade, trindade, lstPermutation);
        assertEquals(Collections.emptyList(), result);
        assertNotNull(result);
    }

    @Test
    void calculateMostEfficientPath8() {
        Address trindade = new Address();
        trindade.setStreetName("Pharmacy Trindade");
        trindade.setLatitude(41.15227);
        trindade.setLongitude(-8.60929);
        trindade.setAltitude(104d);

        Address saBandeira = new Address();
        saBandeira.setStreetName("Pharmacy Sa da Bandeira");
        saBandeira.setLatitude(40.741895);
        saBandeira.setLongitude(-7.989308);
        saBandeira.setAltitude(10d);

        Address casteloQueijo = new Address();
        casteloQueijo.setStreetName("Pharmacy Castelo do Queijo");
        casteloQueijo.setLatitude(41.16875);
        casteloQueijo.setLongitude(-8.68995);
        casteloQueijo.setAltitude(4d);

        Address clerigos = new Address();
        clerigos.setStreetName("Clerigos");
        clerigos.setLatitude(41.14582);
        clerigos.setLongitude(-8.61398);
        clerigos.setAltitude(87.0);

        Address majestic = new Address();
        majestic.setStreetName("Majestic");
        majestic.setLatitude(41.14723);
        majestic.setLongitude(-8.60657);
        majestic.setAltitude(91.0);

        Address bolhao = new Address();
        bolhao.setStreetName("Bolhao");
        bolhao.setLatitude(41.14871);
        bolhao.setLongitude(-8.60746);
        bolhao.setAltitude(87.0);

        Address se = new Address();
        se.setStreetName("Sé");
        se.setLatitude(41.14331);
        se.setLongitude(-8.60914);
        se.setAltitude(82.0);

        Address caisDaRibeira = new Address();
        caisDaRibeira.setStreetName("Cais da Ribeira");
        caisDaRibeira.setLatitude(41.14063);
        caisDaRibeira.setLongitude(-8.61118);
        caisDaRibeira.setAltitude(25.0);

        Path p1 = new Path( 41.15227, -8.60929,40.741895, -7.989308, "trindade - sa", 1,
                1, 1, VehicleType.SCOOTER);

        Path p2 = new Path(40.741895, -7.989308, 41.15227, -8.60929, "sa - trindade", 1,
                1, 1, VehicleType.SCOOTER);

        Path p3 = new Path(40.741895, -7.989308, 41.16875, -8.68995, "sa - quejo", 1,
                1, 1, VehicleType.SCOOTER);

        Path p4 = new Path( 41.16875, -8.68995,40.741895, -7.989308, "quejo - sa", 1,
                1, 1, VehicleType.SCOOTER);

        Path p5 = new Path( 41.16875, -8.68995,41.14582, -8.61398, "quejo - clerigos", 1,
                1, 1, VehicleType.SCOOTER);

        Path p6 = new Path( 41.14582, -8.61398, 41.16875, -8.68995, "clerigos - queijo", 1,
                1, 1, VehicleType.SCOOTER);

        Path p7 = new Path( 41.14582, -8.61398, 41.14723, -8.60657, "clerigos - majestic", 1,
                1, 1, VehicleType.SCOOTER);

        Path p8 = new Path( 41.14723, -8.60657,41.14582, -8.61398,  "majestic - clerigos", 1,
                1, 1, VehicleType.SCOOTER);

        Path p9 = new Path( 41.14723, -8.60657,41.14871, -8.60746,  "majestic - bolhao", 1,
                1, 1, VehicleType.SCOOTER);

        Path p10 = new Path( 41.14871, -8.60746,41.14723, -8.60657,  "bolhao - majestic", 1,
                1, 1, VehicleType.SCOOTER);

        Path p11 = new Path( 41.14871, -8.60746,41.14331, -8.60914,  "bolhao - se", 1,
                1, 1, VehicleType.SCOOTER);

        Path p12 = new Path( 41.14331, -8.60914,41.14871, -8.60746,  "se - bolhao", 1,
                1, 1, VehicleType.SCOOTER);

        Path p13 = new Path( 41.14331, -8.60914,41.14063, -8.61118,  "se - cais", 1,
                1, 1, VehicleType.SCOOTER);

        Path p14 = new Path( 41.14063, -8.61118,41.14331, -8.60914,  "cais - se", 1,
                1, 1, VehicleType.SCOOTER);

        Path p15 = new Path( 41.14063, -8.61118,41.15227, -8.60929,  "cais - trindade", 1,
                1, 1, VehicleType.SCOOTER);

        List<Address> lstA = new LinkedList<>();
        lstA.add(trindade);
        lstA.add(saBandeira);
        lstA.add(casteloQueijo);
        lstA.add(clerigos);
        lstA.add(majestic);
        lstA.add(bolhao);
        lstA.add(se);
        lstA.add(caisDaRibeira);

        List<Path> lstP = new LinkedList<>();
        lstP.add(p1);
        lstP.add(p2);
        lstP.add(p3);
        lstP.add(p4);
        lstP.add(p5);
        lstP.add(p6);
        lstP.add(p7);
        lstP.add(p8);
        lstP.add(p9);
        lstP.add(p10);
        lstP.add(p11);
        lstP.add(p12);
        lstP.add(p13);
        lstP.add(p14);
        lstP.add(p15);

        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(lstA);
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(lstP);

        world.createGraph();

        List<Address> lstPermutation = new LinkedList<>();
        lstPermutation.add(bolhao);
        lstPermutation.add(clerigos);
        lstPermutation.add(majestic);


        List<Address> expResult = new LinkedList<>();
        expResult.add(trindade);
        expResult.add(saBandeira);
        expResult.add(casteloQueijo);
        expResult.add(clerigos);
        expResult.add(majestic);
        expResult.add(bolhao);
        expResult.add(se);
        expResult.add(caisDaRibeira);
        expResult.add(trindade);
        List<Address> result = world.calculateMostEfficientPath(VehicleType.SCOOTER, trindade, trindade, lstPermutation);
        assertNotEquals(new LinkedList<>(), result);
        assertEquals(expResult, result);
    }



    @Test
    void calculateMostEfficientPath9() {
        Address trindade = new Address();
        trindade.setStreetName("Pharmacy Trindade");
        trindade.setLatitude(41.15227);
        trindade.setLongitude(-8.60929);
        trindade.setAltitude(104d);

        Address saBandeira = new Address();
        saBandeira.setStreetName("Pharmacy Sa da Bandeira");
        saBandeira.setLatitude(40.741895);
        saBandeira.setLongitude(-7.989308);
        saBandeira.setAltitude(10d);

        Address casteloQueijo = new Address();
        casteloQueijo.setStreetName("Pharmacy Castelo do Queijo");
        casteloQueijo.setLatitude(41.16875);
        casteloQueijo.setLongitude(-8.68995);
        casteloQueijo.setAltitude(4d);

        Address clerigos = new Address();
        clerigos.setStreetName("Clerigos");
        clerigos.setLatitude(41.14582);
        clerigos.setLongitude(-8.61398);
        clerigos.setAltitude(87.0);

        Address majestic = new Address();
        majestic.setStreetName("Majestic");
        majestic.setLatitude(41.14723);
        majestic.setLongitude(-8.60657);
        majestic.setAltitude(91.0);

        Address bolhao = new Address();
        bolhao.setStreetName("Bolhao");
        bolhao.setLatitude(41.14871);
        bolhao.setLongitude(-8.60746);
        bolhao.setAltitude(87.0);

        Address se = new Address();
        se.setStreetName("Sé");
        se.setLatitude(41.14331);
        se.setLongitude(-8.60914);
        se.setAltitude(82.0);

        Address caisDaRibeira = new Address();
        caisDaRibeira.setStreetName("Cais da Ribeira");
        caisDaRibeira.setLatitude(41.14063);
        caisDaRibeira.setLongitude(-8.61118);
        caisDaRibeira.setAltitude(25.0);

        Path p1 = new Path( 41.15227, -8.60929,40.741895, -7.989308, "trindade - sa", 1,
                1, 1, VehicleType.SCOOTER);

        Path p2 = new Path(40.741895, -7.989308, 41.15227, -8.60929, "sa - trindade", 1,
                1, 1, VehicleType.SCOOTER);

        Path p3 = new Path(40.741895, -7.989308, 41.16875, -8.68995, "sa - quejo", 1,
                1, 1, VehicleType.SCOOTER);

        Path p4 = new Path( 41.16875, -8.68995,40.741895, -7.989308, "quejo - sa", 1,
                1, 1, VehicleType.SCOOTER);

        Path p5 = new Path( 41.16875, -8.68995,41.14582, -8.61398, "quejo - clerigos", 1,
                1, 1, VehicleType.SCOOTER);

        Path p6 = new Path( 41.14582, -8.61398, 41.16875, -8.68995, "clerigos - queijo", 1,
                1, 1, VehicleType.SCOOTER);

        Path p7 = new Path( 41.14582, -8.61398, 41.14723, -8.60657, "clerigos - majestic", 1,
                1, 1, VehicleType.SCOOTER);

        Path p8 = new Path( 41.14723, -8.60657,41.14582, -8.61398,  "majestic - clerigos", 1,
                1, 1, VehicleType.SCOOTER);

        Path p9 = new Path( 41.14723, -8.60657,41.14871, -8.60746,  "majestic - bolhao", 1,
                1, 1, VehicleType.SCOOTER);

        Path p10 = new Path( 41.14871, -8.60746,41.14723, -8.60657,  "bolhao - majestic", 1,
                1, 1, VehicleType.SCOOTER);

        Path p11 = new Path( 41.14871, -8.60746,41.14331, -8.60914,  "bolhao - se", 1,
                1, 1, VehicleType.SCOOTER);

        Path p12 = new Path( 41.14331, -8.60914,41.14871, -8.60746,  "se - bolhao", 1,
                1, 1, VehicleType.SCOOTER);

        Path p13 = new Path( 41.14331, -8.60914,41.14063, -8.61118,  "se - cais", 1,
                1, 1, VehicleType.SCOOTER);

        Path p14 = new Path( 41.14063, -8.61118,41.14331, -8.60914,  "cais - se", 1,
                1, 1, VehicleType.SCOOTER);

        Path p15 = new Path( 41.14063, -8.61118,41.15227, -8.60929,  "cais - trindade", 1,
                1, 1, VehicleType.SCOOTER);

        List<Address> lstA = new LinkedList<>();
        lstA.add(trindade);
        lstA.add(saBandeira);
        lstA.add(casteloQueijo);
        lstA.add(clerigos);
        lstA.add(majestic);
        lstA.add(bolhao);
        lstA.add(se);
        lstA.add(caisDaRibeira);

        List<Path> lstP = new LinkedList<>();
        lstP.add(p1);
        lstP.add(p2);
        lstP.add(p3);
        lstP.add(p4);
        lstP.add(p5);
        lstP.add(p6);
        lstP.add(p7);
        lstP.add(p8);
        lstP.add(p9);
        lstP.add(p10);
        lstP.add(p11);
        lstP.add(p12);
        lstP.add(p13);
        lstP.add(p14);
        lstP.add(p15);

        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(lstA);
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(lstP);

        world.createGraph();

        List<Address> lstPermutation = new LinkedList<>();
        lstPermutation.add(bolhao);
        lstPermutation.add(clerigos);
        lstPermutation.add(majestic);


        List<Address> expResult = new LinkedList<>();
        expResult.add(trindade);
        expResult.add(saBandeira);
        expResult.add(casteloQueijo);
        expResult.add(clerigos);
        expResult.add(majestic);
        expResult.add(bolhao);
        expResult.add(se);
        expResult.add(caisDaRibeira);
        expResult.add(trindade);
        List<Address> result = world.calculateMostEfficientPath(VehicleType.SCOOTER, trindade, trindade, lstPermutation);
        assertNotEquals(new LinkedList<>(), result);
        assertEquals(expResult, result);
    }


    @Test
    void calculatePermutationPaths() {
        Address trindade = new Address();
        trindade.setStreetName("Pharmacy Trindade");
        trindade.setLatitude(41.15227);
        trindade.setLongitude(-8.60929);
        trindade.setAltitude(104d);

        Address saBandeira = new Address();
        saBandeira.setStreetName("Pharmacy Sa da Bandeira");
        saBandeira.setLatitude(40.741895);
        saBandeira.setLongitude(-7.989308);
        saBandeira.setAltitude(10d);

        Address casteloQueijo = new Address();
        casteloQueijo.setStreetName("Pharmacy Castelo do Queijo");
        casteloQueijo.setLatitude(41.16875);
        casteloQueijo.setLongitude(-8.68995);
        casteloQueijo.setAltitude(4d);

        Address clerigos = new Address();
        clerigos.setStreetName("Clerigos");
        clerigos.setLatitude(41.14582);
        clerigos.setLongitude(-8.61398);
        clerigos.setAltitude(87.0);

        Address majestic = new Address();
        majestic.setStreetName("Majestic");
        majestic.setLatitude(41.14723);
        majestic.setLongitude(-8.60657);
        majestic.setAltitude(91.0);

        Address bolhao = new Address();
        bolhao.setStreetName("Bolhao");
        bolhao.setLatitude(41.14871);
        bolhao.setLongitude(-8.60746);
        bolhao.setAltitude(87.0);

        Address se = new Address();
        se.setStreetName("Sé");
        se.setLatitude(41.14331);
        se.setLongitude(-8.60914);
        se.setAltitude(82.0);

        Address caisDaRibeira = new Address();
        caisDaRibeira.setStreetName("Cais da Ribeira");
        caisDaRibeira.setLatitude(41.14063);
        caisDaRibeira.setLongitude(-8.61118);
        caisDaRibeira.setAltitude(25.0);

        Path p1 = new Path( 41.15227, -8.60929,40.741895, -7.989308, "trindade - sa", 1,
                1, 1, VehicleType.SCOOTER);

        Path p2 = new Path(40.741895, -7.989308, 41.15227, -8.60929, "sa - trindade", 1,
                1, 1, VehicleType.SCOOTER);

        Path p3 = new Path(40.741895, -7.989308, 41.16875, -8.68995, "sa - quejo", 1,
                1, 1, VehicleType.SCOOTER);

        Path p4 = new Path( 41.16875, -8.68995,40.741895, -7.989308, "quejo - sa", 1,
                1, 1, VehicleType.SCOOTER);

        Path p5 = new Path( 41.16875, -8.68995,41.14582, -8.61398, "quejo - clerigos", 1,
                1, 1, VehicleType.SCOOTER);

        Path p6 = new Path( 41.14582, -8.61398, 41.16875, -8.68995, "clerigos - queijo", 1,
                1, 1, VehicleType.SCOOTER);

        Path p7 = new Path( 41.14582, -8.61398, 41.14723, -8.60657, "clerigos - majestic", 1,
                1, 1, VehicleType.SCOOTER);

        Path p8 = new Path( 41.14723, -8.60657,41.14582, -8.61398,  "majestic - clerigos", 1,
                1, 1, VehicleType.SCOOTER);

        Path p9 = new Path( 41.14723, -8.60657,41.14871, -8.60746,  "majestic - bolhao", 1,
                1, 1, VehicleType.SCOOTER);

        Path p10 = new Path( 41.14871, -8.60746,41.14723, -8.60657,  "bolhao - majestic", 1,
                1, 1, VehicleType.SCOOTER);

        Path p11 = new Path( 41.14871, -8.60746,41.14331, -8.60914,  "bolhao - se", 1,
                1, 1, VehicleType.SCOOTER);

        Path p12 = new Path( 41.14331, -8.60914,41.14871, -8.60746,  "se - bolhao", 1,
                1, 1, VehicleType.SCOOTER);

        Path p13 = new Path( 41.14331, -8.60914,41.14063, -8.61118,  "se - cais", 1,
                1, 1, VehicleType.SCOOTER);

        Path p14 = new Path( 41.14063, -8.61118,41.14331, -8.60914,  "cais - se", 1,
                1, 1, VehicleType.SCOOTER);

        Path p15 = new Path( 41.14063, -8.61118,41.15227, -8.60929,  "cais - trindade", 1,
                1, 1, VehicleType.SCOOTER);

        List<Address> lstA = new LinkedList<>();
        lstA.add(trindade);
        lstA.add(saBandeira);
        lstA.add(casteloQueijo);
        lstA.add(clerigos);
        lstA.add(majestic);
        lstA.add(bolhao);
        lstA.add(se);
        lstA.add(caisDaRibeira);

        List<Path> lstP = new LinkedList<>();
        lstP.add(p1);
        lstP.add(p2);
        lstP.add(p3);
        lstP.add(p4);
        lstP.add(p5);
        lstP.add(p6);
        lstP.add(p7);
        lstP.add(p8);
        lstP.add(p9);
        lstP.add(p10);
        lstP.add(p11);
        lstP.add(p12);
        lstP.add(p13);
        lstP.add(p14);
        lstP.add(p15);

        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(lstA);
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(lstP);

        world.createGraph();

        List<Address> lstPermutation = new LinkedList<>();
        lstPermutation.add(bolhao);
        lstPermutation.add(bolhao);

        List<LinkedList<Address>> permutations = world.calculatePermutations(lstPermutation);

        List<Address> finalResult = new LinkedList<>();
        finalResult.add(trindade);
        finalResult.add(saBandeira);
        finalResult.add(casteloQueijo);
        finalResult.add(clerigos);
        finalResult.add(majestic);
        finalResult.add(bolhao);
        finalResult.add(se);
        finalResult.add(caisDaRibeira);
        finalResult.add(trindade);
        double doubleResult = 154775.30410811413;
        Pair<List<Address>, Double> pairResult = new Pair<>(finalResult, doubleResult);
        List<Pair<List<Address>, Double>> listPairResult = new LinkedList<>();
        listPairResult.add(pairResult);
        listPairResult.add(pairResult);
        assertEquals(listPairResult, world.calculatePermutationPaths(world.getScooterGraph(), trindade, trindade, permutations));
    }

    @Test
    void calculatePermutationPaths2() {
        Address trindade = new Address();
        trindade.setStreetName("Pharmacy Trindade");
        trindade.setLatitude(41.15227);
        trindade.setLongitude(-8.60929);
        trindade.setAltitude(104d);

        Address saBandeira = new Address();
        saBandeira.setStreetName("Pharmacy Sa da Bandeira");
        saBandeira.setLatitude(40.741895);
        saBandeira.setLongitude(-7.989308);
        saBandeira.setAltitude(10d);

        Address casteloQueijo = new Address();
        casteloQueijo.setStreetName("Pharmacy Castelo do Queijo");
        casteloQueijo.setLatitude(41.16875);
        casteloQueijo.setLongitude(-8.68995);
        casteloQueijo.setAltitude(4d);

        Address clerigos = new Address();
        clerigos.setStreetName("Clerigos");
        clerigos.setLatitude(41.14582);
        clerigos.setLongitude(-8.61398);
        clerigos.setAltitude(87.0);

        Address majestic = new Address();
        majestic.setStreetName("Majestic");
        majestic.setLatitude(41.14723);
        majestic.setLongitude(-8.60657);
        majestic.setAltitude(91.0);

        Address bolhao = new Address();
        bolhao.setStreetName("Bolhao");
        bolhao.setLatitude(41.14871);
        bolhao.setLongitude(-8.60746);
        bolhao.setAltitude(87.0);

        Address se = new Address();
        se.setStreetName("Sé");
        se.setLatitude(41.14331);
        se.setLongitude(-8.60914);
        se.setAltitude(82.0);

        Address caisDaRibeira = new Address();
        caisDaRibeira.setStreetName("Cais da Ribeira");
        caisDaRibeira.setLatitude(41.14063);
        caisDaRibeira.setLongitude(-8.61118);
        caisDaRibeira.setAltitude(25.0);

        Path p1 = new Path( 41.15227, -8.60929,40.741895, -7.989308, "trindade - sa", 1,
                1, 1, VehicleType.SCOOTER);

        List<Address> lstA = new LinkedList<>();
        lstA.add(trindade);
        lstA.add(saBandeira);
        lstA.add(casteloQueijo);
        lstA.add(clerigos);
        lstA.add(majestic);
        lstA.add(bolhao);
        lstA.add(se);
        lstA.add(caisDaRibeira);

        List<Path> lstP = new LinkedList<>();
        lstP.add(p1);

        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(lstA);
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(lstP);

        world.createGraph();

        List<Address> lstPermutation = new LinkedList<>();
        lstPermutation.add(bolhao);
        lstPermutation.add(bolhao);

        List<LinkedList<Address>> permutations = world.calculatePermutations(lstPermutation);

        List<Address> finalResult = new LinkedList<>();
        finalResult.add(trindade);
        finalResult.add(saBandeira);
        finalResult.add(casteloQueijo);
        finalResult.add(clerigos);
        finalResult.add(majestic);
        finalResult.add(bolhao);
        finalResult.add(se);
        finalResult.add(caisDaRibeira);
        finalResult.add(trindade);
        double doubleResult = 154775.30410811413;
        Pair<List<Address>, Double> pairResult = new Pair<>(finalResult, doubleResult);
        List<Pair<List<Address>, Double>> listPairResult = new LinkedList<>();
        listPairResult.add(pairResult);
        listPairResult.add(pairResult);
        assertEquals(new LinkedList<>(), world.calculatePermutationPaths(world.getScooterGraph(), trindade, trindade, permutations));
        assertNotEquals(listPairResult, world.calculatePermutationPaths(world.getScooterGraph(), trindade, trindade, permutations));
    }

    @Test
    void calculatePermutationPaths3() {
        Address trindade = new Address();
        trindade.setStreetName("Pharmacy Trindade");
        trindade.setLatitude(41.15227);
        trindade.setLongitude(-8.60929);
        trindade.setAltitude(104d);

        Address saBandeira = new Address();
        saBandeira.setStreetName("Pharmacy Sa da Bandeira");
        saBandeira.setLatitude(40.741895);
        saBandeira.setLongitude(-7.989308);
        saBandeira.setAltitude(10d);

        Address casteloQueijo = new Address();
        casteloQueijo.setStreetName("Pharmacy Castelo do Queijo");
        casteloQueijo.setLatitude(41.16875);
        casteloQueijo.setLongitude(-8.68995);
        casteloQueijo.setAltitude(4d);

        Address clerigos = new Address();
        clerigos.setStreetName("Clerigos");
        clerigos.setLatitude(41.14582);
        clerigos.setLongitude(-8.61398);
        clerigos.setAltitude(87.0);

        Address majestic = new Address();
        majestic.setStreetName("Majestic");
        majestic.setLatitude(41.14723);
        majestic.setLongitude(-8.60657);
        majestic.setAltitude(91.0);

        Address bolhao = new Address();
        bolhao.setStreetName("Bolhao");
        bolhao.setLatitude(41.14871);
        bolhao.setLongitude(-8.60746);
        bolhao.setAltitude(87.0);

        Address se = new Address();
        se.setStreetName("Sé");
        se.setLatitude(41.14331);
        se.setLongitude(-8.60914);
        se.setAltitude(82.0);

        Address caisDaRibeira = new Address();
        caisDaRibeira.setStreetName("Cais da Ribeira");
        caisDaRibeira.setLatitude(41.14063);
        caisDaRibeira.setLongitude(-8.61118);
        caisDaRibeira.setAltitude(25.0);

        Path p1 = new Path( 41.15227, -8.60929,40.741895, -7.989308, "trindade - sa", 1,
                1, 1, VehicleType.SCOOTER);

        List<Address> lstA = new LinkedList<>();
        lstA.add(trindade);
        lstA.add(saBandeira);
        lstA.add(casteloQueijo);
        lstA.add(clerigos);
        lstA.add(majestic);
        lstA.add(bolhao);
        lstA.add(se);
        lstA.add(caisDaRibeira);

        List<Path> lstP = new LinkedList<>();
        lstP.add(p1);

        when(mockDeliveryRunDB.getAllAddresses()).thenReturn(lstA);
        when(mockDeliveryRunDB.getAllPaths()).thenReturn(lstP);

        world.createGraph();

        List<Address> lstPermutation = new LinkedList<>();
        lstPermutation.add(bolhao);
        lstPermutation.add(majestic);

        List<LinkedList<Address>> permutations = world.calculatePermutations(lstPermutation);

        List<Address> finalResult = new LinkedList<>();
        finalResult.add(trindade);
        finalResult.add(saBandeira);
        finalResult.add(casteloQueijo);
        finalResult.add(clerigos);
        finalResult.add(majestic);
        finalResult.add(bolhao);
        finalResult.add(se);
        finalResult.add(caisDaRibeira);
        finalResult.add(trindade);
        double doubleResult = 154775.30410811413;
        Pair<List<Address>, Double> pairResult = new Pair<>(finalResult, doubleResult);
        List<Pair<List<Address>, Double>> listPairResult = new LinkedList<>();
        listPairResult.add(pairResult);
        listPairResult.add(pairResult);
        assertEquals(new LinkedList<>(), world.calculatePermutationPaths(world.getScooterGraph(), trindade, trindade, permutations));
        assertNotEquals(permutations, world.calculatePermutationPaths(world.getScooterGraph(), trindade, trindade, permutations));

    }

    @Test
    void calculatePermutations() {
        Address saBandeira = new Address();
        saBandeira.setStreetName("Pharmacy Sa da Bandeira");
        saBandeira.setLatitude(40.741895);
        saBandeira.setLongitude(-7.989308);
        saBandeira.setAltitude(10d);

        Address trindade = new Address();
        trindade.setStreetName("Pharmacy Trindade");
        trindade.setLatitude(41.15227);
        trindade.setLongitude(-8.60929);
        trindade.setAltitude(104d);

        Address casteloQueijo = new Address();
        casteloQueijo.setStreetName("Pharmacy Castelo do Queijo");
        casteloQueijo.setLatitude(41.16875);
        casteloQueijo.setLongitude(-8.68995);
        casteloQueijo.setAltitude(4d);

        Address clerigos = new Address();
        clerigos.setStreetName("Clerigos");
        clerigos.setLatitude(41.14582);
        clerigos.setLongitude(-8.61398);
        clerigos.setAltitude(87.0);

        Address majestic = new Address();
        majestic.setStreetName("Majestic");
        majestic.setLatitude(41.14723);
        majestic.setLongitude(-8.60657);
        majestic.setAltitude(91.0);

        Address bolhao = new Address();
        bolhao.setStreetName("Bolhao");
        bolhao.setLatitude(41.14871);
        bolhao.setLongitude(-8.60746);
        bolhao.setAltitude(87.0);

        Address se = new Address();
        se.setStreetName("Sé");
        se.setLatitude(41.14331);
        se.setLongitude(-8.60914);
        se.setAltitude(82.0);

        Address caisDaRibeira = new Address();
        caisDaRibeira.setStreetName("Cais da Ribeira");
        caisDaRibeira.setLatitude(41.14063);
        caisDaRibeira.setLongitude(-8.61118);
        caisDaRibeira.setAltitude(25.0);

        List<Address> lst = new LinkedList<>();
        List<List<Address>> expResult = new LinkedList<>();
        expResult.add(lst);
        List<LinkedList<Address>> result = world.calculatePermutations(lst);
        assertEquals(expResult, result);

        lst.add(trindade);
        expResult = new LinkedList<>();
        expResult.add(lst);
        assertNotEquals(expResult, result);

        lst.add(bolhao);
        lst.add(se);

        result = world.calculatePermutations(lst);

        lst.add(trindade);
        lst.add(bolhao);
        lst.add(se);

        expResult = new LinkedList<>();
        expResult.add(lst);

        lst = new LinkedList<>();
        lst.add(bolhao);
        lst.add(trindade);
        lst.add(se);
        expResult.add(lst);

        lst = new LinkedList<>();
        lst.add(bolhao);
        lst.add(se);
        lst.add(trindade);
        expResult.add(lst);

        lst = new LinkedList<>();
        lst.add(trindade);
        lst.add(se);
        lst.add(bolhao);
        expResult.add(lst);

        lst = new LinkedList<>();
        lst.add(se);
        lst.add(trindade);
        lst.add(bolhao);
        expResult.add(lst);

        assertNotEquals(expResult, result);

        lst = new LinkedList<>();
        lst.add(se);
        lst.add(bolhao);
        lst.add(trindade);
        expResult.add(lst);

        assertEquals(expResult,result);
    }
}