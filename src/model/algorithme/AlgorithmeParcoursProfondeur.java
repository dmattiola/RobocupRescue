/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.algorithme;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.graph.TypeNode;
import model.robot.Robot;

/**
 *
 * @author Anthony
 */
public class AlgorithmeParcoursProfondeur extends Algorithme{

    public enum COULEUR {BLANC, NOIR};
    private ArrayList<Node> listNodes;
    private ArrayList<Edge> listEdges;
    private Robot r;
    public Map<Node,COULEUR> couleur_noeud;
    public Map<Node,Integer> distance_pour_ce_noeud;
    public Map<Node,LinkedList<Node>> chemin_vers_ce_noeud;
    public AlgorithmeParcoursProfondeur() {
        super();
        this.listNodes = new ArrayList<>();
        this.listEdges = new ArrayList<>();
    }
    @Override
    public LinkedList<Node> shortestTrip(Graph g, Node n, Robot r) {
        this.listNodes = (ArrayList<Node>) g.getListNodes().clone();
        this.listEdges = (ArrayList<Edge>) g.getListEdges().clone();
        this.r = r;
        initList();
        LinkedList<Node> resultat = new LinkedList<>();
        ArrayList<Node> file = new ArrayList<>();
        LinkedList<Node> temp = new LinkedList<Node>();
        Node current = r.getN();
        file.add(current);
        couleur_noeud.put(current, COULEUR.NOIR);
        temp.add(current);
        while (!file.isEmpty()){
            current = file.get(0);
            file.remove(0);
            for(Node enf : getNeighbours(current)){
                int dist = distance_pour_ce_noeud.get(current);  
                LinkedList<Node> chemversnoeud = new LinkedList<>();
                chemversnoeud = (LinkedList<Node>) chemin_vers_ce_noeud.get(current).clone();
                if(couleur_noeud.get(enf)!=COULEUR.NOIR) {
                    file.add(enf);
                    couleur_noeud.put(enf, COULEUR.NOIR);
                    dist = dist + getLongueur(current, enf);
                    distance_pour_ce_noeud.put(enf, dist);
                    chemversnoeud.add(enf);
                    chemin_vers_ce_noeud.put(enf, chemversnoeud);
                    if(enf.getId()==n.getId()) {
                        resultat = chemversnoeud;
                        System.out.println("dist : " + dist);
                        return resultat;
                    }
                }
            } 
        }
        return resultat;
    }
    @Override
    public Map<Node, Integer> getDistance() {
        return this.distance_pour_ce_noeud;
    }
    public void initList() {
        this.couleur_noeud = new Hashtable<>();
        this.distance_pour_ce_noeud = new Hashtable<>();
        this.chemin_vers_ce_noeud = new Hashtable<>();
        for (Node n : this.listNodes) {
            this.couleur_noeud.put(n, COULEUR.BLANC);
            this.distance_pour_ce_noeud.put(n,0);
            this.chemin_vers_ce_noeud.put(n, new LinkedList<Node>());
        }
    }
    
    //fct djistra
    private int getLongueur(Node n1, Node n2){
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
    //garder
    private boolean isMarked(Node n){
        return COULEUR.NOIR == couleur_noeud.get(n);
    }
}
