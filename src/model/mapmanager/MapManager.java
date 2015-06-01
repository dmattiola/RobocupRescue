package model.mapmanager;

import java.util.*;
import java.util.logging.*;
import model.algorithme.Algorithme;
import model.graph.Graph;
import model.graph.Node;
import model.robot.Robot;
import view.PanelMap;

/**
 *
 * @author Dylan
 */
public class MapManager extends Observable implements Runnable {

    private Graph gr;
    private Algorithme a;
    private ArrayList<Robot> listRobots;
    private ArrayList<Node> listFires;
    private PanelMap panmap;
    private boolean isRunning;
    
    public MapManager() {
        this.gr = new Graph();
        this.listRobots = new ArrayList<>();
        this.listFires = new ArrayList<>();
    }
    public MapManager(Algorithme a, PanelMap panmap){
        this.gr = panmap.getGh();
        this.a = a;
        this.panmap = panmap;
        this.listRobots = new ArrayList<>();
        this.listFires = new ArrayList<>();
    }

    private void addRobot(Robot r){
        this.listRobots.add(r);
    }
    
    public Robot closestRobot(Node n){
        int pathValue = Integer.MAX_VALUE;
        Robot closest = null;
        Map<Integer,ArrayList<Node>> map = null;
        for (Robot r : listRobots){
            if (r.getState().equals("FREE")){
                map = this.a.shortestTrip(getGr(), n, r);
                for (int val : map.keySet()){
                    if (pathValue > val){
                        pathValue = val;
                        closest = r;
                    }
                }
            }
        }
        return closest;
    }

    private void updateFires(){
        for (Node n : this.getGr().getListNodes()){
            if (n.getFire() != 0){
                this.listFires.add(n);
            }
        }
    }
    
    @Override
    public void run() {
        this.isRunning = true;
        while(this.isRunning){
            // maj feu +++ regarder si robot peut eteindre feu +++ 
            // apres prend un de feu ++++ prend robot plus pres ++++ etat bouger +++ bouger selon court chemin (avec maj etat 
            // robot fini eteidnre feu deviennet libre
            updateFires();
            for (Node n : this.listFires){
                Robot r = closestRobot(n);
                r.setState("MOVING");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MapManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Graph getGr() {
        return gr;
    }
    public ArrayList<Robot> getListRobot() {
        return this.listRobots;
    }
    public void setGr(Graph g) {
        this.gr=g;
    }

    
}
