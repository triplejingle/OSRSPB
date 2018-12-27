package scripts.core.goalmethods.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.Guide;
import scripts.core.data.StatsData;
import scripts.core.enumcollection.state;

public class GoalAntiBanHoverMouseAboveStats extends AntiBanGoal {

    Guide guide = new Guide(ctx);
    public GoalAntiBanHoverMouseAboveStats(ClientContext arg0) {
        super(arg0);
    }
    @Override
    protected void setup() {
        if(isEnabled==false){
            this.status= state.COMPLETED;
        }
        if(setup){
            setup=false;
            activateTimer.setPeriodBetween(1000,3000);
            goal="check stats xp" + System.currentTimeMillis()/1000;
        }
    }
    @Override
    public void activate() {
        if(madeAttempt==false) {
            if(guide.checkStatsXP(StatsData.getLastTrainedSkill())){
                madeAttempt=true;
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
