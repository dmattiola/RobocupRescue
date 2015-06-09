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

    private Set<Node> markedNodes;
    private Set<Node> unmarkeddNodes;
    private Map<Node,Node> predecessors;
    
    public Dijkstra(){
        super();
    }
    
    @Override
    public LinkedList<Node> shortestTrip(Graph g, Node n, Robot r) {
        this.markedNodes = new HashSet<>();
        this.unmarkeddNodes = new HashSet<>();
        this.predecessors = new HashMap<>();
        super.setR(r);
        super.setDistance(new HashMap<Node,Integer>());
        super.setListNodes((ArrayList<Node>) g.getListNodes().clone());
        super.setListEdges((ArrayList<Edge>) g.getListEdges().clone());
        super.getDistance().put(r.getN(), 0);
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
        Integer dist = super.getDistance().get(n);
        if (dist == null){
            return Integer.MAX_VALUE;
        } else {
            return dist;
        }
    }
    
    private void findDistanceMinimal(Node n){
        List<Node> neighbours = getNeighbours(n);
        for(Node node : neighbours){
            if (getShortestDistance(node) > getShortestDistance(n)+getLongueur(n,node)){
                super.getDistance().put(node, getShortestDistance(n)+getLongueur(n,node));
                this.predecessors.put(node, n);
                this.unmarkeddNodes.add(node);
            }
        }
    }
    
    @Override
    public boolean isMarked(Node n){
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
        path.remove(0);
        return path;
    }

}
