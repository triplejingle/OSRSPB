package Scripts.Core.GoalMethods.compositegoal;

import Scripts.Core.ENUM.state;
import org.powerbot.script.rt4.ClientContext;

public class GoalTime extends CompositeGoal {
    private long runTime;

    public GoalTime(ClientContext arg0, long runTime) {
        super(arg0);
        startTime=System.currentTimeMillis();
        this.runTime = runTime*60000;
    }

    public boolean goalReached() {
        long currentTime = System.currentTimeMillis();
        return startTime + runTime <currentTime;
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
}
