package model.algorithme;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.robot.Robot;

/**
 * Model Algorithm
 * @author Dylan & Anthony
*/
public abstract class Algorithm {
    
    // ATTRIBUTES
    private ArrayList<Node> listNodes;
    private ArrayList<Edge> listEdges;
    private Robot r;
    private Map<Node,Integer> distance;
    
    // CONSTRUCTORS
    
    /**
     * Constructor of an Algorithm
    */   
    public Algorithm(){ 
        this.listNodes = new ArrayList<>();
        this.listEdges = new ArrayList<>();
        this.r = null;
    }

    // METHODS
    
    /**
     * Get length of the edge composed by 2 nodes
     * @param n1 One of the Edge Node
     * @param n2 The Other Node
     * @return (int) Length of the Edge
    */
    public int getLength(Node n1, Node n2){
        for (Edge e : this.getListEdges()){
            if ((e.getNode1().equals(n1) && e.getNode2().equals(n2))||((e.getNode1().equals(n2) && e.getNode2().equals(n1)))){
                return (int)e.getLength();
            }
        }
        return 0;
    }

    /**
     * Get neighours of a node
     * @param n Node
     * @return (List<Node>) List of the Neighboring Nodes 
    */
    public List<Node> getNeighbours(Node n){
        List<Node> neighbours = new ArrayList<>();
        for (Edge e : this.listEdges){
            if (e.getNode1().equals(n) && !isMarked(e.getNode2()) && this.getR().possibleTrip(e)){
                neighbours.add(e.getNode2());
            } else if (e.getNode2().equals(n) && !isMarked(e.getNode1()) && this.getR().possibleTrip(e)){
                neighbours.add(e.getNode1());
            }
        }
        return neighbours;
    }
    
    // SPECIFICS METHODS
    
    /**
     * Get list nodes corresponding to the trip
     * @param g Graph Concerned
     * @param n Arrival Node
     * @param r Robot Concerned (Starting Node)
     * @return (LinkedList<Node>) List of Nodes
    */
    public abstract LinkedList<Node> shortestTrip(Graph g, Node n, Robot r);
    
    /**
     * Get if a node is marked or not
     * @param n Node Concerned
     * @return (boolean) Node Marked
    */
    public abstract boolean isMarked(Node n);
    
    // GETTERS & SETTERS
    
    /**
     * Get the list of nodes
     * @return (ArrayList<Node>) List of Nodes
    */
    public ArrayList<Node> getListNodes(){
        return listNodes;
    }

    /**
     * Set the list of nodes
     * @param listNodes List of Nodes
    */
    public void setListNodes(ArrayList<Node> listNodes){
        this.listNodes = listNodes;
    }
    
    /**
     * Get the list of edges of the Graph
     * @return (ArrayList<Edge>) List of Edges
    */
    public ArrayList<Edge> getListEdges(){
        return listEdges;
    }

    /**
     * Set the list of edges
     * @param listEdges List of Edges
    */
    public void setListEdges(ArrayList<Edge> listEdges){
        this.listEdges = listEdges;
    }

    /**
     * Get the robot concerned by the algorithm (containing inital node)
     * @return (Robot) Robot Concerned
    */
    public Robot getR(){
        return r;
    }

    /**
     * Set the robot concerned by the algorithm (containing initial node)
     * @param r Robot Concerned
    */
    public void setR(Robot r){
        this.r = r;
    }

    /**
     * Get map of distance : node to fire
     * @return (Map<Node,Integer>) Map of distance between a node and the fire
    */
    public Map<Node,Integer> getDistance(){
        return distance;
    }

    /**
     * Set map of distance : node to fire
     * @param distance Map of distance between a node and the fire
    */
    public void setDistance(Map<Node,Integer> distance){
        this.distance = distance;
    }
    
}