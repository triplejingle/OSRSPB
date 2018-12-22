package scripts.planner;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

//ids afhandelen
public class PlannerCooking extends Planner {

    static final Tile[] bankToRangeEdge = {new Tile(3094, 3490, 0), new Tile(3090, 3490, 0), new Tile(3089, 3494, 0), new Tile(3086, 3498, 0), new Tile(3083, 3501, 0), new Tile(3080, 3498, 0), new Tile(3078, 3494, 0)};
     Tile[] rangeToBankEdge = ctx.movement.newTilePath(bankToRangeEdge).reverse().toArray();
     Random random = new Random();

    public PlannerCooking(ClientContext arg0) {
        super(arg0);

    }

    @Override
    void chooseMethod() {

    }

    public void cookingStove(){

    }

    Tile getDestinationFromPath(Tile[] path){
        return path[path.length-1];
    }

}
