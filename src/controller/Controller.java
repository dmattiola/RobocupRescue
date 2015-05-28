package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
                break;
            case "Arrêter":
                break;
            case "Sauvegarder":
                break;
            case "Charger":
                break;
            case "Ajouter Incendie":
                break;
            case "Ajouter Robot":
                break;
            case "Effacer":
                break;
            case "Quitter":
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent me){
        
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
