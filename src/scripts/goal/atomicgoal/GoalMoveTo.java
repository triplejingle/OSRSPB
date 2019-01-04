package scripts.goal.atomicgoal;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import scripts.core.WalkerMethods;
import scripts.core.data.PlayerData;
import scripts.core.selector.PlayerSelector;

public class GoalMoveTo extends AtomicGoal {
    private PlayerSelector playerSelector = new PlayerSelector(ctx);
    private PlayerData playerData = new PlayerData(ctx);
    private Tile nextLocation;
    private WalkerMethods walkerMethods = new WalkerMethods(ctx);

    public GoalMoveTo(ClientContext arg0, Tile nextLocation) {
        super(arg0);
        this.nextLocation = nextLocation;
        playerSelector.local();
    }

    @Override
    protected void init() {
        if (setup) {
            setup = false;
            activateTimer.setPeriodBetween(5000, 10000);
            goal = "move to" + System.currentTimeMillis() / 1000;
        }
    }

    @Override
    public void activate() {
        if (!madeAttempt) {
            if (walkerMethods.walkToTile(nextLocation)) {
                System.out.println(nextLocation);
                madeAttempt = true;
                executeTimer.setPeriodBetween(3000, 5000);
            }
        }
    }

    @Override
    public boolean goalReached() {
        return walkerMethods.isDestinationSetNearTile(nextLocation)||ctx.players.local().tile().distanceTo(nextLocation)<8;
    }

    @Override
    public boolean isStuck() {
        if (playerData.isPlayerMoving()) {
            return false;
        }
        if (madeAttempt && executeTimer.isTime()) {
            return !playerData.isPlayerMoving();
        }
        return activateTimer.isTime();
    }
}
