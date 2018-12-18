package Scripts.Core;

import Scripts.Core.Collection.Animations;
import Scripts.Core.Interfaces.Core;
import Scripts.Tools.ATimer;
import Scripts.Tools.Factory.StatsTabFactory;
import org.powerbot.script.Locatable;
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
    int closeComponent=999;
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

    public void moveMouseOutOfScreen(){
        ctx.input.move(-1,random.nextInt(0,300));
    }

    public boolean checkStatsXP(int skill) {
        prevTab = ctx.game.tab();
        ctx.game.tab(Game.Tab.STATS);
        Component component =  statsTabFactory.getComponent(skill);
        return component.hover();
    }

    public boolean openGuide(int skill){
        Component component =  statsTabFactory.getComponent(skill);
        return component.click();
    }
	public boolean moveMouseInGuideScreen(){
    	int x = random.nextInt(28, 303);
			    int y = random.nextInt(84, 310);
		ctx.widgets.widget(214).component(8);
		ctx.input.move(x,y );
		return x<=ctx.input.getLocation().x;
	}
    public void scrollThroughGuide() {
	    ctx.input.scroll();
    }
    public boolean switchToTab(Game.Tab tab){
         ctx.game.tab(tab);
         return ctx.input.click(true);
    }
    public boolean closeGuide(){
        Component close = statsTabFactory.getComponent(closeComponent);
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
        Component component  = statsTabFactory.getComponent(closeComponent);
        return component.visible();
    }

    public boolean isInventoryFull() {
        return ctx.inventory.select().count()>=28;
    }

    public boolean isPlayerMoving() {
        return ctx.players.local().inMotion();
    }

	public int getHealth() {
		return ctx.players.local().healthPercent();
	}

    public void showYourself() {
    	ctx.camera.turnTo(ctx.players.local());
    }
}
