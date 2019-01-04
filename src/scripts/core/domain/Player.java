package scripts.core.domain;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import scripts.core.collection.Animations;
import scripts.core.interfaces.Core;

public class Player extends Core {

    private Animations animations = new Animations();

    public Player(ClientContext arg0, String name) {
        super(arg0, name);
    }


    public void attack() {
        //attacks a player
    }

    public void walkHere() {
        //walks on a player
    }


    public void follow() {
        //follows a player
    }


    public void trade() {
        //trades a player
    }


    public void report() {
        //reports a player. Auto reporter?:P
    }

    public boolean isDoingNothing() {
        return ctx.players.local().animation() == animations.getNothing();
    }

    public void run(int runWhenEnergyabove) {
        if (ctx.movement.energyLevel() > runWhenEnergyabove) {
            ctx.movement.running(true);
        }
    }

    public Tile getLocation() {
        return ctx.players.local().tile();
    }

    public boolean isPlayerMoving() {
        return ctx.players.local().inMotion();
    }

    public int getHealth() {
        return ctx.players.local().healthPercent();
    }
}
