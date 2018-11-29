package Scripts.Skills;

import Scripts.Core.Collection.Animations;
import Scripts.Core.ItemInventoryInteractive;
import Scripts.Core.ObjectInteractive;
import Scripts.Tools.ATimer;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.GameObject;

public class Cooking extends CoreSkill {
    Woodcutting woodcutting = new Woodcutting(ctx);
    Firemaking firemaking = new Firemaking(ctx);
    ItemInventoryInteractive item;
    Animations animations = new Animations();
    ObjectInteractive fire = new ObjectInteractive(ctx,"Fire");
    ObjectInteractive stove = new ObjectInteractive(ctx, "Stove");
    //270.6 voor 1 5 10 x all
//270 widget.14 component.38 component voor selectObstacle make
    ATimer cookATimer = new ATimer();
    Random random = new Random();
    Component make = ctx.widgets.widget(270).component(14).component(38);
    public Cooking(ClientContext arg0, int period) {
        super(arg0, period);
    }
    public Cooking(ClientContext arg0) {
        super(arg0);
    }

    public void cookRawMeatWithFire(){
        ATimer.setPeriod(750);
        item = new ItemInventoryInteractive(ctx,"Raw beef") ;

        cookWithFire();
    }

    private void cookWithFire(){
        GameObject fire = ctx.objects.select().name("Fire").nearest().poll();
        if(fire.inViewport()) {
            item.use();
            fire.interact("Use");
            Condition.sleep(1000);
            make.click();
            ATimer.saveTime();
            while(ctx.inventory.select().name(item.getName()).count()>0){
                if(ATimer.isTime() && ctx.players.local().animation()==animations.getNothing()){
                    break;
                }else if(ctx.players.local().animation()!=animations.getNothing()){
                    ATimer.saveTime();
                }
            }
        }
    }

    public void cookWithRange(String item) {
        ATimer.setPeriod(random.nextInt(1000,10000));
        if(ATimer.isTime()) {
            if (ctx.players.local().animation() == animations.getNothing()) {
                stove.select().nearest();
                stove.interact("Cook");
            }
        }
        cookATimer.setPeriod(random.nextInt(300,500));
        if(cookATimer.isTime()) {
            if (make.visible()) {
                make.click();
            }
        }
    }
}
