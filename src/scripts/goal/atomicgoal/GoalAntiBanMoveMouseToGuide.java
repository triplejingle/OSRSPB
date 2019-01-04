package scripts.goal.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.domain.Guide;
import scripts.core.enumcollection.state;

public class GoalAntiBanMoveMouseToGuide extends AntiBanGoal {
    private Guide guide = new Guide(ctx);

    public GoalAntiBanMoveMouseToGuide(ClientContext arg0) {
        super(arg0);
    }
    @Override
    protected void init() {
        if(!isEnabled){
            this.status= state.COMPLETED;
        }
        if(setup){
            setup=false;
            activateTimer.setPeriodBetween(2000,4000);
            goal="move mouse to guide"+ System.currentTimeMillis()/1000;
            System.out.println("just moving my mouse to the guide");
        }
    }
    @Override
    public void activate() {
        if(!madeAttempt){
            if(guide.moveMouseInGuideScreen()) {
                madeAttempt = true;
            }
        }
    }

    public boolean goalReached() {
        return madeAttempt;
    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }
}
