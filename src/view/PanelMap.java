package view;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;

/**
 *
 * @author Dylan
 */
public class PanelMap extends JPanel implements Observer {

    // Attributes
    private int width;
    private int height;
    private Graph gh;
    
    // Constructors
    public PanelMap(){
        super();
        this.gh = new Graph();
    }
    
    // Methods
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color c = g.getColor();
	Dimension dim = getSize();
	g.setColor(Color.white);
	g.fillRect(0,0,dim.width, dim.height);
        try {
            Image im = ImageIO.read(new File("resources/mapsixieme.jpg"));
            g.drawImage(im, 0, 0, this);
        } catch (IOException e) {
            System.out.println("Painting Image Error : " + e.getMessage());
        }
    }
    
    private void addNode(Node n){
        this.gh.getListNodes().add(n);
    }
    
    private void addEdge(Edge e){
        this.gh.getListEdges().add(e);
    }
    
    private void drawNode(Node n, Graphics g){
        Color c = Color.BLACK;
        g.setColor(c);
        if (n.getFire() == 0){
            g.fillOval((int)n.getX()-5, (int)n.getY()-5, 10, 10);
        } else {
            try {
                Image im = ImageIO.read(new File("resources/fire.png"));
                g.drawImage(im, 10, 10, this);
            } catch (IOException e) {
            System.out.println("Painting Image Error : " + e.getMessage());
            }
        }
        g.drawString(Integer.toString(n.getId()), (int)n.getX(), (int)n.getY());
    }
    
    private void drawEdge(Edge e, Graphics g){
        Color c = Color.BLUE;
        g.setColor(c);
        g.drawLine((int)e.getNode1().getX(), (int)e.getNode1().getY(), (int)e.getNode2().getX(), (int)e.getNode2().getY());
    }
    
    private void showGraph(Graphics g){
        if (!this.gh.getListNodes().isEmpty()){
            for(Node n : this.gh.getListNodes()){
                this.drawNode(n, g);
            }
        }
        if (!this.gh.getListEdges().isEmpty()){
            for(Edge e : this.gh.getListEdges()){
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
    
}
