package Scripts.Planners;

import Scripts.Core.Bank;
import Scripts.Core.WalkerMethods;
import Scripts.Skills.Woodcutting;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

public class PlannerWoodcutting extends Planner{
    WalkerMethods walkToBank = new WalkerMethods(ctx);
    WalkerMethods walkToTrees = new WalkerMethods(ctx);
    Woodcutting woodcutting = new Woodcutting(ctx);
    Bank bank = new Bank(ctx);
    Tile startTile = ctx.players.local().tile();

    public PlannerWoodcutting(ClientContext arg0) {
        super(arg0);
        Tile[] tmp = {startTile};
        walkToTrees.addPath(tmp);
    }

    @Override
    void chooseMethod() {

    }

    public void customPowerChopWoodcutting(){
        String tree = "Tree";
        if(ctx.inventory.select().count()<28) {
            if(ctx.players.local().tile().distanceTo(startTile)<15){
                woodcutting.chop(tree);
            }else{
                walkToTrees.walkPath();
            }
        }else{
            for(Item item: ctx.inventory.select().shuffle()){
                item.interact("Drop");
            }
        }
    }
}
