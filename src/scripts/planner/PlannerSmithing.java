package scripts.planner;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import scripts.core.WalkerMethods;

public class PlannerSmithing extends Planner{
    public static final Tile[] furnaceToBank = { new Tile(3279, 3185, 0), new Tile(3280, 3180, 0), new Tile(3279, 3175, 0), new Tile(3276, 3171, 0), new Tile(3273, 3167, 0)};
    Tile[] bankToFurnace = ctx.movement.newTilePath(furnaceToBank).reverse().toArray();
    public PlannerSmithing(ClientContext arg0) {
        super(arg0);

    }

    @Override
    void chooseMethod() {

    }


    public void smith(){

    }
    public void Smelt(){

    }
}
