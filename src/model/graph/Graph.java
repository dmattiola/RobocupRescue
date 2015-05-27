package model.graph;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Dylan
 */
public class Graph {
    
    // Attributes
    private ArrayList<Node> listNodes;
    private ArrayList<Edge> listEdges;
    
    // Constructors
    public Graph(){
    }
    
    // Methods
    private void createFile(){
        File f = new File("graphmap.xml");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            PrintWriter pw = new PrintWriter(bw);
            pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            pw.println("<osm>");
            for (Node node : this.getListNodes()) {
                pw.println(" <node id=\""+node.getId()+"\" x=\""+node.getX()+"\" y=\""+node.getY()+"\" type=\""+node.getType()+"\" />");
            }
            for (Edge edge : this.getListEdges()) {
                pw.println(" <edge nd1=\""+edge.getNode1().getId()+"\" nd2=\""+edge.getNode2().getId()+"\" type=\""+edge.getType()+"\" />");
            }
            pw.println("</osm>");
            pw.close();
        } catch (IOException e) {
            System.out.println("Writing Error : " + e.getMessage());
        }
    }
    
    // Getters & Setters
    public ArrayList<Node> getListNodes(){
        return listNodes;
    }
    public ArrayList<Edge> getListEdges(){
        return listEdges;
    }
}
