package Scripts.Core.GoalMethods.atomicgoal;

import Scripts.Core.ENUM.state;
import Scripts.Core.Object;
import org.powerbot.script.rt4.ClientContext;

public class GoalTurnToObject extends AtomicGoal {
   Object object =new Object(ctx);

    public GoalTurnToObject(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public void activate() {
        if(madeAttempt==false){

            object.turnTo();
            if(activateTimer.isTime()){
                madeAttempt=true;
            }
        }
    }

    public boolean goalReached() {
        if(madeAttempt) {
            return true;
        }
        return activateTimer.isTime();
    }

    @Override
    public void terminate() {

    }

    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
           activateTimer.setPeriodBetween(300,1000);
            object.chooseRandomObject();
        }
        if (status == state.ACTIVE && !goalReached()) {
            activate();
        }
    }

    public boolean isStuck() {
        return false;
    }
}
