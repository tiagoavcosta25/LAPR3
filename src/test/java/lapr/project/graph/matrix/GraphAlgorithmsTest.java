
package lapr.project.graph.matrix;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author DEI-ESINF
 */
public class GraphAlgorithmsTest {

	AdjacencyMatrixGraph <String, String> completeMap = new AdjacencyMatrixGraph<>();
	AdjacencyMatrixGraph <String, String> incompleteMap;

	public GraphAlgorithmsTest() {
	}

	@BeforeAll
	public static void setUpClass() {
	}

	@SuppressWarnings("unchecked")
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

		completeMap.insertEdge("Porto", "Aveiro", "A1");
		completeMap.insertEdge("Porto", "Braga", "A3");
		completeMap.insertEdge("Porto", "Vila Real", "A4");
		completeMap.insertEdge("Viseu", "Guarda", "A25");
		completeMap.insertEdge("Guarda",  "Castelo Branco", "A23");
		completeMap.insertEdge("Aveiro", "Coimbra", "A1");
		completeMap.insertEdge("Coimbra", "Lisboa", "A1");
		completeMap.insertEdge("Coimbra",  "Leiria",  "A34");
		completeMap.insertEdge("Aveiro", "Leiria", "A17");
		completeMap.insertEdge("Leiria", "Lisboa", "A8");


		incompleteMap = (AdjacencyMatrixGraph<String, String>) completeMap.clone();

		completeMap.insertEdge("Aveiro", "Viseu", "A25");
		completeMap.insertEdge("Leiria", "Castelo Branco", "A23");
		completeMap.insertEdge("Lisboa", "Faro", "A2");

	}


	@Test
	public void testDFS() {
		System.out.println("Test of DFS");

		LinkedList<String> path;

		assertNull(GraphAlgorithms.DFS(completeMap, "LX"));

		path = GraphAlgorithms.DFS(incompleteMap, "Faro");

		assert path != null;
		assertEquals(1, path.size());

		Iterator<String> it = path.iterator();

		assertEquals(0, it.next().compareTo("Faro"));

		path = GraphAlgorithms.DFS(completeMap, "Porto");

		assert path != null;
		assertEquals(11, path.size());

		it = path.iterator();

		assertEquals(0, it.next().compareTo("Porto"));
		assertEquals(0, it.next().compareTo("Braga"));
		assertEquals(0, it.next().compareTo("Vila Real"));
		assertEquals(0, it.next().compareTo("Aveiro"));

		assertEquals(0, it.next().compareTo("Coimbra"));
		assertEquals(0, it.next().compareTo("Leiria"));
		assertEquals(0, it.next().compareTo("Castelo Branco"));
		assertEquals(0, it.next().compareTo("Guarda"));
		assertEquals(0, it.next().compareTo("Viseu"));
		assertEquals(0, it.next().compareTo("Lisboa"));
		assertEquals(0, it.next().compareTo("Faro"));


		path = GraphAlgorithms.DFS(incompleteMap, "Viseu");

		assert path != null;
		assertEquals(3, path.size());

		it = path.iterator();

		assertEquals(0, it.next().compareTo("Viseu"));
		assertEquals(0, it.next().compareTo("Guarda"));
		assertEquals(0, it.next().compareTo("Castelo Branco"));
	}

	@Test
	public void testBFS() {
		System.out.println("Test of BFS");

		LinkedList<String> path;

		assertNull(GraphAlgorithms.BFS(completeMap, "LX"));

		path = GraphAlgorithms.BFS(incompleteMap, "Faro");

		assert path != null;
		assertEquals(1, path.size());

		Iterator<String> it = path.iterator();

		assertEquals(0, it.next().compareTo("Faro"));


		path = GraphAlgorithms.BFS(completeMap, "Porto");

		assert path != null;
		assertEquals(11, path.size());

		it = path.iterator();

		assertEquals(0, it.next().compareTo("Porto"));
		assertEquals(0, it.next().compareTo("Braga"));
		assertEquals(0, it.next().compareTo("Vila Real"));
		assertEquals(0, it.next().compareTo("Aveiro"));

		assertEquals(0, it.next().compareTo("Coimbra"));
		assertEquals(0, it.next().compareTo("Leiria"));
		assertEquals(0, it.next().compareTo("Viseu"));
		assertEquals(0, it.next().compareTo("Lisboa"));
		assertEquals(0, it.next().compareTo("Castelo Branco"));
		assertEquals(0, it.next().compareTo("Guarda"));
		assertEquals(0, it.next().compareTo("Faro"));


		path = GraphAlgorithms.BFS(incompleteMap, "Viseu");

		assert path != null;
		assertEquals(3, path.size());

		it = path.iterator();

		assertEquals(0, it.next().compareTo("Viseu"));
		assertEquals(0, it.next().compareTo("Guarda"));
		assertEquals(0, it.next().compareTo("Castelo Branco"));

	}

}
