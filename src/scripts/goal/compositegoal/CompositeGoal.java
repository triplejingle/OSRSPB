package scripts.goal.compositegoal;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import scripts.core.domain.Player;
import scripts.core.enumcollection.state;
import scripts.goal.IGoal;

import java.util.Stack;

public abstract class CompositeGoal extends ClientAccessor implements IGoal {
    protected Stack<IGoal> children = new Stack<>();
    protected state status =state.INACTIVE;
    protected long startTime;
    protected boolean setup =true;
    protected String goal;
    protected Player player = new Player(ctx,"its you but in code");

    @Override
    public String getGoal() {
        return goal;
    }

	public CompositeGoal(ClientContext arg0) {
        super(arg0);
    }

    public void activate(){processLowLevelGoal();
    }

	@Override
	public state process() {
	    init();
		if (isStuck()) {
			System.out.println("task failed when executing "+goal);
			status = state.FAILED;
            emptyStack();
			return status;
		} else if (goalReached()) {
			System.out.println("goal reached "+ goal);
			status = state.COMPLETED;
			return status;
		}

        activateIfInactive();
		return status;
	}

    protected abstract void init();

    public void activateIfInactive(){
        if(status==state.INACTIVE){
            System.out.println(goal);
            status = state.ACTIVE;
        }
        if(status==state.ACTIVE){
            activate();
        }
    }

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
        if(!children.isEmpty()) {
            IGoal child = children.peek();
            child.process();
            if ( child.getState()== state.COMPLETED) {
                children.pop();
                System.out.println("popping " + child.getGoal());
            }
        }
    }

    public void emptyStack(){
        int stackSize = children.size();
        for(int i = 0;i<stackSize;i++){
            children.pop();
        }
    }

    boolean hasChildFailed(){
        if(!children.isEmpty()){
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
