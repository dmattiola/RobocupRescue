package model.graph;

import java.util.Observable;

/**
 * Model Edge
 * @author Dylan & Anthony
*/
public class Edge extends Observable {
    
    // ATTRIBUTES
    private final Node node1;
    private final Node node2;
    private TypeEdge type;
    private final double length;
    
    // CONSTRUCTORS

    /**
     * Constructor of an Edge
     * @param node1 initial node
     * @param node2 final node
     * @param type edge type
    */
    public Edge(Node node1, Node node2, TypeEdge type){
        this.node1 = node1;
        this.node2 = node2;
        this.type = type;
        double x_ = node1.getX()-node2.getX();
        double y_ = node1.getY()-node2.getY();
        this.length = Math.sqrt((x_*x_)+(y_*y_));
    }
    
    // GETTERS & SETTERS

    /**
     * Get Initial Node
     * @return (Node) initial node
    */
    public Node getNode1(){
        return node1;
    }

    /**
     * Get Final Node
     * @return (Node) final node
    */
    public Node getNode2(){
        return node2;
    }

    /**
     * Get Edge Type
     * @return (TypeEdge) Edge Type
    */
    public TypeEdge getType(){
        return type;
    }

    /**
     * Get Edge Length
     * @return (double) Edge Length
    */
    public double getLength(){
        return this.length;
    }

    /**
     * Set Edge Type
     * @param type Edge Type
    */
    public void setType(TypeEdge type){
        this.type = type;
    }
    
}