package Scripts.Core.GoalMethods.atomicgoal;

import Scripts.Core.ENUM.state;
import Scripts.Core.Player;
import org.powerbot.script.rt4.ClientContext;

public class GoalAntiBanCheckStatsXp extends AtomicGoal {

    Player player = new Player(ctx,"its you but in code");
    public GoalAntiBanCheckStatsXp(ClientContext arg0) {
        super(arg0);
    }
    int skill;
    public void setSkill(int skill){
        this.skill = skill;
    }
    
    @Override
    public void activate() {
        if(madeAttempt==false) {
          if(player.checkStatsXP(skill)){
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
            activateTimer.setPeriodBetween(5000,10000);
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }
}
