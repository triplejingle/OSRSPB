package Scripts.Tasks;
import Scripts.Core.ENUM.state;
import Scripts.Core.GoalMethods.*;
import Scripts.Core.Player;
import Scripts.Core.WalkerMethods;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

public class TrainMining  extends ClientAccessor {
    GoalMining trainMining = new GoalMining(ctx);
    WalkerMethods walkerMethods = new WalkerMethods(ctx);
    public TrainMining(ClientContext arg0) {
        super(arg0);
        mine1drop1();
    }
    Goal getMiningMethod(Long n){
        chooseRandomMiningMethod(n);
        return trainMining;
    }
    public void chooseRandomMiningMethod(long n) {
        Random random = new Random();
        int method = random.nextInt(0,1);
        trainMining.setExpGoal(n);
        switch(method){
            case 0:
                System.out.println("full inventory mining method");
                mineOresAndBank();
                break;
            case 1:
                System.out.println("m1d1");
                mine1drop1();
                break;
        }
    }

    public void trainMining() {
        if (trainMining.getStatus() != state.COMPLETED && trainMining.status != state.FAILED) {
            if(trainMining.children.size()==0){
                mine1drop1();
            }
            trainMining.process();
        } else {
            System.out.println("we did it");
            int experience = 20000;
            chooseRandomMiningMethod(experience);
        }
    }

    Player player = new Player(ctx, "its you but in code");
    void mine1drop1() {
            Tile[] pathToMine = {new Tile(3269, 3167, 0), new Tile(3274, 3167, 0), new Tile(3275, 3172, 0), new Tile(3276, 3177, 0), new Tile(3279, 3182, 0), new Tile(3280, 3187, 0), new Tile(3280, 3192, 0), new Tile(3280, 3197, 0), new Tile(3280, 3202, 0), new Tile(3281, 3207, 0), new Tile(3279, 3212, 0), new Tile(3279, 3217, 0), new Tile(3279, 3222, 0), new Tile(3279, 3227, 0), new Tile(3281, 3232, 0), new Tile(3282, 3237, 0), new Tile(3283, 3242, 0), new Tile(3287, 3246, 0), new Tile(3287, 3251, 0), new Tile(3290, 3255, 0), new Tile(3293, 3259, 0), new Tile(3295, 3264, 0), new Tile(3295, 3269, 0), new Tile(3297, 3274, 0), new Tile(3297, 3279, 0), new Tile(3300, 3283, 0), new Tile(3300, 3288, 0), new Tile(3298, 3293, 0), new Tile(3296, 3298, 0), new Tile(3298, 3303, 0), new Tile(3300, 3308, 0), new Tile(3300, 3313, 0)};
            trainMining.addSubGoal(new GoalMine1(ctx));
            if(!player.isInventoryEmpty()) {
                trainMining.addSubGoal(new GoalDropOre(ctx));
            }
            GoalWalkToLocation walkToMine = new GoalWalkToLocation(ctx);
            walkToMine.setPath(pathToMine);
            trainMining.addSubGoal(walkToMine);
            trainMining.status = state.INACTIVE;
    }

    public void mineOresAndBank() {
        Tile[] pathToMine = {new Tile(3269, 3167, 0), new Tile(3274, 3167, 0), new Tile(3275, 3172, 0), new Tile(3276, 3177, 0), new Tile(3279, 3182, 0), new Tile(3280, 3187, 0), new Tile(3280, 3192, 0), new Tile(3280, 3197, 0), new Tile(3280, 3202, 0), new Tile(3281, 3207, 0), new Tile(3279, 3212, 0), new Tile(3279, 3217, 0), new Tile(3279, 3222, 0), new Tile(3279, 3227, 0), new Tile(3281, 3232, 0), new Tile(3282, 3237, 0), new Tile(3283, 3242, 0), new Tile(3287, 3246, 0), new Tile(3287, 3251, 0), new Tile(3290, 3255, 0), new Tile(3293, 3259, 0), new Tile(3295, 3264, 0), new Tile(3295, 3269, 0), new Tile(3297, 3274, 0), new Tile(3297, 3279, 0), new Tile(3300, 3283, 0), new Tile(3300, 3288, 0), new Tile(3298, 3293, 0), new Tile(3296, 3298, 0), new Tile(3298, 3303, 0), new Tile(3300, 3308, 0), new Tile(3300, 3313, 0)};
        Tile[] pathToBank = ctx.movement.newTilePath(pathToMine).reverse().toArray();
        trainMining.addSubGoal(new GoalBankAllItems(ctx));
        GoalWalkToLocation walkToBank = new GoalWalkToLocation(ctx);
        walkToBank.setPath(pathToBank);
        trainMining.addSubGoal(walkToBank);
        if (player.countItemsInventory() > 10) {
            trainMining.addSubGoal(new GoalMineUntilInventoryFull(ctx));
            GoalWalkToLocation walkToMine = new GoalWalkToLocation(ctx);
            walkToMine.setPath(pathToMine);
            trainMining.addSubGoal(walkToMine);
        }
        trainMining.status = state.INACTIVE;//als dit niet werkt dan new composite
    }
}

