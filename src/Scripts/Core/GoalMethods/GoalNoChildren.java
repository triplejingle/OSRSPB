package Scripts.Core.GoalMethods;

import Scripts.Core.ENUM.state;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;


public class GoalNoChildren extends Goal {
    Random random = new Random();
    public GoalNoChildren(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public void activate() {
      processLowLevelGoal();
    }

    @Override
    public state process() {
        activateIfInactive();
        if (isStuck()){
            System.out.println("task failed");
            status = state.FAILED;
        }else if (goalReached()){
            System.out.println("goalCompleted reached");
            status = state.COMPLETED;
        }
        return status;
    }

    private void activateIfInactive() {
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

    private boolean goalReached() {
        if(children.size()>0) {
            return children.peek().children.size()==0;
        }
        return false;
    }

    @Override
    public void terminate() {

    }

    public boolean isStuck() {
        return false;
    }
}
