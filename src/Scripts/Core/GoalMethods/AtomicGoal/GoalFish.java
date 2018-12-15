package Scripts.Core.GoalMethods.AtomicGoal;

import Scripts.Core.Collection.Animations;
import Scripts.Core.ENUM.state;
import Scripts.Core.GoalMethods.AtomicGoal.AtomicGoal;
import Scripts.Core.GoalMethods.IGoal;
import Scripts.Skills.Fishing;
import org.powerbot.script.rt4.ClientContext;

import java.util.Stack;

public class GoalFish extends AtomicGoal {

    Fishing fishing=new Fishing(ctx);
    Animations animations = new Animations();
    String fishingTool;
    public GoalFish(ClientContext arg0) {
        super(arg0);
    }

    public  void activate() {
        if(madeAttempt==false) {
            if (fishing.fish(fishingTool)) {
                madeAttempt = true;
            }
        }
    }

    public boolean goalReached() {
        return ctx.players.local().animation()!=animations.getNothing();
    }

    public  void terminate(){

    }

    public boolean isStuck() {
        if(madeAttempt) {
            return activateTimer.isTime();
        }
        return false;
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
}
