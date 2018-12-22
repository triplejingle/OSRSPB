
package scripts.core.goalmethods.compositegoal;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import scripts.core.WalkerMethods;
import scripts.core.goalmethods.IGoal;
import scripts.core.goalmethods.atomicgoal.GoalMoveTo;

public class GoalWalkToLocation extends CompositeGoal {
    WalkerMethods walkerMethods =new WalkerMethods(ctx);
    Tile[] path;
    public GoalWalkToLocation(ClientContext arg0,Tile[] path) {
        super(arg0);
        this.path = path;
    }
    @Override
    protected void setup() {
        if(setup){
            setup=false;
            for(int i = path.length-1;i>=0;i--){
                IGoal goal = new GoalMoveTo(ctx,path[i]);
                addSubGoal(goal);
            }
            goal="walk to location"+ System.currentTimeMillis()/1000;
        }
    }

    public boolean goalReached() {
        return walkerMethods.isNear(path[path.length-1]);
    }

    public boolean isStuck(){
        return false;
    }

    @Override
    public void terminate() {
        emptyStack();
    }

    public void removeAllSubGoals(){
        for(IGoal IGoal : children){
            IGoal.terminate();
            children.remove(IGoal);
        }
    }
}
