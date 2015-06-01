package model.algorithme;

import java.util.ArrayList;
import java.util.Map;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.robot.Robot;

/**
 *
 * @author Dylan
 */
public class Dijkstra extends Algorithme {

    private ArrayList<Node> nodeFixed;
    
    public Dijkstra(){
        super();
        this.nodeFixed = new ArrayList<>();
    }
    
    @Override
    public Map<Integer, ArrayList<Node>> shortestTrip(Graph g, Node n, Robot r) {
        ArrayList<Node> listNodes = g.getListNodes();
        Map<Node,Map<Node,Integer>> res = null;
        for (Node node : listNodes){
            Map<Node,Integer> val = null;
            if(r.getN() == node){
                val.put(n, 0);
                res.put(n,val);
            } else {
                val.put(n, Integer.MAX_VALUE);
                res.put(n,val);
            }
        }
        ArrayList<Edge> edged = findNodeUnfixed(g, r.getN());
        Map<Node,Integer> val = null;
        //val.put
        return null;
    }
    
    private ArrayList<Edge> findNodeUnfixed(Graph g, Node n){
        ArrayList<Edge> result = new ArrayList<>();
        for (Edge edge : g.getListEdges()){
            if ((edge.getNode1() == n || edge.getNode2() == n) && !nodeFixed.contains(n)){
                result.add(edge);
            }
        }
        return result;
    }
}
