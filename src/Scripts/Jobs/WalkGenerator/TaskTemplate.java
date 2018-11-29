package Scripts.Jobs.WalkGenerator;

import Scripts.Tasks.Task;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TaskTemplate extends Task{

  public TaskTemplate(ClientContext ctx, Tile taskTile, int tileRange) {
        super(ctx,taskTile,tileRange);
    }
    ArrayList<Tile> list = new ArrayList<>();
    public TaskTemplate(ClientContext ctx, Tile[] path) {
        super(ctx);
    }
    public TaskTemplate(ClientContext ctx, ArrayList<Tile> list) {
        super(ctx);
        this.list = list;
    }
    public void mouseMoved(MouseEvent e) {

    }
    @Override
    public boolean activate() {
      return true;
    }

    @Override
    public void execute(){
        Tile tile = ctx.players.local().tile();
      if(!list.contains(tile)){
          list.add(tile);
          System.out.println("Tile added");
      }
    }
}
