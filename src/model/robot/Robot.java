package model.robot;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import model.graph.Edge;
import model.graph.Node;
import view.PanelMap;

/**
 *
 * @author Dylan
 */
public abstract class Robot extends Observable {
    
    // Attributes
    private String name;
    private Node n;
    private StateRobot state;
    private int capacite;
    
    public Robot(Node n){
        this.n = n;
        this.state = StateRobot.FREE;
        this.capacite = 20;
    }
    
    public void progress(ArrayList<Node> listNodes){
        this.getN().setFilled(false);
        this.setN(listNodes.get(0));
        this.getN().setFilled(true);
    }
 
    public void extinguishFire(){
        this.getN().extinguishFire();
        this.state = StateRobot.FREE;
    }
    
    public abstract boolean possibleTrip(Edge e);
    public abstract void drawRobot(Graphics g, int x, int y, PanelMap pm);
    
    public StateRobot getState() {
        return state;
    }

    public void setState(StateRobot state) {
        this.state = state;
    }

    public Node getN() {
        return n;
    }

    public void setN(Node n) {
        this.n = n;
    }
}
