package model.robot;

import model.graph.Edge;
import model.graph.Node;

/**
 *
 * @author Dylan
 */
public abstract class Robot {
    
    // Attributes
    private String name;
    private Node n;
    private String state="FREE";
    private int capacite;
    
    public abstract boolean possibleTrip(Edge e);

    public Robot(){
        
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public void setNode(Node no) {
        this.n = no;
    }
    public void setName(String str){
        this.name = str;
    }
    public String getName(){
        return this.name;
    }
}
