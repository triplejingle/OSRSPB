package scripts.planner;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class PlannerCrafting extends Planner  {
    public static final Tile[] furnaceToBank = { new Tile(3272, 3171,0)};
    Tile[] bankToFurnace = { new Tile(3276, 3185,0)};
    int goldBar = 2357;
    int sapphire = 1607;
    int ringMould = 1592;
    public PlannerCrafting(ClientContext arg0) {
        super(arg0);

    }

    @Override
    void chooseMethod() {

    }

    public void craftUsingFurnace(){
        String ring = "Sapphire ring";
        String bar ="Gold bar";
        String mould = "Ring mould";
        String gem = "Sapphire";

    }


}
