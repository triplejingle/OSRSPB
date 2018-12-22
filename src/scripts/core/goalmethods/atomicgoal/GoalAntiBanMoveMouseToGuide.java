package scripts.core.goalmethods.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.Guide;

public class GoalAntiBanMoveMouseToGuide extends AtomicGoal {
    Guide guide = new Guide(ctx);

    public GoalAntiBanMoveMouseToGuide(ClientContext arg0) {
        super(arg0);
    }
    @Override
    protected void setup() {
        if(setup){
            setup=false;
            activateTimer.setPeriodBetween(2000,4000);
            goal="move mouse to guide"+ System.currentTimeMillis()/1000;
            System.out.println("just moving my mouse to the guide");
        }
    }
    @Override
    public void activate() {
        if(madeAttempt==false){ ;
            if(guide.moveMouseInGuideScreen()) {
                madeAttempt = true;
            }
        }
    }

    public boolean goalReached() {
        return madeAttempt;
    }

    @Override
    public void terminate() {

    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }
}
