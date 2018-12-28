package scripts.core;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.data.IObjectData;
import scripts.core.data.ObjectData;
import scripts.core.interfaces.EnvironmentDefault;
import scripts.core.selector.ObjectSelector;

public class Object extends EnvironmentDefault  {

    IObjectData objectData ;

    public Object(ClientContext arg0,String name) {
        super(arg0,name);
        this.objectData = new ObjectData(ctx);
    }
    public  Object(ClientContext ctx){
        super(ctx);
        this.objectData = new ObjectData(ctx);
    }

    @Override
    public boolean examine() {
        return objectData.interact("Examine");
    }

    @Override
    public boolean cancel() {
        return objectData.interact("Cancel");
    }

    public boolean interact(String action){
        if (objectData.inViewPort()) {
            return objectData.interact(action);
        } else {
            objectData.turnTo();
        }
        return false;
    }

    public boolean interactWithouthTurning(String action){
        return objectData.interact(action);
    }
}
