package Scripts.Core.GoalMethods;

import Scripts.Core.Collection.Animations;
import Scripts.Core.ENUM.state;
import Scripts.Core.GoalMethods.Goal;
import org.powerbot.script.rt4.ClientContext;

public class fillInventoryGoal extends Goal {
    java.util.Stack<Goal> subgoals = new java.util.Stack();
    Animations animations = new Animations();

    public fillInventoryGoal(ClientContext arg0) {
        super(arg0);
    }

    public void activate() {
//        status = state.ACTIVE;
//        processSubGoals();
//        startTime = System.currentTimeMillis();
//        timeExpected = 840000+startTime;
//        double MarginOfError = 10000;
//        timeExpected += MarginOfError;
//        int numberOfFreeSpace = 28-ctx.inventory.select().count();
//        for(int i = 0;i< numberOfFreeSpace;i++) {
//            addSubGoal(new GoalMineUntilInventoryFull(ctx));
//        }
    }

    public state process(){
        activateIfInactive();
        status = processSubGoals();
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
        return ctx.inventory.select().count()>=28;
    }

    //    Process, which is executed each update step, returns an enumerated
//    value indicating the status of the goalCompleted. This can be one of four values:
//             inactive: The goalCompleted is waiting to be activated.
// active: The goalCompleted has been activated and will be processed each
//    update step.
//             completed: The goalCompleted has completed and will be removed on the next
//    update.
// failed: The goalCompleted has failed and will either replan or be removed on
//    the next update.
    public  void terminate(){

    }
//    The Terminate method undertakes any necessary tidying up before a goalCompleted is
//    exited and is called just before a goalCompleted is destroyed.

    @Override
    public void addSubGoal(Goal c) {
        subgoals.add(c);
    }

    public state processSubGoals() {
        for (Goal goal : subgoals) {
            if (!subgoals.empty() && (subgoals.firstElement().isCompleted() || subgoals.firstElement().hasFailed())) {
                subgoals.firstElement().terminate();
                subgoals.pop();
            } else {
                break;
            }
        }

        if (!subgoals.empty()) {
            if (subgoals.firstElement().isCompleted()) {
                return state.ACTIVE;
            }
            return subgoals.firstElement().process();
        } else {
            return state.COMPLETED;
        }
    }

    public void removeAllSubGoals(){
        for(Goal goal: subgoals){
            goal.terminate();
            subgoals.remove(goal);
        }
    }

    public boolean isStuck() {
        if(System.currentTimeMillis()>timeExpected){
            if(ctx.players.local().inMotion()){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }

    public void activateIfInactive(){
        if(status==state.INACTIVE){

            activate();
        }
    }
}
