package scripts.goal.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;

public class GoalSwitchTab extends AtomicGoal {
    private Game.Tab tab;

    public GoalSwitchTab(ClientContext arg0, Game.Tab tab) {
        super(arg0);
        this.tab = tab;
    }

    @Override
    protected void init() {
        if (setup) {
            setup = false;
            activateTimer.setPeriodBetween(4000, 7000);
            System.out.println("switching tabs");
            goal = "switch tab" + System.currentTimeMillis() / 1000;
        }
    }

    @Override
    public void activate() {
        if (!madeAttempt) {
            if (ctx.game.tab(tab)) {
                madeAttempt = true;
            }
        }
    }

    public boolean goalReached() {
        return ctx.game.tab() == tab;
    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }
}
