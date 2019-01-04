package scripts.goal.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.domain.Inventory;
import scripts.core.domain.Player;

public class GoalDropItem extends AtomicGoal {
    private Inventory player = new Inventory(ctx);
    private String item;

    public GoalDropItem(ClientContext arg0, String item) {
        super(arg0);
        this.item = item;
    }

    @Override
    protected void init() {
        if (setup) {
            setup = false;
            activateTimer.setPeriodBetween(5000, 10000);
            goal = "drop item" + System.currentTimeMillis() / 1000;
        }
    }

    @Override
    public void activate() {
        if (!madeAttempt) {
            ctx.input.send("{VK_SHIFT down}");
            if (player.dropItem()) {
                madeAttempt = true;
            }
            ctx.input.send("{VK_SHIFT up}");
        }
    }

    public boolean goalReached() {
        if (madeAttempt) {
            return true;
        }
        return ctx.inventory.select().name(item).count() == 0;
    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }

    public void setItem(String item) {
        this.item = item;
    }
}
