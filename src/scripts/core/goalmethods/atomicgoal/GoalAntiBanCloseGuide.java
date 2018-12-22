package scripts.core.goalmethods.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.Guide;

public class GoalAntiBanCloseGuide extends AtomicGoal {
    Guide guide = new Guide(ctx);

    public GoalAntiBanCloseGuide(ClientContext arg0) {
        super(arg0);
    }
    @Override
    protected void setup() {
        if(setup){
            setup=false;
            System.out.println("Closing guide");
            activateTimer.setPeriodBetween(4000,6000);
            goal="close guide"+ System.currentTimeMillis()/1000;
        }
    }
    @Override
    public void activate() {
        if(madeAttempt==false){
            if(guide.closeGuide()){
                madeAttempt =true;
            }
        }
    }

    public boolean goalReached() {
        if(madeAttempt) {
            return !guide.isGuideOpen();
        }
        return false;
    }

    @Override
    public void terminate() {

    }

    public boolean isStuck(){
        if(madeAttempt) {
			return activateTimer.isTime();
        }
		return false;
    }
}