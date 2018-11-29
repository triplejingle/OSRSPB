package Scripts.Core.GoalMethods;

import Scripts.Core.ENUM.state;
import Scripts.Skills.Mining;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;

public class GoalMine1 extends Goal {
    Mining mining = new Mining(ctx);
    boolean attempt = true;
    static int nrOfItems=28;
    public GoalMine1(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public void activate() {
        if(attempt) {
            prevNOItems=ctx.inventory.select().count();
            if(mining.tmo("Copper")){
                attempt = false;
                startTime = System.currentTimeMillis();
                timeExpected = 30000+startTime;
                double MarginOfError = 5000;
                timeExpected += MarginOfError;
                System.out.println("clicked ore");
            }
        }
        if(prevNOItems+1==ctx.inventory.select().count()){
            System.out.println("ore obtained");
            nrOfItems--;
            attempt =true;
        }
        if(mining.rockDissappeared()){
            attempt =true;
        }
    }

    @Override
    public state process() {
        activateIfInactive();
        if (isStuck()){
            System.out.println("task failed");
            status = state.FAILED;
        }else if (goalReached()){
            System.out.println("goalCompleted reached");
            status = state.COMPLETED;
        }
        return status;
    }

    private boolean goalReached() {
        return prevNOItems+1==ctx.inventory.select().count();
    }

    @Override
    public void terminate() {
        for (Goal goal: children){
            children.pop();
        }
    }



    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
            prevNOItems=ctx.inventory.select().count();
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }
    int prevNOItems;
    public boolean isStuck() {
        return false;
    }

}
