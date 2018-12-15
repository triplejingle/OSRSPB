package Scripts.Planners;

import Scripts.Core.Bank;
import Scripts.Core.WalkerMethods;
import Scripts.Skills.Crafting;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class PlannerCrafting extends Planner  {
    Bank bank = new Bank(ctx);    WalkerMethods walkToBank = new WalkerMethods(ctx);
    WalkerMethods walkToFurnace = new WalkerMethods(ctx);
    public static final Tile[] furnaceToBank = { new Tile(3272, 3171,0)};
    Tile[] bankToFurnace = { new Tile(3276, 3185,0)};
    Crafting crafting = new Crafting(ctx);
    int goldBar = 2357;
    int sapphire = 1607;
    int ringMould = 1592;
    public PlannerCrafting(ClientContext arg0) {
        super(arg0);
        walkToBank.addPath(furnaceToBank);
        walkToFurnace.addPath(bankToFurnace);
        if(ctx.inventory.select().count()>14){
            walkToBank.setPath();
        }else{
            walkToFurnace.setPath();
        }
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
