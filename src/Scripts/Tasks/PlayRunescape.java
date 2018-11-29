package Scripts.Tasks;

import Scripts.Core.ENUM.state;
import Scripts.Core.GoalMethods.Goal;
import Scripts.Core.GoalMethods.GoalNoChildren;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

public class PlayRunescape extends ClientAccessor {
    TrainMining trainMining = new TrainMining(ctx);
    TrainFishing trainFishing = new TrainFishing(ctx);
    GoalNoChildren playRunescape = new GoalNoChildren(ctx);
    public PlayRunescape(ClientContext arg0) {
        super(arg0);
        playRunescape.addSubGoal(chooseRandomGoal(random.nextInt(1000,30000)));
        playRunescape.status=state.FAILED;
    }
    public void play(){
        if(playRunescape.getStatus()!= state.COMPLETED&&playRunescape.status!=state.FAILED){
            playRunescape.process();
        }else{
            playRunescape.emptyStack();
            playRunescape.addSubGoal(chooseRandomGoal(random.nextInt(10000,30000)));
            playRunescape.status=state.INACTIVE;
        }

    }

    Random random = new Random();
    Goal chooseRandomGoal(long exp){
        int methodNumber =1;//random.nextInt(0,1);
        switch (methodNumber){
            case 0:
                return trainMining.getMiningMethod(exp);
            case 1:
                return trainFishing.getFishingMethod(exp);
        }
        return null;
    }
}
