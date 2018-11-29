package Scripts.Tasks;

import Scripts.Core.garbage.LocationMethods;
import Scripts.Core.garbage.PlayerMethods;
import Scripts.Tasks.Task;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public abstract class WalkTask extends Task {
    private Tile[] taskPath;
    private LocationMethods locationMethods = new LocationMethods(ctx);
    private PlayerMethods playerMethods = new PlayerMethods(ctx);

    public WalkTask(ClientContext ctx, Tile[] taskPath) {
        super(ctx);
        this.taskPath=taskPath;
        super.setTaskTile(taskPath[taskPath.length-1]);
    }

    @Override
    public void execute(){
        int withinTileRange = 5;
        if(!playerMethods.isPlayerMoving()||locationMethods.getDistanceToDestination()<withinTileRange) {
            locationMethods.walkToLocation(taskPath);
        }
    }

}
