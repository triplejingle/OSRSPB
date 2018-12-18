package Scripts.Core.GoalMethods.compositegoal;

import Scripts.Core.ENUM.state;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;


public class GoalNoChildren extends CompositeGoal {

    public GoalNoChildren(ClientContext arg0) {
        super(arg0);
    }

    public void activateIfInactive() {
        if(status==state.INACTIVE){
            status = state.ACTIVE;
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    public boolean goalReached() {
        return (children.size()==0);
    }

    @Override
    public void terminate() {

    }

    public boolean isStuck() {
        return hasChildFailed();
    }


}
