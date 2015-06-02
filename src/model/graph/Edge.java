package model.graph;

import java.util.Observable;

/**
 *
 * @author Dylan
 */
public class Edge extends Observable {
    
    // Attributes
    private Node node1;
    private Node node2;
    private TypeEdge type;
    private double length;
    
    // Constructors
    public Edge(Node node1, Node node2, TypeEdge type){
        this.node1 = node1;
        this.node2 = node2;
        this.type = type;
        double x_ = node1.getX()-node2.getX();
        double y_ = node1.getY()-node2.getY();
        this.length = Math.sqrt((x_*x_)+(y_*y_));
    }
    
    // Methods
    
    
    // Getters & Setters
    public Node getNode1(){
        return node1;
    }
    public Node getNode2(){
        return node2;
    }
    public TypeEdge getType(){
        return type;
    }

    public double getLength() {
        return length;
    }
}
