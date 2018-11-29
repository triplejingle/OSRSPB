package Scripts.Core.GoalMethods;

import Scripts.Core.ENUM.state;
import Scripts.Core.GoalMethods.Goal;
import Scripts.Core.Player;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Widget;

public class GoalAntiBanCheckGuide extends Goal{

    Player player = new Player(ctx,"its you but in code");
    public GoalAntiBanCheckGuide(ClientContext arg0) {
        super(arg0);
    }
    String skill;
    public void setSkill(String skill){
        this.skill = skill;
    }

    @Override
    public void activate() {
        player.checkStatsGuide(skill);
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
        return player.isGuideClosed();
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
