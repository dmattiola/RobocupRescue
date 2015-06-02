package model.mapmanager;

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
    private PanelMap m;
    
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
        Map<Node,Integer> map = null;
        for (Robot r : getListRobots()){
            if (r.getState() == StateRobot.FREE){
                LinkedList<Node> rr = (this.a.shortestTrip(this.gr, n, r));
                map = this.a.getDistance();
                if (map.get(n)!= null){
                    if (pathValue > map.get(n)){
                        pathValue = map.get(n);
                        closest = r;
                        r.setListNodes(rr);
                    }
                }
            }
        }
        return closest;
    }

    public void updateFires(){
        ArrayList<Node> fires = new ArrayList<>();
        for (Node n : this.getGr().getListNodes()){
            if (n.getType() == TypeNode.INCENDIE){
                fires.add(n);
            }
        }
        this.setListFires(fires);
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
    private void updateBusyRobots(){
        for(Robot r : this.getListRobots()){
            for(Node n : this.getListFires()){
                if (r.getN().equals(n)){
                    r.setState(StateRobot.BUSY);
                }
            }   
        }
    }
    
    @Override
    public void run() {
        this.isRunning = true;
        ArrayList<Robot> listBusy,listMoving = new ArrayList<>();
        while(this.isRunning){
            // MISE A JOUR DES FEUX
            updateFires();            
            // RECUPERE LES ROBOTS OCCUPES
            listBusy = getListBusyRobot();
            // ON FAIT BOUGER LES ROBOTS MOVING
            listMoving = getListMovingRobot();
            for(Robot r : listMoving){
                r.move();
            }
            // ON PARCOURS LA LISTE DES FEUX QUI NE SONT PAS OCCUPES
            for (Node n : this.listFires){
                if (!n.isFilled()){
                    // ON CHERCHE LE ROBOT LE PLUS PRES
                    Robot r = closestRobot(n);   
                    // SI ON LE TROUVE IL AVANCE
                    if (r != null){
                        n.setFilled(true);
                        r.move();
                    }
                }
            }
            // MISE A JOUR DES ROBOTS OCCUPEES
            // ON A DONC LES ROBOTS OCCUPES, LES ROBOTS EN MOUVEMENT sont à jour
            updateBusyRobots();
            // POUR LES ROBOTS OCCUPES cad ETEIGNE LE FEU
            for (Robot r : listBusy){
                r.extinguishFire();
            }
            this.setChanged();
            this.notifyObservers();
            this.m.repaint();
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

    public void setM(PanelMap m) {
        this.m = m;
    }

    private ArrayList<Robot> getListMovingRobot() {
        ArrayList<Robot> listMoving = new ArrayList<>();
        for (Robot r : this.getListRobots()){
            if (r.getState() == StateRobot.MOVING){
                listMoving.add(r);
            }
        }
        return listMoving;
    }
    
}
