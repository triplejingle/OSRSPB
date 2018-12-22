package scripts.core.goalmethods.compositegoal;

import org.powerbot.script.rt4.ClientContext;


public class GoalNoChildren extends CompositeGoal {

    public GoalNoChildren(ClientContext arg0) {
        super(arg0);
    }


    @Override
    protected void setup() {
        if(setup){
            setup=false;
            goal="no children"+ System.currentTimeMillis()/1000;
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
