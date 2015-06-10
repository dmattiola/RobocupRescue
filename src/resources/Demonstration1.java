package resources;

import controller.Controller;
import model.graph.Graph;
import view.FrameRobocup;

/**
 * Demonstration 1 : Launched the Program
 * @author Dylan & Anthony
 */
public class Demonstration1 {
    
    /**
     * Main
     * @param args 
    */
    public static void main(String[] args){
        //Instanciation de notre modèle
        Graph g = new Graph();
        //Création du contrôleur
        Controller controler = new Controller();
        //Création de notre fenêtre avec le contrôleur en paramètre
        FrameRobocup fr = new FrameRobocup(controler,g);
    }
    
}
