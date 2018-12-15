package Scripts.Core;

import Scripts.Core.Interfaces.EnvironmentDefault;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class Object extends EnvironmentDefault  {

    GameObject gameObject;
    public Object(ClientContext arg0,String name) {
        super(arg0,name);
    }
    public  Object(ClientContext ctx){
        super(ctx);
    }
    public void chooseRandomObject(){
        GameObject gameObject = ctx.objects.select().within(20).limit(5).shuffle().poll();
        super.setName(gameObject.name());
    }
    public GameObject getGameObject() {
        return gameObject;
    }

    @Override
    public boolean examine() {
       gameObject = ctx.objects.select().name(super.getName()).poll();
       if(gameObject.inViewport()) {
           return gameObject.interact("Examine");
       }else{
           ctx.camera.turnTo(gameObject);
       }
       return false;
    }

    @Override
    public boolean cancel() {
        gameObject = ctx.objects.select().name(super.getName()).poll();
        return gameObject.interact("Cancel");
    }
}
