package Scripts.Tasks;

import Scripts.Core.ENUM.state;
import Scripts.Core.GoalMethods.*;
import Scripts.Core.Player;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

public class TrainFishing extends ClientAccessor {
    public Tile[] BankToBarbarianvillageFish = {new Tile(3094, 3491, 0), new Tile(3090, 3488, 0), new Tile(3095, 3486, 0), new Tile(3098, 3482, 0), new Tile(3098, 3477, 0), new Tile(3098, 3472, 0), new Tile(3098, 3467, 0), new Tile(3093, 3465, 0), new Tile(3088, 3464, 0), new Tile(3088, 3459, 0), new Tile(3089, 3454, 0), new Tile(3090, 3449, 0), new Tile(3093, 3444, 0), new Tile(3097, 3441, 0), new Tile(3101, 3438, 0), new Tile(3103, 3433, 0)};
    public final Tile[] barbarianVillageFishToEdgeBank = ctx.movement.newTilePath(BankToBarbarianvillageFish).reverse().toArray();
    GoalXp trainFishing = new GoalXp(ctx);
    public TrainFishing(ClientContext arg0) {
        super(arg0);
    }

    public Goal getFishingMethod(long exp) {
        chooseRandomMiningMethod(exp);
        trainFishing.status=state.INACTIVE;
        return trainFishing;
    }

    public void chooseRandomMiningMethod(long n) {
        Random random = new Random();
        int method =0; //random.nextInt(0,1);
        trainFishing.setExpGoal(10000);
        switch(method){
            case 0:
                System.out.println("fish and bank");
                fishAndBank();
            break;
        }
    }
    Player player = new Player(ctx,"its you but in code");
    Tile[] bankToFishDraynor = {new Tile(3092, 3245, 0), new Tile(3087, 3247, 0), new Tile(3087, 3242, 0), new Tile(3087, 3237, 0), new Tile(3086, 3232, 0)};
    Tile[] draynorFishToBank = ctx.movement.newTilePath(bankToFishDraynor).reverse().toArray();
    private void fishAndBank(){
        ctx.properties.setProperty("randomevents.disable","True");

        trainFishing.addSubGoal(new GoalFishUntilInventoryFull(ctx));
        GoalWalkToLocation walkToFishSpot = new GoalWalkToLocation(ctx);
        walkToFishSpot.setPath(bankToFishDraynor);
        trainFishing.addSubGoal(walkToFishSpot);
        int fishingNet = 303;

        GoalGetEquipment goalGetEquipment = new GoalGetEquipment(ctx);
        goalGetEquipment.addEquipment(fishingNet);
        trainFishing.addSubGoal(goalGetEquipment);

        if(ctx.inventory.select().count()>1) {
            GoalBankAllItems bankAllItems = new GoalBankAllItems(ctx);
            trainFishing.addSubGoal(bankAllItems);
            System.out.println("walking to bank added");
            GoalWalkToLocation walkToBank = new GoalWalkToLocation(ctx);
            walkToBank.setPath(draynorFishToBank);
            trainFishing.addSubGoal(walkToBank);
        }
        trainFishing.status = state.INACTIVE;
    }
}
