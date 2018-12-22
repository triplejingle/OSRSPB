package scripts.core.goalmethods.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.Player;

public class GoalIdle extends AtomicGoal {
    int waitForSeconds;
    Player player = new Player(ctx,"its you but in code");
    public GoalIdle(ClientContext arg0, int seconds) {
        super(arg0);
        this.waitForSeconds = seconds*1000;
    }
    @Override
    protected void setup() {
        if(setup){
            setup=false;
            activateTimer.setPeriod(waitForSeconds);
            System.out.println("waiting...");
            goal="idle"+ System.currentTimeMillis()/1000;
        }
    }
    @Override
    public void activate() {

    }

    public boolean goalReached() {
        return activateTimer.isTime();
    }

    @Override
    public void terminate() {

    }

    public boolean isStuck(){
        return player.getHealth()<100;
    }
}
