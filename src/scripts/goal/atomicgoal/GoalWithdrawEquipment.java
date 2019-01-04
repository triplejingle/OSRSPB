package scripts.goal.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.domain.Bank;

public class GoalWithdrawEquipment extends AtomicGoal {
    private String itemName;
    private int amount;
    private Bank bank = new Bank(ctx);
    private int prevNumberOfItems;

    public GoalWithdrawEquipment(ClientContext arg0, String itemName, int amount) {
        super(arg0);
        this.itemName = itemName;
        this.amount = amount;
    }

    @Override
    protected void init() {
        if (setup) {
            setup = false;
            System.out.println("getting equipment");
            prevNumberOfItems = ctx.inventory.select().count();
            activateTimer.setPeriodBetween(6000, 10000);
            goal = "withdraw equipment" + System.currentTimeMillis() / 1000;
        }
    }

    @Override
    public void activate() {
        if (!madeAttempt) {
            if (bank.withdraw(itemName, amount)) {
                madeAttempt = true;
                executeTimer.setPeriodBetween(5000, 10000);
            }
        }
    }

    public boolean goalReached() {
        return prevNumberOfItems < ctx.inventory.select().count();
    }

    public boolean isStuck() {
        if (madeAttempt) {
            return executeTimer.isTime();
        }
        return activateTimer.isTime();
    }
}
