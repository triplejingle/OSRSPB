package Scripts.Core.GoalMethods.atomicgoal;

import Scripts.Core.Bank;
import Scripts.Core.ENUM.state;
import Scripts.Core.Player;
import org.powerbot.script.rt4.ClientContext;

public class GoalWithdrawEquipment extends AtomicGoal {
    int itemId;
    int amount;
    Bank bank = new Bank(ctx);
    Player player = new Player(ctx,"its you but in code");
    public GoalWithdrawEquipment(ClientContext arg0, int itemId) {
        super(arg0);
        this.itemId = itemId;
    }

    @Override
    public void activate() {
        if(madeAttempt==false) {
            if(bank.withdraw(itemId, amount)){
                madeAttempt=true;
                executeTimer.setPeriodBetween(5000,10000);
            }
        }
    }

    public boolean goalReached() {
        return prevNumberOfItems<player.countItemsInventory();
    }

    int prevNumberOfItems;
    public void activateIfInactive() {
        if(madeAttempt){
            return;
        }
        if(status==state.INACTIVE){
            System.out.println("getting equipment");
            status = state.ACTIVE;
            prevNumberOfItems = ctx.inventory.select().count();
            activateTimer.setPeriodBetween(6000,10000);
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    @Override
    public void terminate() {

    }

    public void addEquipment(int equipmentId){
        itemId = equipmentId;
    }

    public boolean isStuck() {
        if(madeAttempt){
            return executeTimer.isTime();
        }
        return activateTimer.isTime();
    }
}
