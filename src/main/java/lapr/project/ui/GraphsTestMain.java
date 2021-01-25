package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.model.*;
import lapr.project.model.service.GraphServices;

import java.util.LinkedList;


class GraphsTestMain {

    public static void main(String[] args) {
        System.out.println("Hello world\n");

        Address trindade = new Address();
        trindade.setStreetName("Trindade");
        trindade.setLatitude(41.15227);
        trindade.setLongitude(-8.60929);

        Address casteloDoQueijo = new Address();
        casteloDoQueijo.setStreetName("Castelo do Queijo");
        casteloDoQueijo.setLatitude(41.16875);
        casteloDoQueijo.setLongitude(-8.68995);

        Address clerigos = new Address();
        clerigos.setStreetName("Clérigos");
        clerigos.setLatitude(41.14582);
        clerigos.setLongitude(-8.61398);

        Address majestic = new Address();
        majestic.setStreetName("Majestic");
        majestic.setLatitude(41.14723);
        majestic.setLongitude(-8.60657);

        Address bolhao = new Address();
        bolhao.setStreetName("Bolhão");
        bolhao.setLatitude(41.14871);
        bolhao.setLongitude(-8.60746);

        Address se = new Address();
        se.setStreetName("Sé");
        se.setLatitude(41.14331);
        se.setLongitude(-8.60914);

        Address caisDaRibeira = new Address();
        caisDaRibeira.setStreetName("Cais da Ribeira");
        caisDaRibeira.setLatitude(41.14063);
        caisDaRibeira.setLongitude(-8.61118);


        long time = System.currentTimeMillis();
        // Order order1 = new Order(40, 50, 0, new Date(time), "Descricao", "Status", true, new Client())



        ApplicationPOT.getInstance().getWorldMap().createGraph();
        GraphServices world = ApplicationPOT.getInstance().getWorldMap();

        LinkedList<Address> test = new LinkedList<>();

        System.out.println("\nGoodbye world");
    }
}