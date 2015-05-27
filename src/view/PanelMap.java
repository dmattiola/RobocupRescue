package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private int width;
    private int height;
    private Graph gh;
    
    public PanelMap(){
        super();
        this.gh = new Graph();
    }
    
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
    
    private void drawNode(Node n, Graphics g){
        Color c = Color.BLACK;
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
    public void update(Observable o, Object o1) {
        this.repaint();
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
}
