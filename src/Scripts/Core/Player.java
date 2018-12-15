package Scripts.Core;

import Scripts.Core.Collection.Animations;
import Scripts.Core.Interfaces.Core;
import Scripts.Tools.ATimer;
import Scripts.Tools.Factory.StatsTabFactory;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Game;

public class Player extends Core{
    StatsTabFactory statsTabFactory = new StatsTabFactory(ctx);
    ATimer runATimer =new ATimer();
    Game.Tab prevTab;
    Animations animations = new Animations();
    public Player(ClientContext arg0, String name) {
        super(arg0,name);
    }

    public void run(int runWhenEnergyabove) {
        runATimer.setPeriod(10000);
        if(runATimer.isTime()) {
            if ( ctx.movement.energyLevel()>runWhenEnergyabove) {
                ctx.movement.running(true);
            }
        }
    }

    public void walkHere() {

    }


    public void follow() {

    }


    public void trade() {

    }


    public void report() {

    }

    public void openRunePack(String runePack){
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
//                openGuide("Defence");
//            }
            moveMouseOutOfScreen();
        }else if(antiBanNr==2){
            moveMouseOutOfScreen();
        }
    }

    public void moveMouseOutOfScreen(){
        ctx.input.move(-1,random.nextInt(0,300));
    }

    public boolean checkStatsXP(String skill) {
        prevTab = ctx.game.tab();
        ctx.game.tab(Game.Tab.STATS);
        Component component =  statsTabFactory.getComponent(skill);
        return component.hover();
    }

    public boolean openGuide(String skill){
        Component component =  statsTabFactory.getComponent(skill);
        component.click();
        ctx.widgets.widget(214).component(8);
        ctx.input.move(random.nextInt(28,303),random.nextInt(84,310));
       return ctx.input.scroll();
    }
    public boolean switchToTab(Game.Tab tab){
        return ctx.game.tab(tab);
    }
    public boolean closeGuide(){
        Component close = statsTabFactory.getComponent("Close");
        if (close.visible()) {
            return close.click();
        }
        return false;
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
    public boolean hasItem(int item) {
        if(ctx.inventory.select().id(item).count()>0){
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

    public boolean drop(String ore) {
        ItemInventory itemInventory = new ItemInventory(ctx,ore);
        return itemInventory.dropItem();
    }

    public Tile getLocation() {
        return ctx.players.local().tile();
    }

    public boolean isGuideOpen() {
        Component component  = statsTabFactory.getComponent("Close");
        return component.visible();
    }

    public boolean isInventoryFull() {
        return ctx.inventory.select().count()>=28;
    }

    public boolean isPlayerMoving() {
        return ctx.players.local().inMotion();
    }
}
