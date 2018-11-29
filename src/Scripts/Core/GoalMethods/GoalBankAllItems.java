package Scripts.Core.GoalMethods;

import Scripts.Core.Bank;
import Scripts.Core.ENUM.state;
import org.powerbot.script.rt4.ClientContext;

public class GoalBankAllItems extends Goal{

    public GoalBankAllItems(ClientContext arg0) {
        super(arg0);
    }
    Bank bank =  new Bank(ctx);
    public  void activate() {
        startTime = System.currentTimeMillis();
        timeExpected = 8000+startTime;
        double MarginOfError = 2000;
        timeExpected += MarginOfError;
        bank.openBank();
        bank.depositAllItems();
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
        return ctx.inventory.select().count()==0;
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
        children.add(c);
    }

    public state processSubGoals() {
        for (Goal goal : children) {
            if (!children.empty() && (children.firstElement().isCompleted() || children.firstElement().hasFailed())) {
                children.firstElement().terminate();
                children.pop();
            } else {
                break;
            }
        }

        if (!children.empty()) {
            if (children.firstElement().isCompleted()) {
                return state.ACTIVE;
            }
            return children.firstElement().process();
        } else {
            return state.COMPLETED;
        }
    }

    public void removeAllSubGoals(){
        for(Goal goal: children){
            goal.terminate();
            children.remove(goal);
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
    int prevNOItems;
    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }
}
