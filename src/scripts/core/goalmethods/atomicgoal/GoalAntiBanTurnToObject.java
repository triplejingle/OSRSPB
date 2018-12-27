package scripts.core.goalmethods.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
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
        if(setup){
            setup=false;
            activateTimer.setPeriodBetween(300,1000);
            GameObject gameObject =objectSelector.select().shuffle().poll();
            if(gameObject.name()==null){
                this.status=state.COMPLETED;
                return;
            }
            IObjectData.setObject(gameObject);
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
