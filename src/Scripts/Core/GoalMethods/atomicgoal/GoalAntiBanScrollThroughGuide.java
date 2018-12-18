package Scripts.Core.GoalMethods.atomicgoal;

import Scripts.Core.ENUM.state;
import Scripts.Core.Player;
import org.powerbot.script.rt4.ClientContext;

public class GoalAntiBanScrollThroughGuide extends AtomicGoal {
    Player player = new Player(ctx,"its you but in code");

    public GoalAntiBanScrollThroughGuide(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public void activate() {
        if(madeAttempt==false){
            if(activateTimer.isTime()) {
                madeAttempt = true;
            }
        }
        player.scrollThroughGuide();
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
           activateTimer.setPeriodBetween(1000,6000);
           executeTimer.setPeriod(7000);
            System.out.println("never mind me just scrolling through the guide");
        }
        if (status == state.ACTIVE) {
            activate();
        }
    }

    public boolean isStuck() {
        return executeTimer.isTime();
    }
}
