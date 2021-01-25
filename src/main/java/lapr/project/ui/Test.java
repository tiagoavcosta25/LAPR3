package lapr.project.ui;

import javafx.util.Pair;
import lapr.project.controller.ApplicationPOT;
import lapr.project.model.*;
import lapr.project.utils.FileReader;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.*;

public class Test {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        FileReader.readFiles();
        ApplicationPOT.getInstance().getWorldMap().createGraph();
        WorldMap world = ApplicationPOT.getInstance().getWorldMap();

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

        //ORDER
        List<Order> lst = new ArrayList<>();
        List<CreditCard> order1CC = new ArrayList<>();

        order1CC.add(new CreditCard(9412348029177724L,Date.valueOf("2023-10-01"),123));
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
        //ORDER

        System.out.println(world.getScooterGraph().toString());
        System.out.println("---------------------------------------------\n\n\n");
        System.out.println(world.getDroneGraph().toString());
        System.out.println("---------------------------------------------\n\n\n");
/*
        Pair<Pair<VehicleModel, Double>, List<Address>> result = world.calculateBestVehicleAndBestPath(lst);
        System.out.println("\n\n");
        VehicleModel vmResult = result.getKey().getKey();
        Double costResult = result.getKey().getValue();
        List<Address> pathResult = result.getValue();
        double distance = pathResult.get(0).distanceTo(pathResult.get(1));
        System.out.println("Modelo: " + vmResult);
        System.out.println("Path: " + pathResult);
        System.out.println("Custo: " + costResult);
        //System.out.println("Distancia 1: " + distance);
        */
    }
}
