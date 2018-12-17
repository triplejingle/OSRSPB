package Scripts.Planners;

import Scripts.Core.GoalMethods.IGoal;
import Scripts.Core.WalkerMethods;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;


import java.util.Stack;

public abstract class Planner extends ClientAccessor{
    String goalLocation;
    IGoal IGoal;
    long xpGoal;
    int method ;
    WalkerMethods walkerMethods = new WalkerMethods(ctx);
    Random random = new Random();

    public Planner(ClientContext arg0) {
        super(arg0);
    }

    public String getGoalLocation(){
        return goalLocation;
    }

    public void setGoalLocation(String goalLocation){
        this.goalLocation = goalLocation;
    }
    public void replan(){
        chooseMethod();
    }

    public IGoal plan(){
        method=0;//random.nextInt(0,1);
        chooseMethod();
        return IGoal;
    }
    abstract void chooseMethod();
        public IGoal getHighLevelGoal(){
            return IGoal;
        }

    public Stack<IGoal> getSubGoals() {
        return IGoal.getSubGoals();
    }

    public long getXpGoal() {
        return xpGoal;
    }

    public void setXpGoal(long xpGoal) {
        this.xpGoal = xpGoal;
    }

    public void walkTo(Tile[] path){

    }
}
