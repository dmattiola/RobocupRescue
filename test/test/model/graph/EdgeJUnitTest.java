package test.model.graph;

import model.graph.Edge;
import model.graph.Node;
import model.graph.TypeEdge;
import model.graph.TypeNode;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test JUnit on Model Edge
 * @author Dylan & Anthony
 */
public class EdgeJUnitTest {
    
    private Edge e;
    
    /**
     * Constructor of JUnit edge test
    */
    public EdgeJUnitTest(){ }
    
    /**
     * Initialize the test edge
    */
    @Before
    public void setUp() {
        Node n1 = new Node(0, 0, TypeNode.NORMAL);
        Node n2 = new Node(10, 10, TypeNode.RECHARGE);
        e = new Edge(n1, n2, TypeEdge.PLAT);
    }
    
    /**
     * Test if the edge legnth is > 0
     * With the controller, a length == 0 is impossible
    */
    @Test
    public void testLenth(){
        assertTrue("Longueur Positive", e.getLength()> 0);
    }
    
}
