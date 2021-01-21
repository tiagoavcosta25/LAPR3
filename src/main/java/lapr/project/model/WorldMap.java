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

    public WorldMap() {
        moGraph = new Graph<>(true);
    }

    public Graph<Address, Path> getGraph() {
        return moGraph;
    }

    /**
     * CREATE GRAPH
     */
    public void createGraph() {
    }





    public LinkedList<Path> getListOfPaths() {
        return new LinkedList<>();
    }

    public Path getPathFromAddresses(Address addA, Address addB) {
        return null;
    }

    /**
     * CALCULATE PATH COST
     */






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

        return new LinkedList<>();
    }

    public List<Pair<LinkedList<Address>, Double>> calculatePermutationPaths(Address a1, Address a2,
                                                                             List<LinkedList<Address>> permutations) {    //O(k^2*V^2)

        return new LinkedList<>();
    }




}
