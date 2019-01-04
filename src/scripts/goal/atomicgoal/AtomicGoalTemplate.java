package scripts.goal.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.enumcollection.state;

public class AtomicGoalTemplate extends AtomicGoal {
    public AtomicGoalTemplate(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public void activate() {
        //used to poll
    }

    @Override
    protected void init() {
        if(setup){
            setup=false;
        }
    }
    @Override
    protected void activateIfInactive() {
        if(status==state.INACTIVE){
            status = state.ACTIVE;
            activateTimer.setPeriodBetween(5000,10000);
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    @Override
    public boolean goalReached() {
        return false;
    }

    @Override
    public boolean isStuck() {
        return false;
    }

}
