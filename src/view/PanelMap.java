package view;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.graph.*;
import model.mapmanager.MapManager;
import model.robot.Robot;

/**
 * View PanelMap
 * @author Dylan & Anthony
*/
public class PanelMap extends JPanel implements Observer {

    // ATTRIBUTES
    private int width;
    private int height;
    private MapManager map;
    
    // CONTRUCTORS
    
    /**
     * Constructor of a PanelMap
     * @param map MapManager Manager
    */
    public PanelMap(MapManager map){
        super();
        this.map = map;
        this.map.setM(this);
    }
    
    // METHODS
    /**
     * Paint component PanelMap
     * @param g Graphics
    */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Color c = g.getColor();
	Dimension dim = getSize();
	g.setColor(Color.BLACK);
	g.fillRect(0,0,dim.width, dim.height);
        try {
            Image im = ImageIO.read(new File("src\\resources\\mapsixieme.jpg"));
            g.drawImage(im, 0, 0, this);
        } catch (IOException e) {
            System.out.println("Painting Image Error : " + e.getMessage());
        }
        if (this.getMap().getGr().isShown()){
           showGraph(g); 
        } else {
           showFires(g);
        }
        showRobots(g);
    }
    
    /**
     * Add node
     * @param n Node to Add
    */
    public void addNode(Node n){
        this.getMap().getGr().getListNodes().add(n);
        update(n,this);
    }
    
    /**
     * Add edge
     * @param e Edge to Add
    */
    public void addEdge(Edge e){
        this.getMap().getGr().getListEdges().add(e);
        update(e,this);
    }
    
    /**
     * Add robot
     * @param r Robot to Add
    */
    public void addRobot(Robot r){
        this.getMap().getListRobots().add(r);
        update(r,this);
    }
    
    // DRAW METHODS
    
    /**
     * Draw a node
     * @param n Node to Draw
     * @param g Graphics
    */
    private void drawNode(Node n, Graphics g){
        g.setColor(Color.BLACK);
        if (n.getType() == TypeNode.NORMAL){
            g.drawOval((int)n.getX()-5, (int)n.getY()-5, 12, 12);
        } else if (n.getType() == TypeNode.INCENDIE){
            try {
                Image im = ImageIO.read(new File("src\\resources\\fire.gif"));
                g.drawImage(im, (int)n.getX()-6, (int)n.getY()-12, this);
            } catch (IOException e) {
                System.out.println("Painting Image Error : " + e.getMessage());
            }
        } else if (n.getType() == TypeNode.RECHARGE){
            try {
                Image im = ImageIO.read(new File("src\\resources\\recharge.gif"));
                g.drawImage(im, (int)n.getX()-6, (int)n.getY()-12, this);
            } catch (IOException e) {
                System.out.println("Painting Image Error : " + e.getMessage());
            }
        }
        g.drawString(Integer.toString(n.getId()), (int)n.getX()-17, (int)n.getY());
    }
    
    /**
     * Draw an edge
     * @param e Edge to Draw
     * @param g Graphics
    */
    private void drawEdge(Edge e, Graphics g){
        Color c = Color.BLACK;
        if (e.getType() == TypeEdge.ESCARPE){
            c = Color.RED;
        } else if (e.getType() == TypeEdge.INONDE){
            c = Color.BLUE;
        } else if (e.getType() == TypeEdge.PLAT){
            c = Color.BLACK;
        }
        g.setColor(c);
        g.drawLine((int)e.getNode1().getX(), (int)e.getNode1().getY(), (int)e.getNode2().getX(), (int)e.getNode2().getY());
    }
    
    /**
     * Draw a robot
     * @param g Graphics
     * @param x Position on X
     * @param y Position on Y
     * @param r Robot to Draw
    */
    public void drawRobot(Graphics g, int x, int y, Robot r){
        try {
            Image im = ImageIO.read(new File(r.getImage()));
            g.drawImage(im, x, y, this);
        } catch (IOException e) {
            System.out.println("Painting Image Error : " + e.getMessage());
        }
    }
    
    /**
     * Show a graph
     * @param g Graphics
    */
    private void showGraph(Graphics g){
        if (!this.map.getGr().getListNodes().isEmpty()){
            for(Node n : this.getMap().getGr().getListNodes()){
                this.drawNode(n, g);
            }
        }
        if (!this.map.getGr().getListEdges().isEmpty()){
            for(Edge e : this.getMap().getGr().getListEdges()){
                this.drawEdge(e, g);
            }
        }
    }
    
    /**
     * Show a robot
     * @param g Graphics
    */
    public void showRobots(Graphics g){
        for(Robot r : this.getMap().getListRobots()){
            drawRobot(g, (int)r.getN().getX()-10, (int)r.getN().getY()-12, r);
        }
    }
    
    /**
     * Show a fire
     * @param g Graphics
    */
    public void showFires(Graphics g){
        this.getMap().updateFires();
        for (Node n : this.getMap().getListFires()){
            drawNode(n, g);
        }
    }
    
    /**
     * Update PanelMap
     * @param o Observable
     * @param o1 Object
    */
    @Override
    public void update(Observable o, Object o1){
        this.repaint();
    }

    // GETTERS & SETTERS
    
    /**
     * Set width of the JPanel
     * @param width Width
    */
    public void setWidth(int width){
        this.width = width;
    }
    
    /**
     * Set height of the JPanel
     * @param height Height
    */
    public void setHeight(int height){
        this.height = height;
    }

    /**
     * Get the manager
     * @return (MapManager) Manager 
    */
    public MapManager getMap(){
        return map;
    }

    /**
     * Set the manager
     * @param map Manager
    */
    public void setMap(MapManager map){
        this.map = map;
    }
   
}