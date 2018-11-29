package Scripts.Tasks;

import Scripts.Core.WalkerTMPMethods;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
public abstract class Task extends ClientAccessor {
    WalkerTMPMethods walkerMethods = new WalkerTMPMethods(ctx);

    public Task(ClientContext ctx) {
        super(ctx);
    }

    public void setAreaInNoTiles(int areaInNoTiles) {
        this.areaInNoTiles = areaInNoTiles;
    }

    private Tile taskTile;

    public int getAreaInNoTiles() {
        return areaInNoTiles;
    }

    private int areaInNoTiles;
    public WalkerTMPMethods getWalkerMethods() {
        return walkerMethods;
    }

    public Tile getTaskTile() {
        return taskTile;
    }


    public Task(ClientContext ctx, Tile taskTile, int tileRange) {
        super(ctx);
        this.setTaskTile(taskTile);
        this.setAreaInNoTiles(tileRange);
    }

    public abstract boolean activate();
    public abstract void execute();

    public void setTaskTile(Tile taskTile) {
        this.taskTile = taskTile;
    }
    public boolean isWithinTaskRange(Tile tile){
        return getTaskTile().distanceTo(tile)<areaInNoTiles;
    }
}