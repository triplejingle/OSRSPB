package Scripts.Tasks;

import Scripts.Core.Bank;
import Scripts.Core.ObjectInteractive;
import Scripts.Core.WalkerMethods;
import Scripts.Skills.Crafting;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TrainCrafting extends ClientAccessor  {
    Bank bank = new Bank(ctx);    WalkerMethods walkToBank = new WalkerMethods(ctx);
    WalkerMethods walkToFurnace = new WalkerMethods(ctx);
    public static final Tile[] furnaceToBank = { new Tile(3272, 3171,0)};
    Tile[] bankToFurnace = { new Tile(3276, 3185,0)};
    Crafting crafting = new Crafting(ctx);
    int goldBar = 2357;
    int sapphire = 1607;
    int ringMould = 1592;
    public TrainCrafting(ClientContext arg0) {
        super(arg0);
        walkToBank.addPath(furnaceToBank);
        walkToFurnace.addPath(bankToFurnace);
        if(ctx.inventory.select().count()>14){
            walkToBank.setPath();
        }else{
            walkToFurnace.setPath();
        }
    }

    public void craftUsingFurnace(){
        String ring = "Sapphire ring";
        String bar ="Gold bar";
        String mould = "Ring mould";
        String gem = "Sapphire";
        if(ctx.inventory.select().name(gem).count()>0&&
                    ctx.inventory.select().name(mould).count()>0&&
                    ctx.inventory.select().name(bar).count()>0) {
                if(ctx.players.local().tile().distanceTo(bankToFurnace[0])<9) {
                    crafting.craftRing("Sapphire");
                }else{
                    walkToFurnace.walkPath();
                }
        }else{
            if(ctx.players.local().tile().distanceTo(furnaceToBank[0])<8) {
                bank.openBank();
                if(ctx.inventory.select().name(ring).count()>0){
                    bank.depositAllItems();
                }else {
                    if (ctx.inventory.select().name(bar).count() == 0) {
                        bank.withdraw(goldBar, 13);
                    }
                    if (ctx.inventory.select().name(gem).count() == 0) {
                        bank.withdraw(sapphire, 13);
                    }
                    if (ctx.inventory.select().name(mould).count() == 0) {
                        bank.withdraw(ringMould, 1);
                    }
                }
            }else{
                walkToBank.walkPath();
            }
        }
    }


}
