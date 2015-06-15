package test.model.graph;

import java.util.ArrayList;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.graph.TypeEdge;
import model.graph.TypeNode;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test JUnit on Model Graph
 * @author Dylan & Anthony
 */
public class GraphJUnitTest {
    
    private Graph g;
    private ArrayList<Edge> listEdge;
    private ArrayList<Node> listNode;
    
    /**
     * Constructor of JUnit graph test
    */
    public GraphJUnitTest(){ }
    
    /**
     * Initialize the test graph and lists
    */
    @Before
    public void setUp(){
        g = new Graph();
        listNode = new ArrayList<>();
        listNode.add(new Node(0,0,TypeNode.INCENDIE));
        listNode.add(new Node(1,1,TypeNode.NORMAL));
        listNode.add(new Node(2,2,3,TypeNode.RECHARGE));
        g.setListNodes(listNode);
        listEdge = new ArrayList<>();
        listEdge.add(new Edge(listNode.get(0),listNode.get(1),TypeEdge.PLAT));
        listEdge.add(new Edge(listNode.get(1),listNode.get(2),TypeEdge.INONDE));
        g.setListEdge(listEdge);
    }

    /**
     * 
    */
    @Test
    public void testFindEdge(){
        Edge e = g.findEdge(listNode.get(0), listNode.get(1));
        assertTrue("Arc trouvé", e != null);
        e = g.findEdge(listNode.get(0), listNode.get(2));
        assertTrue("Arc non trouvé", e == null);
    }
    
    @Test
    public void testLoadFile() {
        g = g.loadFile();
        ArrayList<Node> list = g.getListNodes();
        for (Node n : list){
            if(n.getId() == 0){
                assertTrue("Test noeud feu 1 erreur", n.getType() == TypeNode.INCENDIE);
                assertTrue("Test noeud feu 2 erreur", n.getFire()== 50);
            }
            if(n.getId() == 1){
                System.out.println("1" + n.getType() + "1");
                
                assertTrue("Test noeud 1 erreur", n.getType() == TypeNode.NORMAL);
                assertTrue("Test noeud 2 erreur", n.getFire()== 0);
            }
        }
        ArrayList<Edge> list2 = g.getListEdges();
        for (Edge e : list2){
            if(e.getNode1().getId() == 0 && e.getNode1().getId() == 1){
                assertTrue("Test arc 1 plat erreur", e.getType() == TypeEdge.PLAT);    
            }
            /*if(e.getNode1().getId() == 1 && e.getNode1().getId() == 2){
                assertTrue("Test arc 1 plat ok", e.getType() == "PLAT");
                
            }
            if(e.getNode1().getId() == 0 && e.getNode1().getId() == 1){
                assertTrue("Test arc 1 plat ok", e.getType() == "PLAT");*/
                
            }

        }
        
    }

