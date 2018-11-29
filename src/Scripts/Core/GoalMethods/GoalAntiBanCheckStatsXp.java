package Scripts.Core.GoalMethods;

import Scripts.Core.ENUM.state;
import Scripts.Core.Player;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;

public class GoalAntiBanCheckStatsXp extends Goal{

    Player player = new Player(ctx,"its you but in code");
    public GoalAntiBanCheckStatsXp(ClientContext arg0) {
        super(arg0);
    }
    String skill;
    public void setSkill(String skill){
        this.skill = skill;
    }
    
    @Override
    public void activate() {
        player.checkStatsXP(skill);
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
        return System.currentTimeMillis()>timeExpected;
    }

    @Override
    public void terminate() {

    }
    Random random = new Random();
    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
            startTime = System.currentTimeMillis();
            timeExpected = random.nextInt(1000,10000)+startTime;
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    public boolean isStuck() {
        return false;
    }
}
