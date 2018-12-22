package scripts.core.goalmethods.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.Bank;

public class GoalOpenBank extends AtomicGoal {

    Bank bank =  new Bank(ctx);

    public GoalOpenBank(ClientContext arg0) {
        super(arg0);
    }
    @Override
    protected void setup() {
        if(setup){
            setup=false;
            activateTimer.setPeriodBetween(10000,12000);
            goal="open bank"+ System.currentTimeMillis()/1000;
        }
    }
    public  void activate() {
        if(madeAttempt==false) {
            if(bank.openBank()){
                madeAttempt=true;
            }
        }
    }

    public boolean goalReached() {
        return bank.isBankOpened();
    }

    public  void terminate(){

    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }

}
