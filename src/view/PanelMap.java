package view;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.graph.Edge;
import model.graph.Node;
import model.graph.TypeEdge;
import model.graph.TypeNode;
import model.mapmanager.MapManager;
import model.robot.Robot;

/**
 *
 * @author Dylan
 */
public class PanelMap extends JPanel implements Observer {

    // Attributes
    private int width;
    private int height;
    private MapManager map;
    
    // Constructors
    public PanelMap(MapManager map){
        super();
        this.map = map;
    }
    
    // Methods
    @Override
    public void paintComponent(Graphics g) {
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
    
    public void addNode(Node n){
        this.getMap().getGr().getListNodes().add(n);
        update(n,this);
    }
    
    public void addEdge(Edge e){
        this.getMap().getGr().getListEdges().add(e);
        update(e,this);
    }
    
    public void addRobot(Robot r){
        this.getMap().getListRobots().add(r);
        update(r,this);
    }
    
    private void drawNode(Node n, Graphics g){
        g.setColor(Color.BLACK);
        if (n.getType() != TypeNode.INCENDIE){
            g.drawOval((int)n.getX()-5, (int)n.getY()-5, 12, 12);
        } else {
            try {
                Image im = ImageIO.read(new File("src\\resources\\fire.gif"));
                g.drawImage(im, (int)n.getX()-6, (int)n.getY()-12, this);
            } catch (IOException e) {
                System.out.println("Painting Image Error : " + e.getMessage());
            }
        }
        g.drawString(Integer.toString(n.getId()), (int)n.getX()-17, (int)n.getY());
    }
    
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
    
    @Override
    public void update(Observable o, Object o1){
        this.repaint();
    }

    // Getters & Setters
    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){
        this.height = height;
    }

    public void showRobots(Graphics g) {
        for(Robot r : this.getMap().getListRobots()){
            r.drawRobot(g, (int)r.getN().getX()-10, (int)r.getN().getY()-12, this);
        }
    }
    
    public void showFires(Graphics g){
        this.getMap().updateFires();
        for (Node n : this.getMap().getListFires()){
            drawNode(n, g);
        }
    }

    public MapManager getMap() {
        return map;
    }

    public void setMap(MapManager map) {
        this.map = map;
    }

    
}
