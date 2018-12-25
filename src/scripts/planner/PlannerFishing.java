package scripts.planner;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import scripts.core.Player;
import scripts.core.goalmethods.atomicgoal.*;
import scripts.core.goalmethods.compositegoal.GoalTime;
import scripts.core.goalmethods.compositegoal.GoalWaitUntilInventoryFull;
import scripts.core.goalmethods.compositegoal.GoalWalkToLocation;

public class PlannerFishing extends Planner {
    public Tile[] BankToBarbarianvillageFish = {new Tile(3094, 3491, 0), new Tile(3090, 3488, 0), new Tile(3095, 3486, 0), new Tile(3098, 3482, 0), new Tile(3098, 3477, 0), new Tile(3098, 3472, 0), new Tile(3098, 3467, 0), new Tile(3093, 3465, 0), new Tile(3088, 3464, 0), new Tile(3088, 3459, 0), new Tile(3089, 3454, 0), new Tile(3090, 3449, 0), new Tile(3093, 3444, 0), new Tile(3097, 3441, 0), new Tile(3101, 3438, 0), new Tile(3103, 3433, 0)};
    public final Tile[] barbarianVillageFishToEdgeBank = ctx.movement.newTilePath(BankToBarbarianvillageFish).reverse().toArray();
    Tile[] fishDraynor = {new Tile(3090, 3232,0)};
    Tile[] draynorBank = {new Tile(3094, 3243,0)};
    String[] userSettings;
    public PlannerFishing(ClientContext arg0, String[] userSettings){
        super(arg0);
        int minutes = 120;
        IGoal = new GoalTime(ctx,minutes);
        this.userSettings = userSettings;
    }

    public void chooseMethod() {
        Random random = new Random();
        method =0; //random.nextInt(0,1);
        switch(method){
            case 0:
                System.out.println("fish and bank");
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
            IGoal.addSubGoal(new GoalCloseBank(ctx));
            IGoal.addSubGoal(new GoalBankAllItemsExcept(ctx,getCorrespondingTool(tool)));
            IGoal.addSubGoal(new GoalOpenBank(ctx));
            IGoal.addSubGoal(new GoalWalkToLocation(ctx,pathToBank));
        }else {
            IGoal.addSubGoal(new GoalWaitUntilInventoryFull(ctx));
            IGoal.addSubGoal(new GoalFish(ctx, getCorrespondingFishingSpot(tool)));
            IGoal.addSubGoal(new GoalSwitchTab(ctx, Game.Tab.INVENTORY));
            IGoal.addSubGoal(new GoalWalkToLocation(ctx, pathToFishingSpot));
        }
       if(!player.hasItem(getCorrespondingTool(tool))) {
           IGoal.addSubGoal(new GoalCloseBank(ctx));
           String[] tools = getCorrespondingTool(tool);
           for(String missingTool:tools) {
               if(!player.hasItem(new String[]{missingTool})) {
                   IGoal.addSubGoal(new GoalWithdrawEquipment(ctx, missingTool));
               }
           }
           IGoal.addSubGoal(new GoalOpenBank(ctx));
           IGoal.addSubGoal(new GoalWalkToLocation(ctx,pathToBank));
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
    String getCorrespondingFishingSpot(String tool){
        switch (tool){
            case "Small fishing net":
                return "Small Net";
            case "Fly fishing rod":
                return "Lure";
                default:
                    return "";
        }
    }
}
