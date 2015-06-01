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

    public RobotCrossCountry(Node n){
        super(n);
    }

    @Override
    public boolean possibleTrip(Edge e) {
        return true;
    }

    @Override
    public void drawRobot(Graphics g, int x, int y, PanelMap pm) {
        try {
            Image im = ImageIO.read(new File("src\\resources\\RobotCrossCountry.gif"));
            g.drawImage(im, x, y, pm);
        } catch (IOException e) {
            System.out.println("Painting Image Error : " + e.getMessage());
        }
    }

    
}
