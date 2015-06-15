package test.model.robot;

import java.util.LinkedList;
import model.graph.Edge;
import model.graph.Node;
import model.graph.TypeEdge;
import model.graph.TypeNode;
import model.robot.Robot;
import model.robot.RobotCaterpillar;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test JUnit on Model Robot
 * @author Dylan & Anthony
 */
public class RobotJUnitTest {
    
    private Robot r;
    
    /**
     * Constructor of JUnit Robot test
    */
    public RobotJUnitTest(){ }

    /**
     * Initialize the test Robot
    */
    @Before
    public void setUp(){
        r = new RobotCaterpillar(new Node(0,0,TypeNode.NORMAL));
    }
   
    /**
     * Test the function move
    */
    @Test
    public void testMove(){
        Node n1 = r.getN();
        Node n2 = new Node(r.getSpeed()+10,10,TypeNode.NORMAL);
        LinkedList<Node> listNodes = new LinkedList<>();
        listNodes.add(n1);
        listNodes.add(n2);
        r.setListNodes(listNodes);
        Edge e = new Edge(n1,n2,TypeEdge.PLAT);
        assertTrue("Le robot a avancé sur l'arc",r.getPosition()+r.getSpeed() < e.getLength());
        r.move(e);
        assertTrue("Le robot a changé de noeud",r.getPosition()+r.getSpeed() >= e.getLength());
        r.move(e);
        // same work with the other robots
    }
    
    /**
     * Test the function extinguishFire
    */
    @Test
    public void testExtinguishFire(){
        r.getN().setType(TypeNode.INCENDIE);
        r.getN().kindleFire(50);
        int valueFire = r.getN().getFire() - r.getCapacity();
        int watter = r.getCapacity() - r.getN().getFire();
        r.extinguishFire();
        assertTrue("Vide sa capacité sur le feu et va remplir son reservoir", r.getN().getFire() > 0 && r.getCapacity() <= 0);
        r.setCapacity(40);
        r.extinguishFire();
        assertTrue("Le Robot éteint le feu",r.getN().getFire() <= 0);
    }
    
}
