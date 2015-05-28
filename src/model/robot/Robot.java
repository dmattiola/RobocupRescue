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
    private String state;
    private int capacite;
    
    public abstract boolean possibleTrip(Edge e);

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
