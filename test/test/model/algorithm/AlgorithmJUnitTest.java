package test.model.algorithm;

import java.util.ArrayList;
import java.util.List;
import model.algorithme.Dijkstra;
import model.graph.*;
import model.robot.RobotLegs;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test JUnit on Model AlgorithmJUnitTest
 * @author Dylan & Anthony
*/
public class AlgorithmJUnitTest {
    
    private ArrayList<Node> listN;
    private ArrayList<Edge> listE;
    private Dijkstra algo;
    
    /**
     * Constructor of JUnit algorithm test
    */
    public AlgorithmJUnitTest(){ }
    
    /**
     * Initialize algorithm dikjstra test and lists
    */
    @Before
    public void setUp(){
        this.listN = new ArrayList<>();
        this.listE = new ArrayList<>();
        algo = new Dijkstra();
    }

    /**
     * Test function getLength
    */
    @Test
    public void testGetLentgth(){
        Edge e1, e2, e3, e4, e5;
        Node n1 ,n2,n3,n4,n5;
        n1 = new Node(0, 100, TypeNode.NORMAL);
        n2 = new Node(0, 200, TypeNode.NORMAL);
        n3 = new Node(100, 200, TypeNode.NORMAL);
        n4 = new Node(100, 400, TypeNode.NORMAL);
        n5 = new Node(400, 200, TypeNode.NORMAL);
        listN.add(n1);listN.add(n2);listN.add(n3);listN.add(n4);listN.add(n5);
        e1 = new Edge(n2,n3, TypeEdge.PLAT);
        e2 = new Edge(n2,n4, TypeEdge.PLAT);
        e3 = new Edge(n3,n4, TypeEdge.PLAT);
        e4 = new Edge(n3,n1, TypeEdge.PLAT);
        e5 = new Edge(n5,n1, TypeEdge.PLAT);
        listE.add(e1);listE.add(e2);listE.add(e3);listE.add(e4);listE.add(e5);
        algo.setListNodes(listN);
        algo.setListEdges(listE);
        assertTrue("Error : wrong length t1.0", algo.getLength(n1, n2)==0);
        assertTrue("Error : wrong length t1.1", algo.getLength(n3, n2)==e1.getLength());
        assertTrue("Error : wrong length t1.2", algo.getLength(n2, n3)==e1.getLength());    
    }
    
    /**
     * Test function gesNeighbourgs
    */
    public void testGetNeighbourgs(){
        Edge e1, e2, e3, e4, e5;
        Node n1 ,n2,n3,n4,n5,n6;
        n1 = new Node(0, 100, TypeNode.NORMAL);
        n2 = new Node(0, 200, TypeNode.NORMAL);
        n3 = new Node(100, 200, TypeNode.NORMAL);
        n4 = new Node(100, 400, TypeNode.NORMAL);
        n5 = new Node(400, 200, TypeNode.NORMAL);
        n6 = new Node(400, 200, TypeNode.NORMAL);
        listN.add(n1);listN.add(n2);listN.add(n3);listN.add(n4);listN.add(n5);
        e1 = new Edge(n2,n3, TypeEdge.PLAT);
        e2 = new Edge(n2,n4, TypeEdge.PLAT);
        e3 = new Edge(n3,n4, TypeEdge.PLAT);
        e4 = new Edge(n3,n1, TypeEdge.PLAT);
        e5 = new Edge(n5,n1, TypeEdge.INONDE);
        listE.add(e1);listE.add(e2);listE.add(e3);listE.add(e4);listE.add(e5);
        algo.setListNodes(listN);
        algo.setListEdges(listE);
        algo.setR(new RobotLegs(n6));
        List<Node> res = algo.getNeighbours(n6);
        assertTrue("Error : no neighbours", res.isEmpty());
        res = algo.getNeighbours(n2);
        assertTrue("Error : not enough neighbours t2.0", res.size()==2);
        assertTrue("Error : wrong first child t2.1 ", res.contains(n4));
        assertTrue("Error : wrong first child t2.2 ", res.contains(n3));
        res = algo.getNeighbours(n1);
        assertTrue("Error : not enough neighbours t3.0", res.size()==1);
        assertTrue("Error : wrong first child t3.1", res.contains(n3));
    }
    
}