package model.robot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import model.graph.Edge;
import model.graph.Node;

/**
 *
 * @author Dylan
 */
public abstract class Robot extends Observable {
    
    // Attributes
    private Node n;
    private StateRobot state;
    private LinkedList<Node> listNodes;
    
    // CONSTRUCTORS

    /**
     * Constructors of a Robot
     * @param n node where the robot is
     */
    public Robot(Node n){
        this.n = n;
        this.state = StateRobot.FREE;
    }
    
    // METHODS

    /**
     * Move a robot to the first node in his list
     * @param e
    */
    public void move(Edge e){
        if (e != null){
            if (this.getPosition()+this.getSpeed() >= e.getLength()){
                this.getN().setFilled(false);
                this.n = getListNodes().get(0);
                this.getListNodes().remove(n);
                this.getN().setFilled(true);
                this.setPosition(0);
            } else {
                this.setPosition(this.getPosition()+this.getSpeed());
            }
            this.state = StateRobot.MOVING;
        }
        this.setChanged();
        this.notifyObservers();
    }
 
    /**
     * Extinguish the fire in th node where the robot is
    */
    public void extinguishFire(){
        this.getN().extinguishFire();
        this.state = StateRobot.FREE;
        this.listNodes = null;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * To know if the robot can pass the edge (specific to each type of robot)
     * @param e Edge concerned
     * @return true if the robot can pass, else false 
    */
    public abstract boolean possibleTrip(Edge e);
    public abstract String getImage();
    public abstract int getCapacity();
    public abstract int getPosition();
    public abstract void setPosition(int position);
    public abstract int getSpeed();
    
    // GETTERS & SETTERS

    /**
     *
     * @return
     */
    public StateRobot getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(StateRobot state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    public Node getN() {
        return n;
    }

    /**
     *
     * @param n
     */
    public void setN(Node n) {
        this.n = n;
    }

    /**
     *
     * @return
     */
    public LinkedList<Node> getListNodes() {
        return listNodes;
    }

    /**
     *
     * @param listNodes
     */
    public void setListNodes(LinkedList<Node> listNodes) {
        this.listNodes = listNodes;
    }

}
