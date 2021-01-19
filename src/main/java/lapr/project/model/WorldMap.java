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



}
