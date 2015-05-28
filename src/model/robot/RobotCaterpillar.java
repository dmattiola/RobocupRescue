package model.robot;

import model.graph.Edge;

/**
 *
 * @author Dylan
 */
public class RobotCaterpillar extends Robot {

    @Override
    public boolean possibleTrip(Edge e){
        return !e.getType().equals("ESCARPE");
    }
    
}
