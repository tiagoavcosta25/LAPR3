package lapr.project.graph.matrix;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests class
 * for AdjacencyMatrixGraph
 *
 * @author DEI_ESINF
 *
 */
public class AdjacencyMatrixGraphTest {

	public AdjacencyMatrixGraphTest() {
	}


	@Test
	public void testNumVertices() {

		System.out.println("Test of numVertices");
		AdjacencyMatrixGraph<String, Integer> instance = new AdjacencyMatrixGraph<>();
		assertTrue((instance.numVertices()==0));
		instance.insertVertex("Vert 1");
		assertTrue((instance.numVertices()==1));
		instance.insertVertex("Vert 2");
		assertTrue((instance.numVertices()==2));
		instance.removeVertex("Vert 1");
		assertTrue((instance.numVertices()==1));
		instance.removeVertex("Vert 2");
		assertTrue((instance.numVertices()==0));
	}

	@Test
	public void testNumEdges() {
		System.out.println("Test of numEdges");
		AdjacencyMatrixGraph<String, Integer> instance = new AdjacencyMatrixGraph<>();

		assertTrue((instance.numEdges()==0));

		for(int i = 1 ; i < 5; i++)
			instance.insertVertex("Vert "+i);

		instance.insertEdge("Vert 3",  "Vert 2", 12);
		assertTrue((instance.numEdges()==1));

		instance.insertEdge("Vert 1",  "Vert 4", 11);
		assertTrue((instance.numEdges()==2));

		instance.removeEdge("Vert 2", "Vert 3");
		assertTrue((instance.numEdges()==1));

		instance.removeEdge("Vert 4", "Vert 1");
		assertTrue((instance.numEdges()==0));
	}

	@Test
	public void testVertices() {
		System.out.println("Test of vertices getter");

		AdjacencyMatrixGraph<String, Integer> instance = new AdjacencyMatrixGraph<>();

		Iterator<String> itVert = instance.vertices().iterator();

		assertTrue((!itVert.hasNext()));

		instance.insertVertex("Vert 1");
		instance.insertVertex("Vert 2");

		itVert = instance.vertices().iterator();

		assertTrue((itVert.next().compareTo("Vert 1")==0));
		assertTrue((itVert.next().compareTo("Vert 2")==0));

		instance.removeVertex("Vert 1");

		itVert = instance.vertices().iterator();
		assertTrue((itVert.next().compareTo("Vert 2")==0));

		instance.removeVertex("Vert 2");

		itVert = instance.vertices().iterator();
		assertTrue((!itVert.hasNext()));

		instance.insertVertex("Vert 1");

		itVert = instance.vertices().iterator();

		instance.removeVertex("Vert 1");

		assertTrue((itVert.next().compareTo("Vert 1")==0));
		assertTrue((!instance.vertices().iterator().hasNext()));

	}

	@Test
	public void testEdges() {
		System.out.println("Test of Edges getter");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();

		Iterator<String> itEdge = instance.edges().iterator();

		assertTrue((!itEdge.hasNext()));

		for(int i = 1 ; i < 5; i++)
			instance.insertVertex("Vert "+i);

		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 3", "Edge 3");

		itEdge = instance.edges().iterator();

		assertTrue((itEdge.next().compareTo("Edge 1")==0));
		assertTrue((itEdge.next().compareTo("Edge 3")==0));
		assertTrue((itEdge.next().compareTo("Edge 2")==0));

		instance.removeEdge("Vert 1", "Vert 2");

		itEdge = instance.edges().iterator();
		assertTrue((itEdge.next().compareTo("Edge 3")==0));

		instance.removeEdge("Vert 1", "Vert 3");
		instance.removeEdge("Vert 2", "Vert 4");

		itEdge = instance.edges().iterator();
		assertTrue((!itEdge.hasNext()));
	}

	@Test
	public void testOutDegree() {
		System.out.println("Test of Out degree");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();

		for(int i = 1 ; i <= 5; i++)
			instance.insertVertex("Vert "+i);

		assertTrue((instance.outDegree("Vert 2")==0));

		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 3", "Edge 3");
		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 1", "Vert 5", "Edge 5");

		assertTrue((instance.outDegree("Vert 2")==3));

		instance.removeEdge("Vert 1", "Vert 2");

		assertTrue((instance.outDegree("Vert 2")==2));

		instance.removeEdge("Vert 2", "Vert 4");
		instance.removeEdge("Vert 2", "Vert 3");
		assertTrue((instance.outDegree("Vert 2")==0));
	}

	@Test
	public void testInDegree() {
		System.out.println("Test of In degree");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();

		for(int i = 1 ; i <= 5; i++)
			instance.insertVertex("Vert "+i);

		assertTrue((instance.inDegree("Vert 3")==0));

		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 3", "Edge 3");
		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 3", "Vert 5", "Edge 5");

		assertTrue((instance.inDegree("Vert 3")==3));

		instance.removeEdge("Vert 1", "Vert 3");

		assertTrue((instance.inDegree("Vert 3")==2));

		instance.removeEdge("Vert 3", "Vert 5");
		instance.removeEdge("Vert 2", "Vert 3");
		assertTrue((instance.outDegree("Vert 3")==0));

	}

	@Test
	public void testDirectConnections() {
		System.out.println("Test of Direct Connections");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();

		for (int i = 1 ; i <= 5; i++)
			instance.insertVertex("Vert "+i);

		Iterator<String> itVertex = instance.directConnections("Vert 2").iterator();

		assertTrue((!itVertex.hasNext()));

		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 3", "Edge 3");
		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 1", "Vert 5", "Edge 5");

		itVertex = instance.directConnections("Vert 1").iterator();

		assertTrue((itVertex.next().compareTo("Vert 2")==0));
		assertTrue((itVertex.next().compareTo("Vert 3")==0));
		assertTrue((itVertex.next().compareTo("Vert 5")==0));

		instance.removeEdge("Vert 1", "Vert 2");

		itVertex = instance.directConnections("Vert 1").iterator();
		assertTrue((itVertex.next().compareTo("Vert 3")==0));

		instance.removeEdge("Vert 1", "Vert 3");
		instance.removeEdge("Vert 1", "Vert 5");

		itVertex = instance.directConnections("Vert 1").iterator();
		assertTrue((!itVertex.hasNext()));

	}


	@Test
	public void testOutgoingEdges() {
		System.out.println("Test of Outgoing Edges");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();

		for(int i = 1 ; i <= 5; i++)
			instance.insertVertex("Vert "+i);

		Iterator<String> itEdge = instance.outgoingEdges("Vert 2").iterator();

		assertTrue((!itEdge.hasNext()));

		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 3", "Edge 3");
		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 1", "Vert 5", "Edge 5");

		itEdge = instance.outgoingEdges("Vert 1").iterator();

		assertTrue((itEdge.next().compareTo("Edge 1")==0));
		assertTrue((itEdge.next().compareTo("Edge 3")==0));
		assertTrue((itEdge.next().compareTo("Edge 5")==0));

		instance.removeEdge("Vert 1", "Vert 2");

		itEdge = instance.outgoingEdges("Vert 1").iterator();
		assertTrue((itEdge.next().compareTo("Edge 3")==0));

		instance.removeEdge("Vert 1", "Vert 3");
		instance.removeEdge("Vert 1", "Vert 5");

		itEdge = instance.outgoingEdges("Vert 1").iterator();
		assertTrue((!itEdge.hasNext()));
	}

	@Test
	public void testIncomingEdges() {
		System.out.println("Test of Incoming Edges");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();

		for(int i = 1 ; i <= 5; i++)
			instance.insertVertex("Vert "+i);

		Iterator<String> itEdge = instance.incomingEdges("Vert 2").iterator();

		assertTrue((!itEdge.hasNext()));

		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 5", "Edge 3");
		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 1", "Vert 3", "Edge 5");

		itEdge = instance.incomingEdges("Vert 1").iterator();

		assertTrue((itEdge.next().compareTo("Edge 1")==0));
		assertTrue((itEdge.next().compareTo("Edge 5")==0));
		assertTrue((itEdge.next().compareTo("Edge 3")==0));

		instance.removeEdge("Vert 1", "Vert 2");

		itEdge = instance.incomingEdges("Vert 1").iterator();
		assertTrue((itEdge.next().compareTo("Edge 5")==0));

		instance.removeEdge("Vert 1", "Vert 3");
		instance.removeEdge("Vert 1", "Vert 5");

		itEdge = instance.incomingEdges("Vert 1").iterator();
		assertTrue((!itEdge.hasNext()));
	}

	@Test
	public void testGetEdge() {
		System.out.println("Test of Get Edge");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();

		for(int i = 1 ; i <= 5; i++)
			instance.insertVertex("Vert "+i);

		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 5", "Edge 3");
		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 1", "Vert 3", "Edge 5");

		assertNull(instance.getEdge("Vert 2", "Vert 5"));

		assertEquals(0, instance.getEdge("Vert 4", "Vert 2").compareTo("Edge 2"));

		instance.removeEdge("Vert 2", "Vert 4");

		assertNull(instance.getEdge("Vert 2", "Vert 5"));
		instance.insertEdge("Vert 2", "Vert 4", "Edge 6");
		assertEquals(0, instance.getEdge("Vert 4", "Vert 2").compareTo("Edge 6"));
	}

	@Test
	public void testEndVertices() {
		System.out.println("Test of end vertices");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();

		for(int i = 1 ; i <= 5; i++)
			instance.insertVertex("Vert "+i);

		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 5", "Edge 3");
		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 3", "Vert 1", "Edge 5");

		Object [] endVertices = instance.endVertices("Edge 6");

		assertNull(endVertices);

		endVertices = instance.endVertices("Edge 5");

		String v1 = (String) endVertices[0];
		String v2 = (String) endVertices[1];

		assertEquals(0, v1.compareTo("Vert 1"));
		assertEquals(0, v2.compareTo("Vert 3"));
	}

	@Test
	public void testInsertEdge() {
		System.out.println("Test of insert edge");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();

		for(int i = 1 ; i < 5; i++)
			instance.insertVertex("Vert "+i);

		assertTrue((instance.numEdges()==0));

		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		assertTrue((instance.numEdges()==1));

		instance.insertEdge("Vert 1", "Vert 3", "Edge 2");
		assertTrue((instance.numEdges()==2));

		instance.removeEdge("Vert 1",  "Vert 3");
		assertTrue((instance.numEdges()==1));

		instance.insertEdge("Vert 2", "Vert 4", "Edge 3");
		assertTrue((instance.numEdges()==2));

		Iterator <String> itEdge = instance.edges().iterator();

		assertTrue((itEdge.next().compareTo("Edge 1")==0));
		assertTrue((itEdge.next().compareTo("Edge 3")==0));

	}

	@Test
	public void testInsertVertex() {
		System.out.println("Test of insert vertex");

		AdjacencyMatrixGraph<String, Integer> instance = new AdjacencyMatrixGraph<>();
		assertTrue((instance.numVertices()==0));
		instance.insertVertex("Vert 1");
		assertTrue((instance.numVertices()==1));
		instance.insertVertex("Vert 2");
		assertTrue((instance.numVertices()==2));

		assertFalse(instance.insertVertex("Vert 2"));

		instance.removeVertex("Vert 1");
		assertTrue((instance.numVertices()==1));

		instance.insertVertex("Vert 3");
		assertTrue((instance.numVertices()==2));

		instance.insertVertex("Vert 4");

		Iterator <String> itVert = instance.vertices().iterator();

		assertTrue((itVert.next().compareTo("Vert 2")==0));
		assertTrue((itVert.next().compareTo("Vert 3")==0));
		assertTrue((itVert.next().compareTo("Vert 4")==0));

		// Force resize of matrix

		for (int i = 0; i< 100 ; i++)
			instance.insertVertex("Vert "+ i);

		instance.insertEdge("Vert 1", "Vert 80", 80);

		Iterator<Integer> itEdge = instance.edges().iterator();

		assertEquals(80, (int) itEdge.next());
	}

	@Test
	public void testRemoveVertex() {
		System.out.println("Test of remove vertex");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();

		for(int i = 1 ; i <= 5; i++)
			instance.insertVertex("Vert "+i);

		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 5", "Edge 3");
		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 3", "Vert 1", "Edge 5");
		instance.insertEdge("Vert 4", "Vert 1", "Edge 6");


		assertTrue((instance.numVertices()==5));
		assertTrue((instance.numEdges()==6));

		instance.removeVertex("Vert 3");

		assertTrue((instance.numVertices()==4));
		assertTrue((instance.numEdges()==4));

		// Vertex 2 was removed - The vertices should now be 1, 2, 4, 5
		// Edge matrix should collapse - line and column 2 should refer to Vert 4
		// Requesting the edge between first and third vertices should give "Edge 6"
		// Requesting the edge between second and fourth vertices should give "Edge 2"

		Iterator <String> itVert = instance.vertices().iterator();

		String v1 = itVert.next();
		assertEquals(0, v1.compareTo("Vert 1"));
		String v2 = itVert.next();
		assertEquals(0, v2.compareTo("Vert 2"));
		String v3 = itVert.next();
		assertEquals(0, v3.compareTo("Vert 4"));
		String v4 = itVert.next();
		assertEquals(0, v4.compareTo("Vert 5"));

		assertEquals(0, instance.getEdge(v1, v3).compareTo("Edge 6"));
		assertEquals(0, instance.getEdge(v2, v3).compareTo("Edge 2"));
		assertNull(instance.getEdge(v2, v4));
	}

	@Test
	public void testRemoveEdge() {
		System.out.println("Test of remove edge");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();

		for(int i = 1 ; i < 5; i++)
			instance.insertVertex("Vert "+i);

		assertTrue((instance.numEdges()==0));

		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		assertTrue((instance.numEdges()==1));

		instance.insertEdge("Vert 1", "Vert 3", "Edge 2");
		assertTrue((instance.numEdges()==2));

		instance.removeEdge("Vert 1",  "Vert 3");
		assertTrue((instance.numEdges()==1));

		instance.insertEdge("Vert 2", "Vert 4", "Edge 3");
		assertTrue((instance.numEdges()==2));

		Iterator <String> itEdge = instance.edges().iterator();

		assertTrue((itEdge.next().compareTo("Edge 1")==0));
		assertTrue((itEdge.next().compareTo("Edge 3")==0));

		instance.removeEdge("Vert 4",  "Vert 2");
		instance.removeEdge("Vert 2",  "Vert 1");
		assertTrue((instance.numEdges()==0));
	}

	@Test
	public void testClone() {
		System.out.println("Test of Clone");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();

		for(int i = 1; i<= 5; i++)
			instance.insertVertex("Vert "+i);

		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 3", "Edge 3");
		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 1", "Vert 5", "Edge 5");

		@SuppressWarnings("unchecked")
		AdjacencyMatrixGraph<String, String> instance2 = (AdjacencyMatrixGraph<String, String>) instance.clone();

		assertEquals(instance.numVertices(), instance2.numVertices());
		assertEquals(instance.numEdges(), instance2.numEdges());

		Iterator <String> itVert = instance2.vertices().iterator();

		for(int j = 1; j<=5; j++)
			assertTrue((itVert.next().compareTo("Vert "+j)==0));

		String edge = instance2.getEdge("Vert 1", "Vert 2");
		assertEquals(0, edge.compareTo("Edge 1"));
		edge = instance2.getEdge("Vert 2", "Vert 4");
		assertEquals(0, edge.compareTo("Edge 2"));
		edge = instance2.getEdge("Vert 1", "Vert 3");
		assertEquals(0, edge.compareTo("Edge 3"));
		edge = instance2.getEdge("Vert 2", "Vert 3");
		assertEquals(0, edge.compareTo("Edge 4"));
		edge = instance2.getEdge("Vert 1", "Vert 5");
		assertEquals(0, edge.compareTo("Edge 5"));


		instance.removeVertex("Vert 2");


		// instance should be different
		assertEquals(4, instance.numVertices());
		assertEquals(2, instance.numEdges());

		// instance 2 should maintain the same as before

		assertEquals(5, instance2.numVertices());
		assertEquals(5, instance2.numEdges());


		itVert = instance2.vertices().iterator();

		itVert.next();

		assertEquals(0, itVert.next().compareTo("Vert 2"));

		edge = instance2.getEdge("Vert 2", "Vert 4");
		assertEquals(0, edge.compareTo("Edge 2"));

	}

	@Test
	public void testEqualsObject() {
		System.out.println("Test Equals");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();

		for(int i = 1; i<= 5; i++)
			instance.insertVertex("Vert "+i);

		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 3", "Edge 3");
		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 1", "Vert 5", "Edge 5");

		assertNotEquals(null, instance);

		assertEquals(instance, instance);

		assertEquals(instance, instance.clone());

		AdjacencyMatrixGraph<String, String> instance3 = new AdjacencyMatrixGraph<>();

		for(int i = 1; i<= 5; i++)
			instance3.insertVertex("Vert "+i);

		instance3.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance3.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance3.insertEdge("Vert 1", "Vert 3", "Edge 3");
		instance3.insertEdge("Vert 2", "Vert 3", "Edge 4");

		assertNotEquals(instance, instance3);

		instance3.insertEdge("Vert 1", "Vert 5", "Edge 5");

		assertEquals(instance, instance3);
	}

	@Test
	public void testToString() {
		System.out.println("Test of To String");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();

		for(int i = 1 ; i <= 5; i++)
			instance.insertVertex("Vert "+i);

		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 3", "Edge 3");
		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 1", "Vert 5", "Edge 5");

		System.out.println(instance);
	}


}