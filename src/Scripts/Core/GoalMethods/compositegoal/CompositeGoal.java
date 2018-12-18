package Scripts.Core.GoalMethods.compositegoal;

import Scripts.Core.ENUM.state;
import Scripts.Core.GoalMethods.IGoal;
import Scripts.Core.GoalMethods.atomicgoal.GoalAntiBanExamineRandomObject;
import Scripts.Core.GoalMethods.atomicgoal.GoalTurnToObject;
import Scripts.Core.Player;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

import java.util.Stack;

public abstract class CompositeGoal extends ClientAccessor implements IGoal {
    public Stack<IGoal> children = new Stack<>();
    public state status =state.INACTIVE;
    long startTime;
    long timeExpected;
	Player player = new Player(ctx,"its you but in code");

	public CompositeGoal(ClientContext arg0) {
        super(arg0);
    }

    public void activate(){processLowLevelGoal();
    }

	@Override
	public state process() {
		activateIfInactive();
		if (isStuck() || isBeingAttacked()) {
			System.out.println("task failed");
			status = state.FAILED;
		} else if (goalReached()) {
			System.out.println("goal reached");
			status = state.COMPLETED;
		}
		return status;
	}

    protected abstract void activateIfInactive();

    public abstract void terminate();

    public void addSubGoal(IGoal c){
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

    public state getState(){
        return status;
    }

    public void processLowLevelGoal(){
        if(children.size()>0) {
            IGoal goal = children.peek();
            goal.process();
            if ( goal.getState()== state.COMPLETED) {
                children.pop();
            }
            if (goal.getState() == state.FAILED) {
                terminate();
            }
        }
    }

    public void emptyStack(){
        if(children.size()==0){
            return;
        }
        int stackSize = children.size();
        for(int i = 0;i<stackSize;i++){
            children.pop();
        }
    }

    boolean hasChildFailed(){
        if(children.size()>0){
            for(int i = 0 ;i<children.size();i++){
                if(children.get(i).getState() == state.FAILED){
                    return true;
                }
                if(children.get(i).getState()==state.ACTIVE){
                    return false;
                }
            }
        }
        return false;
    }
    @Override
    public Stack<IGoal> getSubGoals() {
        return children;
    }

	public boolean isBeingAttacked() {
		return player.;
	}
}
