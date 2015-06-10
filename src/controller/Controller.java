package controller;

import java.awt.event.*;
import javax.swing.*;
import model.algorithme.BreadthFirstSearch;
import model.algorithme.Dijkstra;
import model.graph.*;
import model.mapmanager.MapManager;
import model.robot.*;
import view.*;

/**
 * Controller of the RobotCup Rescue
 * @author Dylan & Anthony
*/
public class Controller implements ActionListener, MouseListener, ItemListener {

    // ATTRIBUTES
    private FrameRobocup fr;
    private PanelMap pm;
    private Thread th;
    private MapManager manager;
    private String action = "";
    private Node selectedNode = null;
    
    // CONSTRUCTORS

    /**
     * Constructor of a Controller
    */
    public Controller(){ }
    
    // ACTIONLISTENER METHODS

    /**
     * When a click happens on a button
     * @param ae
    */
    @Override
    public void actionPerformed(ActionEvent ae){
        String actions = ae.getActionCommand();
        switch(actions){
            case "Lancer":
                this.th = new Thread(this.getManager());
                this.th.start();
                changeButtons(false);
                break;
            case "Arrêter":
                this.th.stop();
                changeButtons(true);
                break;
            case "Sauvegarder":
                this.manager.getGr().createFile();
                break;
            case "Charger":
                this.manager.setGr(this.manager.getGr().loadFile());
                this.pm.repaint();
                break;
            case "Incendie":
                this.action = actions;
                break;
            case "Robot":
                this.action = actions;
                break;
            case "Noeud":
                this.action = actions;
                break;
            case "Arc":
                this.action = actions;
                break;
            case "Recharge":
                this.action = actions;
                break;
            case "Afficher":
                this.fr.changeHide("Cacher");
                this.manager.getGr().setShown(true);
                this.pm.repaint();
                break;
            case "Cacher":
                this.fr.changeHide("Afficher");
                this.manager.getGr().setShown(false);
                this.pm.repaint();
                break;
            case "Effacer":
                this.fr.delete();
                break;
            case "Quitter":
                this.fr.quit();
                break;
            default:
                break;
        }
    }

    // MOUSELISTENER METHODS
    
    /**
     * When a click happens on the map
     * @param me MouseEvent
    */
    @Override
    public void mouseClicked(MouseEvent me){
        int x_ = me.getX();
        int y_ = me.getY();
        Node n = null;
        switch(this.action){
            case "Noeud":
                if (findNode(x_, y_) == null){
                    pm.addNode(new Node(x_,y_,TypeNode.NORMAL));
                    this.action = "";
                } else {
                    JOptionPane.showMessageDialog(pm, "Noeud déjà existant.");
                }
                break;
            case "Arc":
                n = findNode(x_, y_); 
                if (n == null){
                    JOptionPane.showMessageDialog(pm, "Vous n'avez pas cliqué sur un noeud.");
                } else {
                    if (selectedNode != null && !n.equals(selectedNode)){     
                        pm.addEdge(new Edge(n,selectedNode,this.fr.getTypeedge()));
                        this.action = "";
                        this.selectedNode = null;
                    }
                    else {
                        selectedNode = n;
                    }
                }
                break;
            case "Incendie":
                n = findNode(x_, y_);
                if (n == null){
                    Node node = new Node(x_,y_,TypeNode.INCENDIE);
                    node.setFire(this.fr.getIntensityFire());
                    pm.addNode(node);
                } else {
                    n.kindleFire(this.fr.getIntensityFire());
                    this.pm.repaint();
                }
                break;
            case "Recharge":
                n = findNode(x_, y_);
                if (n == null){
                    Node node = new Node(x_,y_,TypeNode.RECHARGE);
                    node.setFire(this.fr.getIntensityFire());
                    pm.addNode(node);
                } else {
                    n.setType(TypeNode.RECHARGE);
                    this.pm.repaint();
                }
                break;
            case "Robot":
                n = findNode(x_,y_);
                if (n == null){
                    JOptionPane.showMessageDialog(pm, "Vous devez ajouter un robot sur un noeud.");
                }
                else {
                    if (this.fr.getTyperobot() == TypeRobot.TOUT_TERRAIN){
                        this.pm.addRobot(new RobotCrossCountry(n));
                    }
                    if (this.fr.getTyperobot() == TypeRobot.A_PATTES){
                        this.pm.addRobot(new RobotLegs(n));
                    }
                    if (this.fr.getTyperobot() == TypeRobot.A_CHENILLES){
                        this.pm.addRobot(new RobotCaterpillar(n));
                    }
                }
                break;
            default:
                break;
        }
    }
    
    @Override
    public void mousePressed(MouseEvent me){ }
    @Override
    public void mouseReleased(MouseEvent me){ }
    @Override
    public void mouseEntered(MouseEvent me){ }
    @Override
    public void mouseExited(MouseEvent me){ }

    // ITEMLISTENER METHODS

    /**
     * When a combobox change its selected item
     * @param ie ItemEvent
    */
    @Override
    public void itemStateChanged(ItemEvent ie){
        JComboBox cb = (JComboBox)ie.getSource();
        Object type = cb.getSelectedItem();
        if (type instanceof TypeEdge){
            this.fr.setTypeedge(TypeEdge.valueOf(type.toString()));
        } else if (type instanceof TypeRobot){
            this.fr.setTyperobot(TypeRobot.valueOf(type.toString()));
        } else {
            switch(this.fr.getAlgorithme()){
                case "Dijkstra":
                    this.manager.setA(new Dijkstra());
                    break;
                case "Largeur":
                    this.manager.setA(new BreadthFirstSearch());
                    break;
            }
        }
    }
    
    // METHODS
    
    /**
     * Change buttons Start / Stop
     * @param b disable buttons
    */
    public void changeButtons(boolean b){
        ((JButton)((JPanel)fr.getContentPane().getComponent(2)).getComponent(0)).setEnabled(b);
        ((JButton)((JPanel)fr.getContentPane().getComponent(2)).getComponent(1)).setEnabled(!b);
        ((JComboBox)((JPanel)fr.getContentPane().getComponent(2)).getComponent(3)).setEnabled(b);
    }
    
    /**
     * Find node with its position
     * @param x Position on X
     * @param y Position on Y
     * @return (Node) Node at this Position
    */
    private Node findNode(int x, int y){
        for (Node n : this.manager.getGr().getListNodes()){
            if (x > n.getX()-6 && x < n.getX()+6 && y < n.getY()+6 && y > n.getY()-6){
                return n;
            }
        }
        return null;
    }
    
    // GETTERS & SETTERS
    
    /**
     * Get the manager : MapManager
     * @return (MapManager) Manager
    */
    public MapManager getManager(){
        return manager;
    }

    /**
     * Set the manager : MapManager
     * @param manager Manager
    */
    public void setManager(MapManager manager){
        this.manager = manager;
    }
    
    /**
     * Get the frame : FrameRobocup
     * @return (FrameRobocup) Frame
    */
    public FrameRobocup getFr(){
        return fr;
    }

    /**
     * Set the frame : FrameRobocup
     * @param fr Frame
    */
    public void setFr(FrameRobocup fr){
        this.fr = fr;
    }

    /**
     * Get the panel : PanelMap
     * @return (PanelMap) Panel
    */
    public PanelMap getPm(){
        return pm;
    }

    /**
     * Set the panel : PanelMap
     * @param pm Panel
    */
    public void setPm(PanelMap pm){
        this.pm = pm;
    }
    
}