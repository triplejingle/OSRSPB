package scripts.core.goalmethods.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.Player;

public class GoalDropItem extends AtomicGoal {
    Player player =  new Player(ctx,"its you but in code");
    public GoalDropItem(ClientContext arg0, String item) {
        super(arg0);
        this.item=item;
    }
    @Override
    protected void setup() {
        if(setup){
            setup=false;
            activateTimer.setPeriodBetween(5000,10000);
            goal="drop item"+ System.currentTimeMillis()/1000;
        }
    }
    String item;
    @Override
    public void activate() {
        if(madeAttempt==false) {
            ctx.input.send("{VK_SHIFT down}");
            if(player.drop(item)){
                madeAttempt = true;
            }
            ctx.input.send("{VK_SHIFT up}");
        }
    }

    @Override
    public void terminate() {

    }

    public boolean goalReached() {
        if(madeAttempt){
            return true;
        }
        return ctx.inventory.select().name(item).count()==0;
    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }
    public void setItem(String item){
        this.item = item;
    }
}
