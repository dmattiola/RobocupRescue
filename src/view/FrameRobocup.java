package view;

import controller.Controller;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import model.algorithme.Dijkstra;
import model.graph.Graph;
import model.graph.Node;
import model.graph.TypeEdge;
import model.mapmanager.MapManager;

/**
 * View FrameRobocup
 * @author Dylan & Anthony
*/
public class FrameRobocup extends JFrame {
    
    // ATTRIBUTES
    public static final Dimension VGAP = new Dimension(1,5);
    public static final Dimension HGAP = new Dimension(5,1);
    public static final String[] listAlgo = {"Dijkstra", "Largeur" };
    public static final String[] listTypeRobot = {"TOUT TERRAIN", "A CHENILLES", "A PATTES"};
    private PanelMap map;
    private final Controller c;
    
    // CONSTRUCTORS
    
    /**
     * Constructor of a FrameRobocup
     * @param c Controller
     * @param gr Graph
    */
    public FrameRobocup(Controller c, Graph gr){
        super("Robocup Rescue");
        this.c = c;
        c.setManager(new MapManager(new Dijkstra(),gr));
        initFrame(gr);
        c.getManager().addObserver(map);
	this.addWindowListener(new WindowAdapter() {
            @Override
	    public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
		System.exit(0);
	    }
	});
        c.changeButtons(true);
    }
    
    // METHODS
    
    /**
     * Quit the program
    */
    public void quit(){
        System.exit(0);
    }
    
    /**
     * Delete the current graph
    */
    public void delete(){
        map.getMap().setGr(new Graph());
	map.repaint();
        Node.setNb_node(0);
        JOptionPane.showMessageDialog(this, "Vous avez effacé le graphe en cours.");
    }
    
    /**
     * Change the button name : Afficher / Cacher
     * @param name afficher ou cacher
    */
    public void changeHide(String name){
        ((JButton)((JToolBar)((JPanel)this.getContentPane().getComponent(1)).getComponent(0)).getComponent(1)).setName(name);
        ((JButton)((JToolBar)((JPanel)this.getContentPane().getComponent(1)).getComponent(0)).getComponent(1)).setText(name);
        ((JButton)((JToolBar)((JPanel)this.getContentPane().getComponent(1)).getComponent(0)).getComponent(1)).setToolTipText(name);
    }
    
    /**
     * Get type edge to create the edge
     * @return Current Type Edge
     */
    public TypeEdge getTypeEdgeCurrent(){
        String typeedge = ((JComboBox)((JToolBar)((JPanel)this.getContentPane().getComponent(1)).getComponent(0)).getComponent(9)).getSelectedItem().toString();
        switch(typeedge){
            case "PLAT":
                return TypeEdge.PLAT;
            case "INONDE":
                return TypeEdge.INONDE;
            case "ESCARPE":
                return TypeEdge.ESCARPE;
        }
        return null;
    }
    
    /**
     * Get type robot to create the robot
     * @return Current Type Robot
     */
    public String getTypeRobotCurrent(){
        String typerobot = ((JComboBox)((JToolBar)((JPanel)this.getContentPane().getComponent(1)).getComponent(0)).getComponent(19)).getSelectedItem().toString();
        return typerobot;
    }
    
    /**
     * Get the JLabel intensity of the fire
     * @return (int) Fire Intensity
    */
    public int getIntensityFire(){
        return Integer.parseInt(((JTextField)((JToolBar)((JPanel)this.getContentPane().getComponent(1)).getComponent(0)).getComponent(13)).getText().toString());
    }
    
    // CONSTRUCTIONS FRAME METHODS
    
    /**
     * Frame initialization and construction 
     * @param g (Graph)
    */
    private void initFrame(Graph g){
        getContentPane().setLayout(new BorderLayout(10,10));

        map = new PanelMap(this.c.getManager());
        map.setBackground(Color.white);
        map.setSize(new Dimension(600,480));
        map.setPreferredSize(new Dimension(600,480));
        map.setWidth(600);
        map.setHeight(480);
        map.getMap().setGr(g);

        getContentPane().add(map,"Center");

        map.addMouseListener(c);

        // Boutons
        JToolBar toolBar = new JToolBar();
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(toolBar);

        getContentPane().add(buttonPanel,"North");

        addButton(toolBar,"Effacer","Nouveau dessin","/icons/index.png");
        addButton(toolBar,"Cacher","Cacher",null);
        toolBar.add(Box.createRigidArea(HGAP));
        toolBar.add(new JLabel("Ajouter :"));
        toolBar.add(Box.createRigidArea(HGAP));
        addButton(toolBar, "Noeud", "Ajouter Noeud", null);
        toolBar.add(Box.createRigidArea(HGAP));
        addButton(toolBar, "Arc", "Ajouter Arc", null);
        toolBar.add(Box.createRigidArea(HGAP));
        JComboBox typeEdgeList = new JComboBox(TypeEdge.values());
        toolBar.add(typeEdgeList);
        toolBar.add(Box.createRigidArea(HGAP));
        addButton(toolBar, "Incendie", "Ajouter Incendie", null);
        toolBar.add(Box.createRigidArea(HGAP));
        JTextField fireIntensity = new JTextField("50");
        toolBar.add(fireIntensity);
        toolBar.add(Box.createRigidArea(HGAP));
        addButton(toolBar, "Recharge", "Ajouter Station de Rechargement", null);
        toolBar.add(Box.createRigidArea(HGAP));
        addButton(toolBar, "Robot", "Ajouter Robot", null);
        toolBar.add(Box.createRigidArea(HGAP));
        JComboBox typeRobotList = new JComboBox(listTypeRobot);
        toolBar.add(typeRobotList);

        // Menus
        JMenuBar menubar=new JMenuBar();
        setJMenuBar(menubar);	// on installe le menu bar

        // on installe le premier menu
        JMenu menuFile=new JMenu("RobocupRescue"); 
        menubar.add(menuFile);
        addMenuItem(menuFile, "Effacer", "Effacer", KeyEvent.VK_N);
        addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

        JMenu menuGraph=new JMenu("Graphe");
        menubar.add(menuGraph);
        addMenuItem(menuGraph, "Ajouter Noeud", "Ajouter Noeud", -1);
        addMenuItem(menuGraph, "Ajouter Arc", "Ajouter Arc", -1);
        addMenuItem(menuGraph, "Sauvegarder", "Sauvegarder", -1);
        addMenuItem(menuGraph, "Charger", "Charger", -1);

        JMenu menuSimulation=new JMenu("Simulation");
        menubar.add(menuSimulation);
        addMenuItem(menuSimulation, "Lancer", "Lancer", -1);
        addMenuItem(menuSimulation, "Suspendre / Reprendre", "Suspendre / Reprendre", -1);
        addMenuItem(menuSimulation, "Arrêter", "Arrêter", -1);

        JMenu menuHelp=new JMenu("Aide");
        menubar.add(menuHelp);
        addMenuItem(menuHelp, "Aide", "Help", -1);
        addMenuItem(menuHelp, "A propos", "About", -1);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // les boutons du bas
        JPanel p2 = new JPanel(new GridLayout());
        JButton b20 = new JButton("Lancer");
        p2.add(b20);
        b20.addActionListener(c);
        JButton b22 = new JButton("Arrêter");
        p2.add(b22);
        b22.addActionListener(c);
        JLabel algo = new JLabel(" Algorithme : ",SwingConstants.CENTER);
        p2.add(algo);
        JComboBox listAlgorithme = new JComboBox(listAlgo);
        p2.add(listAlgorithme);
        listAlgorithme.addItemListener(c);
        getContentPane().add(p2,"South");
        
        pack();
        setVisible(true);
        c.setFr(this);
        c.setPm(map);
    }
    
    /**
     * Util to set up a button
     * @param name Button Name
     * @param tooltiptext Button ToolTipText
     * @param imageName Button ImageName
    */
    private void addButton(JComponent p, String name, String tooltiptext, String imageName){
        JButton b;
	if ((imageName == null) || (imageName.equals(""))){
            b = (JButton)p.add(new JButton(name));
        } else {
            java.net.URL u = this.getClass().getResource(imageName);
            if (u != null){
                ImageIcon im = new ImageIcon (u);
		b = (JButton) p.add(new JButton(im));
            } else {
		b = (JButton) p.add(new JButton(name));
            }
            b.setActionCommand(name);
	}
	b.setToolTipText(tooltiptext);
	b.setBorder(BorderFactory.createRaisedBevelBorder());
	b.setMargin(new Insets(0,0,0,0));
	b.addActionListener(c);
    }

    /**
     * Util to set up a MenuItem
     * @param m JMenu
     * @param label Item Label
     * @param command Item Command
     * @param key Item Key
    */
    private void addMenuItem(JMenu m, String label, String command, int key){
        JMenuItem menuItem;
        menuItem = new JMenuItem(label);
        m.add(menuItem);

        menuItem.setActionCommand(command);
        menuItem.addActionListener(c);
        if (key > 0){
            if (key != KeyEvent.VK_DELETE){
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
            } else {
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
            }
       }
    }

    // GETTERS & SETTERS
    
    /**
     * Get the algorithm in the enum
     * @return (String) Algorithm
    */
    public String getAlgorithme(){
        return ((JComboBox)((JPanel)this.getContentPane().getComponent(2)).getComponent(3)).getSelectedItem().toString();
    }

    
}