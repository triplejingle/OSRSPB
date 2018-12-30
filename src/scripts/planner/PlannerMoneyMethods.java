package scripts.planner;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class PlannerMoneyMethods extends Planner{
    public final Tile[] ExchangeToAubury = {new Tile(3164, 3487, 0), new Tile(3164, 3482, 0), new Tile(3164, 3477, 0), new Tile(3164, 3472, 0), new Tile(3165, 3467, 0), new Tile(3166, 3462, 0), new Tile(3171, 3461, 0), new Tile(3176, 3459, 0), new Tile(3180, 3456, 0), new Tile(3185, 3454, 0), new Tile(3189, 3450, 0), new Tile(3192, 3446, 0), new Tile(3196, 3442, 0), new Tile(3200, 3439, 0), new Tile(3205, 3438, 0), new Tile(3205, 3433, 0), new Tile(3209, 3429, 0), new Tile(3214, 3429, 0), new Tile(3217, 3425, 0), new Tile(3221, 3421, 0), new Tile(3225, 3418, 0), new Tile(3227, 3413, 0), new Tile(3232, 3411, 0), new Tile(3235, 3407, 0), new Tile(3235, 3402, 0), new Tile(3240, 3402, 0), new Tile(3245, 3402, 0), new Tile(3249, 3399, 0), new Tile(3253, 3402, 0)};
    public final Tile[] AuburyToExchange = ctx.movement.newTilePath(ExchangeToAubury).reverse().toArray();

    public PlannerMoneyMethods(ClientContext arg0) {
        super(arg0);
    }

    @Override
    void chooseMethod() {

    }

    public void buyMindRunesVarrock() {

    }
}
