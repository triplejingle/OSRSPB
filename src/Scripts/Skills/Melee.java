package Scripts.Skills;

import Scripts.Core.GroundItem;
import Scripts.Core.ItemInventoryInteractive;
import Scripts.Core.Mobs;
import Scripts.Tools.ATimer;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;

//acties in area uitvoeren
public class Melee extends CoreSkill{
    Random random = new Random();
    ATimer attackATimer = new ATimer();
    ATimer foodATimer = new ATimer();
    ATimer selectATimer = new ATimer();
    public void setEatAtHealth(int eatAtHealth) {
        this.eatAtHealth = random.nextInt(20,eatAtHealth);
    }

    private int eatAtHealth =20;
    public Melee(ClientContext arg0,int period) {
        super(arg0,period);
    }
    public Melee(ClientContext arg0) {
        super(arg0);
    }
    Mobs mobs;

    public void killInMultiArea(String mobName){
        setMobs(mobName);
        if(ctx.players.local().inCombat()&& mobs.inCombatWithMe()){
            return;
        }
        selectATimer.setPeriod(500);
        if(selectATimer.isTime()) {
            if(mobs.isMobDead()|| mobs.target==null) {
                mobs.select().within(7);
                System.out.println("finding mobs");
                mobs.getFirstTarget();
            }
        }

        if(mobs.target!=null) {
            attackATimer.setPeriod(random.nextInt(500,2000));
            attack();
        }else if(!mobs.inCombatWithMe()){
            mobs.target =null;
        }
    }

    public void killInSingleArea(String mobName){
        setMobs(mobName);
        selectATimer.setPeriod(1000);
        if(selectATimer.isTime()) {
             if(mobs.isMobDead()|| mobs.target==null||mobs.target.combatLevel()<1) {
                mobs.select().within(5).notInCombat();

                mobs.getFirstTarget();
            }
        }
        if(mobs.target!=null) {
            if ( mobs.target.combatLevel() > 0) {
                attackATimer.setPeriod(random.nextInt(500, 1500));
                attack();
            } else if ( mobs.target.combatLevel() < 1) {
                mobs.target = null;
                System.out.println("finding new mob");
            }
        }
    }

    private void attack(){
        if(attackATimer.isTime()) {
            mobs.attack();

        }
    }
    public void killAfk(String mobName){
        setMobs(mobName);
        if(ctx.players.local().inCombat()&& mobs.inCombatWithMe()){
            return;
        }

        selectATimer.setPeriod(500);
        if(selectATimer.isTime()) {
            if(mobs.isMobDead()|| mobs.target==null) {
                mobs.select().within(7).reachable().notInCombat();
                System.out.println("finding new mob");
                mobs.getFirstTarget();
            }
        }

        if(mobs.target!=null) {
            attackATimer.setPeriod(random.nextInt(30000,120000));
            attack();
        }else if(!mobs.inCombatWithMe()){
            mobs.target =null;
        }
    }

    public void eatWhenHealthIsBelow(int eatAtHealthInPercent, String food){
        int noFoodInInventory = 0;
        if(ctx.inventory.select().name(food).count()<=noFoodInInventory){
            return;
        }
        if (ctx.players.local().healthPercent() <= eatAtHealthInPercent) {
            if(ctx.game.tab()!= Game.Tab.INVENTORY ){
                ctx.game.tab(Game.Tab.INVENTORY);
            }else {
                foodATimer.setPeriod(random.nextInt(200,500));
                if(foodATimer.isTime()) {
                    ItemInventoryInteractive itemInventory = new ItemInventoryInteractive(ctx, food);
                    itemInventory.action("Eat");
                }
            }
        }
    }

    public void pickUpItems(String[] items){
        for(int i = 0 ;i< items.length;i++) {
            GroundItem item = new GroundItem(ctx,items[i]);
            item.take();
        }
    }

    public void setMobs(String mobname){
        if(mobs ==null) {
            mobs = new Mobs(ctx, mobname);
        }
    }
}
