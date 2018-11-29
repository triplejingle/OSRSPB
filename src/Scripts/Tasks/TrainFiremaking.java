package Scripts.Tasks;

import Scripts.Skills.Firemaking;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

public class TrainFiremaking extends ClientAccessor {
    Firemaking firemaking = new Firemaking(ctx);
    public TrainFiremaking(ClientContext arg0) {
        super(arg0);
    }

    public void makeFireUsingLogs(){
        firemaking.makeFireUsing("Logs");
    }

    Tile getDestinationFromPath(Tile[] path){
        return path[path.length-1];
    }

}
