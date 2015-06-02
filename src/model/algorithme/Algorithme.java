/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.algorithme;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import model.graph.Graph;
import model.graph.Node;
import model.robot.Robot;

/**
 *
 * @author Dylan
 */
public abstract class Algorithme {
    
    public Algorithme(){ }
    
    public abstract LinkedList<Node> shortestTrip(Graph g, Node n, Robot r);
    public abstract Map<Node,Integer> getDistance();
    
}