package lapr.project.ui;

import javafx.util.Pair;
import lapr.project.controller.ApplicationPOT;
import lapr.project.model.*;
import lapr.project.model.service.GraphService;
import lapr.project.utils.FileReader;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.*;

public class Test {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        FileReader.readFiles();
        ApplicationPOT.getInstance().getWorldMap().createGraph();
        GraphService world = ApplicationPOT.getInstance().getWorldMap();

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

        System.out.println(world.getScooterGraph().toString());
        System.out.println("---------------------------------------------\n\n\n");
        System.out.println(world.getDroneGraph().toString());
        System.out.println("---------------------------------------------\n\n\n");

        Pair<Pair<VehicleModel, Double>, List<Address>> result = world.calculateBestVehicleAndBestPath(lstOrders);
        System.out.println("\n\n");
        VehicleModel vmResult = result.getKey().getKey();
        Double costResult = result.getKey().getValue();
        List<Address> pathResult = result.getValue();
        double distance = pathResult.get(0).distanceTo(pathResult.get(1));
        System.out.println("Modelo: " + vmResult);
        System.out.println("Path: " + pathResult);
        System.out.println("Custo: " + costResult);
        //System.out.println("Distancia 1: " + distance);

    }
}
