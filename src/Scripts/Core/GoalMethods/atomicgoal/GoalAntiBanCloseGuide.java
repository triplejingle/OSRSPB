package Scripts.Core.GoalMethods.atomicgoal;

import Scripts.Core.ENUM.state;
import Scripts.Core.Player;
import org.powerbot.script.rt4.ClientContext;

public class GoalAntiBanCloseGuide extends AtomicGoal {
    Player player = new Player(ctx,"its you but in code");

    public GoalAntiBanCloseGuide(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public void activate() {
        if(madeAttempt==false){
            if(player.closeGuide()){
                madeAttempt =true;
            }
        }
    }

    public boolean goalReached() {
        if(madeAttempt) {
            return !player.isGuideOpen();
        }
        return false;
    }

    @Override
    public void terminate() {

    }

    public void activateIfInactive(){
        if(madeAttempt){
            return;
        }
        if(status==state.INACTIVE){
            System.out.println("Closing guide");
            status = state.ACTIVE;
            activateTimer.setPeriodBetween(4000,6000);
        }
        if (status == state.ACTIVE && !goalReached()) {
            activate();
        }
    }

    public boolean isStuck(){
        if(madeAttempt) {
			return activateTimer.isTime();
        }
		return false;
    }
}
