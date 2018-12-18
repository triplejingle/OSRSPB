package Scripts.Core.GoalMethods.atomicgoal;

import Scripts.Core.ENUM.state;
import Scripts.Core.Player;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;

public class GoalSwitchTab extends AtomicGoal {
    Player player = new Player(ctx,"its you but in code");
    Game.Tab tab;

    public GoalSwitchTab(ClientContext arg0, Game.Tab tab) {
        super(arg0);
        this.tab = tab;
    }

    @Override
    public void activate() {
        if(madeAttempt==false) {
            if(player.switchToTab(tab)){
                madeAttempt=true;
            }
        }
    }

    public boolean goalReached() {
        if(madeAttempt) {
            return ctx.game.tab()==tab;
        }
        return false;
    }

    @Override
    public void terminate() {

    }

    public void activateIfInactive(){
        if(madeAttempt){
            return;
        }
        if(status==state.INACTIVE){
            status = state.ACTIVE;
            activateTimer.setPeriodBetween(4000,7000);
	        System.out.println("switching tabs");
        }
        if (status == state.ACTIVE ) {
            activate();
        }
    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }
}
