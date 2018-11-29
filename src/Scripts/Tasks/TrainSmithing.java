package Scripts.Tasks;

import Scripts.Core.Bank;
import Scripts.Core.WalkerMethods;
import Scripts.Skills.Smithing;
import Scripts.Tools.ATimer;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

public class TrainSmithing extends ClientAccessor{
    int copperOre = 436;
    int tinOre = 438;
    Smithing smithing = new Smithing(ctx);
    Bank bank = new Bank(ctx);
    WalkerMethods walkToBank = new WalkerMethods(ctx);
    WalkerMethods walkToFurnace = new WalkerMethods(ctx);
    public static final Tile[] furnaceToBank = { new Tile(3279, 3185, 0), new Tile(3280, 3180, 0), new Tile(3279, 3175, 0), new Tile(3276, 3171, 0), new Tile(3273, 3167, 0)};
    Tile[] bankToFurnace = ctx.movement.newTilePath(furnaceToBank).reverse().toArray();
    public TrainSmithing(ClientContext arg0) {
        super(arg0);
        walkToBank.addPath(furnaceToBank);
        walkToFurnace.addPath(bankToFurnace);
        if(ctx.inventory.select().count()>14){
            walkToBank.setPath();
        }else{
            walkToFurnace.setPath();
        }
    }

    public void smith(){

    }
    public void Smelt(){
        String ore="Copper ore";
        String ore2 = "Tin ore";
        String bar = "Bronze bar";
        if(ctx.inventory.select().name(ore).count()>0&&
                ctx.inventory.select().name(ore2).count()>0) {
            if(smithing.furnaceInViewport()) {
                smithing.Smelt(bar);
            }else{
                walkToFurnace.walkPath();
            }
        }else{
            if(ctx.bank.inViewport()) {
                bank.openBank();
                bank.depositAllItems();
                bank.withdraw(copperOre,14);
                bank.withdraw(tinOre,14);
                if(ctx.inventory.select().count()>=2) {
                    bank.closeBank();
                }
            }else{
                walkToBank.walkPath();
            }
        }
    }
}
