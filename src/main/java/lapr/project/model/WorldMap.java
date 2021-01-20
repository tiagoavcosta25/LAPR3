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


        return  null;
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
