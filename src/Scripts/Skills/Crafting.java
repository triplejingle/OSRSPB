package Scripts.Skills;

import Scripts.Core.Collection.Animations;
import Scripts.Core.ObjectInteractive;
import Scripts.Tools.ATimer;
import Scripts.Tools.Factory.CraftingScreenFactory;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

public class Crafting extends CoreSkill{
    CraftingScreenFactory craftingScreenFactory = new CraftingScreenFactory(ctx);
    Animations animations = new Animations();
    Scripts.Tools.ATimer smelTimer = new ATimer();
    Scripts.Tools.ATimer selectTimer = new ATimer();
    Random random = new Random();
    public Crafting(ClientContext arg0) {
        super(arg0);
    }
    ObjectInteractive furnace = new ObjectInteractive(ctx,"Furnace");
    public void craftRing(String sapphire) {
        Component component = craftingScreenFactory.getComponent(sapphire+" ring");
        smelTimer.setPeriod(random.nextInt(2000,4000));
        if(!component.visible()) {
            if (smelTimer.isTime()) {
                if (ctx.players.local().animation() == animations.getNothing()) {
                    furnace.select().nearest();
                    furnace.interact("Smelt");
                }
            }
            if(ctx.players.local().animation()!=animations.getNothing()||ctx.players.local().inMotion()){
                smelTimer.saveTime();
            }
        }
        selectTimer.setPeriod(random.nextInt(2000,4000));
        if(selectTimer.isTime()) {
            if (component.visible()) {
                component.interact("Make-All");
            }
        }
    }

}
