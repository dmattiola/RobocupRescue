package model.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Model Graph
 * @author Dylan & Anthony
*/
public class Graph extends Observable {
    
    // ATTRIBUTES
    private ArrayList<Node> listNodes;
    private ArrayList<Edge> listEdges;
    private boolean shown;
    
    // CONSTRUCTORS

    /**
     * Constructor of a Graph
    */
    public Graph(){
        this.listEdges = new ArrayList<>();
        this.listNodes = new ArrayList<>();
        this.shown = true;
    }
    
    // METHODS

    /**
     * Save the current graph in a chosen file
    */
    public void createFile(){
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Only XML Files", "xml");
        chooser.addChoosableFileFilter(filter);
        chooser.showSaveDialog(null);
        File f = chooser.getSelectedFile();
        if (!f.getName().endsWith(".xml")){
            f = new File(f.getAbsolutePath().concat(".xml"));
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            try (PrintWriter pw = new PrintWriter(bw)) {
                pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                pw.println("<osm>");
                for (Node node : this.getListNodes()) {
                    if(node.getType() == TypeNode.INCENDIE){
                        pw.println(" <node id=\""+node.getId()+"\" x=\""+node.getX()+"\" y=\""+node.getY()+"\" type=\""+node.getType()+"\" intensity=\""+node.getFire()+"\" />");
                    } else {
                        pw.println(" <node id=\""+node.getId()+"\" x=\""+node.getX()+"\" y=\""+node.getY()+"\" type=\""+node.getType()+"\" />");
                    }
                }
                for (Edge edge : this.getListEdges()) {
                    pw.println(" <edge nd1=\""+edge.getNode1().getId()+"\" nd2=\""+edge.getNode2().getId()+"\" type=\""+edge.getType()+"\" />");
                }
                pw.println("</osm>");
            }
        } catch (IOException e) {
            System.out.println("Writing Error : " + e.getMessage());
        }
    }
    
    /**
     * Load graph from a chosen file
     * @return (Graph) New Graph From File
    */
    public Graph loadFile(){
        String path = "";
        JFileChooser choix = new JFileChooser();
        choix.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Only xml files", "xml");
        choix.addChoosableFileFilter(filter);
        if (choix.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            path = choix.getSelectedFile().getAbsolutePath();
        }
        Graph g = new Graph();
        boolean quit = false;
        int max = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            do {
                String line = br.readLine();
                if (line.indexOf("<node") != -1){
                    String[] node_xml = line.split("\"");
                    Node node = new Node(Integer.parseInt(node_xml[1]),(int)Double.parseDouble(node_xml[3]),(int)Double.parseDouble(node_xml[5]),TypeNode.valueOf(node_xml[7]));
                    max = Math.max(max, node.getId());
                    if (node.getType() == TypeNode.INCENDIE){
                        node.kindleFire((int)Double.parseDouble(node_xml[9]));
                    }
                    g.getListNodes().add(node);
                }
                if (line.indexOf("<edge") != -1){
                    String[] edge_xml = line.split("\"");
                    Edge edge = new Edge(findNode(g,Integer.parseInt(edge_xml[1])),findNode(g,Integer.parseInt(edge_xml[3])),TypeEdge.valueOf(edge_xml[5]));
                    g.getListEdges().add(edge);
                }
                if (line.startsWith("</osm>")){
                    quit = true;
                } 
                Node.setNb_node(max+1);
            } while (!quit);
        } catch (FileNotFoundException e) {
            System.out.println("File not found : "+e.getMessage());
        } catch (IOException e) {
            System.out.println("Listening file error : "+e.getMessage());
        }
        return g;
    }
    
    /**
     * Find node from its ID and the current graph
     * @param g Current Graph
     * @param id Node ID
     * @return (Node) Node Found By Its ID
    */
    private Node findNode(Graph g, int id){
        for(Node n : g.getListNodes()){
            if (n.getId() == id){
                return n;
            }
        }
        return null;
    }

    /**
     * Find Edge from its initial and final node
     * @param n1 Initial or Final Node
     * @param n2 Initial or Final Node
     * @return (Edge) Edge Found By Its Initial and Final Node
    */
    public Edge findEdge(Node n1, Node n2){
        for(Edge e: getListEdges()){
            if(e.getNode1().getId() == n1.getId() && e.getNode2().getId() == n2.getId())
                return e;
            if(e.getNode1().getId() == n2.getId() && e.getNode2().getId() == n1.getId())
                return e;
        }
        return null;
    }
    
    // GETTERS & SETTERS

    /**
     * Get the list of nodes of the graph
     * @return (ArrayList<Node>) List of Nodes of the Graph
    */
    public ArrayList<Node> getListNodes(){
        return listNodes;
    }

    /**
     * Set the list of nodes for the current graph
     * @param list ArrayList containing Nodes
    */
    public void setListNodes (ArrayList<Node> list){
        this.listNodes = list;
    }

    /**
     * Get the list of edges of the graph
     * @return (ArrayList<Edge>) List of Edges of the Graph
    */
    public ArrayList<Edge> getListEdges(){
        return listEdges;
    }
    
    /**
     * Set the list of edges for the current graph
     * @param list ArrayList containing Edges
    */
    public void setListEdge (ArrayList<Edge> list){
        this.listEdges = list;
    }

    /**
     * Get if the graph is shown or not
     * @return (boolean) Parameter Shown
    */
    public boolean isShown(){
        return shown;
    }

    /**
     * Set parameter shown to show or not the graph
     * @param shown Boolean Graph Shown
    */
    public void setShown(boolean shown){
        this.shown = shown;
    }
    
}