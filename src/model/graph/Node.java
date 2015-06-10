package model.graph;

import java.util.Observable;

/**
 * Model Node
 * @author Dylan & Anthony
*/
public class Node extends Observable {

    // ATTRIBUTES
    private final int id;
    private final double x;
    private final double y;
    private TypeNode type;
    private int fire;
    private boolean filled;
    private static int nb_node = 0;
    
    // CONSTRUCTORS

    /**
     * Constructor of a simple node
     * @param x Position on X
     * @param y Position on Y
     * @param type Node Type
    */
    public Node(int x, int y, TypeNode type){
        this.id = nb_node;
        this.x = x;
        this.y = y;
        this.type = type;
        this.fire = 0;
        this.filled = false;
        nb_node++;
    }
    
    /**
     * Constructor of a node with its ID
     * @param id Node ID
     * @param x Position on X
     * @param y Position on Y
     * @param type Node Type
    */
    public Node(int id, int x, int y, TypeNode type){
        this.id = id;
        this.x = x;
        this.y = y;
        this.type = type;
        this.fire = 0;
        this.filled = false;
    }
    
    // METHODS

    /**
     * Kindle a fire on a node
     * @param intensity Intensity of the fire
    */
    public void kindleFire(int intensity){
        this.setFire(intensity);
        this.setType(TypeNode.INCENDIE);
    }
    
    /**
     * Extinguish the fire
    */
    public void extinguishFire(){
        this.setFire(0);
        this.setType(TypeNode.NORMAL);
    }
    
    // GETTERS & SETTERS

    /**
     * Get position on X
     * @return (double) Position X
    */
    public double getX(){
        return x;
    }

    /**
     * Get position on Y
     * @return (double) Position Y
    */
    public double getY(){
        return y;
    }

    /**
     * Get Node ID
     * @return (int) Node ID
    */
    public int getId(){
        return id;
    }

    /**
     * Get Type of the Node
     * @return (TypeNode) Node Type
    */
    public TypeNode getType(){
        return type;
    }

    /**
     * Set the type of the node
     * @param type Node Type
    */
    public void setType(TypeNode type){
        this.type = type;
    }
    
    /**
     * Get the intensity of the fire
     * @return (int) Fire Intensity
    */
    public int getFire(){
        return fire;
    }

    /**
     * Set the intensity of the fire
     * @param fire Fire Intensity
    */
    public void setFire(int fire){
        this.fire = fire;
    }
    
    /**
     * Get if the node is occupied or is concerned by a robot
     * @return (boolean) Node Occupation
    */
    public boolean isFilled(){
        return filled;
    }

    /**
     * Set the node occupation
     * @param filled Node Occupation
    */
    public void setFilled(boolean filled){
        this.filled = filled;
    }
    
    /**
     * Set the number of nodes oin the graph
     * @param aNb_node Number of Nodes in the Graph
    */
    public static void setNb_node(int aNb_node){
        nb_node = aNb_node;
    }

}