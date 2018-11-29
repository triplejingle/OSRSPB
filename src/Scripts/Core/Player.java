package Scripts.Core;

import Scripts.Core.Collection.Animations;
import Scripts.Core.Interfaces.Core;
import Scripts.Core.Interfaces.IPlayerDefault;
import Scripts.Tools.ATimer;
import Scripts.Tools.Factory.StatsTabFactory;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;
import org.powerbot.script.rt4.Component;

public class Player extends Core implements IPlayerDefault{
    Inventory inventory = ctx.inventory;
    StatsTabFactory statsTabFactory = new StatsTabFactory(ctx);
    ATimer runATimer =new ATimer();
    Game.Tab prevTab;
    Animations animations = new Animations();
    public Player(ClientContext arg0, String name) {
        super(arg0,name);
    }

    public void run(int runWhenEnergyabove) {
        int triedIndex =0;
        if( nrOfTries[triedIndex] ==maxTries){
            return;
        }
        runATimer.setPeriod(10000);
        if(runATimer.isTime()) {
            if ( ctx.movement.energyLevel()>runWhenEnergyabove) {
                if(ctx.movement.running(true)){
                    nrOfTries[triedIndex] ++;
                }
            }
        }
    }

    public void walkHere() {
        int triedIndex = 1;
        if( nrOfTries[triedIndex] >=maxTries){
            stopScript(triedIndex);
            return;
        }
    }


    public void follow() {
        int triedIndex =2;
        if( nrOfTries[triedIndex] >=maxTries){
            stopScript(triedIndex);
            return;
        }
    }


    public void trade() {
        int triedIndex =3;
        if( nrOfTries[triedIndex] >=maxTries){
            stopScript(triedIndex);
            return;
        }
    }


    public void report() {
        int triedIndex = 4;
        if( nrOfTries[triedIndex] >=maxTries){
            stopScript(triedIndex);
            return;
        }
    }

    public void openRunePack(String runePack){
        int triedIndex = 5;
        if( nrOfTries[triedIndex] >=maxTries){
            stopScript(triedIndex);
            return;
        }
        ItemInventoryInteractive itemInventoryInteractive = new ItemInventoryInteractive(ctx,runePack);
        itemInventoryInteractive.action("Open");//select random
    }
    Random random = new Random();

    public void doRandomAntiBan(){

        int antiBanNr = random.nextInt(0,2);
        if(antiBanNr==0){
           // checkStatsXP("Defence");
            moveMouseOutOfScreen();
        }else if(antiBanNr==1){
//            if(!ctx.players.local().inCombat()) {
//                checkStatsGuide("Defence");
//            }
            moveMouseOutOfScreen();
        }else if(antiBanNr==2){
            moveMouseOutOfScreen();
        }
    }

    public void moveMouseOutOfScreen(){
        ctx.input.move(-1,random.nextInt(0,300));
    }
    public void checkStatsXP(String skill) {
          int triedIndex = 6;
        if( nrOfTries[triedIndex] >=maxTries){
            stopScript(triedIndex);
            return;
        }
        prevTab = ctx.game.tab();
        ctx.game.tab(Game.Tab.STATS);
        Component component =  statsTabFactory.getComponent(skill);
        runATimer.setPeriod(5000);
        if(runATimer.isTime()) {
            component.hover();
        }
    }

    public void checkStatsGuide(String skill){
        int triedIndex = 7;
        if( nrOfTries[triedIndex] >=maxTries){
            stopScript(triedIndex);
            return;
        }
        int waitForMs = random.nextInt(5000,20000);
        ctx.game.tab(Game.Tab.STATS);
        Component component =  statsTabFactory.getComponent(skill);
        component.click();
        ctx.widgets.widget(214).component(8);
        runATimer.setPeriod(waitForMs);
        ctx.input.move(random.nextInt(28,303),random.nextInt(84,310));
        ctx.input.scroll();
        runATimer.setPeriod(waitForMs);
        if(runATimer.isTime()) {
            Component close = statsTabFactory.getComponent("Close");
            if (close.visible()) {
                close.click();
            }
        }
    }

    public boolean isDoingNothing() {
        return ctx.players.local().animation() ==animations.getNothing();
    }

    public boolean hasItem(String item) {
        if(ctx.inventory.select().name(item).count()>0){
            return true;
        }
        //check if item is equipped
        return false;
    }

    public boolean isInventoryEmpty() {
        return ctx.inventory.select().count()==0;
    }

    public int countItemsInventory() {
        return ctx.inventory.select().count();
    }

    public void drop(String ore) {
        ItemInventory itemInventory = new ItemInventory(ctx,ore);
        itemInventory.dropAllItems();
    }

    public Tile getLocation() {
        return ctx.players.local().tile();
    }

    public boolean isGuideClosed() {
        Component component  = statsTabFactory.getComponent("Guide");
        return component.visible();
    }
}
