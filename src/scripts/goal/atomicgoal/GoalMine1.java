package scripts.goal.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.domain.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GoalMine1 extends AtomicGoal {

    private Player player = new Player(ctx, "its you but in code");
    private int prevNOItems;
    private String rockName;

    public GoalMine1(ClientContext arg0, String rockName) {
        super(arg0);
        this.rockName = rockName;
    }

    @Override
    protected void init() {
        if (setup) {
            setup = false;
            activateTimer.setPeriodBetween(5000, 10000);
            goal = "mine 1" + System.currentTimeMillis() / 1000;
        }
    }

    @Override
    public void activate() {
        if (!madeAttempt) {
            prevNOItems = ctx.inventory.select().count();
            madeAttempt = true;
            executeTimer.setPeriodBetween(1000, 3000);
            throw new NotImplementedException();
        }
    }


    public boolean goalReached() {
        if (ctx.inventory.select().count()>=28) {
            return true;
        }
        if (madeAttempt) {
            return prevNOItems < ctx.inventory.select().count();
        }
        return false;
    }

    public boolean isStuck() {
        if (!player.isDoingNothing()) {
            return false;
        }
        if (madeAttempt) {
            return executeTimer.isTime();
        }
        return activateTimer.isTime();
    }
}
