package Scripts.Core.GoalMethods;

import Scripts.Core.ENUM.state;
import Scripts.Core.Object;
import Scripts.Core.Player;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class GoalAntiBanExamineRandomObject extends Goal{

    Player player = new Player(ctx,"its you but in code");
    Object object = new Object(ctx,"");
    public GoalAntiBanExamineRandomObject(ClientContext arg0) {
        super(arg0);
    }
    String skill;
    public void setSkill(String skill){
        this.skill = skill;
    }
    boolean goalCompleted = false;
    @Override
    public void activate() {
        GameObject gameObject = ctx.objects.select().within(20).poll();
        object = new Object(ctx,gameObject.name());
       if(object.examine()){
           goalCompleted = true;
       }
    }

    @Override
    public state process() {
        activateIfInactive();
        if (isStuck()) {
            System.out.println("task failed");
            status = state.FAILED;
        } else if (goalReached()) {
            System.out.println("goalCompleted reached");
            status = state.COMPLETED;
        }
        return status;
    }

    private boolean goalReached() {
        return goalCompleted;
    }

    @Override
    public void terminate() {

    }

    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
            startTime = System.currentTimeMillis();
            timeExpected = 30000+startTime;
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    public boolean isStuck() {
        return false;
    }
}
