package Scripts.Core.garbage;

import Scripts.Core.Collection.Animations;
import Scripts.Tools.ATimer;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class PlayerMethods extends ClientAccessor {
    LocationMethods locationMethods = new LocationMethods(ctx);
    Animations aIdCollection = new Animations();
    ATimer ATimer = new ATimer();
    public PlayerMethods(ClientContext ctx) {
        super(ctx);
    }

   public boolean isPlayerInCombat(){
        boolean inCombet= ctx.players.local().inCombat();
        return inCombet;
    }

    public void eat(int itemId){
        ctx.inventory.select().id(itemId).poll().interact("Eat");
    }

    public boolean healthLow(int warnAtPercentage){
        boolean isHealthLow =  (warnAtPercentage>=getLifePercentage());
        return isHealthLow;
    }

    public int getLifePercentage(){
        return ctx.combat.healthPercent();
    }
    public int getLifes(){
        return ctx.combat.health();
    }

    public boolean isPlayerNearbyLocation(Tile tile){
        return locationMethods.currentLocationIsNearbyTile(tile,5);
    }

    public boolean isPlayerStandingOnFlag(){
        return ctx.movement.destination().equals(Tile.NIL);
    }

    public void waitUntilInCombat(){
        int checkEverySecond = 3000;
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return isPlayerInCombat();
            }
        }, checkEverySecond, 20);
    }
    public void waitUntilOutOfCombat(){
        int checkEverySecond = 200;
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !isPlayerInCombat();
            }
        }, checkEverySecond, 5);
    }
    public boolean isPlayerMoving(){
        return ctx.players.local().inMotion();
    }


    public boolean isPlayer(int animation){
        int tmp1 =ctx.players.local().animation();
        boolean tmp =  tmp1== animation;

        return  tmp;
    }

    void waitUntilAnimation(int animation){
        ATimer.setPeriod(1000);
        ATimer.saveTime();
        while(!isPlayer(animation)){
            if(ATimer.isTime()&&isPlayer(aIdCollection.getNothing())&&!isPlayerMoving()){
                break;
            }else if(!isPlayer(aIdCollection.getNothing())){
                ATimer.saveTime();
            }
        }
    }

    public void waitUntilAnimationOver(int animation){
        ATimer.setPeriod(1000);
        ATimer.saveTime();
        while(isPlayer(animation)){
            if(ATimer.isTime()&&isPlayer(aIdCollection.getNothing())){
                break;
            }else if(!isPlayer(aIdCollection.getNothing())){
                ATimer.saveTime();
            }
        }
    }

    public boolean isPlayerDoingNothing() {
        return isPlayer(aIdCollection.getNothing());
    }
}
