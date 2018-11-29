package Scripts.Skills;

import Scripts.Core.Collection.Animations;
import Scripts.Core.ItemInventoryInteractive;
import Scripts.Tools.ATimer;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class Firemaking extends CoreSkill{
    ItemInventoryInteractive tinderbox = new ItemInventoryInteractive(ctx,"Tinderbox");
    Animations animations = new Animations();
    ATimer ATimer = new ATimer();
    Random random = new Random();
    public Firemaking(ClientContext arg0) {
        super(arg0);
    }

    public void makeFireUsing(String logType){
        int nothingInInventory = 0;
        if(ctx.inventory.select().name("Tinderbox").count()>nothingInInventory&&
                ctx.inventory.select().name(logType).count()>nothingInInventory) {
            GameObject fire = ctx.objects.select().name("Fire").nearest().poll();
            if (ctx.players.local().animation() == animations.getNothing()&&
                    fire.tile().distanceTo(ctx.players.local().tile())>0) {
                ATimer.setPeriod(random.nextInt(250, 500));
                if (ATimer.isTime()) {
                    if (ctx.inventory.selectedItemIndex() == -1) {
                        ItemInventoryInteractive logs = new ItemInventoryInteractive(ctx, logType);
                        logs.use();
                    }else{
                        tinderbox.use();
                    }
                }
            }
        }
    }
}
