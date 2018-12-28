package scripts.core.goalmethods.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.Bank;

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

    @Override
    protected void setup() {
        if(setup){
            setup=false;
            activateTimer.setPeriodBetween(5000,10000);
            goal="close depositbox"+ System.currentTimeMillis()/1000;
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
}
