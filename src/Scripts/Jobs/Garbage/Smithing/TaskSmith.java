package Scripts.Jobs.Garbage.Smithing;

import Scripts.Tasks.Task;
import Scripts.Tasks.TrainSmithing;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TaskSmith extends Task{
    TrainSmithing trainSmithing = new TrainSmithing(ctx);
    public TaskSmith(ClientContext ctx, Tile taskTile, int tileRange) {
        super(ctx,taskTile,tileRange);
    }

    public TaskSmith(ClientContext ctx, Tile[] path) {
        super(ctx);
    }
    public TaskSmith(ClientContext ctx) {
        super(ctx);

    }

    @Override
    public boolean activate() {
      return true;
    }

    @Override
    public void execute() {
      trainSmithing.Smelt();
    }
}
