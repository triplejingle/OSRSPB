package Scripts.Core.GoalMethods.AtomicGoal;

import Scripts.Core.Bank;
import Scripts.Core.ENUM.state;
import Scripts.Core.GoalMethods.IGoal;
import Scripts.Core.Player;
import org.powerbot.script.rt4.ClientContext;

public class GoalWithdrawEquipment extends AtomicGoal {
    int itemId;
    int amount;
    Bank bank = new Bank(ctx);

    public GoalWithdrawEquipment(ClientContext arg0) {
        super(arg0);
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
        return ctx.inventory.select().count()==prevNumberOfItems+amount;
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
