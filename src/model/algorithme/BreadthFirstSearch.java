package model.algorithme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.robot.Robot;

/**
 *
 * @author Anthony
 */
public class BreadthFirstSearch extends Algorithme{

    public enum COULEUR {WHITE, BLACK};
    public Map<Node,COULEUR> color_nodes;
    public Map<Node,LinkedList<Node>> path;
    
    public BreadthFirstSearch() {
        super();
    }
    
    @Override
    public LinkedList<Node> shortestTrip(Graph g, Node n, Robot r) {
        super.setListNodes((ArrayList<Node>) g.getListNodes().clone());
        super.setListEdges((ArrayList<Edge>) g.getListEdges().clone());
        super.setR(r);
        initList();
        LinkedList<Node> result = new LinkedList<>();
        ArrayList<Node> file = new ArrayList<>();
        LinkedList<Node> temp = new LinkedList<>();
        Node current = r.getN();
        file.add(current);
        color_nodes.put(current, COULEUR.BLACK);
        temp.add(current);
        while (!file.isEmpty()){
            current = file.get(0);
            file.remove(0);
            for(Node child : getNeighbours(current)){
                int dist = super.getDistance().get(current);  
                LinkedList<Node> chemversnoeud = new LinkedList<>();
                chemversnoeud = (LinkedList<Node>) path.get(current).clone();
                if(color_nodes.get(child)!=COULEUR.BLACK) {
                    file.add(child);
                    color_nodes.put(child, COULEUR.BLACK);
                    dist = dist + getLongueur(current, child);
                    super.getDistance().put(child, dist);
                    chemversnoeud.add(child);
                    path.put(child, chemversnoeud);
                    if (child.getId() == n.getId()){
                        result = chemversnoeud;
                        return result;
                    }
                }
            } 
        }
        return result;
    }
    
    public void initList() {
        this.color_nodes = new HashMap<>();
        super.setDistance(new HashMap<Node,Integer>());
        this.path = new HashMap<>();
        for (Node n : super.getListNodes()) {
            this.color_nodes.put(n, COULEUR.WHITE);
            super.getDistance().put(n,0);
            this.path.put(n, new LinkedList<Node>());
        }
    }

    
    //garder
    @Override
    public boolean isMarked(Node n){
        return COULEUR.BLACK == color_nodes.get(n);
    }
}
