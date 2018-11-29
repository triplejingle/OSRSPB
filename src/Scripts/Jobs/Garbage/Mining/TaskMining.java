package Scripts.Jobs.Garbage.Mining;

import Scripts.Tasks.Task;
import Scripts.Tasks.TrainMining;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import java.util.List;

public class TaskMining extends Task{

   TrainMining trainMining = new TrainMining(ctx);
    public TaskMining(ClientContext ctx, Tile taskTile, int tileRange) {
        super(ctx,taskTile,tileRange);
    }

    public TaskMining(ClientContext ctx, Tile[] path) {
        super(ctx);
    }
    public TaskMining(ClientContext ctx) {
        super(ctx);
    }
    List<Tile> customRocks;
    public TaskMining(ClientContext ctx, List<Tile> rocks) {
        super(ctx);
        this.customRocks = rocks;
    }

    @Override
    public boolean activate() {
      return true;
    }

    @Override
    public void execute() {
        trainMining.trainMining();
    }
}
