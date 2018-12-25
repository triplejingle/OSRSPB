package scripts.core.goalmethods.compositegoal;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import scripts.core.Player;
import scripts.core.enumcollection.state;
import scripts.core.goalmethods.IGoal;

import java.util.Stack;

public abstract class CompositeGoal extends ClientAccessor implements IGoal {
    public Stack<IGoal> children = new Stack<>();
    public state status =state.INACTIVE;
    long startTime;
    long timeExpected;
    boolean setup =true;
    @Override
    public String getGoal() {
        return goal;
    }

    String goal;
	Player player = new Player(ctx,"its you but in code");

	public CompositeGoal(ClientContext arg0) {
        super(arg0);
    }

    public void activate(){processLowLevelGoal();
    }

	@Override
	public state process() {
	    setup();
		if (isStuck()) {
			System.out.println("task failed when executing "+goal);
			status = state.FAILED;
			return status;
		} else if (goalReached()) {
			System.out.println("goal reached "+ goal);
			status = state.COMPLETED;
			terminate();
			return status;
		}
        activateIfInactive();
		return status;
	}

    protected abstract void setup();

    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
        }
        if(status==state.ACTIVE){
            activate();
        }
    }

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
                System.out.println("popping " + goal.getGoal());
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
		return player.getHealth()<100;
	}
}
