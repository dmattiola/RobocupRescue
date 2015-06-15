package test.model.robot;

import model.graph.Edge;
import model.graph.Node;
import model.graph.TypeEdge;
import model.graph.TypeNode;
import model.robot.RobotCaterpillar;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test JUnit on Model RobotCaterpillar
 * @author Dylan & Anthony
 */
public class RobotCaterpillarJUnitTest {
    
    private RobotCaterpillar r;
    
    /**
     * Constructor of JUnit RobotCaterpillar test
    */
    public RobotCaterpillarJUnitTest(){ }
    
    /**
     * Initialize the test RobotCaterpillar
    */
    @Before
    public void setUp() {
        r = new RobotCaterpillar(new Node(10,10,TypeNode.NORMAL));
    }
    
    /**
     * Test the function possibleTrip
    */
    @Test
    public void testPossibleTrip(){
        Node n1 = new Node(0,0,TypeNode.NORMAL);
        Node n2 = new Node(10,10,TypeNode.NORMAL);
        Node n3 = new Node(20,20,TypeNode.NORMAL);
        Edge e = new Edge(n1, n2, TypeEdge.PLAT);
        assertTrue("Robot Caterpillar peut passer sur un arc plat",r.possibleTrip(e));
        e = new Edge(n1, n3, TypeEdge.ESCARPE);
        assertTrue("Robot Caterpillar ne peut pas passer sur un arc escarpé",!r.possibleTrip(e));
        e = new Edge(n3, n2, TypeEdge.INONDE);
        assertTrue("Robot Caterpillar peut passer sur un arc inondé",r.possibleTrip(e));
    }
    
}
