package Scripts.Skills;

import Scripts.Core.Collection.Animations;
import Scripts.Core.ObjectInteractive;
import Scripts.Tools.ATimer;
import Scripts.Tools.Factory.SmeltBarFactory;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

public class Smithing extends ClientAccessor{

    SmeltBarFactory smeltBarFactory = new SmeltBarFactory(ctx);
    ObjectInteractive furnace = new ObjectInteractive(ctx,"Furnace");
    Animations animations = new Animations();
    ATimer ATimer = new ATimer();
    Random random = new Random();
    public Smithing(ClientContext arg0) {
        super(arg0);
    }

    public void smith(String bar, String item){

    }

    public void Smelt(String bar){
        ATimer.setPeriod(random.nextInt(1000,15000));
        if(ATimer.isTime()) {
            if (ctx.players.local().animation() == animations.getNothing()) {
                furnace.select().nearest();
                furnace.interact("Smelt");
            }
        }
        Component selectBar = smeltBarFactory.getComponent(bar);
        if(selectBar.visible()) {
            selectBar.click();
        }
    }

    public boolean furnaceInViewport() {
        return furnace.inViewport();
    }
}
