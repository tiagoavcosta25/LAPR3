package lapr.project.graph.map;

import java.util.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author DEI-ISEP
 */
public class GraphAlgorithmsTest {
    
    Graph<String,String> completeMap = new Graph<>(false);
    Graph<String,String> incompleteMap = new Graph<>(false);
    
    public GraphAlgorithmsTest() {
    }
    
    @BeforeEach
    public void setUp() {
        
        completeMap.insertVertex("Porto");
        completeMap.insertVertex("Braga");
        completeMap.insertVertex("Vila Real");
        completeMap.insertVertex("Aveiro");
        completeMap.insertVertex("Coimbra");
        completeMap.insertVertex("Leiria");

        completeMap.insertVertex("Viseu");
        completeMap.insertVertex("Guarda");
        completeMap.insertVertex("Castelo Branco");
        completeMap.insertVertex("Lisboa");
        completeMap.insertVertex("Faro");
                
        completeMap.insertEdge("Porto","Aveiro","A1",75);
        completeMap.insertEdge("Porto","Braga","A3",60);
        completeMap.insertEdge("Porto","Vila Real","A4",100);
        completeMap.insertEdge("Viseu","Guarda","A25",75);
        completeMap.insertEdge("Guarda","Castelo Branco","A23",100);
        completeMap.insertEdge("Aveiro","Coimbra","A1",60);
        completeMap.insertEdge("Coimbra","Lisboa","A1",200);
        completeMap.insertEdge("Coimbra","Leiria","A34",80);
        completeMap.insertEdge("Aveiro","Leiria","A17",120);
        completeMap.insertEdge("Leiria","Lisboa","A8",150);
       
        completeMap.insertEdge("Aveiro","Viseu","A25",85);
        completeMap.insertEdge("Leiria","Castelo Branco","A23",170);
        completeMap.insertEdge("Lisboa","Faro","A2",280);
        
        incompleteMap = completeMap.clone();
        
        incompleteMap.removeEdge("Aveiro","Viseu");
        incompleteMap.removeEdge("Leiria","Castelo Branco");
        incompleteMap.removeEdge("Lisboa","Faro");
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Test of BreadthFirstSearch method, of class GraphAlgorithms.
     */
    @Test
    public void testBreadthFirstSearch() {
        System.out.println("Test BreadthFirstSearch");

        assertNull(GraphAlgorithms.breadthFirstSearch(completeMap, "LX"));

        List<String> path = GraphAlgorithms.breadthFirstSearch(incompleteMap, "Faro");

        assert path != null;
        assertEquals(1, path.size());

        Iterator<String> it = path.iterator();
        assertEquals(0, it.next().compareTo("Faro"));
        
        path = GraphAlgorithms.breadthFirstSearch(incompleteMap, "Porto");
        assert path != null;
        assertEquals(7, path.size());
        
        path = GraphAlgorithms.breadthFirstSearch(incompleteMap, "Viseu");
        assert path != null;
        assertEquals(3, path.size());
    }

    /**
     * Test of DepthFirstSearch method, of class GraphAlgorithms.
     */
    @Test
    public void testDepthFirstSearch() {
        System.out.println("Test of DepthFirstSearch");

        LinkedList<String> path;

        assertNull(GraphAlgorithms.depthFirstSearch(completeMap, "LX"));

        path = GraphAlgorithms.depthFirstSearch(incompleteMap, "Faro");
        assert path != null;
        assertEquals(1, path.size());

        Iterator<String> it = path.iterator();
        assertEquals(0, it.next().compareTo("Faro"));

        path = GraphAlgorithms.depthFirstSearch(incompleteMap, "Porto");
        assert path != null;
        assertEquals(7, path.size());

        path = GraphAlgorithms.depthFirstSearch(incompleteMap, "Viseu");
        assert path != null;
        assertEquals(3, path.size());

        it = path.iterator();
        assertEquals(0, it.next().compareTo("Viseu"));
        assertEquals(0, it.next().compareTo("Guarda"));
        assertEquals(0, it.next().compareTo("Castelo Branco"));
    }

    /**
     * Test of allPaths method, of class GraphAlgorithms.
     */
    @Test
    public void testAllPaths() {
        System.out.println("Test of all paths");
        
        List<LinkedList<String>> paths;
       
        paths=GraphAlgorithms.allPaths(completeMap, "Porto", "LX");
        assertNull(paths);
 
        paths = GraphAlgorithms.allPaths(incompleteMap, "Porto", "Lisboa");
        assert paths != null;
        assertEquals(4, paths.size());
        
        paths=GraphAlgorithms.allPaths(incompleteMap, "Porto", "Faro");
        assert paths != null;
        assertEquals(0, paths.size());
    }

    /**
    * Test of shortestPath method, of class GraphAlgorithms.
    */
    @Test
    public void testShortestPath() {
        System.out.println("Test of shortest path");
		
	    LinkedList<String> shortPath = new LinkedList<>();
	    double lenpath;
        lenpath=GraphAlgorithms.shortestPath(completeMap,"Porto","LX",shortPath);
        assertEquals(0, lenpath);
	
        lenpath=GraphAlgorithms.shortestPath(incompleteMap,"Porto","Faro",shortPath);
        assertEquals(0, lenpath);
		
        GraphAlgorithms.shortestPath(completeMap,"Porto","Porto",shortPath);
        assertEquals(1, shortPath.size());
		
	    lenpath=GraphAlgorithms.shortestPath(incompleteMap,"Porto","Lisboa",shortPath);
        assertEquals(335, lenpath);
		
        Iterator<String> it = shortPath.iterator();

        assertEquals(0, it.next().compareTo("Porto"));
        assertEquals(0, it.next().compareTo("Aveiro"));
        assertEquals(0, it.next().compareTo("Coimbra"));
        assertEquals(0, it.next().compareTo("Lisboa"));

	    lenpath=GraphAlgorithms.shortestPath(incompleteMap,"Braga","Leiria",shortPath);
        assertEquals(255, lenpath);
		
        it = shortPath.iterator();

        assertEquals(0, it.next().compareTo("Braga"));
        assertEquals(0, it.next().compareTo("Porto"));
        assertEquals(0, it.next().compareTo("Aveiro"));
        assertEquals(0, it.next().compareTo("Leiria"));
        
        shortPath.clear();
        lenpath=GraphAlgorithms.shortestPath(completeMap,"Porto","Castelo Branco",shortPath);
        assertEquals(335, lenpath);
        assertEquals(5, shortPath.size());

        it = shortPath.iterator();

        assertEquals(0, it.next().compareTo("Porto"));
        assertEquals(0, it.next().compareTo("Aveiro"));
        assertEquals(0, it.next().compareTo("Viseu"));
        assertEquals(0, it.next().compareTo("Guarda"));
        assertEquals(0, it.next().compareTo("Castelo Branco"));

        //Changing Edge: Aveiro-Viseu with Edge: Leiria-C.Branco 
        //should change shortest path between Porto and Castelo Branco

        completeMap.removeEdge("Aveiro", "Viseu");
        completeMap.insertEdge("Leiria","Castelo Branco","A23",170);
	    shortPath.clear();
        lenpath=GraphAlgorithms.shortestPath(completeMap,"Porto","Castelo Branco",shortPath);
        assertEquals(365, lenpath);
        assertEquals(4, shortPath.size());

        it = shortPath.iterator();

        assertEquals(0, it.next().compareTo("Porto"));
        assertEquals(0, it.next().compareTo("Aveiro"));
        assertEquals(0, it.next().compareTo("Leiria"));
        assertEquals(0, it.next().compareTo("Castelo Branco"));
		
    }
    
     /**
    * Test of shortestPaths method, of class GraphAlgorithms.
    */
    @Test
    public void testShortestPaths() {
        System.out.println("Test of shortest path");
		
	    ArrayList <LinkedList<String>> paths = new ArrayList<>();
        ArrayList <Double> dists = new ArrayList<>();
        
        GraphAlgorithms.shortestPaths(completeMap,"Porto",paths,dists);
        
        assertEquals(paths.size(), dists.size());
        assertEquals(completeMap.numVertices(), paths.size());
        assertEquals(1, paths.get(completeMap.getKey("Porto")).size());
        assertEquals(Arrays.asList("Porto","Aveiro","Coimbra","Lisboa"), paths.get(completeMap.getKey("Lisboa")));
	    assertEquals(Arrays.asList("Porto","Aveiro","Viseu","Guarda","Castelo Branco"), paths.get(completeMap.getKey("Castelo Branco")));
        assertEquals(335, dists.get(completeMap.getKey("Castelo Branco")),0.01);

        //Changing Edge: Aveiro-Viseu with Edge: Leiria-C.Branco 
        //should change shortest path between Porto and Castelo Branco        
        completeMap.removeEdge("Aveiro", "Viseu");
        completeMap.insertEdge("Leiria","Castelo Branco","A23",170);
        GraphAlgorithms.shortestPaths(completeMap,"Porto",paths,dists);
        assertEquals(365, dists.get(completeMap.getKey("Castelo Branco")),0.01);
        assertEquals(Arrays.asList("Porto","Aveiro","Leiria","Castelo Branco"), paths.get(completeMap.getKey("Castelo Branco")));

        
        
        GraphAlgorithms.shortestPaths(incompleteMap,"Porto",paths,dists);
	    assertEquals(Double.MAX_VALUE, dists.get(completeMap.getKey("Faro")),0.01);
        assertEquals(335, dists.get(completeMap.getKey("Lisboa")),0.01);
        assertEquals(Arrays.asList("Porto","Aveiro","Coimbra","Lisboa"), paths.get(completeMap.getKey("Lisboa")));
        assertEquals(335, dists.get(completeMap.getKey("Lisboa")),0.01);

        GraphAlgorithms.shortestPaths(incompleteMap,"Braga",paths,dists);
        assertEquals(255, dists.get(completeMap.getKey("Leiria")),0.01);
        assertEquals(Arrays.asList("Braga", "Porto","Aveiro","Leiria"), paths.get(completeMap.getKey("Leiria")));
    }
}

