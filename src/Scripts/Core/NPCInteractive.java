package Scripts.Core;

import Scripts.Core.Collection.Animations;
import Scripts.Tools.Factory.ExchangeFactory;
import Scripts.Tools.Factory.ShopFactory;
import Scripts.Tools.ATimer;
import org.powerbot.script.Condition;
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
    public void fish(String action){

            ATimer.setPeriodBetween(3000,15000);
            if(ATimer.isTime()) {
                if(ctx.players.local().animation()==animations.getNothing()) {
                Npc npc = ctx.npcs.select().name(super.getName()).nearest().poll();
                if(npc.inViewport()) {
                    npc.interact(action, super.getName());
                }else{
                    ctx.camera.turnTo(npc);
                }
            }
        }
    }
    public void trade(){
        int triedIndex = 4;
        if( nrOfTries[triedIndex] >=maxTries){
            stopScript(triedIndex);
            return;
        }
        Component screen = shop.getComponent("screen");
        Npc npc = ctx.npcs.select().name(super.getName()).poll();
        npc.interact("Trade", super.getName());
        ATimer.setPeriod(random.nextInt(1000,2000));
        ATimer.saveTime();
        if(ATimer.isTime()){
            ATimer.saveTime();
           if(npc.interact("Trade", super.getName())){
               nrOfTries[triedIndex]++;
           }
        }
        if(screen.visible()){
            nrOfTries[triedIndex] = 0;
        }
    }

    public void exchange(){
        int triedIndex = 5;
        stopScript(triedIndex);
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
                if(npc.interact("Exchange", super.getName())){
                    nrOfTries[triedIndex] =0;
                }else{
                    nrOfTries[triedIndex]++;
                }
            }
        }
    }
    int prevItemStackSize ;
    public void buyRunes(Component item, int amount){
        int triedIndex = 6;
        if(item.itemStackSize()<prevItemStackSize) {
            nrOfTries[triedIndex]=0;
        }
        stopScript(triedIndex);
        if (item.itemStackSize() > 0) {
            prevItemStackSize = item.itemStackSize();
            ATimer.setPeriod(random.nextInt(3000,5000));
            if(ATimer.isTime()) {
                if (item.inViewport()) {
                    if (item.interact("Buy " + String.valueOf(amount))) {
                        nrOfTries[triedIndex]++;
                    }
                }
            }
        }
    }
    public void closeRuneShop(){
        int triedIndex = 4;
        Component screen = shop.getComponent("screen");
        if(!screen.inViewport()) {
            nrOfTries[triedIndex]=0;
        }
        stopScript(triedIndex);
        ATimer.setPeriod(random.nextInt(1000,2000));
        ATimer.saveTime();
            if (ATimer.isTime()) {
                ATimer.saveTime();
                if(shop.getComponent("close").click()){
                    nrOfTries[triedIndex]++;
                }
            }
        }


    public boolean inViewport() {
        return ctx.npcs.select().name(super.getName()).nearest().poll().inViewport();
    }
}
