/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.algorithme;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.robot.Robot;

/**
 *
 * @author Anthony
 */
public class AlgorithmeParcoursProfondeur extends Algorithme{
    public enum COULEUR {BLANC, GRIS, NOIR};
    public Graph ng;
    public Map<Node,COULEUR> couleur_noeud;
    public Map<Node,Double> distance_pour_ce_noeud;
    public Map<Node,ArrayList<Node>> chemin_vers_ce_noeud;
    public AlgorithmeParcoursProfondeur() {
        super();
        couleur_noeud = new Hashtable<>();
        distance_pour_ce_noeud = new Hashtable<>();
        chemin_vers_ce_noeud = new Hashtable<>();
    }
    @Override
    public Map<Integer, ArrayList<Node>> shortestTrip(Graph g, Node n, Robot r) {
        Map<Integer,ArrayList<Node>> resultat = new Hashtable<>();
        ng = NouveauGraph(g, r);
        Node current = n;
        ArrayList<Node> file = new ArrayList<>();
        file.add(current);
        couleur_noeud.replace(current, COULEUR.NOIR);
        while (!file.isEmpty()){
            current = file.get(0);
            System.out.println("");
            for(Node enf : listeEnfant(ng, current)){
                double dist = distance_pour_ce_noeud.get(current);  
                ArrayList chemversnoeud = chemin_vers_ce_noeud.get(current);
                if(couleur_noeud.get(enf)!=COULEUR.NOIR) {
                    file.add(enf);
                    couleur_noeud.replace(enf, COULEUR.NOIR);
                    dist = dist + ng.findEdge(current, enf).getLength();
                    distance_pour_ce_noeud.replace(enf, dist);
                    chemversnoeud.add(enf);
                    chemin_vers_ce_noeud.replace(enf, chemversnoeud);
                    if(enf.getId()==n.getId()) {
                        resultat.put((int)dist, chemversnoeud);
                    }
                }
            } 
        }
        return resultat;
    }
    public Graph NouveauGraph(Graph g, Robot r){
        Graph nouv = new Graph();
        
        for (Node n : g.getListNodes()) {
            ng.getListNodes().add(n);
            couleur_noeud.put(n, COULEUR.BLANC);
            distance_pour_ce_noeud.put(n,0.0);
            chemin_vers_ce_noeud.put(n, new ArrayList<Node>());
        }
        for(Edge e : g.getListEdges()) {
            if(r.possibleTrip(e)) {
                ng.getListEdges().add(e);
            }
        }
        return nouv;
    }
    public ArrayList<Node> listeEnfant(Graph g, Node n){
        ArrayList<Node> list = new ArrayList<>();
        for(Edge e : g.getListEdges()) {
            Node n1 = e.getNode1();
            Node n2 = e.getNode2();
            if(n.getId()==n1.getId()&&couleur_noeud.get(n2) != COULEUR.NOIR) {
                list.add(n2);
            }
            if(n.getId()==n2.getId()&&couleur_noeud.get(n1) != COULEUR.NOIR) {
                list.add(n1);
            }
        }
        return list;
    }

}
