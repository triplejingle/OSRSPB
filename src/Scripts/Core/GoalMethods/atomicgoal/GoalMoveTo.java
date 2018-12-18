package Scripts.Core.GoalMethods.atomicgoal;

import Scripts.Core.ENUM.state;
import Scripts.Core.Player;
import Scripts.Core.WalkerMethods;
import Scripts.Tools.ATimer;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class GoalMoveTo extends AtomicGoal {
	ATimer cameraTimer = new ATimer();
    public GoalMoveTo(ClientContext arg0, Tile nextLocation) {
        super(arg0);
        this.nextLocation= nextLocation;
    }
    Tile nextLocation;
    WalkerMethods walkerMethods = new WalkerMethods(ctx);
    @Override
    public void activate() {
        if(madeAttempt==false){
            if(walkerMethods.walkToTile(nextLocation)){
                madeAttempt=true;
            }
        }
	    cameraTimer.setPeriodBetween(200,1000);
	    if(cameraTimer.isTime()) {
		    ctx.camera.turnTo(nextLocation);
	    }
    }

    @Override
    protected void activateIfInactive() {
        if(status== state.INACTIVE){
            status = state.ACTIVE;
            activateTimer.setPeriodBetween(5000,10000);
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    @Override
    public void terminate() {

    }

    @Override
    public boolean goalReached() {
        return walkerMethods.isNear(nextLocation);
    }

    Player player = new Player(ctx,"its you but in code");
    @Override
    public boolean isStuck() {
        if(madeAttempt) {
            return !player.isPlayerMoving();
        }
        return activateTimer.isTime();
    }
}
