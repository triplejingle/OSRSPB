package Scripts.Jobs;

import Scripts.Core.WalkerMethods;
import Scripts.Tasks.Task;
import Scripts.Tools.Algoritm.F2PMap;
import org.powerbot.script.Tile;

import org.powerbot.script.rt4.ClientContext;

public class TaskTemplate extends Task{
  F2PMap f2PMap = new F2PMap(ctx);
  public TaskTemplate(ClientContext ctx, Tile taskTile, int tileRange) {
        super(ctx,taskTile,tileRange);
    }

    public TaskTemplate(ClientContext ctx, Tile[] path) {
        super(ctx);
    }
    public TaskTemplate(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
      return true;
    }

    @Override
    public void execute(){
    f2PMap.map();
    f2PMap.getPath("tmp");
    }
}
