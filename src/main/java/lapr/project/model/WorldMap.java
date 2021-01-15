package lapr.project.model;

import javafx.util.Pair;
import lapr.project.data.DeliveryDB;
import lapr.project.graph.map.Graph;
import lapr.project.graph.map.GraphAlgorithms;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WorldMap {

    private Graph<Address, String> m_graph;
    private DeliveryDB m_deliveryDB;

    public WorldMap() {
        m_graph = new Graph<>(true);
        m_deliveryDB = new DeliveryDB();
    }

    public Graph<Address, String> getGraph() {
        return m_graph;
    }

    /**
     * CREATE GRAPH
     */
    public void createGraph() {
        createGraph(m_deliveryDB.getAllAddresses(), m_deliveryDB.getAllPaths());
    }

    private void createGraph(List<Address> addresses, List<Path> paths) {
        double dist = 0;
        Pair<Address, Address> pathAdd;
        for (Address a : addresses) {
            m_graph.insertVertex(a);
        }
        for (Path p : paths) {

            pathAdd = getAddressesById(p.getIdAddressA(), p.getIdAddressB());

            if (pathAdd != null) {
                dist = pathAdd.getKey().distanceTo(pathAdd.getValue());
                m_graph.insertEdge(pathAdd.getKey(), pathAdd.getValue(), "Distance", dist);
            }
        }
    }

    private Pair<Address, Address> getAddressesById(int intAddressAId, int intAddressBId) {
        Address origem = null;
        Address destino = null;
        for (Address a : m_graph.vertices()) {
            if (a.getId() == intAddressAId) {
                origem = a;
            } else if (a.getId() == intAddressBId) {
                destino = a;
            }
            if (origem != null && destino != null)
                return new Pair<>(origem, destino);
        }
        return null;
    }

    /**
     * CALCULATE MOST EFFICIENT PATH/SHORTEST PATH
     */
     public Pair<LinkedList<Address>, Double> calculateMostEfficientPath(Address startAddress, Address endAddress, List<Address> deliveryPoints) {
        List<LinkedList<Address>> permutations = calculatePermutations(deliveryPoints);
        List<Pair<LinkedList<Address>, Double>> lst = calculatePermutationPaths(startAddress, endAddress, permutations);

        Double minimumWeight = Double.MAX_VALUE;
        Pair<LinkedList<Address>, Double> result = new Pair<LinkedList<Address>, Double>(null, 0d);

        for (Pair<LinkedList<Address>, Double> pair : lst) {
            if (pair.getValue() < minimumWeight) {
                minimumWeight = pair.getValue();
                result = pair;
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
                totalCost += GraphAlgorithms.shortestPath(m_graph, list.get(i), list.get(i + 1), tempPath);   //O(V^2)
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
