/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.algorithme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.robot.Robot;

/**
 *
 * @author Dylan
 */
public abstract class Algorithme {
    
    private ArrayList<Node> listNodes;
    private ArrayList<Edge> listEdges;
    private Robot r;
    private Map<Node,Integer> distance;
    
    public Algorithme(){ 
        this.listNodes = new ArrayList<>();
        this.listEdges = new ArrayList<>();
        this.r = null;
    }
    
    public abstract LinkedList<Node> shortestTrip(Graph g, Node n, Robot r);
    
    public int getLongueur(Node n1, Node n2){
        for (Edge e : this.getListEdges()){
            if ((e.getNode1().equals(n1) && e.getNode2().equals(n2))||((e.getNode1().equals(n2) && e.getNode2().equals(n1)))){
                return (int)e.getLength();
            }
        }
        return 0;
    }
    public List<Node> getNeighbours(Node n){
        List<Node> neighbours = new ArrayList<>();
        for (Edge e : this.listEdges){
            if (e.getNode1().equals(n) && !isMarked(e.getNode2()) && this.getR().possibleTrip(e)){
                neighbours.add(e.getNode2());
            } else if (e.getNode2().equals(n) && !isMarked(e.getNode1()) && this.getR().possibleTrip(e)){
                neighbours.add(e.getNode1());
            }
        }
        return neighbours;
    }
    public abstract boolean isMarked(Node n);
    
    public ArrayList<Node> getListNodes() {
        return listNodes;
    }

    public ArrayList<Edge> getListEdges() {
        return listEdges;
    }

    public void setListNodes(ArrayList<Node> listNodes) {
        this.listNodes = listNodes;
    }

    public void setListEdges(ArrayList<Edge> listEdges) {
        this.listEdges = listEdges;
    }

    public Robot getR() {
        return r;
    }

    public void setR(Robot r) {
        this.r = r;
    }

    public Map<Node,Integer> getDistance() {
        return distance;
    }

    public void setDistance(Map<Node,Integer> distance) {
        this.distance = distance;
    }
    
}