package scripts.core.goalmethods.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.Guide;
import scripts.core.enumcollection.state;

public class GoalAntiBanScrollThroughGuide extends AntiBanGoal {
    Guide guide = new Guide(ctx);
    public GoalAntiBanScrollThroughGuide(ClientContext arg0) {
        super(arg0);
    }
    @Override
    protected void setup() {
        if(isEnabled==false){
            this.status=state.COMPLETED;
        }
        if(setup){
            setup=false;
            activateTimer.setPeriodBetween(1000,6000);
            executeTimer.setPeriod(7000);
            goal="scroll guide"+ System.currentTimeMillis()/1000;
            System.out.println("never mind me just scrolling through the guide");
        }
    }
    @Override
    public void activate() {
        if(madeAttempt==false){
            if(activateTimer.isTime()) {
                madeAttempt = true;
            }
        }
        guide.scrollThroughGuide();
    }

    public boolean goalReached() {
       return madeAttempt;
    }

    @Override
    public void terminate() {

    }

    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
        }
        if (status == state.ACTIVE) {
            activate();
        }
    }

    public boolean isStuck() {
        return executeTimer.isTime();
    }
}
