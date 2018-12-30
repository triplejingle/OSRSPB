
package scripts.core.goal.compositegoal;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import scripts.core.WalkerMethods;
import scripts.core.enumcollection.state;
import scripts.core.goal.IGoal;
import scripts.core.goal.atomicgoal.GoalMoveTo;

public class GoalWalkToLocation extends CompositeGoal {
    WalkerMethods walkerMethods = new WalkerMethods(ctx);
    Tile[] path;
    int tileRange;
    public GoalWalkToLocation(ClientContext arg0, Tile[] path,int tileRange) {
        super(arg0);
        this.path = path;
        this.tileRange = tileRange;
    }

    @Override
    protected void setup() {
        if (setup) {
            setup = false;
            ctx.properties.setProperty("randomevents.disable", "false");
            addSubGoal(new GoalMoveTo(ctx, path[path.length - 1]));
            for (int i = path.length - 2; i > 0; i--) {
                if(walkerMethods.isNear(path[i],6)){
                   break;
                }
                addSubGoal(new IdleUntilNearNextLocation(ctx, path[i]));
                addSubGoal(new GoalMoveTo(ctx, path[i]));
                if(ctx.movement.energyLevel()>90){
                    ctx.movement.running(true);
                }
            }
            goal = "walk to location" + System.currentTimeMillis() / 1000;
        }
    }

    public boolean goalReached() {
        boolean goalReached= walkerMethods.isNear(path[path.length - 1],10);
        if(goalReached){
            ctx.properties.setProperty("randomevents.disable", "true");
        }
        return goalReached;
    }

    public boolean isStuck() {
        return hasChildFailed();
    }

    @Override
    public void terminate() {
        emptyStack();
        this.status= state.FAILED;
    }

    public void removeAllSubGoals() {
        for (IGoal IGoal : children) {
            IGoal.terminate();
            children.remove(IGoal);
        }
    }
}