package scripts.goal.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.domain.Guide;
import scripts.core.enumcollection.state;

public class GoalAntiBanOpenGuide extends AntiBanGoal {
    private Guide guide = new Guide(ctx);
    private int skill;

    public GoalAntiBanOpenGuide(ClientContext arg0, int skill) {
        super(arg0);
        this.skill = skill;
    }

    @Override
    protected void init() {
        if (!isEnabled) {
            this.status = state.COMPLETED;
        }
        if (setup) {
            setup = false;
            System.out.println("open guide");
            activateTimer.setPeriodBetween(3000, 6000);
            goal = "open guide" + System.currentTimeMillis() / 1000;
        }
    }

    @Override
    public void activate() {
        if (!madeAttempt) {
            if (guide.openGuide(skill)) {
                madeAttempt = true;
            }
        }
    }

    public boolean goalReached() {
        if (madeAttempt) {
            return guide.isGuideOpen();
        }
        return false;
    }

    @Override
    public void activateIfInactive() {
        if (status == state.INACTIVE) {
            status = state.ACTIVE;
        }
        if (status == state.ACTIVE) {
            activate();
        }
    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }
}
