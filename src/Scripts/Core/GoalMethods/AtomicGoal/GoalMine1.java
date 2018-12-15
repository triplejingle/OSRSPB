package Scripts.Core.GoalMethods.AtomicGoal;

import Scripts.Core.Collection.Animations;
import Scripts.Core.ENUM.state;
import Scripts.Core.Player;
import Scripts.Skills.Mining;
import org.powerbot.script.rt4.ClientContext;

public class GoalMine1 extends AtomicGoal {
    Mining mining = new Mining(ctx);
    Animations animations = new Animations();
    Player player = new Player(ctx, "its you but in code");
    int prevNOItems;
    static int nrOfItems=28;
    public GoalMine1(ClientContext arg0, String rockName) {
        super(arg0);
        this.rockName = rockName;
    }
    String rockName;
    @Override
    public void activate() {
        if(madeAttempt==false) {
            prevNOItems=ctx.inventory.select().count();
            if(mining.tmo(rockName)){
                madeAttempt =true;
                executeTimer.setPeriodBetween(27000,30000);
            }
        }
    }

    public boolean goalReached() {
        if(player.isInventoryFull()){
            return true;
        }
        if(madeAttempt) {
            return prevNOItems+1==ctx.inventory.select().count();
        }
        return false;
    }

    @Override
    public void terminate() {

    }

    public void activateIfInactive(){
        if(madeAttempt){
            return;
        }
        if(status==state.INACTIVE){
            status = state.ACTIVE;
            prevNOItems=ctx.inventory.select().count();
            activateTimer.setPeriodBetween(5000,10000);
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    public boolean isStuck() {
        if(ctx.players.local().animation()!=animations.getNothing()) {
            return false;
        }
        if(madeAttempt){
            return executeTimer.isTime();
        }
        return activateTimer.isTime();
    }

}
