package controller;

import java.awt.event.*;
import java.util.logging.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import model.graph.Edge;
import model.graph.Node;
import model.mapmanager.MapManager;
import view.FrameRobocup;
import view.PanelMap;

/**
 *
 * @author Dylan
 */
public class Controller implements ActionListener, MouseListener {

    // Attributes
    private FrameRobocup fr;
    private PanelMap pm;
    private Thread th;
    private MapManager manager;
    private String action = "";
    private Node selectedNode = null;
    
    // Constructors
    public Controller(){ }
    
    // Methods
    @Override
    public void actionPerformed(ActionEvent ae){
        String action = ae.getActionCommand();
        switch(action){
            case "Lancer":
                
                break;
            case "Suspendre / Reprendre":
                try {
                    this.th.wait();
                } catch (InterruptedException ex) {
                    System.out.println("Error wait thread");
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Arrêter":
                this.th.stop();
                break;
            case "Sauvegarder":
                this.pm.getGh().createFile();
                break;
            case "Charger":
                this.pm.setGh(pm.getGh().loadFile());
                this.pm.repaint();
                break;
            case "Ajouter Incendie":
                this.action = action;
                break;
            case "Ajouter Robot":
                break;
            case "Ajouter Noeud":
                this.action = action;
                break;
            case "Ajouter Arc":
                this.action = action;
                break;
            case "Afficher":
                this.fr.changeHide("Cacher");
                this.pm.getGh().setShown(true);
                this.pm.repaint();
                break;
            case "Cacher":
                this.fr.changeHide("Afficher");
                this.pm.getGh().setShown(false);
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
        if (this.action.equals("Ajouter Noeud")){
            pm.addNode(new Node(x_,y_,"BASE"));
            this.action = "";
        }
        if (this.action.equals("Ajouter Arc")){
            Node node1 = findNode(x_, y_); 
            if (node1 == null){
                JOptionPane.showMessageDialog(pm, "Vous n'avez pas cliqué sur un noeud.");
            } else {
                if (selectedNode != null){     
                    pm.addEdge(new Edge(node1,selectedNode,"PLAT"));
                    this.action = "";
                    this.selectedNode = null;
                }
                else {
                    selectedNode = node1;
                }
            }
        }
        if (this.action.equals("Ajouter Incendie")){
            Node n = findNode(x_, y_);
            if (n == null){
                pm.addNode(new Node(x_,y_,"INCENDIE"));
            } else {
                n.kindleFire();
                this.pm.repaint();
            }
        }
       
    }

    private Node findNode(int x, int y){
        for (Node n : pm.getGh().getListNodes()){
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
    
}
