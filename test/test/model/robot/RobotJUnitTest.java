/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.model.robot;

import model.graph.Edge;
import model.graph.Node;
import model.graph.TypeEdge;
import model.graph.TypeNode;
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

/**
 *
 * @author Anthony
 */
public class RobotJUnitTest {
    public Robot r;
    public Edge eesc, einon;
    public RobotJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        eesc = new Edge(new Node(0,0,TypeNode.NORMAL), new Node(1,2,TypeNode.NORMAL), TypeEdge.ESCARPE);
        einon = new Edge(new Node(0,0,TypeNode.NORMAL), new Node(1,2,TypeNode.NORMAL), TypeEdge.INONDE);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testTrip() {
        r = new RobotCaterpillar(eesc.getNode1());
        assertTrue("Erreur caterpillar escarpe",!r.possibleTrip(eesc));
        r = new RobotCaterpillar(einon.getNode1());
        assertTrue("Erreur caterpillar inondé", r.possibleTrip(einon));
        r = new RobotCrossCountry(eesc.getNode1());
        assertTrue("Erreur Cross Country escarpe" , r.possibleTrip(eesc));
        r = new RobotCrossCountry(einon.getNode1());
        assertTrue("Erreur Cross Country inondé" , r.possibleTrip(einon));
        r = new RobotLegs(eesc.getNode1());
        assertTrue("Erreur Legs escarpe" , r.possibleTrip(eesc));
        r = new RobotLegs(einon.getNode1());
        assertTrue("Erreur Legs inondé" , !r.possibleTrip(einon));
        
        
        
    }
}
