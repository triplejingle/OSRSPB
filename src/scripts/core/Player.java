package scripts.core;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import scripts.core.collection.Animations;
import scripts.core.interfaces.Core;
import scripts.tools.ATimer;

public class Player extends Core{

    ATimer runATimer =new ATimer();
    Animations animations = new Animations();

    public Player(ClientContext arg0, String name) {
        super(arg0,name);
    }



    public void attack(){

    }

    public void walkHere() {

    }


    public void follow() {

    }


    public void trade() {

    }


    public void report() {

    }

    public boolean switchToTab(Game.Tab tab){
         return ctx.game.tab(tab);
    }

    public boolean hasItem(String[] item) {
        if(ctx.inventory.select().name(item).count()>0){
            return true;
        }
        //check if item is equipped
        return false;
    }

	public boolean isDoingNothing() {
		return ctx.players.local().animation() ==animations.getNothing();
	}

	public boolean drop(String ore) {
        ItemInventory itemInventory = new ItemInventory(ctx,ore);
        return itemInventory.dropItem();
    }

	public void run(int runWhenEnergyabove) {
		runATimer.setPeriod(10000);
		if(runATimer.isTime()) {
			if ( ctx.movement.energyLevel()>runWhenEnergyabove) {
				ctx.movement.running(true);
			}
		}
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

    public Tile getLocation() {
        return ctx.players.local().tile();
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
}
