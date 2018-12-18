package Scripts.Core.GoalMethods.atomicgoal;

import Scripts.Core.Bank;
import Scripts.Core.ENUM.state;
import Scripts.Core.Player;
import org.powerbot.script.rt4.ClientContext;

public class GoalBankAllItemsExcept extends AtomicGoal {

    private int prevItems;
    Bank bank =  new Bank(ctx);
    Player player = new Player(ctx, "its you but in code");
    String item;
    public GoalBankAllItemsExcept(ClientContext arg0, String item) {
        super(arg0);
        this.item =item;
    }

    public  void activate() {
        if(madeAttempt==false) {
            if(bank.depositAllItemsExcept(item)){
                madeAttempt=true;
            }
        }
    }

    public boolean goalReached() {
        return prevItems<player.countItemsInventory();
    }

    public  void terminate(){

    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }

    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
            activateTimer.setPeriodBetween(8000,10000);
            prevItems = player.countItemsInventory();
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }
}
