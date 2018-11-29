package Scripts.Jobs.Garbage.Firemaking;

import Scripts.Tasks.Task;
import Scripts.Tasks.TrainFiremaking;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TaskFiremaking extends Task{
    TrainFiremaking trainFiremaking = new TrainFiremaking(ctx);
    public TaskFiremaking(ClientContext ctx, Tile taskTile, int tileRange) {
        super(ctx,taskTile,tileRange);
    }

    public TaskFiremaking(ClientContext ctx, Tile[] path) {
        super(ctx);
    }
    public TaskFiremaking(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
      return true;
    }

    @Override
    public void execute() {
      trainFiremaking.makeFireUsingLogs();
    }
}
