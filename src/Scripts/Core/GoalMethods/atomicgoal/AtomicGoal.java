package Scripts.Core.GoalMethods.atomicgoal;

import Scripts.Core.ENUM.state;
import Scripts.Core.GoalMethods.IGoal;
import Scripts.Tools.ATimer;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

import java.util.Stack;

public abstract class AtomicGoal extends ClientAccessor implements IGoal {
    public state status =state.INACTIVE;
    ATimer activateTimer = new ATimer();
    ATimer executeTimer = new ATimer();
    boolean madeAttempt = false;
    public AtomicGoal(ClientContext arg0) {
        super(arg0);
    }

    public abstract void activate();

    @Override
    public state process() {
        activateIfInactive();
        if (isStuck()){
            System.out.println("task failed");
            status = state.FAILED;
            return status;
        }else if (goalReached()){
            System.out.println("goal reached");
            status = state.COMPLETED;
            return status;
        }
        return status;
    }

    protected abstract void activateIfInactive();

    public abstract boolean isStuck();

    public boolean isActive(){
        return status==state.ACTIVE;
    }

    public boolean isCompleted(){
        return status==state.COMPLETED;
    }

    public boolean hasFailed(){
        return status==state.FAILED;
    }
    public state getState(){
        return status;
    }

    public Stack<IGoal> getSubGoals() {
        return null;
    }

    public void addSubGoal(IGoal goal) {

    }
}
