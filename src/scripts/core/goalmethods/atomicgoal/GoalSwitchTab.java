package scripts.core.goalmethods.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import scripts.core.Player;

public class GoalSwitchTab extends AtomicGoal {
    Player player = new Player(ctx, "its you but in code");
    Game.Tab tab;

    public GoalSwitchTab(ClientContext arg0, Game.Tab tab) {
        super(arg0);
        this.tab = tab;
    }

    @Override
    protected void setup() {
        if (setup) {
            setup = false;
            activateTimer.setPeriodBetween(4000, 7000);
            System.out.println("switching tabs");
            goal = "switch tab" + System.currentTimeMillis() / 1000;
        }
    }

    @Override
    public void activate() {
        if (madeAttempt == false) {
            if (player.switchToTab(tab)) {
                madeAttempt = true;
            }
        }
    }

    public boolean goalReached() {
        return ctx.game.tab() == tab;
    }

    @Override
    public void terminate() {

    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }
}
