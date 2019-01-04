package scripts.goal.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.domain.Player;

public class GoalIdle extends AtomicGoal {
    private int waitForSeconds;
    private Player player = new Player(ctx, "its you but in code");

    public GoalIdle(ClientContext arg0, int seconds) {
        super(arg0);
        this.waitForSeconds = seconds * 1000;
    }

    @Override
    protected void init() {
        if (setup) {
            setup = false;
            activateTimer.setPeriod(waitForSeconds);
            System.out.println("waiting...");
            goal = "idle" + System.currentTimeMillis() / 1000;
        }
    }

    @Override
    public void activate() {

    }

    public boolean goalReached() {
        return activateTimer.isTime();
    }

    public boolean isStuck() {
        return false;
    }
}
