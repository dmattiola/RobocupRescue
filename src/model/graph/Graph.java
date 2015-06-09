package model.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Dylan
 */
public class Graph extends Observable {
    
    // Attributes
    private ArrayList<Node> listNodes;
    private ArrayList<Edge> listEdges;
    private boolean shown;
    
    // Constructors
    public Graph(){
        this.listEdges = new ArrayList<>();
        this.listNodes = new ArrayList<>();
        this.shown = true;
    }
    
    // Methods
    public void createFile(){
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Only xml files", "xml");
        chooser.addChoosableFileFilter(filter);
        chooser.showSaveDialog(null);
        File f = chooser.getSelectedFile();
        if (!f.getName().endsWith(".xml")){
            f = new File(f.getAbsolutePath().concat(".xml"));
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            PrintWriter pw = new PrintWriter(bw);
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
            pw.close();
        } catch (IOException e) {
            System.out.println("Writing Error : " + e.getMessage());
        }
    }
    
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
    
    private Node findNode(Graph g, int id){
        for(Node n : g.getListNodes()){
            if (n.getId() == id){
                return n;
            }
        }
        return null;
    }
    public Edge findEdge(Node n1, Node n2){
        for(Edge e: getListEdges()){
            if(e.getNode1().getId() == n1.getId() && e.getNode2().getId() == n2.getId())
                return e;
            if(e.getNode1().getId() == n2.getId() && e.getNode2().getId() == n1.getId())
                return e;
        }
        return null;
    }
    // Getters & Setters
    public ArrayList<Node> getListNodes(){
        return listNodes;
    }
    public void setListNodes (ArrayList<Node> list){
        this.listNodes = list;
    }
        public void setListEdge (ArrayList<Edge> list){
        this.listEdges = list;
    }
    public ArrayList<Edge> getListEdges(){
        return listEdges;
    }

    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }
}
