package Scripts.Core.GoalMethods.atomicgoal;

import Scripts.Core.ENUM.state;
import Scripts.Core.Player;
import org.powerbot.script.rt4.ClientContext;

public class GoalAntiBanOpenGuide extends AtomicGoal {
    Player player = new Player(ctx,"its you but in code");
    int skill;

    public GoalAntiBanOpenGuide(ClientContext arg0, int skill) {
        super(arg0);
        this.skill = skill;
    }

    @Override
    public void activate() {
        if(madeAttempt==false){
            if(player.openGuide(skill)){
                madeAttempt=true;
            }
        }
    }

    public boolean goalReached() {
        if(madeAttempt) {
            return player.isGuideOpen();
        }
        return false;
    }

    @Override
    public void terminate() {

    }

    public void activateIfInactive(){
        if(status==state.INACTIVE){
            System.out.println("open guide");
            status = state.ACTIVE;
           activateTimer.setPeriodBetween(3000,6000);
        }
        if (status == state.ACTIVE) {
            activate();
        }
    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }
}
