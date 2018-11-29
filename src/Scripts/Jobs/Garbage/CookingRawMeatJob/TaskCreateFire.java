package Scripts.Jobs.Garbage.CookingRawMeatJob;

import Scripts.Core.Collection.Animations;
import Scripts.Core.garbage.InventoryMethods;
import Scripts.Core.garbage.LocationMethods;
import Scripts.Core.garbage.ObjectMethods;
import Scripts.Core.garbage.PlayerMethods;
import Scripts.Tasks.Task;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TaskCreateFire extends Task {
    InventoryMethods inventoryMethods = new InventoryMethods(ctx);
    LocationMethods locationMethods = new LocationMethods(ctx);
    ObjectMethods objectMethods = new ObjectMethods(ctx);
    PlayerMethods playerMethods = new PlayerMethods(ctx);
    Animations aIdCollection = new Animations();
    public TaskCreateFire(ClientContext ctx, Tile taskTile, int tileRange) {
        super(ctx, taskTile, tileRange);
        super.setTaskTile(taskTile);
    }

    @Override
    public boolean activate() {
//        if(!(inventoryMethods.inventoryContainsItem(itemIdCollection.getLogs())||
//                !inventoryMethods.inventoryContainsItem(itemIdCollection.getTinderbox()))){
//            return false;
//        }
//        boolean tmp1 = inventoryMethods.inventoryContainsItem(itemIdCollection.getRaw_beef());
//        boolean tmp2 = !objectMethods.selectNearestObjects().inViewport();
//        boolean tmp4 = !playerMethods.isPlayer(aIdCollection.getYouAttemptToLightTheLogs());
//        boolean tmp5 = playerMethods.isPlayer(aIdCollection.getNothing());
//        return tmp1&&tmp2&&tmp4&&tmp5;
        return false;
    }

    @Override
    public void execute() {
//            while (!objectMethods.selectNearestObjects(objectIdCollection.getFire()).inViewport()&&
//                    inventoryMethods.inventoryContainsItem(itemIdCollection.getLogs())) {
//                inventoryMethods.selectItem(itemIdCollection.getTinderbox());
//                inventoryMethods.selectItem(itemIdCollection.getLogs());
//                Condition.sleep(500);
//            }
    }
}
