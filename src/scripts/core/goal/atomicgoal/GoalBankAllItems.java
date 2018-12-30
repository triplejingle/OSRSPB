package scripts.core.goal.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.Bank;
import scripts.core.Player;

public class GoalBankAllItems extends AtomicGoal {

    private int prevItems;
    private Bank bank = new Bank(ctx);
    private Player player = new Player(ctx, "its you but in code");

    public GoalBankAllItems(ClientContext arg0) {
        super(arg0);
        activateTimer.setPeriodBetween(8000, 10000);
        prevItems = player.countItemsInventory();
        goal = "depositbox all items" + System.currentTimeMillis() / 1000;
    }

    @Override
    protected void startSetup() {
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
        return prevItems < player.countItemsInventory();
    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }
}
