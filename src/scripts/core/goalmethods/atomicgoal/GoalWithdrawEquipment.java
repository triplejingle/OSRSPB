package scripts.core.goalmethods.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.Bank;
import scripts.core.Player;

public class GoalWithdrawEquipment extends AtomicGoal {
    String itemName;
    int amount;
    Bank bank = new Bank(ctx);
    Player player = new Player(ctx,"its you but in code");
    public GoalWithdrawEquipment(ClientContext arg0, String itemName) {
        super(arg0);
        this.itemName = itemName;
    }
    @Override
    protected void setup() {
        if(setup){
            setup=false;
            System.out.println("getting equipment");
            prevNumberOfItems = ctx.inventory.select().count();
            activateTimer.setPeriodBetween(6000,10000);
            goal="withdraw equipment"+ System.currentTimeMillis()/1000;
        }
    }
    @Override
    public void activate() {
        if(madeAttempt==false) {
            if(bank.withdraw(itemName, amount)){
                madeAttempt=true;
                executeTimer.setPeriodBetween(5000,10000);
            }
        }
    }

    public boolean goalReached() {
        return prevNumberOfItems<player.countItemsInventory();
    }

    int prevNumberOfItems;

    @Override
    public void terminate() {

    }

    public boolean isStuck() {
        if(madeAttempt){
            return executeTimer.isTime();
        }
        return activateTimer.isTime();
    }
}
