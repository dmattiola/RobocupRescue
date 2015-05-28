package model.robot;

import model.graph.Edge;

/**
 *
 * @author Dylan
 */
public class RobotLegs extends Robot {

    @Override
    public boolean possibleTrip(Edge e) {
        if (e.getType().equals("INONDE")){
            return false;
        } else {
            return true;
        }
    }
    
}
