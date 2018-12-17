package Scripts.Planners;
import Scripts.Core.GoalMethods.atomicgoal.GoalDropItem;
import Scripts.Core.GoalMethods.atomicgoal.GoalMine1;
import Scripts.Core.GoalMethods.atomicgoal.GoalSwitchTab;
import Scripts.Core.GoalMethods.compositegoal.GoalWalkToLocation;
import Scripts.Core.GoalMethods.compositegoal.GoalXp;
import Scripts.Core.Player;
import Scripts.Core.WalkerMethods;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;
import org.powerbot.script.rt4.Game;

public class PlannerMining extends Planner {
    WalkerMethods walkerMethods = new WalkerMethods(ctx);
    Player player = new Player(ctx, "its you but in code");

    public PlannerMining(ClientContext arg0) {
        super(arg0);
        IGoal = new GoalXp(ctx, 1000, Constants.SKILLS_FISHING);
    }

    public void chooseMethod() {
        mine1drop1();
//        switch(method){
//            case 0:
//                System.out.println("m1d1");
//                mine1drop1();
//                break;
//            case 1:
//                System.out.println("full inventory mining method");
//                mineOresAndBank();
//                break;
//        }
    }

    public void trainMining() {

    }

    void mine1drop1() {
        this.goalLocation = "Alkahid";
        Tile[] pathToMine = {new Tile(3269, 3167, 0), new Tile(3274, 3167, 0), new Tile(3275, 3172, 0), new Tile(3276, 3177, 0), new Tile(3279, 3182, 0), new Tile(3280, 3187, 0), new Tile(3280, 3192, 0), new Tile(3280, 3197, 0), new Tile(3280, 3202, 0), new Tile(3281, 3207, 0), new Tile(3279, 3212, 0), new Tile(3279, 3217, 0), new Tile(3279, 3222, 0), new Tile(3279, 3227, 0), new Tile(3281, 3232, 0), new Tile(3282, 3237, 0), new Tile(3283, 3242, 0), new Tile(3287, 3246, 0), new Tile(3287, 3251, 0), new Tile(3290, 3255, 0), new Tile(3293, 3259, 0), new Tile(3295, 3264, 0), new Tile(3295, 3269, 0), new Tile(3297, 3274, 0), new Tile(3297, 3279, 0), new Tile(3300, 3283, 0), new Tile(3300, 3288, 0), new Tile(3298, 3293, 0), new Tile(3296, 3298, 0), new Tile(3298, 3303, 0), new Tile(3300, 3308, 0), new Tile(3300, 3313, 0)};
        for(int i = 0;i<10;i++) {
            IGoal.addSubGoal(new GoalMine1(ctx, "Iron ore"));
            IGoal.addSubGoal(new GoalDropItem(ctx, "Iron ore"));
        }
        IGoal.addSubGoal(new GoalSwitchTab(ctx, Game.Tab.INVENTORY));
        IGoal.addSubGoal(new GoalWalkToLocation(ctx,pathToMine));
    }

    public void mineOresAndBank() {
        this.goalLocation = "Alkahid";
        Tile[] pathToMine = {new Tile(3269, 3167, 0), new Tile(3274, 3167, 0), new Tile(3275, 3172, 0), new Tile(3276, 3177, 0), new Tile(3279, 3182, 0), new Tile(3280, 3187, 0), new Tile(3280, 3192, 0), new Tile(3280, 3197, 0), new Tile(3280, 3202, 0), new Tile(3281, 3207, 0), new Tile(3279, 3212, 0), new Tile(3279, 3217, 0), new Tile(3279, 3222, 0), new Tile(3279, 3227, 0), new Tile(3281, 3232, 0), new Tile(3282, 3237, 0), new Tile(3283, 3242, 0), new Tile(3287, 3246, 0), new Tile(3287, 3251, 0), new Tile(3290, 3255, 0), new Tile(3293, 3259, 0), new Tile(3295, 3264, 0), new Tile(3295, 3269, 0), new Tile(3297, 3274, 0), new Tile(3297, 3279, 0), new Tile(3300, 3283, 0), new Tile(3300, 3288, 0), new Tile(3298, 3293, 0), new Tile(3296, 3298, 0), new Tile(3298, 3303, 0), new Tile(3300, 3308, 0), new Tile(3300, 3313, 0)};
        Tile[] pathToBank = ctx.movement.newTilePath(pathToMine).reverse().toArray();
    }
}

