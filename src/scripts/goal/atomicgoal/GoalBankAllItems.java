package scripts.goal.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.domain.Bank;
import scripts.core.domain.Player;

public class GoalBankAllItems extends AtomicGoal {

    private int prevItems;
    private Bank bank = new Bank(ctx);
    private Player player = new Player(ctx, "its you but in code");

    public GoalBankAllItems(ClientContext arg0) {
        super(arg0);
        activateTimer.setPeriodBetween(8000, 10000);
        prevItems = ctx.inventory.select().count();
        goal = "depositbox all items" + System.currentTimeMillis() / 1000;
    }

    @Override
    protected void init() {
        if (setup) {
            setup = false;
        }
    }

    public void activate() {
        if (!madeAttempt) {
            if (bank.depositAllItems()) {
                madeAttempt = true;
            }
        }
    }

    public boolean goalReached() {
        return prevItems < ctx.inventory.select().count();
    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }
}
