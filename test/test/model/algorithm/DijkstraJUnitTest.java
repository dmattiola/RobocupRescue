package test.model.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import model.algorithme.Dijkstra;
import model.graph.*;
import model.robot.Robot;
import model.robot.RobotLegs;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test JUnit on Model DijkstraJUnitTest
 * @author Dylan & Anthony
*/
public class DijkstraJUnitTest {
    
    private ArrayList<Node> listN;
    private ArrayList<Edge> listE;
    private Robot rt;
    private Node nt1,nt2,nt3;
    private Dijkstra algo;
    private Graph g;
    
    /**
     * Constructor of JUnit dijkstra test
    */
    public DijkstraJUnitTest(){
        this.listN = new ArrayList<>();
        this.listE = new ArrayList<>();
        this.rt = null;
        algo = new Dijkstra();
        g = new Graph();
    }
    
    /**
     * Initialize all objects to test dikjstra methods
    */
    @Before
    public void setUp(){
        Edge e1, e2, e3, e4, e5,e6;
        Node n1 ,n2,n3,n4,n5,n6;
        n1 = new Node(1,0, 100, TypeNode.NORMAL);
        n2 = new Node(2,0, 200, TypeNode.NORMAL);
        n3 = new Node(3,100, 200, TypeNode.NORMAL);
        n4 = new Node(4,100, 400, TypeNode.NORMAL);
        n5 = new Node(5,400, 200, TypeNode.NORMAL);
        n6 = new Node(6,400, 200, TypeNode.NORMAL);
        listN.add(n1);listN.add(n2);listN.add(n3);listN.add(n4);listN.add(n5);
        e1 = new Edge(n2,n3, TypeEdge.PLAT);
        e2 = new Edge(n2,n4, TypeEdge.PLAT);
        e3 = new Edge(n3,n4, TypeEdge.PLAT);
        e4 = new Edge(n3,n1, TypeEdge.PLAT);
        e5 = new Edge(n5,n1, TypeEdge.INONDE);
        e6 = new Edge(n3, n5,TypeEdge.PLAT);
        listE.add(e1);listE.add(e2);listE.add(e3);listE.add(e4);listE.add(e5);listE.add(e6);
        g.setListEdge(listE);
        g.setListNodes(listN);
        rt = new RobotLegs(n2);
        algo.setR(rt);
        nt1 = n1;
        nt2 = n5;
        nt3 = n6;
    }

    /**
     * Test function shortestTrip
    */
    @Test
    public void testShortestTrip(){
        LinkedList<Node> res = algo.shortestTrip(g, nt1, rt);
        assertTrue("Wrong Path to nt1 (size error) (easy path)", res.size() == 2);
        assertTrue("Wrong Path to nt1 (itineraire faux 1)(easy path)", res.get(0).getId()==3);
        assertTrue("Wrong Path to nt1 (itineraire faux 2)(easy path)", res.get(1).getId()==1);
        
        res = algo.shortestTrip(g, nt2, rt);
        assertTrue("Wrong Path to nt2 (size error) (with impossible edge)", res.size() == 2);
        assertTrue("Wrong Path to nt2 (wrong itinerary 1)(with impossible edge)", res.get(0).getId()==3);
        assertTrue("Wrong Path to nt2 (wrong itinerary 2)(with impossible edge)", res.get(1).getId()==5);
        
        res = algo.shortestTrip(g, nt3, rt);
        assertTrue("Wrong Path to nt3 (not null path) (impossible path)", res==null);
    }
    
}