package scripts.goal.compositegoal;

import org.powerbot.script.rt4.ClientContext;

public class GoalTime extends CompositeGoal {
    private long runTime;

    public GoalTime(ClientContext arg0, long runTime) {
        super(arg0);
        startTime=System.currentTimeMillis();
        this.runTime = runTime*60000;
    }
    @Override
    protected void init() {
        if(setup){
            setup=false;
            startTime =System.currentTimeMillis();
            goal="Time "+ System.currentTimeMillis()/1000;
        }
    }

    public boolean goalReached() {
        long currentTime = System.currentTimeMillis();
        return startTime + runTime <currentTime;
    }

    public boolean isStuck() {
        return hasChildFailed();
    }
}
