package scripts.planner;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;
import scripts.goal.compositegoal.GoalXp;

public class PlannerMining extends Planner {

    public PlannerMining(ClientContext arg0) {
        super(arg0);
        iGoal = new GoalXp(ctx, 1000, Constants.SKILLS_FISHING);
    }

    public void chooseMethod() {
        mine1drop1();

    }

    public void trainMining() {

    }

    void mine1drop1() {
        Tile[] pathToMine = {new Tile(3269, 3167, 0), new Tile(3274, 3167, 0), new Tile(3275, 3172, 0), new Tile(3276, 3177, 0), new Tile(3279, 3182, 0), new Tile(3280, 3187, 0), new Tile(3280, 3192, 0), new Tile(3280, 3197, 0), new Tile(3280, 3202, 0), new Tile(3281, 3207, 0), new Tile(3279, 3212, 0), new Tile(3279, 3217, 0), new Tile(3279, 3222, 0), new Tile(3279, 3227, 0), new Tile(3281, 3232, 0), new Tile(3282, 3237, 0), new Tile(3283, 3242, 0), new Tile(3287, 3246, 0), new Tile(3287, 3251, 0), new Tile(3290, 3255, 0), new Tile(3293, 3259, 0), new Tile(3295, 3264, 0), new Tile(3295, 3269, 0), new Tile(3297, 3274, 0), new Tile(3297, 3279, 0), new Tile(3300, 3283, 0), new Tile(3300, 3288, 0), new Tile(3298, 3293, 0), new Tile(3296, 3298, 0), new Tile(3298, 3303, 0), new Tile(3300, 3308, 0), new Tile(3300, 3313, 0)};
//        for(int i = 0;i<10;i++) {
//            iGoal.addSubGoal(new GoalMine1(ctx, "Iron ore"));
//            iGoal.addSubGoal(new GoalDropItem(ctx, "Iron ore"));
//        }
//        iGoal.addSubGoal(new GoalSwitchTab(ctx, Game.Tab.INVENTORY));
//        iGoal.addSubGoal(new GoalWalkToLocation(ctx,pathToMine));
    }

    public void mineOresAndBank() {
        Tile[] pathToMine = {new Tile(3269, 3167, 0), new Tile(3274, 3167, 0), new Tile(3275, 3172, 0), new Tile(3276, 3177, 0), new Tile(3279, 3182, 0), new Tile(3280, 3187, 0), new Tile(3280, 3192, 0), new Tile(3280, 3197, 0), new Tile(3280, 3202, 0), new Tile(3281, 3207, 0), new Tile(3279, 3212, 0), new Tile(3279, 3217, 0), new Tile(3279, 3222, 0), new Tile(3279, 3227, 0), new Tile(3281, 3232, 0), new Tile(3282, 3237, 0), new Tile(3283, 3242, 0), new Tile(3287, 3246, 0), new Tile(3287, 3251, 0), new Tile(3290, 3255, 0), new Tile(3293, 3259, 0), new Tile(3295, 3264, 0), new Tile(3295, 3269, 0), new Tile(3297, 3274, 0), new Tile(3297, 3279, 0), new Tile(3300, 3283, 0), new Tile(3300, 3288, 0), new Tile(3298, 3293, 0), new Tile(3296, 3298, 0), new Tile(3298, 3303, 0), new Tile(3300, 3308, 0), new Tile(3300, 3313, 0)};
        Tile[] pathToBank = ctx.movement.newTilePath(pathToMine).reverse().toArray();
    }
}

