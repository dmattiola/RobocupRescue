package model.robot;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.graph.Edge;
import model.graph.Node;
import view.PanelMap;

/**
 *
 * @author Dylan
 */
public class RobotCrossCountry extends Robot {

    private String image = "src\\resources\\RobotCrossCountry.gif";
    private int capacity;
    private int position;
    private int speed;
    
    public RobotCrossCountry(Node n){
        super(n);
        this.capacity = 20;
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
    public int getCapacity() {
        return this.capacity;
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
