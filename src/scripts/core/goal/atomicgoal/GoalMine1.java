package scripts.core.goal.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.Player;
import scripts.core.collection.Animations;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GoalMine1 extends AtomicGoal {

    Animations animations = new Animations();
    Player player = new Player(ctx, "its you but in code");
    int prevNOItems;
    String rockName;
    public GoalMine1(ClientContext arg0, String rockName) {
        super(arg0);
        this.rockName = rockName;
    }
    @Override
    protected void startSetup() {
        if(setup){
            setup=false;
            activateTimer.setPeriodBetween(5000,10000);
            goal="mine 1"+ System.currentTimeMillis()/1000;
        }
    }
    @Override
    public void activate() {
        if(madeAttempt==false){
            prevNOItems = player.countItemsInventory();
            madeAttempt =true;
            executeTimer.setPeriodBetween(1000,3000);
            throw new NotImplementedException();
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
