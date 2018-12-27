package scripts.core.goalmethods.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import scripts.core.Object;
import scripts.core.data.ObjectData;
import scripts.core.enumcollection.state;
import scripts.core.selector.ObjectSelector;

import java.util.ArrayList;
import java.util.Collections;

public class GoalAntiBanExamineRandomObject extends AntiBanGoal {
    Object object = new Object(ctx);
    ObjectData objectData = new ObjectData(ctx);
    ObjectSelector objectSelector = new ObjectSelector(ctx);
    public GoalAntiBanExamineRandomObject(ClientContext arg0) {
        super(arg0);
    }
    @Override
    protected void setup() {
        if(isEnabled==false){
            this.status= state.COMPLETED;
        }
        if(setup){
            setup=false;
            activateTimer.setPeriodBetween(5000,10000);
            goal="examine random object"+ System.currentTimeMillis()/1000;
            GameObject gameObject =objectSelector.select().within(5).shuffle().poll();
            ArrayList<String> arrayList =new ArrayList();
            Collections.addAll(arrayList,gameObject.actions());

            if(!arrayList.contains("Examine")){
                madeAttempt=true;
                return;
            }
            objectData.setObject(gameObject);
        }
    }
    @Override
    public void activate() {
        if(madeAttempt==false) {
            if (object.examine()) {
                madeAttempt = true;
            }
        }
    }

    public boolean goalReached() {
        return madeAttempt;
    }

    @Override
    public void terminate() {

    }

    public boolean isStuck() {
        return activateTimer.isTime();
    }
}
