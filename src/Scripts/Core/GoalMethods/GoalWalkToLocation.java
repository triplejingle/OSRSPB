
package Scripts.Core.GoalMethods;
import Scripts.Core.ENUM.state;
import Scripts.Core.WalkerMethods;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class GoalWalkToLocation extends Goal{
    java.util.Stack<Goal> subgoals = new java.util.Stack();
    WalkerMethods walkerMethods =new WalkerMethods(ctx);
    public GoalWalkToLocation(ClientContext arg0) {
        super(arg0);
    }
    public void setPath(Tile[] path){
        walkerMethods.addPath(path);
        walkerMethods.setPath();
    }
    @Override
    public void activate() {
        removeAllSubGoals();
        walkerMethods.walkPath();
    }

    @Override
    public state process() {
        activateIfInactive();
        if (isStuck()){
            System.out.println("task failed");
            status = state.FAILED;
        }else if (goalReached()){
            System.out.println("goalCompleted reached");
            status = state.COMPLETED;
        }
        return status;
    }
    private boolean goalReached() {
        return walkerMethods.isNearDestination();
    }
    private boolean isStuck(){
        return false;
    }

    @Override
    public void terminate() {

    }
    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
            System.out.println("Walk to location");
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }
    public void removeAllSubGoals(){
        for(Goal goal: subgoals){
            goal.terminate();
            subgoals.remove(goal);
        }
    }
}
