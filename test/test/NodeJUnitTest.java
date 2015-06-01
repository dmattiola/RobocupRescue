/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import model.graph.Node;
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
public class NodeJUnitTest {
    public Node nodeI;
    public Node nodeB;
    public NodeJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        nodeI = new Node(0,0,"INCENDIE");
        nodeB = new Node(1,1, "BASE");
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testKindleFire() {
        nodeB.kindleFire();
        assertTrue("Feu allumé", nodeB.getFire() == 50);
        assertTrue("Feu allumé", nodeB.getType() == "INCENDIE");
     }
     @Test
     public void testExtinguishFire() {
        nodeI.extinguishFire();
        assertTrue("Feu etein", nodeI.getFire() ==0);
        assertTrue("Feu allumé", nodeB.getType() == "BASE");
     }
}
