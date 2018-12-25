package scripts.core.goalmethods.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.data.IObjectData;
import scripts.core.data.ObjectData;
import scripts.core.enumcollection.state;
import scripts.core.selector.ObjectSelector;

public class GoalAntiBanTurnToObject extends AntiBanGoal {
    IObjectData iObjectData=new ObjectData(ctx);
    ObjectSelector objectSelector = new ObjectSelector(ctx);
    public GoalAntiBanTurnToObject(ClientContext arg0) {
        super(arg0);
    }
    @Override
    protected void setup() {
        if(isEnabled==false){
            this.status= state.COMPLETED;
        }
        if(setup){
            setup=false;
            activateTimer.setPeriodBetween(300,1000);
            IObjectData.setObject(objectSelector.select().shuffle().poll());
            goal="goal turn to object"+ System.currentTimeMillis()/1000;
        }
    }

    @Override
    public void activate() {
        if(madeAttempt==false){
            iObjectData.turnTo();
            if(activateTimer.isTime()){
                madeAttempt=true;
            }
        }
    }

    public boolean goalReached() {
        if(madeAttempt) {
            return true;
        }
        return activateTimer.isTime();
    }

    @Override
    public void terminate() {

    }

    public boolean isStuck() {
        return false;
    }
}
