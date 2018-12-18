
package Scripts.Core.GoalMethods.compositegoal;
import Scripts.Core.ENUM.state;
import Scripts.Core.GoalMethods.atomicgoal.GoalMoveTo;
import Scripts.Core.GoalMethods.IGoal;
import Scripts.Core.WalkerMethods;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class GoalWalkToLocation extends CompositeGoal {
    WalkerMethods walkerMethods =new WalkerMethods(ctx);
    Tile[] path;
    public GoalWalkToLocation(ClientContext arg0,Tile[] path) {
        super(arg0);
        this.path = path;
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

    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
            for(int i = path.length-1;i>=0;i--){
                IGoal goal = new GoalMoveTo(ctx,path[i]);
                addSubGoal(goal);
            }
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    public void removeAllSubGoals(){
        for(IGoal IGoal : children){
            IGoal.terminate();
            children.remove(IGoal);
        }
    }

    public void setPath(Tile[] path){
        this.path = path;
    }
}
