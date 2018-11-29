package Scripts.Jobs.Garbage.Crafting;

import Scripts.Tasks.Task;
import Scripts.Tasks.TrainCrafting;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TaskCrafting extends Task{
  public TaskCrafting(ClientContext ctx, Tile taskTile, int tileRange) {
        super(ctx,taskTile,tileRange);
    }
  TrainCrafting trainCrafting = new TrainCrafting(ctx);
  //loop naar de locatie waar de task kan worden uitgevoerd
    public TaskCrafting(ClientContext ctx, Tile[] path) {
        super(ctx);
    }
    public TaskCrafting(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
      return true;
    }

    @Override
    public void execute(){
      trainCrafting.craftUsingFurnace();
    }
}
