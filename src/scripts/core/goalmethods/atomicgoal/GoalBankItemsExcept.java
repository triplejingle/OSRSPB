package scripts.core.goalmethods.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.Bank;
import scripts.core.data.InventoryData;

public class GoalBankItemsExcept extends AtomicGoal {

    private int prevItems;
    Bank bank =  new Bank(ctx);
    InventoryData inventoryData = new InventoryData(ctx);
    String[] item;
    public GoalBankItemsExcept(ClientContext arg0, String[] item) {
        super(arg0);
        this.item =item;
    }

    @Override
    protected void setup() {
        if(setup){
            setup=false;
            activateTimer.setPeriodBetween(12000,15000);
            inventoryData.setInventory();
            prevItems = inventoryData.countInventory();
            goal="depositbox all items except"+ System.currentTimeMillis()/1000;
        }
    }

    public  void activate() {
        if(madeAttempt==false) {
            if(bank.depositAllItemsExcept(item)){
                madeAttempt=true;
            }
        }
    }

    public boolean goalReached() {
        inventoryData.setInventory();
        return prevItems>inventoryData.countInventory();
    }

    public  void terminate(){

    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }

}
