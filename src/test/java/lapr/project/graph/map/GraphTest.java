package lapr.project.graph.map;

import org.junit.jupiter.api.Test;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author DEI-ISEP
 */
public class GraphTest {
    
    Graph<String, String> instance = new Graph<>(true) ;
    
    public GraphTest() {
    }
    
    /**
     * Test of numVertices method, of class Graph.
     */
    @Test
    public void testNumVertices() {
        System.out.println("Test numVertices");
                      
        assertTrue((instance.numVertices()==0));
        
        instance.insertVertex("A");
        assertTrue((instance.numVertices()==1));
        
        instance.insertVertex("B");
        assertTrue((instance.numVertices()==2));
        
        instance.removeVertex("A");
        assertTrue((instance.numVertices()==1));
        
        instance.removeVertex("B");
        assertTrue((instance.numVertices()==0));
    }
    
    /**
     * Test of vertices method, of class Graph.
     */
    @Test
    public void testVertices() {
        System.out.println("Test vertices");

        Iterator<String> itVerts = instance.vertices().iterator();

        assertFalse(itVerts.hasNext());
        
        instance.insertVertex("A");
        instance.insertVertex("B");
        	
        itVerts = instance.vertices().iterator();
                
        assertTrue((itVerts.next().compareTo("A")==0));
        assertTrue((itVerts.next().compareTo("B")==0));

        instance.removeVertex("A");
		
        itVerts = instance.vertices().iterator();
        assertEquals(0, (itVerts.next().compareTo("B")));

	    instance.removeVertex("B");
		
        itVerts = instance.vertices().iterator();
        assertFalse(itVerts.hasNext());
    }

    /**
     * Test of numEdges method, of class Graph.
     */
    @Test
    public void testNumEdges() {
        System.out.println("Test numEdges");
        
        assertTrue((instance.numEdges()==0));

        instance.insertEdge("A","B","Edge1",6);
        assertTrue((instance.numEdges()==1));
        
        instance.insertEdge("A","C","Edge2",1);
        assertTrue((instance.numEdges()==2));
        
        instance.removeEdge("A","B");
        assertTrue((instance.numEdges()==1));

        instance.removeEdge("A","C");
        assertTrue((instance.numEdges()==0));
    }

    /**
     * Test of edges method, of class Graph.
     */
    @Test
    public void testEdges() {
        System.out.println("Test Edges");

        Iterator<Edge<String,String>> itEdge = instance.edges().iterator();

        assertTrue((!itEdge.hasNext()));

        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);

        itEdge = instance.edges().iterator();
        
        itEdge.next(); itEdge.next();
        assertEquals("Edge3", itEdge.next().getElement());
        
        itEdge.next(); itEdge.next();
        assertEquals("Edge6", itEdge.next().getElement());
        
        instance.removeEdge("A","B");

        itEdge = instance.edges().iterator();
        assertEquals("Edge2", itEdge.next().getElement());

        instance.removeEdge("A","C"); instance.removeEdge("B","D");
        instance.removeEdge("C","D"); instance.removeEdge("C","E");
        instance.removeEdge("D","A"); instance.removeEdge("E","D");
        instance.removeEdge("E","E");
        itEdge = instance.edges().iterator();
        assertTrue((!itEdge.hasNext()));
    }

    /**
     * Test of getEdge method, of class Graph.
     */
    @Test
    public void testGetEdge() {
        System.out.println("Test getEdge");
		        
        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);

        assertNull(instance.getEdge("A", "E"));

        assertEquals("Edge3", instance.getEdge("B", "D").getElement());
        assertNull(instance.getEdge("D", "B"));

	    instance.removeEdge("D","A");
        assertNull(instance.getEdge("D", "A"));

        assertEquals("Edge8", instance.getEdge("E", "E").getElement());
    }

    /**
     * Test of endVertices method, of class Graph.
     */
    @Test
    public void testEndVertices() {
        System.out.println("Test endVertices");
        			 
        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);

        Edge<String,String> edge1 = instance.getEdge("A","B");
        assertEquals("A", instance.endVertices(edge1)[0]);
        assertEquals("B", instance.endVertices(edge1)[1]);
    }

    /**
     * Test of opposite method, of class Graph.
     */
    @Test
    public void testOpposite() {
        System.out.println("Test opposite");
        		
        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");
        
        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);
		     
        Edge<String,String> edge5 = instance.getEdge("C","E");
        String vert = instance.opposite("A", edge5);
        assertNull(vert);
        
        Edge<String,String> edge1 = instance.getEdge("A","B");
        vert = instance.opposite("A", edge1);
        assertEquals("B", vert);
        
        Edge<String,String> edge8 = instance.getEdge("E","E");
        vert = instance.opposite("E", edge8);
        assertEquals("E", vert);
    }

    /**
     * Test of outDegree method, of class Graph.
     */
    @Test
    public void testOutDegree() {
        System.out.println("Test outDegree");
        		
        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");
        
        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);
		    
        int outdeg = instance.outDegree("G");
        assertEquals(outdeg, -1);
        
        outdeg = instance.outDegree("A");
        assertEquals(2, outdeg);
        
        outdeg = instance.outDegree("B");
        assertEquals(1, outdeg);
         
        outdeg = instance.outDegree("E");
        assertEquals(2, outdeg);
    }

    /**
     * Test of inDegree method, of class Graph.
     */
    @Test
    public void testInDegree() {
        System.out.println("Test inDegree");
        
        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");
        
        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);
		       
        int indeg = instance.inDegree("G");
        assertEquals(indeg, -1);
        
        indeg = instance.inDegree("A");
        assertEquals(1, indeg);
        
        indeg = instance.inDegree("D");
        assertEquals(3, indeg);
         
        indeg = instance.inDegree("E");
        assertEquals(2, indeg);
    }

    /**
     * Test of outgoingEdges method, of class Graph.
     */
    @Test
    public void testOutgoingEdges() {
        System.out.println(" Test outgoingEdges");
        		
        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");
        
        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);
		                        
        Iterator<Edge<String,String>> itEdge = instance.outgoingEdges("C").iterator();
        Edge<String,String> first = itEdge.next();
        Edge<String,String> second = itEdge.next();
        assertTrue(((first.getElement().equals("Edge4") && second.getElement().equals("Edge5")) ||
                    (first.getElement().equals("Edge5") && second.getElement().equals("Edge4")) ));
        
        instance.removeEdge("E","E");
        
        itEdge = instance.outgoingEdges("E").iterator();
        assertEquals("Edge7", itEdge.next().getElement());
        
        instance.removeEdge("E","D");

        itEdge = instance.outgoingEdges("E").iterator();
        assertTrue((!itEdge.hasNext()));
    }

    /**
     * Test of incomingEdges method, of class Graph.
     */
    @Test
    public void testIncomingEdges() {
        		
        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");
        
        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);
		      
        Iterator<Edge<String,String>> itEdge = instance.incomingEdges("D").iterator();

        assertEquals("Edge3", itEdge.next().getElement());
        assertEquals("Edge4", itEdge.next().getElement());
        assertEquals("Edge7", itEdge.next().getElement());
        
        itEdge = instance.incomingEdges("E").iterator();

        assertEquals("Edge5", itEdge.next().getElement());
        assertEquals("Edge8", itEdge.next().getElement());
        
        instance.removeEdge("E","E");
        
        itEdge = instance.incomingEdges("E").iterator();

        assertEquals("Edge5", itEdge.next().getElement());
        
        instance.removeEdge("C","E");

        itEdge = instance.incomingEdges("E").iterator();
        assertTrue((!itEdge.hasNext()));
    }

    /**
     * Test of insertVertex method, of class Graph.
     */
    @Test
    public void testInsertVertex() {
        System.out.println("Test insertVertex");
        
        instance.insertVertex("A");   
        instance.insertVertex("B");    
        instance.insertVertex("C");    
        instance.insertVertex("D");      
        instance.insertVertex("E");
             
        Iterator <String> itVert = instance.vertices().iterator();

        assertEquals("A", itVert.next());
        assertEquals("B", itVert.next());
        assertEquals("C", itVert.next());
        assertEquals("D", itVert.next());
        assertEquals("E", itVert.next());
    }
    
    /**
     * Test of insertEdge method, of class Graph.
     */
    @Test
    public void testInsertEdge() {
        System.out.println("Test insertEdge");
        
        assertTrue((instance.numEdges()==0));

        instance.insertEdge("A","B","Edge1",6);
        assertTrue((instance.numEdges()==1));
        
        instance.insertEdge("A","C","Edge2",1);
        assertTrue((instance.numEdges()==2));
        
        instance.insertEdge("B","D","Edge3",3);
        assertTrue((instance.numEdges()==3));
        
        instance.insertEdge("C","D","Edge4",4);
        assertTrue((instance.numEdges()==4));
        
        instance.insertEdge("C","E","Edge5",1);
        assertTrue((instance.numEdges()==5));
        
        instance.insertEdge("D","A","Edge6",2);
        assertTrue((instance.numEdges()==6));
        
        instance.insertEdge("E","D","Edge7",1);
        assertTrue((instance.numEdges()==7));
        
        instance.insertEdge("E","E","Edge8",1);
        assertTrue((instance.numEdges()==8));
        
        Iterator <Edge<String,String>> itEd = instance.edges().iterator();
		
        itEd.next(); itEd.next();
        assertEquals("Edge3", itEd.next().getElement());
        itEd.next(); itEd.next();
        assertEquals("Edge6", itEd.next().getElement());
    }

    /**
     * Test of removeVertex method, of class Graph.
     */
    @Test
    public void testRemoveVertex() {       
        System.out.println("Test removeVertex");
        
        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");
 
        instance.removeVertex("C");
        assertTrue((instance.numVertices()==4));
      
        Iterator<String> itVert = instance.vertices().iterator();
        assertEquals("A", itVert.next());
        assertEquals("B", itVert.next());
        assertEquals("D", itVert.next());
        assertEquals("E", itVert.next());
        
        instance.removeVertex("A");
        assertTrue((instance.numVertices()==3));
   
        itVert = instance.vertices().iterator();
        assertEquals("B", itVert.next());
        assertEquals("D", itVert.next());
        assertEquals("E", itVert.next());

        instance.removeVertex("E");
        assertTrue((instance.numVertices()==2));

        itVert = instance.vertices().iterator();

        assertEquals("B", itVert.next());
        assertEquals("D", itVert.next());
        
        instance.removeVertex("B"); instance.removeVertex("D");
        assertTrue((instance.numVertices()==0));
    }
    
    /**
     * Test of removeEdge method, of class Graph.
     */
    @Test
    public void testRemoveEdge() {     
        System.out.println("Test removeEdge");
        
        assertTrue((instance.numEdges()==0));

        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);
		     
        assertTrue((instance.numEdges()==8));
        
        instance.removeEdge("E","E");
        assertTrue((instance.numEdges()==7));
        
        Iterator <Edge<String,String>> itEd = instance.edges().iterator();
		
        itEd.next(); itEd.next();
        assertEquals("Edge3", itEd.next().getElement());
        itEd.next(); itEd.next();
        assertEquals("Edge6", itEd.next().getElement());
        
        instance.removeEdge("C","D");
        assertTrue((instance.numEdges()==6));
        
        itEd = instance.edges().iterator();	
        itEd.next(); itEd.next();
        assertEquals("Edge3", itEd.next().getElement());
        assertEquals("Edge5", itEd.next().getElement());
        assertEquals("Edge6", itEd.next().getElement());
        assertEquals("Edge7", itEd.next().getElement());
    }
    
    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testClone() {
	System.out.println("Test Clone");
         
        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);
        
        Graph<String,String> instClone = instance.clone();

        assertEquals(instance.numVertices(), instClone.numVertices());
        assertEquals(instance.numEdges(), instClone.numEdges());
	
        //vertices should be equal
        Iterator<String> itvertClone = instClone.vertices().iterator();
        for (String s : instance.vertices()) assertEquals(s, itvertClone.next());
    }

    @Test
    public void testEquals() {
        System.out.println("Test Equals");
              
        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);

        assertNotEquals(null, instance);

        assertEquals(instance, instance);

        assertEquals(instance, instance.clone());
        
        Graph<String,String> other = instance.clone();
       
        other.removeEdge("E","E");
        assertNotEquals(instance, other);
        
        other.insertEdge("E","E","Edge8",1);
        assertEquals(instance, other);
        
        other.removeVertex("D");
        assertNotEquals(instance, other);
        
    }
    
    
    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testToString() {
        System.out.println(instance);
    }
}

