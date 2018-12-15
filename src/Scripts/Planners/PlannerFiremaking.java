package Scripts.Planners;

import Scripts.Skills.Firemaking;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class PlannerFiremaking extends Planner {
    Firemaking firemaking = new Firemaking(ctx);
    public PlannerFiremaking(ClientContext arg0) {
        super(arg0);
    }

    @Override
    void chooseMethod() {

    }

    public void makeFireUsingLogs(){
        firemaking.makeFireUsing("Logs");
    }

    Tile getDestinationFromPath(Tile[] path){
        return path[path.length-1];
    }

}
