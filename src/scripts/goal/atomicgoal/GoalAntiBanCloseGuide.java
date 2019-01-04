package scripts.goal.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.domain.Guide;
import scripts.core.enumcollection.state;

public class GoalAntiBanCloseGuide extends AntiBanGoal {
    private Guide guide = new Guide(ctx);

    public GoalAntiBanCloseGuide(ClientContext arg0) {
        super(arg0);
    }
    @Override
    protected void init() {
        if(!isEnabled){
            this.status= state.COMPLETED;
        }
        if(setup){
            setup=false;
            System.out.println("Closing guide");
            activateTimer.setPeriodBetween(4000,6000);
            goal="close guide"+ System.currentTimeMillis()/1000;
        }
    }
    @Override
    public void activate() {
        if(!madeAttempt){
            if(guide.closeGuide()){
                madeAttempt =true;
            }
        }
    }

    public boolean goalReached() {
            return !guide.isGuideOpen();
    }

    public boolean isStuck(){
        return activateTimer.isTime();
    }
}
