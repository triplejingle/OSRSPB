package Scripts.Skills;

import Scripts.Core.Collection.Animations;
import Scripts.Core.ItemInventoryInteractive;
import Scripts.Core.NPCInteractive;
import Scripts.Tools.ATimer;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

public class Fishing extends CoreSkill{
    Animations animations = new Animations();
    Random random = new Random();
    public Fishing(ClientContext arg0) {
        super(arg0);
    }
    NPCInteractive fishing_spot = new NPCInteractive(ctx,"Fishing spot");
    NPCInteractive rod_fishing_spot = new NPCInteractive(ctx,"Rod Fishing spot");

    public boolean fish(String fishingTool,String fishingSpot){
        if(ctx.inventory.select().name(fishingTool).count()>0) {
            return fishing_spot.fish(fishingSpot);
        }
        return false;
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

    public void fishUsingFlyFishingRod() {
        if(ctx.players.local().animation()==animations.getNothing()) {
            rod_fishing_spot.fish("Lure");
        }
    }

    public boolean inViewport() {
        return rod_fishing_spot.inViewport();
    }
}
