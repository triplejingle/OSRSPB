package Scripts.Core.GoalMethods.compositegoal;

import Scripts.Core.ENUM.state;
import org.powerbot.script.rt4.ClientContext;

public class GoalXp extends CompositeGoal {

    long prevXp;
    long expGoal;
    int skill;
    public GoalXp(ClientContext ctx, long xpGoal, int skill) {
        super(ctx);
        this.expGoal = xpGoal;
        this.skill = skill;
    }

    @Override
    public void activate() {
        processLowLevelGoal();
    }

    public void activateIfInactive() {
        if(status== state.INACTIVE){
            status = state.ACTIVE;
            prevXp =ctx.skills.experience(skill);
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    public boolean goalReached() {
        long currentXp = ctx.skills.experience(skill);
        return prevXp+expGoal<currentXp;
    }

    @Override
    public void terminate() {
        emptyStack();
    }

    public boolean isStuck() {
        return hasChildFailed();
    }

    public void setExpGoal(long expGoal){
        this.expGoal = expGoal;
    }
}
