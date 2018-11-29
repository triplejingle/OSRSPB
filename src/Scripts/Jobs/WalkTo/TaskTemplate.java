package Scripts.Jobs.WalkTo;

import Scripts.Core.WalkerMethods;
import Scripts.Tasks.Task;
import Scripts.Tools.Algoritm.F2PMap;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TaskTemplate extends Task{
  F2PMap f2PMap = new F2PMap(ctx);
  WalkerMethods walkerMethods = new WalkerMethods(ctx);
  public TaskTemplate(ClientContext ctx, Tile taskTile, int tileRange) {
        super(ctx,taskTile,tileRange);
    }
    //path van Draynorvillage1 naar barbarian village fixen
    public TaskTemplate(ClientContext ctx, Tile[] path) {
        super(ctx);
    }
    public TaskTemplate(ClientContext ctx) {
        super(ctx);
      f2PMap.map();
      Tile[] path =  walkerMethods.splicePath(f2PMap.getPath("Varrock1"),4);
      walkerMethods.addPath(path);
      walkerMethods.setPath();
    }

    @Override
    public boolean activate() {
      return true;
    }

    @Override
    public void execute(){
    walkerMethods.walkPath();
    }
}
