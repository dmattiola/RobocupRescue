package model.robot;

import java.util.LinkedList;
import java.util.Observable;
import model.graph.Edge;
import model.graph.Node;

/**
 * Model Robot
 * @author Dylan & Anthony
*/
public abstract class Robot extends Observable {
    
    // ATTRIBUTES
    private Node n;
    private StateRobot state;
    private LinkedList<Node> listNodes;
    private int capacity;
    private int position;
    
    // CONSTRUCTORS

    /**
     * Constructors of a Robot
     * @param n Robot is Located on this Node
    */
    public Robot(Node n){
        this.n = n;
        this.state = StateRobot.FREE;
        this.capacity = 0;
    }
    
    // METHODS

    /**
     * Move a robot to the first node in his list
     * @param e First Edge on the Move List
    */
    public void move(Edge e){
        if (e != null){
            // check if the robot could cross the end of the edge
            if (this.getPosition()+this.getSpeed() >= e.getLength()){
                this.getN().setFilled(false);
                this.n = getListNodes().get(0);
                this.getListNodes().remove(n);
                this.getN().setFilled(true);
                this.setPosition(0);
            } else {
                // the robot could cross the end of the edge so it move on the edge
                this.setPosition(this.getPosition()+this.getSpeed());
            }
        }
        this.setChanged();
        this.notifyObservers();
    }
 
    /**
     * Extinguish the fire in robot node
    */
    public void extinguishFire(){
        int valueFire = this.getN().getFire() - this.capacity;
        // check the new fire value
        if (valueFire <= 0){
            this.capacity = this.capacity - this.getN().getFire();
            this.getN().extinguishFire();
            this.state = StateRobot.FREE;
            this.listNodes = null;
        } else {
            // check if it remains watter on the robot
            if (this.capacity - this.getN().getFire() <= 0){
                this.capacity = 0;
                this.setState(StateRobot.ONRECHARGE);
                this.listNodes = null;
            }
            this.getN().setFire(valueFire);
        }
        this.getN().setFilled(false);
        this.setChanged();
        this.notifyObservers();
    }
    
    // SPECIFICS METHODS
    
    /**
     * Test if the robot can pass the edge (specific to each type of robot)
     * @param e Edge Concerned
     * @return (boolean) Possibility of Crossing the Edge
    */
    public abstract boolean possibleTrip(Edge e);

    /**
     * Get the image path
     * @return (String) Specific Robot Image Path
    */
    public abstract String getImage();

    /**
     * Get the robot speed
     * @return (int) Specific Robot Speed
    */
    public abstract int getSpeed();
    
    // GETTERS & SETTERS

    /**
     * Get the robot state
     * @return (StateRobot) Robot State
    */
    public StateRobot getState(){
        return state;
    }

    /**
     * Set the state of the robot
     * @param state Robot State
    */
    public void setState(StateRobot state){
        this.state = state;
    }

    /**
     * Get the located node of the robot (position)
     * @return (Node) Located Node
    */
    public Node getN(){
        return n;
    }

    /**
     * Set the located node of the robot (position)
     * @param n (Node) Located Node
    */
    public void setN(Node n){
        this.n = n;
    }

    /**
     * Get the list of nodes of the robot (trip)
     * @return (LinkedList<Node>) List of Nodes of the Robot
    */
    public LinkedList<Node> getListNodes(){
        return listNodes;
    }

    /**
     * Set the list of nodes (trip)
     * @param listNodes List of Nodes
    */
    public void setListNodes(LinkedList<Node> listNodes){
        this.listNodes = listNodes;
    }

    /**
     * Get capacity (watter quantity)
     * @return (int) Capacity
    */
    public int getCapacity(){
        return capacity;
    }
    
    /**
     * Set capacity (watter quantity)
     * @param capacity Capacity
    */
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    /**
     * Get position on the current edge
     * @return (int) Position on the Edge
    */
    public int getPosition(){
        return this.position;
    }

    /**
     * Set position on the current edge
     * @param position Position on the Edge
    */
    public void setPosition(int position){
        this.position = position;
    }

}