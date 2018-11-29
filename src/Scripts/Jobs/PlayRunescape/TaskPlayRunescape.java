package Scripts.Jobs.PlayRunescape;

import Scripts.Tasks.PlayRunescape;
import Scripts.Tasks.Task;
import Scripts.Tools.Algoritm.F2PMap;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TaskPlayRunescape extends Task{
  PlayRunescape playRunescape = new PlayRunescape(ctx);
  public TaskPlayRunescape(ClientContext ctx, Tile taskTile, int tileRange) {
        super(ctx,taskTile,tileRange);
    }

    public TaskPlayRunescape(ClientContext ctx, Tile[] path) {
        super(ctx);
    }
    public TaskPlayRunescape(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
      return true;
    }

    @Override
    public void execute(){
        playRunescape.play();
    }
}
