package scripts.core.goalmethods.atomicgoal;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import scripts.core.enumcollection.state;
import scripts.core.goalmethods.IGoal;
import scripts.tools.ATimer;

import java.util.Stack;

public abstract class AtomicGoal extends ClientAccessor implements IGoal {
    public state status =state.INACTIVE;
    ATimer activateTimer = new ATimer();
    ATimer executeTimer = new ATimer();
    boolean madeAttempt = false;
    String goal;
    boolean setup=true;
    public AtomicGoal(ClientContext arg0) {
        super(arg0);
    }
    @Override
    public String getGoal() {
        return goal;
    }
    public abstract void activate();

    @Override
    public state process() {
        setup();
        if (isStuck()){
            System.out.println("task failed when executing "+goal);
            status = state.FAILED;
            return status;
        }else if (goalReached()){
            System.out.println("goal reached " + goal);
            status = state.COMPLETED;
            return status;
        }
        activateIfInactive();
        return status;
    }

    protected void activateIfInactive() {
        if(status==state.INACTIVE){
            status = state.ACTIVE;
        }
        if(status==state.ACTIVE){
            activate();
        }
    }

    protected abstract void setup();

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
