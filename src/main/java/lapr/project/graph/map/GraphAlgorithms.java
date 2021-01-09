/*
 * A collection of graph algorithms.
 */
package lapr.project.graph.map;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author DEI-ESINF
 */

public class GraphAlgorithms {

    public GraphAlgorithms() {
    }

    /**
     * Performs breadth-first search of a Graph starting in a Vertex
     *
     * @param g    Graph instance
     * @param vert information of the Vertex that will be the source of the search
     * @return qbfs a queue with the vertices of breadth-first search
     */
    public static <V, E> LinkedList<V> BreadthFirstSearch(Graph<V, E> g, V vert) {
        if (!g.validVertex(vert)) {
            return null;
        }
        LinkedList<V> neighbors = new LinkedList();
        LinkedList<V> auxiliar = new LinkedList<>();
        auxiliar.add(vert);
        neighbors.add(vert);
        while (!auxiliar.isEmpty()) {
            vert = auxiliar.remove();
            for (V adj : g.adjVertices(vert)) {
                if (!neighbors.contains(adj)) {
                    neighbors.add(adj);
                    auxiliar.add(adj);
                }
            }
        }
        return neighbors;
    }

    /**
     * Performs depth-first search starting in a Vertex
     *
     * @param g     Graph instance
     * @param vOrig Vertex of graph g that will be the source of the search
     * @param vOrig set of discovered vertices
     * @param qdfs  queue with vertices of depth-first search
     */
    private static <V, E> void DepthFirstSearch(Graph<V, E> g, V vOrig, LinkedList<V> qdfs) {
        qdfs.add(vOrig);
        for (V adj : g.adjVertices(vOrig)) {
            if (!qdfs.contains(adj)) {
                DepthFirstSearch(g, adj, qdfs);
            }
        }
    }

    /**
     * @param g    Graph instance
     * @param vert information of the Vertex that will be the source of the search
     * @return qdfs a queue with the vertices of depth-first search
     */
    public static <V, E> LinkedList<V> DepthFirstSearch(Graph<V, E> g, V vert) {
        if (!g.validVertex(vert)) {
            return null;
        }
        LinkedList<V> path = new LinkedList<>();
        DepthFirstSearch(g, vert, path);
        return path;

    }

    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g     Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param vDest Vertex that will be the end of the path
     * @param vDest set of discovered vertices
     * @param path  stack with vertices of the current path (the path is in reverse order)
     * @param paths ArrayList with all the paths (in correct order)
     */


    private static <V, E> boolean allPaths(Graph<V, E> g, V vOrig, V vDest, Map<V, Boolean> visited, LinkedList<V> path, ArrayList<LinkedList<V>> paths) {
        if (vDest.equals(vOrig)) {
            path.add(vDest);
            paths.add(path);
            return true;
        }
        visited.put(vDest, true);
        for (V adj : g.adjVertices(vDest)) {
            if (visited.get(adj) != null) {
                continue;
            }
            if (allPaths(g, vOrig, adj, new HashMap<>(visited), new LinkedList<>(path), paths)) {
                path.add(adj);
            }
        }
        return false;
    }


    /**
     * @param g     Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @return paths ArrayList with all paths from voInf to vdInf
     */
    public static <V, E> ArrayList<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {
        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return null;
        }
        ArrayList<LinkedList<V>> paths = new ArrayList<>();
        allPaths(g, vOrig, vDest);
        return paths;
    }

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with nonnegative edge weights
     * This implementation uses Dijkstra's algorithm
     *
     * @param g        Graph instance
     * @param vOrig    Vertex that will be the source of the path
     * @param visited  set of discovered vertices
     * @param pathKeys minimum path vertices keys
     * @param dist     minimum distances
     */
    protected static <V, E> void shortestPathLength(Graph<V, E> g, V vOrig, V[] vertices,
                                                    boolean[] visited, int[] pathKeys, double[] dist) {
        int vkey = g.getKey(vOrig);
        dist[vkey] = 0;
        while (vkey != -1) {
            vOrig = vertices[vkey];
            visited[vkey] = true;
            for (V vAdj : g.adjVertices(vOrig)) {
                int vkeyAdj = g.getKey(vAdj);
                Edge<V, E> edge = g.getEdge(vOrig, vAdj);
                if (!visited[vkeyAdj] && dist[vkeyAdj] > dist[vkey] + edge.getWeight()) {
                    dist[vkeyAdj] = dist[vkey] + edge.getWeight();
                    pathKeys[vkeyAdj] = vkey;
                }
            }
            double minDist = Double.MAX_VALUE;
            vkey = -1;
            for (int i = 0; i < g.numVertices(); i++) {
                if (!visited[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    vkey = i;
                }
            }
        }

    }

    protected static <V, E> void shortestPathLength2(Graph<V, E> g, V vOrig,
                                                    boolean[] visited, V[] pathKeys, double[] dist) {

        int vKey = g.getKey(vOrig);
        dist[vKey] = 0;
        pathKeys[vKey] = vOrig;
        while (vOrig != null) {
            vKey = g.getKey(vOrig);
            visited[vKey] = true;
            for (V vAdj : g.adjVertices(vOrig)) {
                int vKeyAdj = g.getKey(vAdj);
                Edge<V, E> edge = g.getEdge(vOrig, vAdj);
                if (!visited[vKeyAdj] && dist[vKeyAdj] > dist[vKey] + edge.getWeight()) {
                    dist[vKeyAdj] = dist[vKey] + edge.getWeight();
                    pathKeys[vKeyAdj] = vOrig;
                }
            }
            double minDist = Double.MAX_VALUE;
            vOrig = null;
            for (V vert : g.vertices()) {
                int i = g.getKey(vert);
                if (!visited[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    vOrig = vert;
                }
            }
        }

    }

    protected static <V, E> void shortestPathLengthBorders(Graph<V, E> g, V vOrig,
                                                     boolean[] visited, V[] pathKeys, double[] dist) {

        int vKey = g.getKey(vOrig);
        dist[vKey] = 0;
        pathKeys[vKey] = vOrig;
        while (vOrig != null) {
            vKey = g.getKey(vOrig);
            visited[vKey] = true;
            for (V vAdj : g.adjVertices(vOrig)) {
                int vKeyAdj = g.getKey(vAdj);
                Edge<V, E> edge = g.getEdge(vOrig, vAdj);
                if (!visited[vKeyAdj] && dist[vKeyAdj] > dist[vKey] + 1) {
                    dist[vKeyAdj] = dist[vKey] + 1;
                    pathKeys[vKeyAdj] = vOrig;
                }
            }
            double minDist = Double.MAX_VALUE;
            vOrig = null;
            for (V vert : g.vertices()) {
                int i = g.getKey(vert);
                if (!visited[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    vOrig = vert;
                }
            }
        }

    }


    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf
     * The path is constructed from the end to the beginning
     *
     * @param g        Graph instance
     * @param vOrig    information of the Vertex origin
     * @param vDest    information of the Vertex destination
     * @param pathKeys minimum path vertices keys
     * @param path     stack with the minimum path (correct order)
     */
    protected static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest, V[] verts, int[] pathKeys, LinkedList<V> path) {

        if (!vOrig.equals(vDest)) {
            path.push(vDest);
            int vKey = g.getKey(vDest);
            int prevVKey = pathKeys[vKey];
            vDest = verts[prevVKey];

            getPath(g, vOrig, vDest, verts, pathKeys, path);
        } else {
            path.push(vOrig);
        }
    }

    protected static <V, E> void getPath2(Graph<V, E> g, V vOrig, V vDest, V[] pathKeys, LinkedList<V> path) {

        if (vOrig.equals(vDest)) {
            path.push(vOrig);
        } else {
            path.push(vDest);
            int vkey = g.getKey(vDest);
            vDest = pathKeys[vkey];

            getPath2(g, vOrig, vDest, pathKeys, path);
        }
    }

    //shortest-path between vOrig and vDest
    public static <V, E> double shortestPath(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath) {

        if (!g.validVertex(vOrig) || !g.validVertex(vDest))
            return 0;

        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts];
        int[] pathKeys = new int[nverts];
        double[] dist = new double[nverts];
        V[] vertices = g.allkeyVerts();

        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }
        shortestPathLength(g, vOrig, vertices, visited, pathKeys, dist);
        double lengthPath = dist[g.getKey(vDest)];

        if (lengthPath != Double.MAX_VALUE) {


            getPath(g, vOrig, vDest, vertices, pathKeys, shortPath);
            return lengthPath;
        }

        return 0;
    }

    //shortest-path between voInf and all other
    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig, ArrayList<LinkedList<V>> paths, ArrayList<Double> dists) {

        if (!g.validVertex(vOrig)) return false;
        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts];
        int[] pathKeys = new int[nverts];
        double[] dist = new double[nverts];
        V[] vertices = g.allkeyVerts();

        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }

        shortestPathLength(g, vOrig, vertices, visited, pathKeys, dist);

        dists.clear();
        paths.clear();
        for (int i = 0; i < nverts; i++) {
            paths.add(null);
            dists.add(null);
        }
        for (int i = 0; i < nverts; i++) {
            LinkedList<V> shortPath = new LinkedList<>();
            if (dist[i] != Double.MAX_VALUE)
                getPath(g, vOrig, vertices[i], vertices, pathKeys, shortPath);
            paths.set(i, shortPath);
            dists.set(i, dist[i]);
        }

        return true;
    }

    public static <V, E> double shortestPath2(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath) {

        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return -1;
        }

        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts];
        V[] pathKeys = (V[]) Array.newInstance(vOrig.getClass(), nverts);
        double[] dist = new double[nverts];

        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = null;
        }

        shortestPathLength2(g, vOrig, visited, pathKeys, dist);

        double lengthPath = dist[g.getKey(vDest)];

        if (lengthPath != Double.MAX_VALUE) {
            getPath2(g, vOrig, vDest, pathKeys, shortPath);
            return lengthPath;
        }
        return 0;
    }

    public static <V, E> int shortestPathBorders(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath) {

        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return 0;
        }

        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts];
        V[] pathKeys = (V[]) Array.newInstance(vOrig.getClass(), nverts);
        double[] dist = new double[nverts];

        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = null;
        }

        shortestPathLengthBorders(g, vOrig, visited, pathKeys, dist);

        double lengthPath = dist[g.getKey(vDest)];

        if (lengthPath != Double.MAX_VALUE) {
            getPath2(g, vOrig, vDest, pathKeys, shortPath);
            return (int) lengthPath;
        }
        return 0;
    }



    /**
     * Reverses the path
     *
     * @param path stack with path
     */
    private static <V, E> LinkedList<V> revPath(LinkedList<V> path) {

        LinkedList<V> pathcopy = new LinkedList<>(path);
        LinkedList<V> pathrev = new LinkedList<>();

        while (!pathcopy.isEmpty())
            pathrev.push(pathcopy.pop());

        return pathrev;
    }
}