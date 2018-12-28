package scripts.planner;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import scripts.core.Player;
import scripts.core.data.ObjectData;
import scripts.core.data.PlayerData;
import scripts.core.goalmethods.IGoal;
import scripts.core.goalmethods.atomicgoal.*;
import scripts.core.goalmethods.compositegoal.*;
import scripts.core.selector.ObjectSelector;
import scripts.core.selector.PlayerSelector;

public class PlannerFishing extends Planner {
    private final Tile[] BankToBarbarianvillageFish = {new Tile(3093, 3494, 0), new Tile(3098, 3483, 0), new Tile(3100, 3478, 0), new Tile(3099, 3473, 0), new Tile(3099, 3468, 0), new Tile(3095, 3465, 0), new Tile(3090, 3465, 0), new Tile(3088, 3460, 0), new Tile(3089, 3455, 0), new Tile(3089, 3450, 0), new Tile(3091, 3445, 0), new Tile(3093, 3440, 0), new Tile(3097, 3437, 0), new Tile(3101, 3433, 0)};
    private final Tile[] barbarianVillageFishToEdgeBank = ctx.movement.newTilePath(BankToBarbarianvillageFish).reverse().toArray();
    private final Tile[] fishDraynor = {new Tile(3090, 3232,0)};
    private final Tile[] draynorBank = {new Tile(3094, 3243,0)};
    private final Tile[] depositToSeaman = {new Tile(3045, 3236, 0),new Tile(3041, 3236, 0), new Tile(3036, 3236, 0), new Tile(3031, 3236, 0), new Tile(3028, 3232, 0), new Tile(3028, 3227, 0), new Tile(3027, 3222, 0)};
    private final Tile[] seamanToDeposit = ctx.movement.newTilePath(depositToSeaman).reverse().toArray();
    private final Tile[] shipToKaramjaFishSpot = {new Tile(2948, 3146, 0), new Tile(2943, 3146, 0), new Tile(2938, 3146, 0), new Tile(2933, 3148, 0), new Tile(2928, 3148, 0), new Tile(2924, 3151, 0), new Tile(2919, 3151, 0), new Tile(2916, 3155, 0), new Tile(2919, 3159, 0), new Tile(2920, 3164, 0), new Tile(2920, 3169, 0), new Tile(2924, 3172, 0)};
    private final Tile[] karamjaFishSpotToShip =ctx.movement.newTilePath(shipToKaramjaFishSpot).reverse().toArray();

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
                fishAndBank(userSettings[1],userSettings[0]);
            break;
        }
    }

    Tile[][] getCorrespondingPathToBank(String location){
        switch (location){
            case "Barbarian village":
                return new Tile[][]{ barbarianVillageFishToEdgeBank};
            case "Draynor village":
                return new Tile[][]{ draynorBank};
            case "Karamja":
                return new Tile[][]{seamanToDeposit,karamjaFishSpotToShip};
        }
        return null;
    }

    Tile[][] getCorrespondingPathToFishingSpot(String location){
        switch (location){
            case "Barbarian village":
                return new Tile[][]{BankToBarbarianvillageFish};
            case "Draynor village":
                return new Tile[][]{fishDraynor};
            case "Karamja":
                return new Tile[][]{depositToSeaman,shipToKaramjaFishSpot};
        }
        return null;
    }
    Player player = new Player(ctx,"its you but in code");
    private void fishAndBank(String tool,String destination){
        if(player.isInventoryFull()) {
            setPathToDepositItems(    destination,tool);
        }else {
            IGoal.addSubGoal(new GoalIdle(ctx,random.nextInt(3,8)));
            IGoal.addSubGoal(new GoalFish(ctx, getCorrespondingFishingSpot(tool)));
            setPathToFishingSpot(destination);
        }
       if(!player.hasItem(getCorrespondingTool(tool))) {
           IGoal.addSubGoal(new GoalGetEquipment(ctx,getCorrespondingTool(tool)));
           IGoal.addSubGoal(new GoalWalkToLocation(ctx, getCorrespondingPathToBank(destination)[0],5));
       }
    }

    public void setPathToFishingSpot(String destination){
        Tile[][] pathToFishingSpot = getCorrespondingPathToFishingSpot(destination);
        switch (destination){
            case "Barbarian village":
                IGoal.addSubGoal(new GoalWalkToLocation(ctx, pathToFishingSpot[0], 5));
            case "Draynor village":

            case "Karamja":
                IGoal.addSubGoal(new GoalWalkToLocation(ctx, shipToKaramjaFishSpot, 5));
                IGoal.addSubGoal(new GoalClickOnGangplank(ctx));
                PlayerSelector playerSelector = new PlayerSelector(ctx);
                if( playerSelector.local().tile().distanceTo(depositToSeaman[depositToSeaman.length-1])<20) {
                    IGoal.addSubGoal(new GoalIdle(ctx, 20));
                    IGoal.addSubGoal(new GoalContinueChat(ctx));
                    IGoal.addSubGoal(new GoalSelectOption(ctx, 1));
                    IGoal.addSubGoal(new GoalContinueChat(ctx));
                    IGoal.addSubGoal(new GoalPayFareShipper(ctx));
                     if(Random.nextBoolean()) {
                         IGoal.addSubGoal(new GoalMoveToNpc(ctx, "Seaman Lorris"));
                     }
                    IGoal.addSubGoal(new GoalWalkToLocation(ctx, depositToSeaman, 5));
                }
        }
    }

    public void setPathToDepositItems(String desiredLocation,String tool){
        Tile[][] pathToBank = getCorrespondingPathToBank(desiredLocation);
        switch (desiredLocation){
            case "Barbarian village":
                IGoal.addSubGoal(new GoalBankAllItemsExcept(ctx,getCorrespondingTool(tool),barbarianVillageFishToEdgeBank));
                IGoal.addSubGoal(new GoalWalkToLocation(ctx,barbarianVillageFishToEdgeBank,5));
            case "Draynor village":

            case "Karamja":
                IGoal.addSubGoal(new GoalDepositAllItemsExcept(ctx,getCorrespondingTool(tool),seamanToDeposit));
                IGoal.addSubGoal(new GoalWalkToLocation(ctx, seamanToDeposit, 5));
                IGoal.addSubGoal(new GoalClickOnGangplank(ctx));
                if(ctx.players.local().tile().distanceTo(shipToKaramjaFishSpot[6])<50) {
                    IGoal.addSubGoal(new GoalIdle(ctx, Random.nextInt(8,15)));
                    IGoal.addSubGoal(new GoalContinueChat(ctx));
                    IGoal.addSubGoal(new GoalSelectOption(ctx, 1));
                    IGoal.addSubGoal(new GoalContinueChat(ctx));
                    IGoal.addSubGoal(new GoalContinueChat(ctx));
                    IGoal.addSubGoal(new GoalSelectOption(ctx, 2));
                    IGoal.addSubGoal(new GoalContinueChat(ctx));
                    IGoal.addSubGoal(new GoalContinueChat(ctx));
                    IGoal.addSubGoal(new GoalSelectOption(ctx, 1));
                    IGoal.addSubGoal(new GoalContinueChat(ctx));
                    IGoal.addSubGoal(new GoalPayFareCustomOfficer(ctx));
                    IGoal.addSubGoal(new GoalMoveToNpc(ctx, "Customs officer"));
                    IGoal.addSubGoal(new GoalWalkToLocation(ctx,karamjaFishSpotToShip, 5));
                }
        }
    }
    
    String[] getCorrespondingTool(String tool){
        switch (tool){
            case "Fly fishing rod":
                return new String[]{"Fly fishing rod","Feather"};
            case "Harpoon":
                return new String[]{"Harpoon","Coins"};
            case "Lobster pot":
                return new String[]{"Lobster pot","Coins"};
            default:
                return new String[]{tool};
        }
    }

    String[] getCorrespondingFishingSpot(String tool){
        switch (tool){
            case "Small fishing net":
                return new String[] {"Small Net","Fishing spot"};
            case "Fly fishing rod":
                return new String[]{"Lure", "Rod Fishing spot"};
            case "Harpoon":
                return new String[]{"Harpoon","Fishing spot"};
            case "Lobster pot":
                return new String[]{"Cage","Fishing spot"};
                default:
                    return new String[]{"", "Fishing spot"};
        }
    }
}
