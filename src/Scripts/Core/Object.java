package Scripts.Core;

import Scripts.Core.Interfaces.EnvironmentDefault;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class Object extends EnvironmentDefault  {

    public GameObject getGameObject() {
        return gameObject;
    }

    GameObject gameObject;
    public Object(ClientContext arg0,String name) {
        super(arg0,name);
    }
    boolean goalReached = false;
    @Override
    public boolean examine() {
       gameObject = ctx.objects.select().name(super.getName()).poll();
       return gameObject.interact("Examine");
    }

    @Override
    public boolean cancel() {
        gameObject = ctx.objects.select().name(super.getName()).poll();
        return gameObject.interact("Examine");
    }
}
