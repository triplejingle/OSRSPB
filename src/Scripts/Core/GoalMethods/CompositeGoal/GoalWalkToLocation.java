
package Scripts.Core.GoalMethods.CompositeGoal;
import Scripts.Core.ENUM.state;
import Scripts.Core.GoalMethods.AtomicGoal.GoalMoveTo;
import Scripts.Core.GoalMethods.IGoal;
import Scripts.Core.WalkerMethods;
import Scripts.Tools.ATimer;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class GoalWalkToLocation extends CompositeGoal {
    java.util.Stack<IGoal> subgoals = new java.util.Stack();
    WalkerMethods walkerMethods =new WalkerMethods(ctx);
    Tile[] path;
    public GoalWalkToLocation(ClientContext arg0,Tile[] path) {
        super(arg0);
        this.path = path;
    }

    @Override
    public void activate() {
        IGoal goal = subgoals.peek();
        if(goal.getState()!=state.COMPLETED){
            goal.process();
        }else{
            subgoals.pop();
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

    }

    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
            for(int i = path.length;i>0;i--){
                IGoal goal = new GoalMoveTo(ctx);
                addSubGoal(goal);
            }
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    public void removeAllSubGoals(){
        for(IGoal IGoal : subgoals){
            IGoal.terminate();
            subgoals.remove(IGoal);
        }
    }

    public void setPath(Tile[] path){
        this.path = path;
    }
}
