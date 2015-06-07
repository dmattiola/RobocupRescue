package model.robot;

import model.graph.Edge;
import model.graph.Node;

/**
 *
 * @author Dylan
 */
public class RobotCrossCountry extends Robot {

    private String image = "src\\resources\\RobotCrossCountry.gif";
    private static int capacity = 20;
    private int position;
    private int speed;
        public static int getCapacity_() {
        return capacity;
    }

    public static void setCapacity_(int aCapacity) {
        capacity = aCapacity;
    }
    public RobotCrossCountry(Node n){
        super(n);
        super.setCapacity(capacity);
        this.speed = 75;
    }

    @Override
    public boolean possibleTrip(Edge e) {
        return true;
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
