package model.graph;

import java.util.Observable;

/**
 *
 * @author Dylan
 */
public class Node extends Observable {

    // Attributes
    private int id;
    private double x;
    private double y;
    private TypeNode type;
    private int fire;
    private boolean filled;
    private static int nb_node = 0;
    
    // Constructors
    public Node(int x, int y, TypeNode type){
        this.id = nb_node;
        this.x = x;
        this.y = y;
        this.type = type;
        this.fire = 0;
        this.filled = false;
        nb_node++;
    }
    
    public Node(int id, int x, int y, TypeNode type){
        this.id = id;
        this.x = x;
        this.y = y;
        this.type = type;
        this.fire = 0;
        this.filled = false;
    }
    
    // Methods
    public void kindleFire(){
        this.setFire(50);
        this.setType(TypeNode.INCENDIE);
    }
    
    public void extinguishFire(){
        this.setFire(0);
        this.setType(TypeNode.NORMAL);
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
    public TypeNode getType(){
        return type;
    }
    public int getFire(){
        return fire;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    
    public static void setNb_node(int aNb_node) {
        nb_node = aNb_node;
    }

    public void setFire(int fire) {
        this.fire = fire;
    }

    public void setType(TypeNode type) {
        this.type = type;
    }
}
