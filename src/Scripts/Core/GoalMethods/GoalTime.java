package Scripts.Core.GoalMethods;

import Scripts.Core.ENUM.state;
import org.powerbot.script.rt4.ClientContext;

public class GoalTime extends Goal {
    long startTime;
    private long runTime;

    public GoalTime(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public void activate() {
       processLowLevelGoal();
    }

    @Override
    public state process() {
        activateIfInactive();
        if (isStuck()){
            System.out.println("task failed");
            status = state.FAILED;
        }else if (goalReached()){
            System.out.println("goalCompleted reached");
            status = state.COMPLETED;
        }
        return status;
    }

    private boolean goalReached() {
        long currentXp = System.currentTimeMillis();
        return startTime + runTime <=currentXp;
    }

    @Override
    public void terminate() {
        emptyStack();
    }

    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
            startTime =-System.currentTimeMillis();
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    public boolean isStuck() {
        return false;
    }

    public void setRunTime(long runTime) {
        this.runTime = runTime;
    }
}
