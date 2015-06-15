package model.algorithme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.robot.Robot;

/**
 * Model BreadthFirstSearch inherit Algorithm
 * @author Dylan & Anthony
*/
public class BreadthFirstSearch extends Algorithm {

    // ATTRIBUTES
    /**
     * Enum of Colors used to mark nodes
    */
    private enum COLORS { WHITE, BLACK };
    private Map<Node,COLORS> color_nodes;
    private Map<Node,LinkedList<Node>> path;
    
    //CONSTRUCTORS
    
    /**
     * Constructor of the BreadthFirstSearch Algorithm
    */
    public BreadthFirstSearch(){
        super();
    }
    
    // SPECIFICS METHODS
    
    /**
     * (Specific) Get list nodes corresponding to the trip
     * @param g Graph Concerned
     * @param n Arrival Node
     * @param r Robot Concerned (Starting Node)
     * @return (LinkedList<Node>) List of Nodes
    */
    @Override
    public LinkedList<Node> shortestTrip(Graph g, Node n, Robot r){
        super.setListNodes((ArrayList<Node>) g.getListNodes().clone());
        super.setListEdges((ArrayList<Edge>) g.getListEdges().clone());
        super.setR(r);
        initList();
        LinkedList<Node> result = new LinkedList<>();
        ArrayList<Node> file = new ArrayList<>();
        LinkedList<Node> temp = new LinkedList<>();
        Node current = r.getN();
        file.add(current);
        color_nodes.put(current, COLORS.BLACK);
        temp.add(current);
        while (!file.isEmpty()){
            current = file.get(0);
            file.remove(0);
            for(Node child : getNeighbours(current)){
                int dist = super.getDistance().get(current);  
                LinkedList<Node> pathToNode = new LinkedList<>();
                pathToNode = (LinkedList<Node>) path.get(current).clone();
                if(color_nodes.get(child)!=COLORS.BLACK) {
                    file.add(child);
                    color_nodes.put(child, COLORS.BLACK);
                    dist = dist + getLength(current, child);
                    super.getDistance().put(child, dist);
                    pathToNode.add(child);
                    path.put(child, pathToNode);
                    if (child.getId() == n.getId()){
                        result = pathToNode;
                        return result;
                    }
                }
            } 
        }
        return null;
    }
    
    /**
     * (Specific) Check if the node is marked
     * @param n Node Concerned
     * @return (boolean) Node Marked
    */
    @Override
    public boolean isMarked(Node n){
        return COLORS.BLACK == color_nodes.get(n);
    }
    
    // METHODS
    
    /**
     * Initialize all lists
    */
    private void initList(){
        this.color_nodes = new HashMap<>();
        super.setDistance(new HashMap<Node,Integer>());
        this.path = new HashMap<>();
        for (Node n : super.getListNodes()) {
            this.color_nodes.put(n, COLORS.WHITE);
            super.getDistance().put(n,0);
            this.path.put(n, new LinkedList<Node>());
        }
    }
 
}