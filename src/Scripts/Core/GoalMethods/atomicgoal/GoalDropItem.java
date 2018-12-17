package Scripts.Core.GoalMethods.atomicgoal;

import Scripts.Core.ENUM.state;
import Scripts.Core.Player;
import org.powerbot.script.rt4.ClientContext;

public class GoalDropItem extends AtomicGoal {
    Player player =  new Player(ctx,"its you but in code");
    public GoalDropItem(ClientContext arg0, String item) {
        super(arg0);
        this.item=item;
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

    public void activateIfInactive(){
        if(madeAttempt){
            return;
        }
        if(status==state.INACTIVE){
            status = state.ACTIVE;
            activateTimer.setPeriodBetween(5000,10000);
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }
    public void setItem(String item){
        this.item = item;
    }
}
