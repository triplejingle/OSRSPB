package Scripts.Core.GoalMethods.atomicgoal;

import Scripts.Core.ENUM.state;
import org.powerbot.script.rt4.ClientContext;

public class AtomicGoalTemplate extends AtomicGoal {
    public AtomicGoalTemplate(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public void activate() {

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
    public void terminate() {

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
