package controller;

import java.awt.event.*;
import java.util.logging.*;
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
                    th.wait();
                } catch (InterruptedException ex) {
                    System.out.println("Error wait thread");
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Arrêter":
                th.stop();
                break;
            case "Sauvegarder":
                pm.getGh().createFile();
                break;
            case "Charger":
                break;
            case "Ajouter Incendie":
                break;
            case "Ajouter Robot":
                break;
            case "Ajouter Noeud":
                this.action = action;
                break;
            case "Ajouter Arc":
                break;
            case "Effacer":
                fr.effacer();
                break;
            case "Quitter":
                fr.quitter();
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
        }
        this.action = "";
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
