package scripts.goal.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.data.IObjectData;
import scripts.core.data.IViewport;
import scripts.core.data.ObjectData;
import scripts.core.enumcollection.state;
import scripts.core.selector.ObjectSelector;

public class GoalAntiBanTurnToObject extends AntiBanGoal {
    private IViewport iObjectData = new ObjectData(ctx);
    private ObjectSelector objectSelector = new ObjectSelector(ctx);
    public GoalAntiBanTurnToObject(ClientContext arg0) {
        super(arg0);
    }

    @Override
    protected void init() {
        if (setup) {
            setup = false;
            activateTimer.setPeriodBetween(300, 1000);
            objectSelector.select().shuffle();
            if (ObjectSelector.peek() == null) {
                this.status = state.COMPLETED;
            }
            goal = "goal turn to object" + System.currentTimeMillis() / 1000;
        }
    }

    @Override
    public void activate() {
        if (!madeAttempt) {
            iObjectData.turnTo();
            if (activateTimer.isTime()) {
                madeAttempt = true;
            }
        }
    }

    public boolean goalReached() {
        if (madeAttempt) {
            return true;
        }
        return activateTimer.isTime();
    }

    public boolean isStuck() {
        return false;
    }
}
