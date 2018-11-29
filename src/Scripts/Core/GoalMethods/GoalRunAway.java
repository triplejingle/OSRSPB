package Scripts.Core.GoalMethods;

import Scripts.Core.ENUM.state;
import org.powerbot.script.rt4.ClientContext;

public class GoalRunAway extends Goal {
    public GoalRunAway(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public void activate() {
       //ren weg via de vaste vlucht
        //doe prayer aan als het kan
        //zet rennen aan
        //eet ondertussen als het kan
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
        return true;//afstand is groter dan 40 tiles gerekend vanaf het punt van wegrennen
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
        return false;//binnen 20 secondes is het eindpunt niet bereikt.
    }
}
