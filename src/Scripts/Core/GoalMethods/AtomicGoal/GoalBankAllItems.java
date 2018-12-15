package Scripts.Core.GoalMethods.AtomicGoal;

import Scripts.Core.Bank;
import Scripts.Core.ENUM.state;
import Scripts.Core.GoalMethods.AtomicGoal.AtomicGoal;
import Scripts.Core.GoalMethods.CompositeGoal.CompositeGoal;
import Scripts.Core.GoalMethods.IGoal;
import Scripts.Core.Player;
import org.powerbot.script.rt4.ClientContext;

public class GoalBankAllItems extends AtomicGoal {

    private int prevItems;
    Bank bank =  new Bank(ctx);
    Player player = new Player(ctx, "its you but in code");

    public GoalBankAllItems(ClientContext arg0) {
        super(arg0);
    }

    public  void activate() {
        if(madeAttempt) {
            if(bank.depositAllItems()){
                madeAttempt=false;
            }
        }
    }

    public boolean goalReached() {
        return prevItems>player.countItemsInventory();
    }

    public  void terminate(){

    }

    public boolean isStuck() {
        if(madeAttempt){
            return activateTimer.isTime();
        }
        return false;
    }

    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
            activateTimer.setPeriodBetween(5000,10000);
            prevItems = player.countItemsInventory();
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }
}
