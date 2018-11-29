package Scripts.Jobs.Garbage.Woodcutting;

import Scripts.Tasks.Task;
import Scripts.Tasks.TrainWoodcutting;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TaskWoodcutting extends Task{
  TrainWoodcutting trainWoodcutting = new TrainWoodcutting(ctx);
  public TaskWoodcutting(ClientContext ctx, Tile taskTile, int tileRange) {
        super(ctx,taskTile,tileRange);
    }

    public TaskWoodcutting(ClientContext ctx, Tile[] path) {
        super(ctx);
    }
    public TaskWoodcutting(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
      return true;
    }

    @Override
    public void execute(){
    trainWoodcutting.customPowerChopWoodcutting();
    }
}
