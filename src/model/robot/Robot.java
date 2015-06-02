package model.robot;

import java.awt.Graphics;
import java.util.LinkedList;
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
    private LinkedList<Node> listNodes;
    
    // CONSTRUCTORS

    /**
     * Constructors of a Robot
     * @param n node where the robot is
     */
    public Robot(Node n){
        this.n = n;
        this.state = StateRobot.FREE;
        this.capacite = 20;
    }
    
    // METHODS

    /**
     * Move a robot to the first node in his list
    */
    public void move(){
        this.getN().setFilled(false);
        this.n = getListNodes().get(0);
        this.getListNodes().remove(n);
        this.getN().setFilled(true);
        this.state = StateRobot.MOVING;
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

    /**
     * Methods to draw a robot (specific to each robot)
     * @param g Graphics
     * @param x (abcisse) high left point of the robot image
     * @param y (ordonnee) high left point of the robot image
     * @param pm panel in which the robot is drawn
    */
    public abstract void drawRobot(Graphics g, int x, int y, PanelMap pm);
    
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
