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
        ArrayList<Node> listNode = new ArrayList<>();
        listNode.add(new Node(0,0,TypeNode.INCENDIE));
        listNode.add(new Node(1,1,TypeNode.NORMAL));
        listNode.add(new Node(2,2,3,TypeNode.RECHARGE));
        g.setListNodes(listNode);
        ArrayList<Edge> listEdge = new ArrayList<>();
        listEdge.add(new Edge(listNode.get(0),listNode.get(1),TypeEdge.PLAT));
        listEdge.add(new Edge(listNode.get(1),listNode.get(2),TypeEdge.INONDE));
        g.setListEdge(listEdge);
    }

    /**
     * Test the function findEdge
    */
    @Test
    public void testFindEdge(){
        Edge e = g.findEdge(g.getListNodes().get(0), g.getListNodes().get(1));
        assertTrue("Arc trouvé", e != null);
        e = g.findEdge(g.getListNodes().get(0), g.getListNodes().get(2));
        assertTrue("Arc non trouvé", e == null);
    }
    
    /**
     * Test the function loadFile 
    */
    @Test
    public void testLoadFile(){
        g = g.loadFile();
        ArrayList<Node> list = g.getListNodes();
        for (Node n : list){
            assertTrue("Ajout d'un noeud dans le graphe", n.getType() instanceof TypeNode);
            assertFalse("Erreur dans l'ajout du noeud du graphe", !(n.getType() instanceof TypeNode));
        }
        ArrayList<Edge> list2 = g.getListEdges();
        for (Edge e : list2){
            assertTrue("Ajout d'un arc dans le graphe", e.getType() instanceof TypeEdge);
            assertFalse("Erreur dans l'ajout du arc du graphe", !(e.getType() instanceof TypeEdge));
        }
    }
        
}