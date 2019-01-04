package scripts.goal.atomicgoal;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import scripts.core.data.*;
import scripts.core.enumcollection.state;
import scripts.goal.IGoal;
import scripts.core.selector.ObjectSelector;
import scripts.core.selector.PlayerSelector;
import scripts.tools.ATimer;

import java.util.Random;
import java.util.Stack;


public abstract class AtomicGoal extends ClientAccessor implements IGoal {
    protected state status = state.INACTIVE;
    protected ATimer activateTimer = new ATimer();
    protected ATimer executeTimer = new ATimer();
    protected boolean madeAttempt = false;
    protected String goal;
    protected boolean setup = true;
    protected Random random = new Random();

    public AtomicGoal(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public String getGoal() {
        return goal;
    }

    int generatedNumber = 0;

    protected void turnCamera() {

        PlayerSelector playerSelector = new PlayerSelector(ctx);
        ObjectSelector objectSelector = new ObjectSelector(ctx);
        IViewport iObjectData = new ObjectData(ctx);
        IViewport playerData = new PlayerData(ctx);
        if (generatedNumber == 0) {
            generatedNumber = random.nextInt(100);
        }
        if (generatedNumber > 80) {
            generatedNumber = 1;
            if (playerSelector.select().shuffle().peek() != null) {
                playerData.turnTo();
            } else if (objectSelector.select().shuffle().peek() != null) {
                iObjectData.turnTo();
            }
        }
    }

    @Override
    public state process() {
        init();
        if (isStuck()) {
            System.out.println("task failed when executing " + goal);
            status = state.FAILED;
            return status;
        } else if (goalReached()) {
            System.out.println("goal reached " + goal);
            status = state.COMPLETED;
            return status;
        }
        turnCamera();
        activateIfInactive();
        return status;
    }

    protected void activateIfInactive() {
        if (status == state.INACTIVE) {
            status = state.ACTIVE;
        }
        if (status == state.ACTIVE) {
            activate();
        }
    }

    protected abstract void init();

    public boolean isActive() {
        return status == state.ACTIVE;
    }

    public boolean isCompleted() {
        return status == state.COMPLETED;
    }

    public boolean hasFailed() {
        return status == state.FAILED;
    }

    public state getState() {
        return status;
    }

    public Stack<IGoal> getSubGoals() {
        return new Stack<>();
    }

    public void addSubGoal(IGoal goal) {

    }
}
