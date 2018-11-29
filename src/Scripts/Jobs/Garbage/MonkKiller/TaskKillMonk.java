package Scripts.Jobs.Garbage.MonkKiller;

import Scripts.Tasks.Task;
import Scripts.Tasks.TrainMelee;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TaskKillMonk extends Task{
        TrainMelee trainMelee = new TrainMelee(ctx);


    public TaskKillMonk(ClientContext ctx, Tile[] path) {
        super(ctx);
    }
    public TaskKillMonk(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
      return true;
    }

    @Override
    public void execute() {
       trainMelee.killSingleTarget();
    }
}
