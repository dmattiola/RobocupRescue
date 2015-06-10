package model.robot;

import model.graph.Edge;
import model.graph.Node;
import model.graph.TypeEdge;

/**
 * Model RobotCaterpillar inherit Robot
 * @author Dylan & Anthony
 */
public class RobotCaterpillar extends Robot {

    // ATTRIBUTES
    private final String image = "src\\resources\\RobotCaterpillar.gif";
    private static int capacity = 40;
    private final int speed;
    
    // CONSTRUCTORS

    /**
     * Constructor of a Caterpillar Robot
     * @param n Located Node
    */
    public RobotCaterpillar(Node n){
        super(n);
        super.setCapacity(capacity);
        this.speed = 100;
    }
    
    // SPECIFICS METHODS
        
    /**
     * (Specific) Test if the robot can cross the edge 
     * @param e Edge Concerned
     * @return (boolean) Possibilty of Crossing the Edge
    */    
    @Override
    public boolean possibleTrip(Edge e){
        return (e.getType() != TypeEdge.ESCARPE);
    }
    
    /**
     * (Specific)Get the image path
     * @return (String) Robot Image Path
    */
    @Override
    public String getImage(){
        return this.image;
    }

    /**
     * (Specific) Get the robot speed
     * @return (int) Robot Speed
    */
    @Override
    public int getSpeed(){
        return this.speed;
    }
    
    // GETTERS & SETTERS

    /**
     * Get the robot capacity
     * @return (int) Robot Capacity
    */  
    public static int getCapacity_(){
        return capacity;
    }

    /**
     * Set the robot capacity
     * @param aCapacity Robot Capacity
    */
    public static void setCapacity_(int aCapacity){
        capacity = aCapacity;
    }

}