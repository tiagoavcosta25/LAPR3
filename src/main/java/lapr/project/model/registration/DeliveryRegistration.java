package lapr.project.model.registration;

import lapr.project.graph.map.Graph;
import lapr.project.graph.matrix.AdjacencyMatrixGraph;
import lapr.project.graph.matrix.GraphAlgorithmExtended;
import lapr.project.model.Address;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DeliveryRegistration {

    private AdjacencyMatrixGraph<Address, Double> m_oGraph = new AdjacencyMatrixGraph<>();


    public double getShortestPath(Address startingPoint, Address destiny) {

        return 0.0;
    }

    public int numMinConnectionsTo2Address() {
        if (isConnectedGraph()) {
            return GraphAlgorithmExtended.floydWarshallAlgorithm((AdjacencyMatrixGraph<Address, Double>) this.m_oGraph.clone());
        } else {
            return -1;
        }
    }

    /**
     * Verifies if the friendship network is connected (if the graph is connected).
     */

    private boolean isConnectedGraph() {
        HashSet<Address> aux = new HashSet<>();
        Address a = getAddressWithRelations().get(0);
        for (Address adj : m_oGraph.adjVertices(a)) {
            aux.add(adj);
        }
        HashSet<Address> aux2 = checkConnectionAux(aux);
        if (aux2 == null) {
            return false;
        }
        return aux2.size() == m_oGraph.numVertices();
    }

    /**
     * Auxiliar and recursive method that adds all the Addresss that are connected.
     */
    private HashSet<Address> checkConnectionAux(HashSet<Address> aux) {
        int previousSizeAux;
        if (aux.size() >= getAddressWithRelations().size()) {
            return aux;
        }
        previousSizeAux = aux.size();
        addAdjacentAddress(aux);
        if (aux.size() == previousSizeAux) {
            return null;
        }
        return checkConnectionAux(aux);
    }

    /**
     * Adds the Addresss connected to a determined Address.
     */
    private void addAdjacentAddress(HashSet<Address> list) {
        List<Address> listAdjAddresss = new ArrayList<>();
        for (Address a : list) {
            for (Address adj : m_oGraph.adjVertices(a)) {
                if (!(listAdjAddresss.contains(adj) || list.contains(adj))) {
                    listAdjAddresss.add(adj);
                }
            }
        }
        list.addAll(listAdjAddresss);
    }

    /**
     * Returns a List of Addresss that have at least one connection.
     */

    private List<Address> getAddressWithRelations() {
        List<Address> lst = new ArrayList<>();
        for (Address a : m_oGraph.vertices()) {
            if (!(m_oGraph.inDegree(a) == -1)) {
                lst.add(a);
            }
        }
        return lst;
    }

}
