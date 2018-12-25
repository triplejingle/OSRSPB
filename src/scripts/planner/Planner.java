package scripts.planner;

import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import scripts.core.goalmethods.IGoal;
import scripts.core.goalmethods.atomicgoal.GoalAntiBanExamineRandomObject;
import scripts.core.goalmethods.atomicgoal.GoalAntiBanTurnToObject;
import scripts.core.goalmethods.compositegoal.GoalCheckGuide;

import java.util.Stack;

public abstract class Planner extends ClientAccessor {
    String goalLocation;
    IGoal IGoal;
    long xpGoal;
    int method ;
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

    protected IGoal getRandomAntiBan() {
        int antiban = random.nextInt(0,2);
        switch (antiban){
            case 0:
                return new GoalCheckGuide(ctx);
            case 1:
                return new GoalAntiBanExamineRandomObject(ctx);
            case 2:
                return  new GoalAntiBanTurnToObject(ctx);
            default:
                return new GoalAntiBanExamineRandomObject(ctx);
        }
    }

    protected void addRandomAntiBan(){
        if(addAntiBan()){
            IGoal.addSubGoal(getRandomAntiBan());
        }
    }

    private boolean addAntiBan() {
        return random.nextInt(0,100)<=50;
    }
}
