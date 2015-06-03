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

    @Override
    public LinkedList<Node> shortestTrip(Graph g, Node n, Robot r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Node, Integer> getDistance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public enum COULEUR {BLANC, NOIR};
    public Graph ng;
    public Map<Node,COULEUR> couleur_noeud;
    public Map<Node,Double> distance_pour_ce_noeud;
    public Map<Node,ArrayList<Node>> chemin_vers_ce_noeud;
    public AlgorithmeParcoursProfondeur() {
        super();
    }
        public Map<Integer, ArrayList<Node>> shortestTrip_(Graph g, Node n, Robot r) {
        couleur_noeud = new Hashtable<>();
        distance_pour_ce_noeud = new Hashtable<>();
        chemin_vers_ce_noeud = new Hashtable<>();
        Map<Integer,ArrayList<Node>> resultat = new Hashtable<>();
        ng = NouveauGraph(g, r);
        Node current = r.getN();
        ArrayList<Node> file = new ArrayList<>();
        file.add(current);
       // couleur_noeud.replace(current, COULEUR.NOIR);
        ArrayList temp = new ArrayList<Node>();
        temp.add(current);
        //chemin_vers_ce_noeud.replace(current, temp);
        while (!file.isEmpty()){
            current = file.get(0);
            file.remove(0);
            System.out.println("");
            for(Node enf : listeEnfant(ng, current)){
                double dist = distance_pour_ce_noeud.get(current);  
                ArrayList<Node> chemversnoeud = new ArrayList<>();
                chemversnoeud = doublonChemin(current);
                //chemversnoeud = chemin_vers_ce_noeud.get(current);
                
                if(couleur_noeud.get(enf)!=COULEUR.NOIR) {
                    file.add(enf);
              //      couleur_noeud.replace(enf, COULEUR.NOIR);
                    dist = dist + ng.findEdge(current, enf).getLength();
                //    distance_pour_ce_noeud.replace(enf, dist);
                    chemversnoeud.add(enf);
                  //  chemin_vers_ce_noeud.replace(enf, chemversnoeud);
                    if(enf.getId()==n.getId()) {
                        resultat.put((int)dist, chemversnoeud);
                        return resultat;
                    }
                }
            } 
        }
        return resultat;
    }
    public Graph NouveauGraph(Graph g, Robot r){
        Graph nouv = new Graph();
        ArrayList<Node> resnode = new ArrayList<>();
        ArrayList<Edge> resedge = new ArrayList<>();
        for (Node n : g.getListNodes()) {
            resnode.add(n);
            couleur_noeud.put(n, COULEUR.BLANC);
            distance_pour_ce_noeud.put(n,0.0);
            chemin_vers_ce_noeud.put(n, new ArrayList<Node>());
        }
        for(Edge e : g.getListEdges()) {
            if(r.possibleTrip(e)) {
                resedge.add(e);
            }
        }
        nouv.setListNodes(resnode);
        nouv.setListEdge(resedge);
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
    public ArrayList<Node> doublonChemin(Node n) {
        ArrayList<Node> res = new ArrayList<>();
        for(Node ne : chemin_vers_ce_noeud.get(n)) {
            Node newn = new Node(ne.getId(), (int)ne.getX(), (int)ne.getY(), ne.getType());
            if (ne.getType() == TypeNode.INCENDIE)
                newn.kindleFire();
            res.add(newn);
        }
        return res;
    }
}
