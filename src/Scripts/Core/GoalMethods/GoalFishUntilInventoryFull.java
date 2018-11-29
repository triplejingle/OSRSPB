package Scripts.Core.GoalMethods;

import Scripts.Core.Collection.Animations;
import Scripts.Core.ENUM.state;
import Scripts.Core.Player;
import Scripts.Skills.Fishing;
import org.powerbot.script.rt4.ClientContext;

public class GoalFishUntilInventoryFull extends Goal{
    java.util.Stack<Goal> subgoals = new java.util.Stack();

    Fishing fishing=new Fishing(ctx);
    public GoalFishUntilInventoryFull(ClientContext arg0) {
        super(arg0);
    }

    public  void activate() {
        status = state.ACTIVE;
        fishing.fishUsingSmallFishingNet();
    }

    public state process(){
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
        return ctx.inventory.select().count()>= 28;
    }

    public  void terminate(){

    }

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
    Animations animations = new Animations();
    public boolean isStuck() {
        if(System.currentTimeMillis()>timeExpected){
            if(ctx.players.local().animation()!=animations.getNothing()){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }
    int prevNOItems;
    public void activateIfInactive(){
        if(status==state.INACTIVE){
            System.out.println("fishing until inventory full");
            status = state.ACTIVE;
            startTime = System.currentTimeMillis();
            timeExpected = 500000+startTime;
            double MarginOfError = 5000;
            timeExpected += MarginOfError;
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }
}
