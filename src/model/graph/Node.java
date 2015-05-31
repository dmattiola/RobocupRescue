package model.graph;

import java.util.Observable;

/**
 *
 * @author Dylan
 */
public class Node extends Observable {

    public static void setNb_node(int aNb_node) {
        nb_node = aNb_node;
    }
    
    // Attributes
    private int id;
    private double x;
    private double y;
    private String type;
    private int fire;
    private static int nb_node = 0;
    
    // Constructors
    public Node(int x, int y, String type){
        this.id = nb_node;
        this.x = x;
        this.y = y;
        this.type = type;
        this.fire = 0;
        nb_node++;
    }
    
    public Node(int id, int x, int y, String type){
        this.id = id;
        this.x = x;
        this.y = y;
        this.type = type;
        this.fire = 0;
    }
    
    // Methods
    public void kindleFire(){
        this.fire = 50;
        this.type = "INCENDIE";
        this.setChanged();
        this.notifyObservers();
    }
    
    public void extinguishFire(){
        this.fire = 0;
        this.type = "BASE";
    }
    
    // Getters & Setters
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public int getId(){
        return id;
    }
    public String getType(){
        return type;
    }
    public int getFire(){
        return fire;
    }
    
}
