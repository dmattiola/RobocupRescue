package test.model.graph;

import model.graph.Node;
import model.graph.TypeNode;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Test JUnit on Model Node
 * @author Dylan & Anthony
 */
public class NodeJUnitTest {

    private Node nodeI;
    private Node nodeB;

    /**
     * Constructor of JUnit node test
    */
    public NodeJUnitTest(){ }
    
    /**
     * Initialize the two test nodes
    */
    @Before
    public void setUp() {
        nodeI = new Node(0,0,TypeNode.INCENDIE);
        nodeB = new Node(1,1, TypeNode.NORMAL);
    }

    /**
     * Test function kindleFire
     * if fire value > 0
     * and fire type is INCENDIE
    */
    @Test
    public void testKindleFire(){
        nodeB.kindleFire(50);
        assertTrue("Feu allumé", nodeB.getFire() > 0);
        assertTrue("Feu allumé", nodeB.getType() == TypeNode.INCENDIE);
    }
     
    /**
     * Test function extinguishFire
     * if fire value == 0
     * and fire type is NORMAL
    */
    @Test
    public void testExtinguishFire(){
        nodeI.extinguishFire();
        assertTrue("Feu eteint", nodeI.getFire() == 0);
        assertTrue("Feu allumé", nodeI.getType() == TypeNode.NORMAL);
    }

}
