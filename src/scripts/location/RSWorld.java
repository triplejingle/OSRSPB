package scripts.location;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.World;
import org.powerbot.script.rt4.Worlds;
import scripts.tools.ATimer;

public class RSWorld extends ClientAccessor {
    Worlds worlds = ctx.worlds.joinable().select().types(World.Type.FREE);
    World freeToPlayWorlds;
    ATimer aTimer = new ATimer();

    public RSWorld(ClientContext arg0) {
        super(arg0);
    }

    public void switchToRandomFreeWorld() {
        worlds.open();
        if (worlds.peek() == null) {
            worlds = ctx.worlds.joinable().select().types(World.Type.FREE);
        }

        aTimer.setPeriod(1000);
        aTimer.saveTime();
        boolean hopped = worlds.poll().hop();
        while (!hopped) {
            if (worlds.peek() == null) {
                worlds = ctx.worlds.joinable().types(World.Type.FREE).select();
            }
            if (aTimer.isTime()) {
                break;
            }
            hopped = worlds.poll().hop();
        }
    }

}
