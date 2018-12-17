package Scripts.Planners;

import Scripts.Core.GoalMethods.atomicgoal.*;
import Scripts.Core.GoalMethods.compositegoal.GoalTime;
import Scripts.Core.GoalMethods.compositegoal.GoalWalkToLocation;
import Scripts.Core.Player;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class PlannerFishing extends Planner {
    public Tile[] BankToBarbarianvillageFish = {new Tile(3094, 3491, 0), new Tile(3090, 3488, 0), new Tile(3095, 3486, 0), new Tile(3098, 3482, 0), new Tile(3098, 3477, 0), new Tile(3098, 3472, 0), new Tile(3098, 3467, 0), new Tile(3093, 3465, 0), new Tile(3088, 3464, 0), new Tile(3088, 3459, 0), new Tile(3089, 3454, 0), new Tile(3090, 3449, 0), new Tile(3093, 3444, 0), new Tile(3097, 3441, 0), new Tile(3101, 3438, 0), new Tile(3103, 3433, 0)};
    public final Tile[] barbarianVillageFishToEdgeBank = ctx.movement.newTilePath(BankToBarbarianvillageFish).reverse().toArray();
    Tile[] fishDraynor = {new Tile(3090, 3232,0)};
    Tile[] draynorBank = {new Tile(3094, 3243,0)};

    public PlannerFishing(ClientContext arg0){
        super(arg0);
        int minutes = 120;
        IGoal = new GoalTime(ctx,minutes);
    }

    public void chooseMethod() {
        Random random = new Random();
        method =0; //random.nextInt(0,1);
        switch(method){
            case 0:
                System.out.println("fish and bank");
                fishAndBank();
            break;
        }
    }
    Player player = new Player(ctx,"its you but in code");
    private void fishAndBank(){
        int fishingNet = 303;
        if(player.isInventoryFull()) {
            IGoal.addSubGoal(new GoalCloseBank(ctx));
            IGoal.addSubGoal(new GoalBankAllItems(ctx));
            IGoal.addSubGoal(new GoalOpenBank(ctx));
        }else {
            IGoal.addSubGoal(new GoalWalkToLocation(ctx, draynorBank));
            IGoal.addSubGoal(new GoalFish(ctx, "Small fishing net", "Small Net"));
            IGoal.addSubGoal(new GoalWalkToLocation(ctx, fishDraynor));
        }
       if(!player.hasItem(fishingNet)) {
           IGoal.addSubGoal(new GoalCloseBank(ctx));
           IGoal.addSubGoal(new GoalWithdrawEquipment(ctx, fishingNet));
           IGoal.addSubGoal(new GoalOpenBank(ctx));
           IGoal.addSubGoal(new GoalWalkToLocation(ctx,draynorBank));
       }
    }

}
