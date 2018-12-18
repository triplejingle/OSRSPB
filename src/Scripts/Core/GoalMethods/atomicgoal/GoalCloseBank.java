package Scripts.Core.GoalMethods.atomicgoal;

import Scripts.Core.Bank;
import Scripts.Core.ENUM.state;
import org.powerbot.script.rt4.ClientContext;

public class GoalCloseBank extends AtomicGoal {

    Bank bank =  new Bank(ctx);

    public GoalCloseBank(ClientContext arg0) {
        super(arg0);
    }

    public  void activate() {
        if(madeAttempt==false) {
            if(bank.closeBank()){
                madeAttempt=true;
            }
        }
    }

    public boolean goalReached() {
        return !bank.isBankOpened();
    }

    public  void terminate(){

    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }

    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
            activateTimer.setPeriodBetween(5000,10000);
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }
}
