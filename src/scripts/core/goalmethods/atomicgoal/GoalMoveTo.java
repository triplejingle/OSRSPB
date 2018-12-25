package scripts.core.goalmethods.atomicgoal;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import scripts.core.WalkerMethods;
import scripts.core.data.PlayerData;
import scripts.core.selector.PlayerSelector;

public class GoalMoveTo extends AtomicGoal {
	PlayerSelector playerSelector = new PlayerSelector(ctx);
	PlayerData playerData = new PlayerData(ctx);
    public GoalMoveTo(ClientContext arg0, Tile nextLocation) {
        super(arg0);
        this.nextLocation= nextLocation;
        PlayerData.setPlayer(playerSelector.local());
    }

    @Override
    protected void setup() {
        if(setup){
            setup=false;
            activateTimer.setPeriodBetween(5000,10000);
            goal="move to"+ System.currentTimeMillis()/1000;
        }
    }
    Tile nextLocation;
    WalkerMethods walkerMethods = new WalkerMethods(ctx);
    @Override
    public void activate() {
        if(madeAttempt==false){
            if(walkerMethods.walkToTile(nextLocation)){
                madeAttempt=true;
                executeTimer.setPeriodBetween(3000,5000);
            }
        }
    }

    @Override
    public void terminate() {

    }

    @Override
    public boolean goalReached() {
        return walkerMethods.isDestinationSetNearTile(nextLocation);
    }

    @Override
    public boolean isStuck() {
        if(playerData.isPlayerMoving()){
            return false;
        }
        if(madeAttempt &&executeTimer.isTime()) {
            return !playerData.isPlayerMoving();
        }
        return activateTimer.isTime();
    }
}
