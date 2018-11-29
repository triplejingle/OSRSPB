package Scripts.Skills;


import Scripts.Core.Collection.Animations;
import Scripts.Core.ObjectInteractive;
import Scripts.Tools.ATimer;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;

public class Woodcutting extends CoreSkill {
    Animations animations = new Animations();
    Scripts.Tools.ATimer timer = new ATimer();
    Random random = new Random();
    public Woodcutting(ClientContext arg0, int period) {
        super(arg0, period);
    }
    public Woodcutting(ClientContext arg0) {
        super(arg0);
    }
    public void chopTrees(){
        chop("Tree");
    }

    public void chop(String treeType){
        int maxInventory = 28;
        ObjectInteractive object = new ObjectInteractive(ctx, treeType);
        timer.setPeriod(random.nextInt(2000,5000));
        if(timer.isTime()) {
            if (ctx.inventory.select().count() < maxInventory) {
                if (ctx.players.local().animation() == animations.getNothing()) {
                    object.select().nearest();
                    object.interact("Chop");
                }
            }
        }
    }
}
