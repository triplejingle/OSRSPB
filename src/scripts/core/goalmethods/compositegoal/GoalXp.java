package scripts.core.goalmethods.compositegoal;

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

    @Override
    protected void setup() {
        if(setup){
            setup=false;
            prevXp =ctx.skills.experience(skill);
            goal="goal xp"+ System.currentTimeMillis()/1000;
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
