package model.robot;

import model.graph.Edge;
import model.graph.Node;
import model.graph.TypeEdge;

/**
 *
 * @author Dylan
 */
public class RobotCaterpillar extends Robot {

    private String image = "src\\resources\\RobotCaterpillar.gif";
    private static int capacity = 40;
    private int position;
    private int speed;
    
    public RobotCaterpillar(Node n){
        super(n);
        super.setCapacity(capacity);
        this.speed = 100;
    }
    
    @Override
    public boolean possibleTrip(Edge e){
        return (e.getType() != TypeEdge.ESCARPE);
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
