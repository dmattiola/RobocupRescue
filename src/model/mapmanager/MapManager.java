package model.mapmanager;

import java.awt.Graphics;
import java.util.*;
import java.util.logging.*;
import model.algorithme.Algorithme;
import model.graph.Graph;
import model.graph.Node;
import model.graph.TypeNode;
import model.robot.Robot;
import model.robot.StateRobot;
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
    private boolean isRunning;
    private boolean wait;
    
    public MapManager(Algorithme a, Graph g){
        this.gr = g;
        this.a = a;
        this.listRobots = new ArrayList<>();
        this.listFires = new ArrayList<>();
        this.wait = false;
    }
    
    private Robot closestRobot(Node n){
        int pathValue = Integer.MAX_VALUE;
        Robot closest = null;
        Map<Integer,ArrayList<Node>> map = null;
        for (Robot r : getListRobots()){
            if (r.getState() == StateRobot.FREE){
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

    public void updateFires(){
        for (Node n : this.getGr().getListNodes()){
            if (n.getType() == TypeNode.INCENDIE){
                this.getListFires().add(n);
            }
        }
    }
    
    private ArrayList<Robot> getListBusyRobot(){
        ArrayList<Robot> listBusy = new ArrayList<>();
        for (Robot r : this.getListRobots()){
            if (r.getState() == StateRobot.BUSY){
                listBusy.add(r);
            }
        }
        return listBusy;
    }
    
    @Override
    public void run() {
        this.isRunning = true;
        ArrayList<Robot> listBusy = new ArrayList<>();
        while(this.isRunning){
            updateFires();
            for (Node n : this.getListFires()){
                if (!n.isFilled()){
                    Robot r = closestRobot(n);
                    r.progress(this.a.shortestTrip(getGr(), n, r).get(0));
                }
            }
            listBusy = getListBusyRobot();
            for (Robot r : listBusy){
                r.extinguishFire();
            }
            /*
            mettre à jour les feus
            
            parcours liste feu non occupé et on lui affecte un robot le plus pres
            fai bouger les robots qui vont eteindre un feu
            une seconde passe
            les robots occupés font leur ation sur le feu à éteindre
            */
            this.setChanged();
            this.notifyObservers();
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

    public ArrayList<Robot> getListRobots() {
        return listRobots;
    }

    public void setListRobots(ArrayList<Robot> listRobots) {
        this.listRobots = listRobots;
    }

    public ArrayList<Node> getListFires() {
        return listFires;
    }

    public void setListFires(ArrayList<Node> listFires) {
        this.listFires = listFires;
    }

    public boolean isWait() {
        return wait;
    }

    public void setWait(boolean wait) {
        this.wait = wait;
    }

    public void setGr(Graph gr) {
        this.gr = gr;
    }
    
}
