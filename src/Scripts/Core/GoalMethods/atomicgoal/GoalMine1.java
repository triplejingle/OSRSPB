package Scripts.Core.GoalMethods.atomicgoal;

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
    String rockName;
    public GoalMine1(ClientContext arg0, String rockName) {
        super(arg0);
        this.rockName = rockName;
    }

    @Override
    public void activate() {
        if(madeAttempt==false&&mining.tmo(rockName)){
            prevNOItems = player.countItemsInventory();
            madeAttempt =true;
            executeTimer.setPeriodBetween(1000,3000);
        }
    }


    public boolean goalReached() {
        if(player.isInventoryFull()){
            return true;
        }
        if(madeAttempt) {
            return prevNOItems < player.countItemsInventory();
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
            activateTimer.setPeriodBetween(5000,10000);
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    public boolean isStuck() {
        if(!player.isDoingNothing()) {
            return false;
        }
        if(madeAttempt){
            return  executeTimer.isTime();
        }
        return activateTimer.isTime();
    }
}
