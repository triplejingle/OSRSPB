package Scripts.Core.GoalMethods;

import Scripts.Core.ENUM.state;
import Scripts.Core.Player;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;

public class GoalDropOre extends Goal {
    Player player =  new Player(ctx,"its you but in code");
    public GoalDropOre(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public void activate() {
      player.drop("Copper ore");
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
        return !player.hasItem("Copper ore");
    }

    @Override
    public void terminate() {

    }


    public void activateIfInactive(){
        if(status==state.INACTIVE){
            status = state.ACTIVE;
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    public boolean isStuck() {
        return false;
    }
}
