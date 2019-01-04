package scripts.goal.compositegoal;

import org.powerbot.script.rt4.ClientContext;


public class GoalNoChildren extends CompositeGoal {

    public GoalNoChildren(ClientContext arg0) {
        super(arg0);
    }

    @Override
    protected void init() {
        if (setup) {
            setup = false;
            goal = "no children" + System.currentTimeMillis() / 1000;
        }
    }

    public boolean goalReached() {
        return (children.isEmpty());
    }

    public boolean isStuck() {
        return hasChildFailed();
    }
}
