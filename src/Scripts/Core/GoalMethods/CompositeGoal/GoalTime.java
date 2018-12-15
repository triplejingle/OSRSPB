package Scripts.Core.GoalMethods.CompositeGoal;

import Scripts.Core.ENUM.state;
import Scripts.Core.GoalMethods.IGoal;
import org.powerbot.script.rt4.ClientContext;

public class GoalTime extends CompositeGoal {
    long startTime;
    private long runTime;

    public GoalTime(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public void activate() {
       processLowLevelGoal();
    }

    public boolean goalReached() {
        long currentTime = System.currentTimeMillis();
        return startTime + runTime <=currentTime;
    }

    @Override
    public void terminate() {
        emptyStack();
    }

    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
            startTime =System.currentTimeMillis();
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    public boolean isStuck() {
        return hasChildFailed();
    }

    public void setRunTime(long runTime) {
        this.runTime = runTime;
    }
}
