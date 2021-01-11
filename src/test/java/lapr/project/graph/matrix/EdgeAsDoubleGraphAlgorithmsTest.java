package lapr.project.graph.matrix;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author DEI-ESINF
 */
public class EdgeAsDoubleGraphAlgorithmsTest {

	AdjacencyMatrixGraph <String, Double> distanceMap = new AdjacencyMatrixGraph<>();

	public EdgeAsDoubleGraphAlgorithmsTest() {
	}

	@BeforeEach
	public void setUp() {
		distanceMap.insertVertex("Porto");
		distanceMap.insertVertex("Braga");
		distanceMap.insertVertex("Vila Real");
		distanceMap.insertVertex("Aveiro");
		distanceMap.insertVertex("Coimbra");
		distanceMap.insertVertex("Leiria");

		distanceMap.insertVertex("Viseu");
		distanceMap.insertVertex("Guarda");
		distanceMap.insertVertex("Castelo Branco");
		distanceMap.insertVertex("Lisboa");
		distanceMap.insertVertex("Faro");
		distanceMap.insertVertex("Évora");


		distanceMap.insertEdge("Porto", "Aveiro", 75.0);
		distanceMap.insertEdge("Porto", "Braga", 60.0);
		distanceMap.insertEdge("Porto", "Vila Real", 100.0);
		distanceMap.insertEdge("Viseu", "Guarda", 75.0);
		distanceMap.insertEdge("Guarda",  "Castelo Branco", 100.0);
		distanceMap.insertEdge("Aveiro", "Coimbra", 60.0);
		distanceMap.insertEdge("Coimbra", "Lisboa", 200.0);
		distanceMap.insertEdge("Coimbra",  "Leiria",  80.0);
		distanceMap.insertEdge("Aveiro", "Leiria", 120.0);
		distanceMap.insertEdge("Leiria", "Lisboa", 150.0);


		distanceMap.insertEdge("Aveiro", "Viseu", 85.0);
		distanceMap.insertEdge("Leiria", "Castelo Branco", 170.0);
		distanceMap.insertEdge("Lisboa", "Faro", 280.0);

	}

	@Test
	public void testShortestPath() {
		System.out.println("Test of shortest path");

		LinkedList<String> path = new LinkedList<>();

		assertEquals(EdgeAsDoubleGraphAlgorithms.shortestPath(distanceMap, "Porto", "LX", path), -1);

		assertEquals(EdgeAsDoubleGraphAlgorithms.shortestPath(distanceMap, "Porto", "Évora", path), -1);

		assertEquals(0, EdgeAsDoubleGraphAlgorithms.shortestPath(distanceMap, "Porto", "Porto", path));

		assertEquals(1, path.size());

		assertEquals(335, EdgeAsDoubleGraphAlgorithms.shortestPath(distanceMap, "Porto", "Lisboa", path));

		Iterator<String> it = path.iterator();

		assertEquals(0, it.next().compareTo("Porto"));
		assertEquals(0, it.next().compareTo("Aveiro"));
		assertEquals(0, it.next().compareTo("Coimbra"));
		assertEquals(0, it.next().compareTo("Lisboa"));

		assertEquals(255, EdgeAsDoubleGraphAlgorithms.shortestPath(distanceMap, "Braga", "Leiria", path));

		it = path.iterator();

		assertEquals(0, it.next().compareTo("Braga"));
		assertEquals(0, it.next().compareTo("Porto"));
		assertEquals(0, it.next().compareTo("Aveiro"));
		assertEquals(0, it.next().compareTo("Leiria"));

		assertEquals(335, EdgeAsDoubleGraphAlgorithms.shortestPath(distanceMap, "Porto", "Castelo Branco", path));
		assertEquals(5, path.size());

		it = path.iterator();

		assertEquals(0, it.next().compareTo("Porto"));
		assertEquals(0, it.next().compareTo("Aveiro"));
		assertEquals(0, it.next().compareTo("Viseu"));
		assertEquals(0, it.next().compareTo("Guarda"));
		assertEquals(0, it.next().compareTo("Castelo Branco"));

		// Changing Viseu to Guarda should change shortest path between Porto and Castelo Branco

		distanceMap.removeEdge("Viseu", "Guarda");
		distanceMap.insertEdge("Viseu", "Guarda", 125.0);

		assertEquals(365, EdgeAsDoubleGraphAlgorithms.shortestPath(distanceMap, "Porto", "Castelo Branco", path));
		assertEquals(4, path.size());

		it = path.iterator();

		assertEquals(0, it.next().compareTo("Porto"));
		assertEquals(0, it.next().compareTo("Aveiro"));
		assertEquals(0, it.next().compareTo("Leiria"));
		assertEquals(0, it.next().compareTo("Castelo Branco"));


	}

}
