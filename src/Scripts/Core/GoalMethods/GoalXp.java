package Scripts.Core.GoalMethods;

import Scripts.Core.ENUM.state;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;

public class GoalXp extends Goal{
    public GoalXp(ClientContext ctx) {
        super(ctx);
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
        if(status== state.INACTIVE){
            status = state.ACTIVE;
            prevXp =ctx.skills.experience(Constants.SKILLS_MINING);
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    long prevXp;
    long expGoal;
    private boolean goalReached() {
        long currentXp = ctx.skills.experience(Constants.SKILLS_MINING);
        return prevXp+expGoal<=currentXp;
    }

    @Override
    public void terminate() {
        emptyStack();
    }
    public void setExpGoal(long expGoal){
        this.expGoal =expGoal;
    }

    public boolean isStuck() {
        return false;
    }
}
