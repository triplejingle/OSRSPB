package Scripts.Core;

import Scripts.Core.Collection.Animations;
import Scripts.Tools.ATimer;
import Scripts.Tools.Factory.ExchangeFactory;
import Scripts.Tools.Factory.ShopFactory;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Npc;

public class NPCInteractive extends NPC{
    ExchangeFactory exchange = new ExchangeFactory(ctx);
    ShopFactory shop = new ShopFactory(ctx);
    Random random = new Random();
    Animations animations = new Animations();
    ATimer ATimer = new ATimer();

    public NPCInteractive(ClientContext arg0, String name) {
        super(arg0, name);
    }

    public boolean fish(String action){
        ATimer.setPeriodBetween(1000,5000);
        if(ATimer.isTime()) {
            if (ctx.players.local().animation() == animations.getNothing()) {
                Npc npc = ctx.npcs.select().name(super.getName()).within(20).shuffle().poll();
                if (npc.inViewport()) {
                    return npc.interact(action, super.getName());
                } else {
                    ctx.camera.turnTo(npc);
                }
            }
        }
        return false;
    }

    public void trade(){
        Component screen = shop.getComponent("screen");
        Npc npc = ctx.npcs.select().name(super.getName()).poll();
        npc.interact("Trade", super.getName());
        ATimer.setPeriod(random.nextInt(1000,2000));
        ATimer.saveTime();
        if(ATimer.isTime()){
            ATimer.saveTime();
           npc.interact("Trade", super.getName());
        }
    }

    public void exchange(){
        Component exchangeScreen = exchange.getComponent("screen");
        if(!exchangeScreen.inViewport()) {
            Npc npc = ctx.npcs.select().name(super.getName()).poll();
            npc.interact("Exchange", super.getName());
            ATimer ATimer = new ATimer();
            ATimer.setPeriod(random.nextInt(1000,2000));
            ATimer.saveTime();
            while(!exchangeScreen.inViewport()){
                if(ATimer.isTime()){
                    break;
                }
                npc.interact("Exchange", super.getName());
            }
        }
    }
    int prevItemStackSize ;
    public boolean buyRunes(Component item, int amount){
        if (item.itemStackSize() > 0) {
            prevItemStackSize = item.itemStackSize();
            ATimer.setPeriod(random.nextInt(3000,5000));
            if(ATimer.isTime()) {
                if (item.inViewport()) {
                    return item.interact("Buy " + String.valueOf(amount));
                }
            }
        }
        return false;
    }
    public boolean closeRuneShop(){
        Component screen = shop.getComponent("screen");
        if(!screen.inViewport()) {
            return true;
        }
        ATimer.setPeriod(random.nextInt(1000,2000));
        ATimer.saveTime();
        if (ATimer.isTime()) {
            ATimer.saveTime();
           return shop.getComponent("close").click();
        }
        return false;
    }


    public boolean inViewport() {
        return ctx.npcs.select().name(super.getName()).nearest().poll().inViewport();
    }
}
