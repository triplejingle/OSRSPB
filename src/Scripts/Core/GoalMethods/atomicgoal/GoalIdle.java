package Scripts.Core.GoalMethods.atomicgoal;

import Scripts.Core.ENUM.state;
import Scripts.Core.Player;
import org.powerbot.script.rt4.ClientContext;

public class GoalIdle extends AtomicGoal {
    int waitForSeconds;
    Player player = new Player(ctx,"its you but in code");
    public GoalIdle(ClientContext arg0, int seconds) {
        super(arg0);
        this.waitForSeconds = seconds*1000;
    }

    @Override
    public void activate() {

    }

    public boolean goalReached() {
        return activateTimer.isTime();
    }

    @Override
    public void terminate() {

    }

    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
            activateTimer.setPeriod(waitForSeconds);
            System.out.println("waiting...");
        }
        if (status == state.ACTIVE) {
            activate();
        }
    }

    public boolean isStuck(){
        return player.getHealth()<100;
    }
}
