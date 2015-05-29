package resources;

import controller.Controller;
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
       // TortueAmelioree t = new TortueAmelioree();
    //Création du contrôleur
        Controller controler = new Controller();
    //Création de notre fenêtre avec le contrôleur en paramètre
        Graph g = new Graph();
        FrameRobocup fr = new FrameRobocup(controler,g);
    //Ajout de la fenêtre comme observer de notre modèle
        
    }
}
