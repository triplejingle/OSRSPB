package Scripts.Core.GoalMethods.atomicgoal;

import Scripts.Core.ENUM.state;
import Scripts.Core.Player;
import org.powerbot.script.rt4.ClientContext;

public class GoalAntiBanMoveMouseToGuide extends AtomicGoal {
    Player player = new Player(ctx,"its you but in code");

    public GoalAntiBanMoveMouseToGuide(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public void activate() {
        if(madeAttempt==false){ ;
            if(player.moveMouseInGuideScreen()) {
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

    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
           activateTimer.setPeriodBetween(2000,4000);
            System.out.println("just moving my mouse to the guide");
        }
        if (status == state.ACTIVE) {
            activate();
        }
    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }
}
