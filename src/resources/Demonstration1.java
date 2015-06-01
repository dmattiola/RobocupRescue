package resources;

import controller.Controller;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import model.graph.Graph;
import model.mapmanager.MapManager;
import view.FrameRobocup;

/**
 *
 * @author Dylan
 */
public class Demonstration1 {
    
    public static void main(String[] args) {
        //Instanciation de notre modèle
        Graph g = new Graph();
        //Création du contrôleur
        Controller controler = new Controller();
        //Création de notre fenêtre avec le contrôleur en paramètre
        FrameRobocup fr = new FrameRobocup(controler,g);
        //((JButton)((JToolBar)((JPanel)fr.getContentPane().getComponent(1)).getComponent(0)).getComponent(3)).setEnabled(false);
    }
}
