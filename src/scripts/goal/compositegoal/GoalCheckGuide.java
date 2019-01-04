package scripts.goal.compositegoal;

import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import scripts.core.data.StatsData;
import scripts.goal.atomicgoal.*;

public class GoalCheckGuide extends CompositeGoal {
    public GoalCheckGuide(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public void activate() {
        super.activate();
        if (player.getHealth() < 100) {
            emptyStack();
            addSubGoal(new GoalRunAway(ctx));
        }
    }

    @Override
    protected void init() {
        if (setup) {
            setup = false;
            int seconds = Random.nextInt(10, 80);
            Game.Tab prevTab = ctx.game.tab();
            addSubGoal(new GoalSwitchTab(ctx, prevTab));
            addSubGoal(new GoalAntiBanCloseGuide(ctx));
            addSubGoal(new GoalIdle(ctx, seconds));
            addSubGoal(new GoalAntiBanScrollThroughGuide(ctx));
            addSubGoal(new GoalAntiBanMoveMouseToGuide(ctx));
            addSubGoal(new GoalAntiBanOpenGuide(ctx, StatsData.getLastTrainedSkill()));
            addSubGoal(new GoalSwitchTab(ctx, Game.Tab.STATS));
            goal = "withdraw equipment" + System.currentTimeMillis() / 1000;
        }
    }


    public boolean goalReached() {
        return (children.isEmpty());
    }

    public boolean isStuck() {
        return hasChildFailed();
    }
}
