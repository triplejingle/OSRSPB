package Scripts.Core.GoalMethods.compositegoal;

import Scripts.Core.ENUM.state;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;


public class GoalNoChildren extends CompositeGoal {
    Random random = new Random();
    public GoalNoChildren(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public void activate() {
      processLowLevelGoal();
    }

    public void activateIfInactive() {
        if(status==state.INACTIVE){
            status = state.ACTIVE;
            startTime = System.currentTimeMillis();
            timeExpected = random.nextInt(600,3600)+startTime;
            double MarginOfError = 5000;
            timeExpected += MarginOfError;
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    public boolean goalReached() {
        return (children.size()==0);
    }

    @Override
    public void terminate() {

    }

    public boolean isStuck() {
        return hasChildFailed();
    }


}
