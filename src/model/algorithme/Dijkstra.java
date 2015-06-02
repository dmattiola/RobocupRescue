package model.algorithme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.robot.Robot;

/**
 *
 * @author Dylan
 */
public class Dijkstra extends Algorithme {

    private ArrayList<Node> listNodes;
    private ArrayList<Edge> listEdges;
    private Set<Node> markedNodes;
    private Set<Node> unmarkeddNodes;
    private Map<Node,Node> predecessors;
    private Map<Node,Integer> distance;
    private Robot r;
    
    public Dijkstra(){
        super();
        this.listNodes = new ArrayList<>();
        this.listEdges = new ArrayList<>();
    }
    
    @Override
    public LinkedList<Node> shortestTrip(Graph g, Node n, Robot r) {
        this.markedNodes = new HashSet<>();
        this.unmarkeddNodes = new HashSet<>();
        this.distance = new HashMap<>();
        this.predecessors = new HashMap<>();
        this.r = r;
        this.listNodes = (ArrayList<Node>) g.getListNodes().clone();
        this.listEdges = (ArrayList<Edge>) g.getListEdges().clone();
        this.distance.put(r.getN(), 0);
        this.unmarkeddNodes.add(r.getN());
        while(this.unmarkeddNodes.size() > 0){
            Node node = findMinimum(this.unmarkeddNodes);
            this.markedNodes.add(node);
            this.unmarkeddNodes.remove(node);
            findDistanceMinimal(node);
        }
        
        return getPath(n);
    }
    
    private Node findMinimum(Set<Node> nodes){
        Node nmin = null;
        for (Node n : nodes){
            if (nmin == null){
                nmin = n;
            } else {
                if (getShortestDistance(n) < getShortestDistance(nmin)){
                    nmin = n;
                }
            }
        }
        return nmin;
    }
    
    private int getShortestDistance(Node n){
        Integer dist = this.distance.get(n);
        if (dist == null){
            return Integer.MAX_VALUE;
        } else {
            return dist;
        }
    }
    
    private void findDistanceMinimal(Node n){
        List<Node> neighbours = getNeighbours(n);
        for(Node node : neighbours){
            if (getShortestDistance(node) > getShortestDistance(n)+getDistance(n,node)){
                this.distance.put(node, getShortestDistance(n)+getDistance(n,node));
                this.predecessors.put(node, n);
                this.unmarkeddNodes.add(node);
            }
        }
    }
    
    private int getDistance(Node n1, Node n2){
        for (Edge e : this.listEdges){
            if ((e.getNode1().equals(n1) && e.getNode2().equals(n2))||((e.getNode1().equals(n2) && e.getNode2().equals(n1)))){
                return (int)e.getLength();
            }
        }
        return 0;
    }
    
    private List<Node> getNeighbours(Node n){
        List<Node> neighbours = new ArrayList<>();
        for (Edge e : this.listEdges){
            if (e.getNode1().equals(n) && !isMarked(e.getNode2()) && this.r.possibleTrip(e)){
                neighbours.add(e.getNode2());
            } else if (e.getNode2().equals(n) && !isMarked(e.getNode1()) && this.r.possibleTrip(e)){
                neighbours.add(e.getNode1());
            }
        }
        return neighbours;
    }
    
    private boolean isMarked(Node n){
        return this.markedNodes.contains(n);
    }
    
    public LinkedList<Node> getPath(Node n){
        LinkedList<Node> path = new LinkedList<>();
        Node nde = n;
        if (this.predecessors.get(nde) == null){
            return null;
        }
        path.add(nde);
        while(this.predecessors.get(nde) != null){
            nde = this.predecessors.get(nde);
            path.add(nde);
        }
        Collections.reverse(path);
        return path;
    }

    @Override
    public Map<Node, Integer> getDistance() {
        return this.distance;
    }

}
