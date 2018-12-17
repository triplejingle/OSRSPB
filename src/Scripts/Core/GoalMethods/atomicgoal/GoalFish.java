package Scripts.Core.GoalMethods.atomicgoal;

import Scripts.Core.Collection.Animations;
import Scripts.Core.ENUM.state;
import Scripts.Core.Player;
import Scripts.Skills.Fishing;
import org.powerbot.script.rt4.ClientContext;

public class GoalFish extends AtomicGoal {

    private Fishing fishing=new Fishing(ctx);
    private Animations animations = new Animations();
    private String fishingTool;
    private String fishingSpot;
    private Player player = new Player(ctx, "its you but in code!:)");

    public GoalFish(ClientContext arg0,String fishingTool, String fishingSpot) {
        super(arg0);
        this.fishingTool = fishingTool;
        this.fishingSpot=fishingSpot;
    }

    public  void activate() {
        if(madeAttempt==false) {
            if (fishing.fish(fishingTool,fishingSpot)) {
                madeAttempt = true;
                executeTimer.setPeriodBetween(5000,20000);
            }
        }
    }

    public boolean goalReached() {
        return player.isInventoryFull();
    }

    public  void terminate(){

    }

    public boolean isStuck() {
        if(!player.isDoingNothing()) {
            return false;
        }
        if(madeAttempt){
            return  executeTimer.isTime();
        }
        return activateTimer.isTime();
    }

    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
           activateTimer.setPeriodBetween(8000,10000);
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }
}
