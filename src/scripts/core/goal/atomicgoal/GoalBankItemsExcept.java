package scripts.core.goal.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.Bank;
import scripts.core.data.InventoryData;

public class GoalBankItemsExcept extends AtomicGoal {

    private int prevItems;
    private Bank bank = new Bank(ctx);
    private InventoryData inventoryData = new InventoryData(ctx);
    private String[] item;

    public GoalBankItemsExcept(ClientContext arg0, String[] item) {
        super(arg0);
        this.item = item;
    }

    @Override
    protected void startSetup() {
        if (setup) {
            setup = false;
            activateTimer.setPeriodBetween(12000, 15000);
            inventoryData.setInventory();
            prevItems = inventoryData.countInventory();
            goal = "depositbox all items except" + System.currentTimeMillis() / 1000;
        }
    }

    public void activate() {
        if (!madeAttempt) {
            if (bank.depositAllItemsExcept(item)) {
                madeAttempt = true;
            }
        }
    }

    public boolean goalReached() {
        inventoryData.setInventory();
        return prevItems > inventoryData.countInventory();
    }


    public boolean isStuck() {
        return activateTimer.isTime();
    }

}
