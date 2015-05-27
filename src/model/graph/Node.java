package model.graph;

/**
 *
 * @author Dylan
 */
public class Node {
    
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
    
    // Methods
    private void kindleFire(){
        this.fire = 50;
    }
    private void extinguishFire(){
        this.fire = 0;
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

    public int getFire() {
        return fire;
    }
}
