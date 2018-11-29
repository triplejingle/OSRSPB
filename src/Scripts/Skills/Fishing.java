package Scripts.Skills;

import Scripts.Core.Collection.Animations;
import Scripts.Core.ItemInventoryInteractive;
import Scripts.Core.NPCInteractive;
import Scripts.Tools.ATimer;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

public class Fishing extends CoreSkill{

    ATimer ATimer = new ATimer();
    Animations animations = new Animations();
    Random random = new Random();
    public Fishing(ClientContext arg0) {
        super(arg0);
    }
    NPCInteractive fishing_spot = new NPCInteractive(ctx,"Fishing spot");
    public void fishUsingSmallFishingNet(){
        fishing_spot.fish("Small Net");
    }

    public void dropFish(String[] fish){
        ATimer.setPeriod(random.nextInt(500,1000));
        if(ATimer.isTime()) {
            for(Item itemName: ctx.inventory.select()) {
                    for (int i = 0; i < fish.length; i++) {
                        String tmp = itemName.name();
                    if(itemName.name().equals(fish[i])) {
                        ItemInventoryInteractive item = new ItemInventoryInteractive(ctx, fish[i]);
                        item.dropItem();
                        break;
                    }
                }
            }
        }
    }
    NPCInteractive rod_fishing_spot = new NPCInteractive(ctx,"Rod Fishing spot");
    public void fishUsingFlyFishingRod() {
        if(ctx.players.local().animation()==animations.getNothing()) {
            rod_fishing_spot.fish("Lure");
        }
    }

    public boolean inViewport() {
        return rod_fishing_spot.inViewport();
    }
}
