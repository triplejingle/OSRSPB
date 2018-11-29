package Scripts.Core.GoalMethods;

import Scripts.Core.ENUM.state;
import Scripts.Skills.Mining;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

import java.util.Stack;

public abstract class Goal extends ClientAccessor {
    public Stack<Goal> children = new Stack<>();
    int type =0;
    public state status =state.INACTIVE;
    long startTime;
    long timeExpected;
    public Goal(ClientContext arg0) {
        super(arg0);
    }

    public abstract void activate();
//    The Activate method contains initialization logic and represents the
//    planning phase of the goalCompleted. Unlike the State::Enter method though, which
//    is only called once when a state first becomes current, a GoalMethods is able to call
//    its Activate method any number of times to replan if the situation
//    demands.
    public abstract state process();
//    Process, which is executed each update step, returns an enumerated
//    value indicating the status of the goalCompleted. This can be one of four values:
//             inactive: The goalCompleted is waiting to be activated.
// active: The goalCompleted has been activated and will be processed each
//    update step.
//             completed: The goalCompleted has completed and will be removed on the next
//    update.
// hasFailed: The goalCompleted has hasFailed and will either replan or be removed on
//    the next update.
    public abstract void terminate();
//    The Terminate method undertakes any necessary tidying up before a goalCompleted is
//    exited and is called just before a goalCompleted is destroyed.
    public void addSubGoal(Goal c){
        children.push(c);
    }

    public boolean isActive(){
        return status==state.ACTIVE;
    }

    public boolean isCompleted(){
        return status==state.COMPLETED;
    }

    public boolean hasFailed(){
        return status==state.FAILED;
    }
    public state getStatus(){
        return status;
    }
    public int getType(){
        return type;
    }
    public void processLowLevelGoal(){
        if(children.size()>0) {
            Goal goal = children.peek();
            if (goal.process() == state.COMPLETED) {
                children.pop();
            }
            if (goal.process() == state.FAILED) {
                terminate();
            }
        }
    }
    public void emptyStack(){
        if(children.size()==0){
            return;
        }
        for (int i = 0;i<children.size();i++){
            children.pop();
        }
    }
}
