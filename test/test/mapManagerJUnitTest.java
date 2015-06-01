/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import model.algorithme.algotest;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.graph.TypeNode;
import model.mapmanager.MapManager;
import model.robot.Robot;
import model.robot.RobotCaterpillar;
import model.robot.RobotCrossCountry;
import model.robot.RobotLegs;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import view.PanelMap;

/**
 *
 * @author Anthony
 */
public class mapManagerJUnitTest {
    MapManager mm = new MapManager();
    Node noeud_test;
    Robot res;
    
    public mapManagerJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Graph g = new Graph();
        ArrayList<Node> listn = new ArrayList<>();
        ArrayList<Node> liste = new ArrayList<>();

        Node n1, n2, n3, n4, n5, n6;
        n1 = new Node(100, 100,1, TypeNode.NORMAL);
        n2 = new Node(110, 110,2, TypeNode.NORMAL);
        n3 = new Node(90, 90,3, TypeNode.NORMAL);
        n4 = new Node(120, 120,4, TypeNode.NORMAL);
        n5 = new Node(80, 80,5, TypeNode.INCENDIE);
        n6 = new Node(90, 110,6, TypeNode.INCENDIE);
        mm.getGr().getListNodes().add(n1);
        mm.getGr().getListNodes().add(n2);
        mm.getGr().getListNodes().add(n3);
        mm.getGr().getListNodes().add(n4);
        mm.getGr().getListNodes().add(n5);
        mm.getGr().getListNodes().add(n6);
        Edge e1,e2,e3,e4,e5,e6;
        e1 = new Edge(n1, n2, null);
        e2 = new Edge(n2, n4, null);
        e3 = new Edge(n2, n6, null);
        e4 = new Edge(n1, n6, null);
        e5 = new Edge(n6, n3, null);
        e6 = new Edge(n5, n3, null);
        mm.getGr().getListEdges().add(e1);
        mm.getGr().getListEdges().add(e2);
        mm.getGr().getListEdges().add(e3);
        mm.getGr().getListEdges().add(e4);
        mm.getGr().getListEdges().add(e5);
        mm.getGr().getListEdges().add(e6);
        mm.setGr(g);
        RobotCaterpillar r1 = new RobotCaterpillar(n1);
        r1.setName("r1");
        mm.getListRobot().add(r1);
        RobotLegs r2 = new RobotLegs(n2);
        r2.setName("r2");
        mm.getListRobot().add(r2);
        RobotCrossCountry r3 = new RobotCrossCountry(n3);
        r3.setName("r3");
        mm.getListRobot().add(r3);
        noeud_test = n4;
        res = r2;
        
        
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testClosestRobot() {
        assertTrue("Erreur robot closest", res.getName()==mm.closestRobot(noeud_test).getName());
    }
}
