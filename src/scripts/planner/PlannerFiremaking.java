package scripts.planner;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class PlannerFiremaking extends Planner {
    public PlannerFiremaking(ClientContext arg0) {
        super(arg0);
    }

    @Override
    void chooseMethod() {

    }

    public void makeFireUsingLogs(){

    }

    Tile getDestinationFromPath(Tile[] path){
        return path[path.length-1];
    }

}
