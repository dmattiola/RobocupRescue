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
        if (this.gh.isShown()){
           showGraph(g); 
        }
    }
    
    public void addNode(Node n){
        this.getGh().getListNodes().add(n);
        update(n,this);
    }
    
    public void addEdge(Edge e){
        this.getGh().getListEdges().add(e);
        update(e,this);
    }
    
    public void addFire(Node n){
        this.getGh().getListNodes().add(n);
        update(n,this);
    }
    
    private void drawNode(Node n, Graphics g){
        g.setColor(Color.BLACK);
        if (!n.getType().equals("INCENDIE")){
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
        Color c = Color.BLUE;
        g.setColor(c);
        g.drawLine((int)e.getNode1().getX(), (int)e.getNode1().getY(), (int)e.getNode2().getX(), (int)e.getNode2().getY());
    }
    
    private void showGraph(Graphics g){
        if (!this.gh.getListNodes().isEmpty()){
            for(Node n : this.getGh().getListNodes()){
                this.drawNode(n, g);
            }
        }
        if (!this.gh.getListEdges().isEmpty()){
            for(Edge e : this.getGh().getListEdges()){
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

    public Graph getGh() {
        return gh;
    }

    public void setGh(Graph gh) {
        this.gh = gh;
    }
    
}
