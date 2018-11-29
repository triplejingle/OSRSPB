package Scripts.Jobs.Garbage.Cooking;

import Scripts.Tasks.Task;
import Scripts.Tasks.TrainCooking;
import org.powerbot.script.rt4.ClientContext;

public class TaskCooking extends Task{
    TrainCooking trainCooking = new TrainCooking(ctx);
    public TaskCooking(ClientContext ctx) {
        super(ctx);

    }

    @Override
    public boolean activate() {
      return true;
    }

    @Override
    public void execute() {
     trainCooking.cookingStove();
    }
}
