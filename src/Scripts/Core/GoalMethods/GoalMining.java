package Scripts.Core.GoalMethods;

import Scripts.Core.ENUM.state;
import Scripts.Core.GoalMethods.Goal;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;

public class GoalMining extends Goal {

    private long expGoal;

    public GoalMining(ClientContext arg0) {
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

    private boolean goalReached() {
        long currentXp = ctx.skills.experience(Constants.SKILLS_MINING);
        return prevXp+expGoal<=currentXp;
    }

    @Override
    public void terminate() {
        emptyStack();
    }
    long prevXp;

    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
            prevXp =ctx.skills.experience(Constants.SKILLS_MINING);
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    public boolean isStuck() {
        return false;
    }

    public void setExpGoal(long expGoal) {
        this.expGoal = expGoal;
    }
}
