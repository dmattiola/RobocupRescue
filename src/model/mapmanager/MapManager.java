package model.mapmanager;

import java.util.*;
import java.util.logging.*;
import model.algorithme.Algorithme;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.graph.TypeEdge;
import model.graph.TypeNode;
import model.robot.Robot;
import model.robot.RobotCaterpillar;
import model.robot.RobotCrossCountry;
import model.robot.RobotLegs;
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
    private static final double probaIndondation = 0.25;
    
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

    private ArrayList<Robot> getListRobot(StateRobot etat){
        ArrayList<Robot> listMoving = new ArrayList<>();
        for (Robot r : this.getListRobots()){
            if (r.getState() == etat){
                listMoving.add(r);
            }
        }
        return listMoving;
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
    private ArrayList<Node> updateRechargingPlaces(){
        ArrayList<Node> list = new ArrayList<>();
        for (Node n : this.gr.getListNodes()){
            if (n.getType() == TypeNode.RECHARGE){
                list.add(n);
            }
        }
        return list;
    }
    private void closestRecharging(Robot r, ArrayList<Node> listRechargingPlace){
        int pathValue = Integer.MAX_VALUE;
        Map<Node,Integer> map = null;
        for (Node n : listRechargingPlace){
            LinkedList<Node> listNode = (this.a.shortestTrip(this.gr, n, r));
            map = this.a.getDistance();
            if (map.get(n) != null){
                if (pathValue > map.get(n)){
                    pathValue = map.get(n);
                    r.setListNodes(listNode);
                }
            }
        }
        
    }
    private void fillCapacity(Robot r){
        if (r instanceof RobotCaterpillar){
            r.setCapacity(RobotCaterpillar.getCapacity_());
        }
        if (r instanceof RobotCrossCountry){
            r.setCapacity(RobotCrossCountry.getCapacity_());
        }
        if (r instanceof RobotLegs){
            r.setCapacity(RobotLegs.getCapacity_());
        }
        r.setState(StateRobot.FREE);
    }
    @Override
    public void run() {
        this.isRunning = true;
        ArrayList<Robot> listBusy, listMoving, listRecharging = new ArrayList<>();
        ArrayList<Node> listRechargingPlace = new ArrayList<>();
        while(this.isRunning){
            // MISE A JOUR DES FEUX
            updateFires(); 
            listRechargingPlace = updateRechargingPlaces();
            // RECUPERE LES ROBOTS OCCUPES
            listBusy = getListRobot(StateRobot.BUSY);
            // ON FAIT BOUGER LES ROBOTS MOVING
            listMoving = getListRobot(StateRobot.MOVING);
            listRecharging = getListRobot(StateRobot.ONRECHARGE);
            
            for(Robot r : listMoving){
                r.move(findEdge(this.gr.getListEdges(),r.getN(),r.getListNodes().get(0)));
            }
            for(Robot r : listRecharging){
                if (r.getListNodes() == null){
                    closestRecharging(r,listRechargingPlace);
                }
                if (r.getListNodes().size() == 0){
                    fillCapacity(r);
                } else {
                    r.move(findEdge(this.gr.getListEdges(),r.getN(),r.getListNodes().get(0)));
                }
            }
            // ON PARCOURS LA LISTE DES FEUX QUI NE SONT PAS OCCUPES
            for (Node n : this.listFires){
                if (!n.isFilled()){
                    // ON CHERCHE LE ROBOT LE PLUS PRES
                    Robot r = closestRobot(n);   
                    // SI ON LE TROUVE IL AVANCE
                    if (r != null){
                        n.setFilled(true);
                        r.setState(StateRobot.MOVING);
                        r.move(findEdge(this.gr.getListEdges(),r.getN(),r.getListNodes().get(0)));
                    }
                }
            }
            // MISE A JOUR DES ROBOTS OCCUPEES
            // ON A DONC LES ROBOTS OCCUPES, LES ROBOTS EN MOUVEMENT sont à jour
            updateBusyRobots();
            // POUR LES ROBOTS OCCUPES cad ETEIGNE LE FEU
            for (Robot r : listBusy){
                r.extinguishFire();
                updateEdges(r);
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
    
    private void updateEdges(Robot r){
        ArrayList<Edge> listEdge = new ArrayList<>();
        for (Edge e : this.gr.getListEdges()){
            if (e.getNode1().equals(r.getN()) || e.getNode2().equals(r.getN())){
                listEdge.add(e);
            }
        }
        Random rand = new Random();
        for (Edge e : listEdge){
            if (rand.nextInt(100) < probaIndondation*100){
                e.setType(TypeEdge.INONDE);
            }
        }
    }
    private Edge findEdge(ArrayList<Edge> listEdge, Node n1, Node n2) {
        for (Edge e : listEdge){
            if ((e.getNode1().equals(n1) && e.getNode2().equals(n2))||(e.getNode1().equals(n2) && e.getNode2().equals(n1))){
                return e;
            }
        }
        return null;
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

    public void setA(Algorithme a) {
        this.a = a;
    }

   
    
}
