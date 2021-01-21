package lapr.project.model;

import javafx.util.Pair;
import lapr.project.data.DeliveryRunDB;
import lapr.project.graph.map.Edge;
import lapr.project.graph.map.Graph;
import lapr.project.graph.map.GraphAlgorithms;
import lapr.project.utils.Constants;
import lapr.project.utils.EnergyCalculator;

import java.util.*;

public class WorldMap {

    private Graph<Address, Path> moGraph;
    private DeliveryRunDB moDeliveryRunDB;

    public WorldMap() {
        moGraph = new Graph<>(true);
        moDeliveryRunDB = new DeliveryRunDB();
    }

    public Graph<Address, Path> getGraph() {
        return moGraph;
    }

    /**
     * CREATE GRAPH
     */
    public void createGraph() {
        createGraph(moDeliveryRunDB.getAllAddresses(), moDeliveryRunDB.getAllPaths());
    }

    private void createGraph(List<Address> addresses, List<Path> paths) {
        double dist = 0;
        Pair<Address, Address> pathAdd;
        for (Address a : addresses) {
            moGraph.insertVertex(a);
        }
        for (Path p : paths) {

            pathAdd = getAddresses(p.getLatitudeA(), p.getLongitudeA(), p.getLatitudeB(), p.getLongitudeB());

            if (pathAdd != null) {
                dist = pathAdd.getKey().distanceTo(pathAdd.getValue());
                moGraph.insertEdge(pathAdd.getKey(), pathAdd.getValue(), p, dist);
            }
        }
    }

    private Pair<Address, Address> getAddresses(double dblLatitudeA, double dblLongitudeA,
                                                double dblLatitudeB, double dblLongitudeB) {
        Address origem = null;
        Address destino = null;
        for (Address a : moGraph.vertices()) {
            if (a.getLatitude() == dblLatitudeA && a.getLongitude() == dblLongitudeA) {
                origem = a;
            } else if (a.getLatitude() == dblLatitudeB && a.getLongitude() == dblLongitudeB) {
                destino = a;
            }
            if (origem != null && destino != null)
                return new Pair<>(origem, destino);
        }
        return null;
    }

    public LinkedList<Path> getListOfPaths() {
        LinkedList<Path> paths = new LinkedList<>();
        for(Edge<Address, Path> edge : this.moGraph.edges())
            paths.add(edge.getElement());
        return paths;
    }

    public Path getPathFromAddresses(Address addA, Address addB) {
        LinkedList<Path> paths = getListOfPaths();
        for(Path path : paths) {
            if(path.getLatitudeA() == addA.getLatitude() && path.getLongitudeA() == addA.getLongitude() &&
                    path.getLatitudeB() == addB.getLatitude() && path.getLongitudeB() == addB.getLongitude())
                return path;
        }
        return null;
    }

    /**
     * CALCULATE PATH COST
     */


    public void checkIfPathIsPossible(Pair<LinkedList<Address>, Double> pathAndCost) {
        //Calcular gasto energético para todos os modelos
        //Ordenar do melhor para o pior
        //Verificar se é possivel com 100% bateria máximo
        //Por ordem, se algum for, escolher esse
        //Se para todos for mais de 100%, continuar
        //Por modelo, retirar das permutações, uma lista de caminhos que passasse por farmácias a meio
        //Verificar pela lista de cada modelo, qual era o melhor caminho possível (Sempre que chegar a uma farmacia, recarregar até 100% bateria)
        //Comparar resultados entre modelos
        //Escolher modelo vencedor e enviar veículo com mais bateria
    }

    public void test(Pair<LinkedList<Address>, Double> lstPath) {

    }



    public Pair<LinkedList<Address>, Double> calculatePathCost(LinkedList<Address> allAddresses, List<Address> deliveryPoints,
                                                               List<Order> orderList) {

        double energyCost = 0;
        double totalMass = Constants.SCOOTER_TOTAL_WEIGHT + Constants.DEFAULT_COURIER_WEIGHT;
        
        Map<Address, Double> orderWeightMap = new HashMap<>();
        for(Order order : orderList) {
            orderWeightMap.put(order.getClient().getAddress(), order.getTotalWeight());
            totalMass += order.getTotalWeight();
        }

        for(int i = 0; i < allAddresses.size() - 1; i++) {
            Path path = getPathFromAddresses(allAddresses.get(i), allAddresses.get(i + 1));
            double distanceUsingCoordinates = allAddresses.get(i).distanceTo(allAddresses.get(i + 1));
            double localHeightDifference = allAddresses.get(i).getAltitude() - allAddresses.get(i + 1).getAltitude();
            double winDegree = path.getWindAngle();
            double winSpeed = path.getWindSpeed();
            double kineticFrictionCoefficient = path.getKineticFrictionCoefficient();
            if(orderWeightMap.containsKey(allAddresses.get(i))) {
                totalMass -= orderWeightMap.get(allAddresses.get(i));
            }

            energyCost += EnergyCalculator.calculoEnergia(distanceUsingCoordinates, winDegree, winSpeed, localHeightDifference,
                    totalMass, kineticFrictionCoefficient);
        }
        return new Pair<>(allAddresses, energyCost);
    }



    /**
     * CALCULATE MOST EFFICIENT PATH/SHORTEST PATH
     */
    public LinkedList<Address> calculateMostEfficientPath(Address startAddress, Address endAddress, List<Address> deliveryPoints) {
        //Pass the weight of each edge of the graph to the enery cost
        for(Edge<Address, Path> e : this.moGraph.edges()) {
            double distanceUsingCoordinates = e.getVOrig().distanceTo(e.getVDest());
            double localHeightDifference = e.getVOrig().getAltitude() - e.getVDest().getAltitude();
            double totalMass = 1;

            double energia = EnergyCalculator.calculoEnergia(distanceUsingCoordinates, e.getElement().getWindAngle(),
                    e.getElement().getWindSpeed(), localHeightDifference, totalMass,
                    e.getElement().getKineticFrictionCoefficient());
            e.setWeight(energia);
        }

        List<LinkedList<Address>> permutations = calculatePermutations(deliveryPoints);
        List<Pair<LinkedList<Address>, Double>> lst = calculatePermutationPaths(startAddress, endAddress, permutations);

        Double minimumWeight = Double.MAX_VALUE;
        LinkedList<Address> result = new LinkedList<>();

        for (Pair<LinkedList<Address>, Double> pair : lst) {
            if (pair.getValue() < minimumWeight) {
                minimumWeight = pair.getValue();
                result = pair.getKey();
            }
        }
        return result;
    }

    public List<Pair<LinkedList<Address>, Double>> calculatePermutationPaths(Address a1, Address a2,
                                                                             List<LinkedList<Address>> permutations) {    //O(k^2*V^2)
        List<Pair<LinkedList<Address>, Double>> listOfPaths = new LinkedList<>();
        for (LinkedList<Address> list : permutations) {                                                                   //O(k^2*V^2)
            list.addFirst(a1);
            list.addLast(a2);
            LinkedList<Address> permutationPath = new LinkedList<>();
            double totalCost = 0.0;
            for (int i = 0; i < list.size() - 1; i++) {                                                                  //O(k*V^2)
                LinkedList<Address> tempPath = new LinkedList<>();
                //calcular energia
                totalCost += GraphAlgorithms.shortestPath(moGraph, list.get(i), list.get(i + 1), tempPath);   //O(V^2)
                if (tempPath.size() == 0)
                    return new LinkedList<>();
                tempPath.removeLast();
                permutationPath.addAll(tempPath);
            }
            permutationPath.add(a2);
            listOfPaths.add(new Pair<>(permutationPath, totalCost));
        }
        return listOfPaths;
    }

    public List<LinkedList<Address>> calculatePermutations(List<Address> original) {                                               //O(n!)
        if (original.isEmpty()) {
            List<LinkedList<Address>> result = new ArrayList<>();
            result.add(new LinkedList<>());
            return result;
        }
        Address firstElement = original.remove(0);
        List<LinkedList<Address>> returnValue = new ArrayList<>();
        List<LinkedList<Address>> permutations = calculatePermutations(original);
        for (List<Address> smallerPermutated : permutations) {
            for (int index = 0; index <= smallerPermutated.size(); index++) {
                LinkedList<Address> temp = new LinkedList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }


}
