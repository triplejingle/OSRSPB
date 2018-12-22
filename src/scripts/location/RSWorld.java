package scripts.location;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.World;
import org.powerbot.script.rt4.Worlds;
import scripts.tools.ATimer;

public class RSWorld extends ClientAccessor {
    Worlds  worlds = ctx.worlds.joinable().select().types(World.Type.FREE);
    World freeToPlayWorlds ;
    public RSWorld(ClientContext arg0) {
        super(arg0);
    }

    public void switchToRandomFreeWorld(){
        worlds.open();
        if(worlds.peek()==null){
            worlds = ctx.worlds.joinable().select().types(World.Type.FREE);
        }
        ATimer ATimer = new ATimer();
        ATimer.setPeriod(1000);
        ATimer.saveTime();
       boolean hopped = worlds.poll().hop();
        while (!hopped){
            if(worlds.peek()==null){
                worlds = ctx.worlds.joinable().types(World.Type.FREE).select();
            }
            if(ATimer.isTime()){
                break;
            }
            hopped = worlds.poll().hop();
        }
    }

}
