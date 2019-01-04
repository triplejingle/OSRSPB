package scripts.goal.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.domain.Bank;

public class GoalOpenBank extends AtomicGoal {

    private Bank bank = new Bank(ctx);

    public GoalOpenBank(ClientContext arg0) {
        super(arg0);
    }

    @Override
    protected void init() {
        if (setup) {
            setup = false;
            activateTimer.setPeriodBetween(7000, 10000);
            goal = "open bank" + System.currentTimeMillis() / 1000;
        }
    }

    public void activate() {
        if (!madeAttempt) {
            if (bank.openBank()) {
                madeAttempt = true;
            }
        }
    }

    public boolean goalReached() {
        return bank.isBankOpened();
    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }

}
