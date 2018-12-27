package scripts.planner;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import scripts.core.Player;
import scripts.core.goalmethods.IGoal;
import scripts.core.goalmethods.atomicgoal.*;
import scripts.core.goalmethods.compositegoal.*;

public class PlannerFishing extends Planner {
    public static final Tile[] BankToBarbarianvillageFish = {new Tile(3093, 3494, 0), new Tile(3098, 3483, 0), new Tile(3100, 3478, 0), new Tile(3099, 3473, 0), new Tile(3099, 3468, 0), new Tile(3095, 3465, 0), new Tile(3090, 3465, 0), new Tile(3088, 3460, 0), new Tile(3089, 3455, 0), new Tile(3089, 3450, 0), new Tile(3091, 3445, 0), new Tile(3093, 3440, 0), new Tile(3097, 3437, 0), new Tile(3101, 3433, 0)};
    public final Tile[] barbarianVillageFishToEdgeBank = ctx.movement.newTilePath(BankToBarbarianvillageFish).reverse().toArray();
    Tile[] fishDraynor = {new Tile(3090, 3232,0)};
    Tile[] draynorBank = {new Tile(3094, 3243,0)};
    String[] userSettings;
    public PlannerFishing(ClientContext arg0, String[] userSettings){
        super(arg0);
        IGoal = getGoal(new String[]{"xp","230"});
        this.userSettings = userSettings;
    }

    private IGoal getGoal(String[] goalInfo){
        switch (goalInfo[0]){
            case "xp":
                return new GoalTime(ctx, Integer.valueOf(goalInfo[1]));
        }
        return null;//run until stopped
    }

    public void chooseMethod() {
        Random random = new Random();
        method =0; //random.nextInt(0,1);
        switch(method){
            case 0:
                fishAndBank(userSettings[1],getCorrespondingPathToBank(userSettings[0]),getCorrespondingPathToFishingSpot(userSettings[0]));
            break;
        }
    }

    Tile[] getCorrespondingPathToBank(String location){
        switch (location){
            case "Barbarian village":
                return barbarianVillageFishToEdgeBank;
            case "Draynor village":
                return draynorBank;
        }
        return null;
    }

    Tile[] getCorrespondingPathToFishingSpot(String location){
        switch (location){
            case "Barbarian village":
                return BankToBarbarianvillageFish;
            case "Draynor village":
                return fishDraynor;
        }
        return null;
    }

    Player player = new Player(ctx,"its you but in code");
    private void fishAndBank(String tool,Tile[] pathToBank, Tile[] pathToFishingSpot){
        if(player.isInventoryFull()) {
            IGoal.addSubGoal(new GoalBankAllItemsExcept(ctx,getCorrespondingTool(tool),pathToBank));
        }else {
            IGoal.addSubGoal(new GoalIdle(ctx,random.nextInt(3,8)));
            IGoal.addSubGoal(new GoalFish(ctx, getCorrespondingFishingSpot(tool),pathToFishingSpot));
        }
       if(!player.hasItem(getCorrespondingTool(tool))) {
           IGoal.addSubGoal(new GoalGetEquipment(ctx,getCorrespondingTool(tool),pathToBank));
       }

    }

    String[] getCorrespondingTool(String tool){
        switch (tool){
            case "Small fishing net":
                return new String[]{"Small fishing net"};
            case "Fly fishing rod":
                return new String[]{"Fly fishing rod","Feather"};
            default:
                return new String[]{""};
        }
    }

    String[] getCorrespondingFishingSpot(String tool){
        switch (tool){
            case "Small fishing net":
                return new String[] {"Small Net","Fishing spot"};
            case "Fly fishing rod":
                return new String[]{"Lure", "Rod Fishing spot"};
                default:
                    return new String[]{""};
        }
    }
}
