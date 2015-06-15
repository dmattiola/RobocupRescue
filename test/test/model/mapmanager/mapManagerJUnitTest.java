package test.model.mapmanager;

import java.util.ArrayList;
import model.algorithme.Dijkstra;
import model.graph.*;
import model.mapmanager.MapManager;
import model.robot.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test JUnit on Model mapManagerJUnitTest
 * @author Dylan & Anthony
*/
public class mapManagerJUnitTest {
    
    private MapManager map;
    private Node noeud_test;
    private Robot res;
    
    /**
     * Constructor of JUnit mapManager test
    */
    public mapManagerJUnitTest(){ }
    
    /**
     * Initialize all objects to test mapManager methods
    */
    @Before
    public void setUp(){
        Graph g = new Graph();
        ArrayList<Node> listn = new ArrayList<>();
        ArrayList<Edge> liste = new ArrayList<>();
        Node n1, n2, n3, n4, n5, n6;
        n1 = new Node(100, 100, TypeNode.NORMAL);
        n2 = new Node(110, 110, TypeNode.NORMAL);
        n3 = new Node(90, 90, TypeNode.NORMAL);
        n4 = new Node(120, 120, TypeNode.NORMAL);
        n5 = new Node(80, 80, TypeNode.INCENDIE);
        n6 = new Node(90, 110, TypeNode.INCENDIE);
        listn.add(n1);
        listn.add(n2);
        listn.add(n3);
        listn.add(n4);
        listn.add(n5);
        listn.add(n6);
        Edge e1,e2,e3,e4,e5,e6;
        e1 = new Edge(n1, n2, TypeEdge.PLAT);
        e2 = new Edge(n2, n4, TypeEdge.PLAT);
        e3 = new Edge(n2, n6, TypeEdge.PLAT);
        e4 = new Edge(n1, n4, TypeEdge.PLAT);
        e5 = new Edge(n4, n3, TypeEdge.PLAT);
        e6 = new Edge(n5, n3, TypeEdge.PLAT);
        liste.add(e1);
        liste.add(e2);
        liste.add(e3);
        liste.add(e4);
        liste.add(e5);
        liste.add(e6);
        map = new MapManager(new Dijkstra(), g);
        RobotCaterpillar r1 = new RobotCaterpillar(n1);
        map.getListRobots().add(r1);
        RobotLegs r2 = new RobotLegs(n2);
        map.getListRobots().add(r2);
        RobotCrossCountry r3 = new RobotCrossCountry(n3);
        map.getListRobots().add(r3);
        noeud_test = n4;
        res = r2;
    }

    /**
     * Test the function closestRobot
     * not finished yet
    */
    @Test
    public void testClosestRobot() {
       //assertFalse("aucun robot pret",map.closestRobot(noeud_test) == null);
       //assertTrue("Erreur robot closest", map.closestRobot(noeud_test) != null);
      
    }
    
}
