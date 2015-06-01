/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import model.graph.Edge;
import model.graph.Graph;
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
public class GraphJUnitTest {
    public Graph g;
    public GraphJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        g = new Graph();
        ArrayList<Node> list = new ArrayList<>();
        /*list.add(new Node(0,0,"INCENDIE"));
        list.add(new Node(1,1,"BASE"));
        list.add(new Node(2,2,3,"INCENDIE"));*/
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testLoadFile() {
        g = g.loadFile();
        ArrayList<Node> list = g.getListNodes();
        for (Node n : list){
            if(n.getId() == 0){
                assertTrue("Test noeud feu 1 erreur", n.getType() == "INCENDIE");
                assertTrue("Test noeud feu 2 erreur", n.getFire()== 50);
            }
            if(n.getId() == 1){
                System.out.println("1" + n.getType() + "1");
                
                assertTrue("Test noeud 1 erreur", n.getType().contains("BASE"));
                assertTrue("Test noeud 2 erreur", n.getFire()== 0);
            }
        }
        ArrayList<Edge> list2 = g.getListEdges();
        for (Edge e : list2){
            if(e.getNode1().getId() == 0 && e.getNode1().getId() == 1){
                assertTrue("Test arc 1 plat erreur", e.getType() == "PLAT");    
            }
            /*if(e.getNode1().getId() == 1 && e.getNode1().getId() == 2){
                assertTrue("Test arc 1 plat ok", e.getType() == "PLAT");
                
            }
            if(e.getNode1().getId() == 0 && e.getNode1().getId() == 1){
                assertTrue("Test arc 1 plat ok", e.getType() == "PLAT");*/
                
            }

        }
        
    }

