/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import model.graph.Edge;
import model.graph.Node;
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
        eesc = new Edge(new Node(0,0,"BASE"), new Node(1,2,"BASE"), "ESCARPE");
        einon = new Edge(new Node(0,0,"BASE"), new Node(1,2,"BASE"), "INONDE");
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testTrip() {
        r = new RobotCaterpillar();
        System.out.println("dd"+r.possibleTrip(eesc));
        assertTrue("Erreur caterpillar escarpe",!r.possibleTrip(eesc));
        assertTrue("Erreur caterpillar inondé", r.possibleTrip(einon));
        r = new RobotCrossCountry();
        assertTrue("Erreur Cross Country escarpe" , r.possibleTrip(eesc));
        assertTrue("Erreur Cross Country inondé" , r.possibleTrip(einon));
        r = new RobotLegs();
        assertTrue("Erreur Legs escarpe" , r.possibleTrip(eesc));
        assertTrue("Erreur Legs inondé" , !r.possibleTrip(einon));
        
        
        
    }
}
