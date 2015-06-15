package model.mapmanager;

import java.util.*;
import java.util.logging.*;
import model.algorithme.Algorithm;
import model.graph.*;
import model.robot.*;
import view.PanelMap;

/**
 * Model MapManager
 * @author Dylan & Anthony
*/
public class MapManager extends Observable implements Runnable {

    // ATTRIBUTES
    private Graph gr;
    private Algorithm a;
    private ArrayList<Robot> listRobots;
    private ArrayList<Node> listFires;
    private boolean isRunning;
    private PanelMap m;
    private static final double probaIndondation = 0.25;
    
    // CONSTRUCTORS
    
    /**
     * Constructor of a MapManger
     * @param a Algorithm Selected
     * @param g Current Graph
    */
    public MapManager(Algorithm a, Graph g){
        this.gr = g;
        this.a = a;
        this.listRobots = new ArrayList<>();
        this.listFires = new ArrayList<>();
    }
    
    // RUNNABLE METHOD
    
    /**
     * When the Thread is launched
    */
    @Override
    public void run(){
        this.isRunning = true;
        ArrayList<Robot> listBusy, listMoving, listRecharging = new ArrayList<>();
        ArrayList<Node> listRechargingPlace = new ArrayList<>();
        while(this.isRunning){
            updateFires(); 
            listRechargingPlace = updateRechargingPlaces();
            listBusy = getListRobot(StateRobot.BUSY);
            listMoving = getListRobot(StateRobot.MOVING);
            listRecharging = getListRobot(StateRobot.ONRECHARGE);            
            for(Robot r : listMoving){
                r.move(findEdge(this.gr.getListEdges(),r.getN(),r.getListNodes().get(0)));
            }
            for(Robot r : listRecharging){
                if (r.getListNodes() == null){
                    closestRecharging(r,listRechargingPlace);
                } else {
                    if (r.getListNodes().size() == 0){
                        fillCapacity(r);
                    } else {
                        r.move(findEdge(this.gr.getListEdges(),r.getN(),r.getListNodes().get(0)));
                    }
                }
            }
            for (Node n : this.listFires){
                if (!n.isFilled()){
                    Robot r = closestRobot(n);   
                    if (r != null){
                        n.setFilled(true);
                        r.setState(StateRobot.MOVING);
                        r.move(findEdge(this.gr.getListEdges(),r.getN(),r.getListNodes().get(0)));
                    }
                }
            }
            updateBusyRobots();
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
    
    // METHODS
    
    /**
     * Get the closest robot of the node n
     * @param n Node Concerned
     * @return (Robot) Closest Robot
    */
    public Robot closestRobot(Node n){
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
    
    /**
     * Update Fires List
    */
    public void updateFires(){
        ArrayList<Node> fires = new ArrayList<>();
        for (Node n : this.getGr().getListNodes()){
            if (n.getType() == TypeNode.INCENDIE){
                fires.add(n);
            }
        }
        this.setListFires(fires);
    }
    
    /**
     * Get list of robot according a robot state
     * @param etat State Robot
     * @return (ArrayList<Robot>) List of Robot (StateRobot)
    */
    private ArrayList<Robot> getListRobot(StateRobot etat){
        ArrayList<Robot> listMoving = new ArrayList<>();
        for (Robot r : this.getListRobots()){
            if (r.getState() == etat){
                listMoving.add(r);
            }
        }
        return listMoving;
    }
    
    /**
     * Update Busy Robots
    */
    private void updateBusyRobots(){
        for(Robot r : this.getListRobots()){
            for(Node n : this.getListFires()){
                if (r.getN().equals(n)){
                    if (!r.getState().equals(StateRobot.ONRECHARGE)){
                        r.setState(StateRobot.BUSY);
                    }
                }
            }   
        }
    }
    
    /**
     * Get Recharging Place
     * @return (ArrayList<Node>) List of Recharging Nodes
    */
    private ArrayList<Node> updateRechargingPlaces(){
        ArrayList<Node> list = new ArrayList<>();
        for (Node n : this.gr.getListNodes()){
            if (n.getType() == TypeNode.RECHARGE){
                list.add(n);
            }
        }
        return list;
    }
    
    /**
     * Set path robot to go to a recharging place
     * @param r Robot Concerned
     * @param listRechargingPlace List of Recharging Nodes
    */
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
    
    /**
     * Reinitialize capacity of a robot (fill watter)
     * @param r Robot Concerned
    */
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
    
    /**
     * When a robot is extinguishing a fire, update flooded edges according a probability
     * @param r Robot Concerned
    */
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
    
    /**
     * Find an edge on a list with its 2 nodes
     * @param listEdge List of Edges
     * @param n1 One of the Edge Node
     * @param n2 The Other Node
     * @return (Edge) Edge linking the 2 Edges
    */
    private Edge findEdge(ArrayList<Edge> listEdge, Node n1, Node n2){
        for (Edge e : listEdge){
            if ((e.getNode1().equals(n1) && e.getNode2().equals(n2))||(e.getNode1().equals(n2) && e.getNode2().equals(n1))){
                return e;
            }
        }
        return null;
    }
    
    // GETTERS & SETTERS
    
    /**
     * Get the graph
     * @return (Graph) Graph
    */
    public Graph getGr(){
        return gr;
    }

    /**
     * Set the graph
     * @param gr Graph
    */
    public void setGr(Graph gr){
        this.gr = gr;
    }
    
    /**
     * Get the robot list
     * @return (ArrayList<Robot>) List of Robots on the Map
    */
    public ArrayList<Robot> getListRobots(){
        return listRobots;
    }

    /**
     * Set the robot list
     * @param listRobots  List of Robots
    */
    public void setListRobots(ArrayList<Robot> listRobots){
        this.listRobots = listRobots;
    }

    /**
     * Get the fires list
     * @return (ArrayList<Node>) List of Fires on the Map
    */
    public ArrayList<Node> getListFires(){
        return listFires;
    }

    /**
     * Set the fires list
     * @param listFires List of Fires
    */
    public void setListFires(ArrayList<Node> listFires){
        this.listFires = listFires;
    }

    /**
     * Set the panel
     * @param m PanelMap
    */
    public void setM(PanelMap m){
        this.m = m;
    }

    /**
     * Set the algorithm
     * @param a Algorithm
    */
    public void setA(Algorithm a){
        this.a = a;
    }
    
}