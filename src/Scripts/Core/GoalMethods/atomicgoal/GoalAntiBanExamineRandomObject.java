package Scripts.Core.GoalMethods.atomicgoal;

import Scripts.Core.ENUM.state;
import Scripts.Core.Object;
import org.powerbot.script.rt4.ClientContext;

public class GoalAntiBanExamineRandomObject extends AtomicGoal {
    Object object = new Object(ctx,"");
    public GoalAntiBanExamineRandomObject(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public void activate() {
        if(madeAttempt==false) {
            object = new Object(ctx);
            object.chooseRandomObject();
            if (object.examine()) {
                madeAttempt = true;
            }
        }
    }

    public boolean goalReached() {
        return madeAttempt;
    }

    @Override
    public void terminate() {

    }

    public void activateIfInactive(){
        if(madeAttempt){
            return;
        }
        if(status==state.INACTIVE){
            status = state.ACTIVE;
            activateTimer.setPeriodBetween(3000,7000);
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }
}
