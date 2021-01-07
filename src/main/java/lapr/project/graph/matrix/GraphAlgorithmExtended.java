package lapr.project.graph.matrix;

import tp2.model.User;

public class GraphAlgorithmExtended extends GraphAlgorithms {

    // Algoritmo de Floyd-Warshall, slides 30-35 da teórica

    public static int floydWarshallAlgorithm(AdjacencyMatrixGraph<User, Double> graph) {
        double maxDist = 0;
        for (int k = 0; k < graph.numVertices; k++) {
            for (int i = 0; i < graph.numVertices; i++) {
                if (i != k && (graph.privateGet(i, k)) != null) {
                    for (int j = 0; j < graph.numVertices; j++) {
                        if (i != j && k != j && graph.privateGet(k, j) != null) {
                            if (graph.privateGet(i, j) == null) {
                                double dist = (graph.privateGet(i, k)) + (graph.privateGet(k, j));
                                graph.privateSet(i, j, dist);
                                if (dist > maxDist) {
                                    maxDist = dist;
                                }
                            }
                        }
                    }
                }
            }
        }
        return (int) maxDist;
    }


}
