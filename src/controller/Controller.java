package controller;

import java.awt.event.*;
import java.util.logging.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.graph.Edge;
import model.graph.Node;
import model.graph.TypeEdge;
import model.graph.TypeNode;
import model.mapmanager.MapManager;
import model.robot.RobotCaterpillar;
import model.robot.RobotCrossCountry;
import model.robot.RobotLegs;
import model.robot.TypeRobot;
import view.FrameRobocup;
import view.PanelMap;

/**
 *
 * @author Dylan
 */
public class Controller implements ActionListener, MouseListener, ItemListener {

    // Attributes
    private FrameRobocup fr;
    private PanelMap pm;
    private Thread th;
    private MapManager manager;
    private String action = "";
    private Node selectedNode = null;
    
    // Constructors
    public Controller(){ }
    
    public void changeButtons(boolean b){
        ((JButton)((JPanel)fr.getContentPane().getComponent(2)).getComponent(0)).setEnabled(b);
        ((JButton)((JPanel)fr.getContentPane().getComponent(2)).getComponent(1)).setEnabled(!b);
        ((JComboBox)((JPanel)fr.getContentPane().getComponent(2)).getComponent(3)).setEnabled(b);
    }
    
    // Methods
    @Override
    public void actionPerformed(ActionEvent ae){
        String action = ae.getActionCommand();
        switch(action){
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
                this.action = action;
                break;
            case "Robot":
                this.action = action;
                break;
            case "Noeud":
                this.action = action;
                break;
            case "Arc":
                this.action = action;
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
                this.fr.effacer();
                break;
            case "Quitter":
                this.fr.quitter();
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent me){
        int x_ = me.getX();
        int y_ = me.getY();
        System.out.println("Clic en : "+x_+" // "+y_);
        if (this.action.equals("Noeud")){
            if (findNode(x_, y_) == null){
                pm.addNode(new Node(x_,y_,TypeNode.NORMAL));
                this.action = "";
            } else {
                JOptionPane.showMessageDialog(pm, "Noeud déjà existant.");
            }
        }
        if (this.action.equals("Arc")){
            Node node1 = findNode(x_, y_); 
            if (node1 == null){
                JOptionPane.showMessageDialog(pm, "Vous n'avez pas cliqué sur un noeud.");
            } else {
                if (selectedNode != null && !node1.equals(selectedNode)){     
                    pm.addEdge(new Edge(node1,selectedNode,this.fr.getTypeedge()));
                    this.action = "";
                    this.selectedNode = null;
                }
                else {
                    selectedNode = node1;
                }
            }
        }
        if (this.action.equals("Incendie")){
            Node n = findNode(x_, y_);
            if (n == null){
                pm.addNode(new Node(x_,y_,TypeNode.INCENDIE));
            } else {
                n.kindleFire();
                this.pm.repaint();
            }
        }
        if (this.action.equals("Robot")){
            Node n = findNode(x_,y_);
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
        }
    }

    private Node findNode(int x, int y){
        for (Node n : this.manager.getGr().getListNodes()){
            if (x > n.getX()-6 && x < n.getX()+6 && y < n.getY()+6 && y > n.getY()-6){
                return n;
            }
        }
        return null;
    }
    
    @Override
    public void mousePressed(MouseEvent me){ }
    @Override
    public void mouseReleased(MouseEvent me){ }
    @Override
    public void mouseEntered(MouseEvent me){ }
    @Override
    public void mouseExited(MouseEvent me){ }

    public FrameRobocup getFr(){
        return fr;
    }
    public void setFr(FrameRobocup fr){
        this.fr = fr;
    }
    public PanelMap getPm(){
        return pm;
    }
    public void setPm(PanelMap pm){
        this.pm = pm;
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        JComboBox cb = (JComboBox)ie.getSource();
        Object type = cb.getSelectedItem();
        if (type instanceof TypeEdge){
            this.fr.setTypeedge(TypeEdge.valueOf(type.toString()));
        } else if (type instanceof TypeRobot){
            this.fr.setTyperobot(TypeRobot.valueOf(type.toString()));
        }
    }

    public MapManager getManager() {
        return manager;
    }

    public void setManager(MapManager manager) {
        this.manager = manager;
    }
    
}
