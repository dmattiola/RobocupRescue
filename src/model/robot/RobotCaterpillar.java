package model.robot;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.graph.Edge;
import model.graph.Node;
import model.graph.TypeEdge;
import view.PanelMap;

/**
 *
 * @author Dylan
 */
public class RobotCaterpillar extends Robot {

    public RobotCaterpillar(Node n){
        super(n);
    }
    
    @Override
    public boolean possibleTrip(Edge e){
        return (e.getType() != TypeEdge.ESCARPE);
    }

    @Override
    public void drawRobot(Graphics g, int x, int y, PanelMap pm) {
        try {
            Image im = ImageIO.read(new File("src\\resources\\RobotCaterpillar.gif"));
            g.drawImage(im, x, y, pm);
        } catch (IOException e) {
            System.out.println("Painting Image Error : " + e.getMessage());
        }
    }


}
