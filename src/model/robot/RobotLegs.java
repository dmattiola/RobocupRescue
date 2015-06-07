package model.robot;

import model.graph.Edge;
import model.graph.Node;
import model.graph.TypeEdge;

/**
 *
 * @author Dylan
 */
public class RobotLegs extends Robot {

    private String image = "src\\resources\\RobotLegs.gif";
    private static int capacity = 30;
    private int position;
    private int speed;
    
    public RobotLegs(Node n){
        super(n);
        super.setCapacity(capacity);
        this.speed = 50;
    }
    
    public static int getCapacity_() {
        return capacity;
    }

    public static void setCapacity_(int aCapacity) {
        capacity = aCapacity;
    }
    @Override
    public boolean possibleTrip(Edge e) {
        return (e.getType() != TypeEdge.INONDE);
    }

    @Override
    public String getImage() {
        return this.image;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }
    @Override
    public int getSpeed() {
        return this.speed;
    }
} 
