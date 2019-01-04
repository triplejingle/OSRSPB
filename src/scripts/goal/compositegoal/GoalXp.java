package scripts.goal.compositegoal;

import org.powerbot.script.rt4.ClientContext;

public class GoalXp extends CompositeGoal {
    private static long prevXp;
    private long expGoal;
    private int skill;

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
    protected void init() {
        if (setup) {
            setup = false;
            prevXp = ctx.skills.experience(skill);
            goal = "goal xp" + System.currentTimeMillis() / 1000;
        }
    }

    public boolean goalReached() {
        long currentXp = ctx.skills.experience(skill);
        return prevXp + expGoal < currentXp;
    }

    public boolean isStuck() {
        return hasChildFailed();
    }
}
