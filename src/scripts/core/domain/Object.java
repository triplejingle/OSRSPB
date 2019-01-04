package scripts.core.domain;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.data.ObjectData;
import scripts.core.interfaces.EnvironmentDefault;

public class Object extends EnvironmentDefault  {

    private ObjectData objectData ;

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
        return objectData.interact(action);
    }
}
